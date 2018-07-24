@SuppressWarnings("serial")
public class InvalidFileFormatException extends RuntimeException{
	public InvalidFileFormatException()
	{
		super("Something wrong with the format of fare.txt\r\nBye Bye!");
	}
	
}

