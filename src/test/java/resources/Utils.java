package resources;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class Utils {

	RequestSpecification req;

	public RequestSpecification requestSpecification() {
		PrintStream log = null;
		try {
			log = new PrintStream(new FileOutputStream("logging.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123").addFilter(RequestLoggingFilter.logRequestTo(log)).addFilter(ResponseLoggingFilter.logResponseTo(log))
				.setContentType(ContentType.JSON).build();
		return req;
	}

}
