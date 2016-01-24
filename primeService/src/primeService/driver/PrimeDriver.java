package primeService.driver;

import primeService.client.ClientDriver;
import primeService.server.ServerDriver;

/**
 * main driver of program
 * @author Anirudha
 *
 */
public class PrimeDriver {
	
	public static void main(String[] args){
		
		DriverInterface driver = new ServerDriver();
		driver.drive(args);
		driver = new ClientDriver();
		driver.drive(args);
	}	
}
