package resourses;

import java.util.ArrayList;
import java.util.List;

import POJO.AddPlace;
import POJO.Location;

public class TestDataBuild {

	public AddPlace addPlace(String name, String language, String address) {
		AddPlace ap = new AddPlace();
		ap.setAccuracy(50);
		ap.setAddress(address);
		ap.setLanguage(language);
		ap.setName(name);
		ap.setPhone_number("(+91) 983 893 3937");
		ap.setWebsite("(+91) 983 893 3937");

		List<String> ls = new ArrayList<String>();
		ls.add("shoe park");
		ls.add("shop");
		ap.setTypes(ls);

		Location l = new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		ap.setLocation(l);
		return ap;
	}
}
