package com.example.test2.spring_test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.toilelibre.libe.curl.Curl.curl;

import org.apache.http.impl.client.BasicResponseHandler;

@SpringBootApplication
@RestController
public class SpringTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringTestApplication.class, args);
	}

	@GetMapping
	public String test() {

		try {
			String command = "curl -X GET https://postman-echo.com/get?foo1=bar1&foo2=bar2";
			var result = curl(command);
			System.out.print(new BasicResponseHandler().handleResponse(result));

		} catch (Exception e) {
			System.out.print("error");
			e.printStackTrace();
		}
		return "Hello";
	}
}
