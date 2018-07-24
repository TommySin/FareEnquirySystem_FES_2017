class PassengerTrain extends Train {
	private int maxTicketNumber;
	
	public PassengerTrain(String code, String startTime, Line line, Station start, Station end, int maxTicketNumber) {
		super(code,startTime,line,start,end);
		this.maxTicketNumber = maxTicketNumber;
	}
	
	public int getMaxTicketNumber() {return maxTicketNumber;}
	
	public double calculateDistanceFare(Station stationFrom, Station stationTo) {
		return (Math.abs(stationFrom.getDistance()-stationTo.getDistance())*0.4);
	}
	
	public String toString() {
		return super.toString()+" Max. Number of Passenger Ticket per Order : "+maxTicketNumber;
	}
}