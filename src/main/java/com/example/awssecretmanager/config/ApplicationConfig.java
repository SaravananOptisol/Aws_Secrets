package com.example.awssecretmanager.config;

import com.google.gson.Gson;

import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueResponse;
import software.amazon.awssdk.services.secretsmanager.model.SecretsManagerException;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class ApplicationConfig {
	private Gson gson = new Gson();

	@Bean
	DataSource dataSource() {
		AwsSecrets secrets = getSecret();

		return DataSourceBuilder.create()
				//.driverClassName("com.mysql.cj.jdbc.driver")
				.url("jdbc:" + secrets.getEngine() + "://" + secrets.getHost() + ":" + secrets.getPort() + "/javadb")
				.username(secrets.getUsername()).password(secrets.getPassword()).build();
	}

	private AwsSecrets getSecret() {

		String secretName = "arn:aws:secretsmanager:ap-northeast-1:871337643207:secret:rof/poc/DemoApplication-FwL6LU";
		Region region = Region.of("ap-northeast-1");
		SecretsManagerClient secretsClient = SecretsManagerClient.builder().region(region)
				.credentialsProvider(ProfileCredentialsProvider.create()).build();

		try {
			GetSecretValueRequest valueRequest = GetSecretValueRequest.builder().secretId(secretName).build();

			GetSecretValueResponse valueResponse = secretsClient.getSecretValue(valueRequest);
			String secret = valueResponse.secretString();
			System.out.println("Secret value is" + secret);
			return gson.fromJson(secret, AwsSecrets.class);

		} catch (SecretsManagerException e) {
			secretsClient.close();
			System.err.println(e.awsErrorDetails().errorMessage());
			System.exit(1);
		}
		return null;
	}

}