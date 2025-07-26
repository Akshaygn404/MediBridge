package com.example.medibridge;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.security.Key;
import java.util.Base64;

@SpringBootApplication
public class MedibridgeApplication {

	public static void main(String[] args) {
//		Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
//		String encodedKey = Base64.getEncoder().encodeToString(key.getEncoded());
//		System.out.println(encodedKey);

		SpringApplication.run(MedibridgeApplication.class, args);
	}

}
