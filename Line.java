class Line{
	private String name;
	private Station[] stops;
	private double passengerFarePerKm;
	private double cargoFarePerKm;
	private int expressTrainSurcharge;
	
	public Line(String name, Station[] stops, double passengerFarePerKm, double cargoFarePerKm, int expressTrainSurchange) {
		this.name = name;
		this.stops = stops;
		this.passengerFarePerKm = passengerFarePerKm;
		this.cargoFarePerKm = cargoFarePerKm;
		this.expressTrainSurcharge = expressTrainSurchange;
	}
	
	public String getName() {return name;}
	public Station[] getStops() {return stops;}
	public double getPassengerFarePerKm() {return passengerFarePerKm;}
	public double getCargoFarePerKm() {return cargoFarePerKm;}
	public int getExpressTrainSurcharge() {return expressTrainSurcharge;}
	
	public int calculateDistance(Station stationA, Station stationB) {
		return Math.abs((stationB.getDistance()-stationA.getDistance()));
	}
	
	public String toString() {
		System.out.println("Line: "+getName());
		
		//this for statement is for show all stops on the line.
		for(int i = 0;i < stops.length;i++) {
			if(i!=6)//when i = 6, that will stop and print the last stop on the line.
			System.out.print(stops[i].getName()+",("+stops[i].getDistance()+") > ");
			else System.out.print(stops[i].getName()+",("+stops[i].getDistance()+")");
			}
		return "\r\n\nFares Summary:"+"\r\nPassenger Per KM: HKD "+getPassengerFarePerKm()+"\r\nCargo Per KG Per KM: HKD "+getCargoFarePerKm()+"\r\nExpress Train Surcharge: HKD "+getExpressTrainSurcharge()+"\r\n";}
	
}