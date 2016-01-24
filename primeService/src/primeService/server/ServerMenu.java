package primeService.server;

import java.util.InputMismatchException;
import java.util.Scanner;

import primeService.util.Debug;

/**
 * provides method to print server menu
 * @author Anirudha
 *
 */
public class ServerMenu {
	
	AllPrimeQueriesInterface apq = null;
	
	/**
	 * parameterized ServerMenu class constructor
	 * @param apq2
	 */
	public ServerMenu(AllPrimeQueriesInterface apq2) {
		Debug.singletonDebug().printToLog(3, "ServerMenu Class parameterized constructor has been called");
		apq = apq2;
	}
	
	/**
	 * default ServerMenu class constructor
	 */
	public ServerMenu() {
		Debug.singletonDebug().printToLog(3, "ServerMenu Class default constructor has been called");
	}
	
	/**
	 * shuts the server down
	 */
	private void shutdown(){
		Debug.singletonDebug().printToLog(2, "ServerMenu Class shutdown() method has been called");
		System.out.println("Server shutting down..");		
		System.out.println("Bye!");
		System.exit(0);
	}
	
	
	/**
	 * prints client menu
	 */
	public void printServerMenu(){
		Scanner sc = null;
		int choice = 0;
		do{
			System.out.println("\nMenu\n[1] Client Name\n[2] All Client Queries\n"
								+"[3] Quit\n");
			System.out.println("\nEnter choice: ");
			sc = new Scanner(System.in);
			try {
				choice = sc.nextInt();
			} catch (InputMismatchException e) {
				System.err.println("Caught InputMismatchException: "+e.getMessage());
			}
			
			switch(choice){
			case 1:
					((DisplayInterface)apq).displayClientNameQuery();
					break;
					
			case 2:
					((DisplayInterface)apq).printAllQueries();
					break;
				
			case 3:
					sc.close();
					shutdown();
					break;
				
			default:
					System.err.println("Please enter a correct choice!");
					break;
			}
		}while(choice!=3);
	}
}
