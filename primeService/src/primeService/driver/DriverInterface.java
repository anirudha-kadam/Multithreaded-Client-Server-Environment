package primeService.driver;

/**
 * Enforce Drive and validateArguments methods to both 
 * ClientDriver and ServerDriver 
 * @param args
 */
public interface DriverInterface {
	
	/**
	 * Responsible to start server/client
	 * @param args
	 */
	void drive(String args[]);
	
	/**
	 * responsible to validate command line arguments
	 * @param args
	 * @return boolean true if arguments are valid
	 */
	boolean validateArguments(String args[]);
}
