package resourses;

//Enum is a special Class in java which comprises of attributes and methods.


public enum APIResourses {
	
	AddPlaceAPI("/maps/api/place/add/json"),
	GetPlaceAPI("/maps/api/place/get/json"),
	DeletePlaceAPI("/maps/api/place/delete/json");
	
	private String resourse;
	
	APIResourses(String resourse)
	{
		this.resourse = resourse;
	}
	
	public String getResourse()
	{
		return resourse;	
	}
}

