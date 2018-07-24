abstract class Train {
	private String code;
	private String startTime;
	private Line line;
	private Station start;
	private Station end;
	
	//an abstract method overridden by sub-classes' methods as there are different calculations for different types of train.
	abstract double calculateDistanceFare(Station Start,Station End);
	
	//a constructor of the class train
	public Train(String code, String startTime, Line line, Station start, Station end) {
		this.code = code;
		this.startTime = startTime;
		this.line = line;
		this.start = start;
		this.end = end;
		}
	
	public String getCode() {return code;}
	public String getStartTime() {return startTime;}
	public Line getLine() {return line;}
	public Station getStart() {return start;}
	public Station getEnd() {return end;}
	
	
	
	public boolean isValidRoute(Station stationFrom, Station stationTo) {
		//two attributes for store the sequence of start and end station by using number.
		int sNoFrom = 0,sNoTo = 0;
		Station s[] = line.getStops();
		
		//store the sequence of start and end station by using number when searched correct station.
		for(int i = 0;i<s.length;i++) {
			if(s[i] == start)
				sNoFrom = i;
			if(s[i] == end)
				sNoTo = i;
		}
		//two if statements for search the valid stops on the route.
			if(sNoFrom > sNoTo) {
				for(int i = sNoFrom;i >= sNoTo;i--) {
					if(s[i] == stationFrom)
						for(int t = i;t >= sNoTo;t--) {
							if(s[t] == stationTo)
								return true;
						}
				}
		}	
			if(sNoFrom < sNoTo) {
				for(int i = sNoFrom;i <= sNoTo;i++) {
					if(s[i] == stationFrom)
						for(int t = i;t <= sNoTo;t++) {
							if(s[t] == stationTo)
								return true;
						}
				}
		}
		return false;
}
	
	
	public double calculateFare(Station stationFrom, Station stationTo, int quantity) {
		return Math.round(calculateDistanceFare(stationFrom,stationTo)*quantity);
	}
	
	public String toString() {
		return getCode()+" "+getStartTime()+" "+start.getName()+" > "+end.getName();
	}
	
}