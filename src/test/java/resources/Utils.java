package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {

	static RequestSpecification req;

	/**
	 * 
	 * @return
	 */
	public RequestSpecification requestSpecification() {
		PrintStream log = null;
		if (req == null) {
			try {
				log = new PrintStream(new FileOutputStream("logging.txt"));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			req = new RequestSpecBuilder().setBaseUri(this.getGlobalProperties("baseUrl"))
					.addQueryParam("key", "qaclick123").addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log)).setContentType(ContentType.JSON).build();
			return req;
		}
		return req;
	}

	/**
	 * 
	 * @param key
	 * @return
	 */
	public String getGlobalProperties(String key) {
		Properties prop = new Properties();
		FileInputStream file = null;
		try {
			file = new FileInputStream(
					"C:\\selenium\\Pom\\PageObjectModel-master\\APIFramework\\src\\test\\java\\resources\\global.properties");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			prop.load(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop.getProperty(key);
	}

	/**
	 * 
	 * @param response
	 * @param key
	 * @return
	 */
	public String getJsonPath(Response response, String key) {
		String resp = response.asString();
		JsonPath path = new JsonPath(resp);
		return path.get(key);
	}

}
