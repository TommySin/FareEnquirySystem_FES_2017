class Station{
	private String name;
	private int distance;
	
	public Station(String name, int distance) {
		this.name = name;
		this.distance = distance;
	}
	
	public String getName() {return name;}
	public int getDistance() {return distance;}
	
	//extra toString method to return all station name.
	//that will be used in showing the route start and end station in the route part in main program.
	public String toString() {
		return name;
	}

}