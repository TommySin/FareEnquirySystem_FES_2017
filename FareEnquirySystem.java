import java.util.Scanner;
import java.io.*;

public class FareEnquirySystem {
	public static void main(String args[]){

		//Station information
		Station [] stations = new Station[7];
		stations[0] = new Station("HongKong", 0);
		stations[1] = new Station("ShenZhen", 50);
		stations[2] = new Station("GuangZhou", 200);
		stations[3] = new Station("ZhengZhou", 700);
		stations[4] = new Station("WuChang", 1000);
		stations[5] = new Station("BeiJing", 2100);
		stations[6] = new Station("InnerMongolia", 2400);

		//initialize the attribute for reading the text file.
		double passengerFarePerKm = 0;
		double cargoFarePerKm = 0;
		int expressTrainSurcharge = 0;
		
		

		String fileName = "fare.txt";
		String RLine;
		int tempcheck=0;
		// read the fare rates from the text file "fare.txt" and handle possible exceptions to be completed by you!
		try {
			//Counting amount of line
			BufferedReader in = new BufferedReader(new FileReader(fileName));
			RLine = in.readLine();
			int count = 0;
			
			//program will stop read until line is null.
			while(RLine != null) {
				RLine = in.readLine();
				count++;
			}
			in.close();
			
			//checking number of line in text file.
			if(count != 2) {
				throw new InvalidFileFormatException();
			}
			
			in = new BufferedReader(new FileReader(fileName));
			RLine = in.readLine();
			String[] st = RLine.split(":");
			
			//check length of String array and the first character is or is not equal "P".
			if(!st[0].equals("P")||st.length>3) {
				throw new InvalidFileFormatException();
			}
			
			passengerFarePerKm = Double.parseDouble(st[1]);
			expressTrainSurcharge = Integer.parseInt(st[2]);
			
			// if finish read line 1 data, program will move to second line and read it.
			if(tempcheck == 0 && tempcheck != 1) {
				RLine = in.readLine();
				st = RLine.split(":");
				cargoFarePerKm = Double.parseDouble(st[1]);
				tempcheck = tempcheck++;	
			}
			
			//check length of String array and the first character is or is not equal "C".
			if(!st[0].equals("C")||st.length>3) {
				throw new InvalidFileFormatException();
			}

		}
		catch (FileNotFoundException EXC) {
			System.out.println("Can't find the file: fare.txt\r\nBye Bye!");
			System.exit(0);
		}
		catch (NumberFormatException EXC) {
			System.out.println("Problem with the format of input data!\r\nBye Bye!");
			System.exit(0);
		}
		catch (Exception EXC) {
			System.out.println("Exception Caught: "+EXC);
			System.exit(0);
		}
	
		//Line information
		Line line =
		  new Line("HongKong-WuChang-InnerMongolia", stations, passengerFarePerKm, cargoFarePerKm, expressTrainSurcharge);
			Station s[] = line.getStops();
		//Train information
		Train trains[] = new Train[8];
		trains[0] = new CargoTrain("C001", "08:00", line, line.getStops()[1], line.getStops()[3], 100);
		trains[1] = new CargoTrain("C002", "12:00", line, line.getStops()[5], line.getStops()[2], 200);
		trains[2] = new CargoTrain("C003", "16:00", line, line.getStops()[0], line.getStops()[6], 50);
		trains[3] = new CargoTrain("C004", "20:00", line, line.getStops()[6], line.getStops()[0], 80);
		trains[4] = new PassengerTrain("P001", "10:30", line, line.getStops()[0], line.getStops()[6], 10);
		trains[5] = new PassengerTrain("P002", "17:30", line, line.getStops()[6], line.getStops()[0], 8);
		trains[6] = new ExpressPassengerTrain("X001", "09:30", line, line.getStops()[0], line.getStops()[5], 4);
		trains[7] = new ExpressPassengerTrain("X001", "16:30", line, line.getStops()[5], line.getStops()[1], 6);

		System.out.println("Fare Enquiry System");
		System.out.println("===================");
		System.out.println(line);
		

		// List of all trains
		System.out.println("\r\nAll train(s) running on this line.");
		for(int i=0; i<trains.length; i++)
			System.out.println(trains[i]);
		// List all stations
		System.out.println("\r\nList of stations");
		for(int i=0; i<stations.length; i++)
			System.out.println(i+". "+stations[i].getName());
		/* 	Ask for departure station and arrival station
			Ask for the number of passenger or weight of the cargo
			Show the total fare
		*/
		Scanner kb = new Scanner(System.in);
		System.out.print("\r\nPlease enter departure station number:\t>");
		int DSN = kb.nextInt();
		
		System.out.print("Please enter arrival station number:\t>");
		int ASN = kb.nextInt();
		
		//checking input of station number whether a reasonable range. 
		//program will not run when user input two same number in departure station number(DSN) and arrival station number(ASN).
		if(ASN>6||ASN<0||DSN<0||DSN>6||ASN==DSN) { 
			System.out.print("Invaild Station Number. \r\nBye Bye!");
			System.exit(0);
		}
		
		System.out.println("\r\nSelect the available train:");
		int[] temp = new int[trains.length];
		//¡ô A array for store the train number which are valid routes between the departure station number and arrival station number.
		
		//call method to check what routes are valid.
		//if not vaild temp[i] will set to -1
		for(int i = 0;i<trains.length;i++) {
			if(trains[i].isValidRoute(s[DSN],s[ASN])) {
			System.out.println(i+": "+trains[i]);
			temp[i] =i; 
			}
			else {System.out.print("");
			temp[i]=-1;}
		}
		System.out.print(">");
		int AT = kb.nextInt();
		
		//call method to check the train number is or is not in the reasonable number stored in array - temp.
		//just return positive number or 0 from temp array.
		if(contains(temp,AT)) {
			if(trains[AT] instanceof CargoTrain) {
				System.out.print("\r\nPlease enter cargoweight(KG):\t>");
				int KG = kb.nextInt();
				
				//check the input value is or is not in the reasonable range.
				if(KG < 0) {
					System.out.print("\r\nCargo weight should be positive number.\r\nBye Bye!");
					System.exit(0);
				}else {
				if(KG > ((CargoTrain)trains[AT]).getMaxCargoWeight()) {
					System.out.print("\r\nCargo weight exceeds the max Cargo weight per order.\r\nBye Bye!");
					System.exit(0);
				}else {
					System.out.println("\r\nResult:");
					System.out.println("For "+KG+"KG cargo travelling from "+trains[AT].getLine().getStops()[DSN]+" to "+trains[AT].getLine().getStops()[ASN]+" on "+trains[AT].getCode());
					System.out.println("Total Fare: HKD "+trains[AT].calculateFare(trains[AT].getLine().getStops()[DSN], trains[AT].getLine().getStops()[ASN], KG));
					System.out.println("\r\nBye Bye!");
					System.exit(0);
				}
				}
			}
			else //else follow to (trains[AT] instanceof CargoTrain) and show passenger train enquiry message to user.
				System.out.print("\r\nPlease enter number of passenger:\t>");
				int NOP = kb.nextInt();
				
				if(NOP < 0) {
					System.out.print("\r\nNumber of passengers should be positive number.\r\nBye Bye!");
					System.exit(0);
				}else
				//check the input value is or is not in the reasonable range.
				if(NOP > ((PassengerTrain)trains[AT]).getMaxTicketNumber()) {
					System.out.print("Number of passengers exceeds the max number of passenger ticket per order.\r\nBye Bye!");
					System.exit(0);
				}else {
					System.out.println("\r\nResult:");
					System.out.println("For "+NOP+" Passenger(s) travelling from "+trains[AT].getLine().getStops()[DSN]+" to "+trains[AT].getLine().getStops()[ASN]+" on "+trains[AT].getCode());
					System.out.println("Total Fare: HKD "+trains[AT].calculateFare(trains[AT].getLine().getStops()[DSN], trains[AT].getLine().getStops()[ASN], NOP));
					System.out.println("\r\nBye Bye!");
					System.exit(0);
				}
				kb.close();
		}else //else follow to (contains(temp,AT)) and show the exception when user input invalid train number.{
			System.out.print("Invalid train.\r\nBye Bye!");
			System.exit(0);}
		
//A method for checking the Train number whether a reasonable range.
public static boolean contains(final int[] array, final int NB) {
	boolean result = false;
	
	for(int i : array) {
		if(i == NB) {
			result = true;
			break;
		}
	}
	return result;
	}
}