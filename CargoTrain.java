class CargoTrain extends Train{
	private int maxCargoWeight; 
	public CargoTrain(String code, String startTime, Line line, Station start, Station end, int maxCargoWeight) {
		super(code,startTime,line,start,end);
		this.maxCargoWeight = maxCargoWeight;
	}

	public int getMaxCargoWeight() {return maxCargoWeight;}
	
	public double calculateDistanceFare(Station stationFrom, Station stationTo) {
		return (Math.abs(stationFrom.getDistance()-stationTo.getDistance())*0.01);
	}
	public String toString() {
		return super.toString()+" Max. Cargo Weight per Order : "+maxCargoWeight+" KG";
	}
	
}