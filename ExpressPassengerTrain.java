class ExpressPassengerTrain extends PassengerTrain {
	public ExpressPassengerTrain(String code, String startTime, Line line, Station start, Station end, int maxTicketNumber) {
		super(code,startTime,line,start,end,maxTicketNumber);
	}
	
	public double calculateFare(Station stationFrom, Station stationTo, int quantity) {
		return super.calculateFare(stationFrom, stationTo, quantity)+50*quantity;
	}
	
	public String toString() {
		return super.toString()+" Express Train Surcharge Required!";
	}
}