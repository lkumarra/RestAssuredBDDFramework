package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.GoogleMapJson;
import pojo.Location;

public class TestDataBuild {
	
	public GoogleMapJson addPlacePayLoad(String name, String language, String addres) {
		GoogleMapJson json = new GoogleMapJson();
		json.setAccuracy(10);
		json.setPhone_number("(+91) 983 893 3937");
		json.setAddress(addres);
		json.setName(name);
		json.setWebsite("http://google.com");
		json.setLanguage(language);
		List<String> types = new ArrayList<String>();
		types.add("shoe park");
		types.add("shop");
		json.setTypes(types);
		Location lc = new Location();
		lc.setLat(38.383494);
		lc.setLng(33.427362);
		json.setLocation(lc);
		return json;
	}

}
