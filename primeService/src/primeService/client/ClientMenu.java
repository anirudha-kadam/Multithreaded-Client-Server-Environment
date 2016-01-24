package primeService.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import primeService.util.Debug;

/**
 * provides method to print client menu and take input from client
 * @author Anirudha
 *
 */
public class ClientMenu {
	
	private int primeQueryNumber;
	private String clientName;
	
	/**
	 * ClientMenu class default constructor
	 */
	public ClientMenu(){
		Debug.singletonDebug().printToLog(3,"ClientMenu class default constructor method has been called");
	}
	
	
	/**
	 * reads client name  from user
	 */
	private void getName(){
		Debug.singletonDebug().printToLog(2,"ClientMenu class getName() method has been called");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter client name: ");
		try {
			clientName = br.readLine();
		} catch (IOException e) {
			System.err.println("Caught IOException: "+e.getMessage());
		}finally{	
		}
	}
	
	/**
	 * reads Query Number rom user
	 * @return Name+Number
	 */
	private String getQueryNumber(){
		Debug.singletonDebug().printToLog(2,"ClientMenu class getQueryNumber() method has been called");
		Scanner sc = null;
		System.out.println("Enter Query number: ");
		try {
			sc = new Scanner(System.in);
			primeQueryNumber = sc.nextInt();
		} catch (InputMismatchException e) {
			System.err.println("Caught InputMismatchException: "+e.getMessage());
		}finally{
		}
		return (clientName+" "+primeQueryNumber);
	}
	
	/**
	 * Prints response sent by server
	 * @param response arraylist with responses stored
	 */
	private void printResponse(List<String> response){
		Debug.singletonDebug().printToLog(2,"ClientMenu class printResponse(List<String> response) method has been called");
		if(response.size() > 0){
			for(int i=0; i<response.size(); i++){
				System.out.println(response.get(i));
			}
		}else{
			System.out.println("No data found");
		}
	}
	
	
	/**
	 * prints client menu
	 * @param response ArrayList with responses stored 
	 * @return String Name+Number
	 * @throws IOException
	 */
	public String printClientMenu(List<String> response) throws IOException {
		Debug.singletonDebug().printToLog(2,"ClientMenu class printClientMenu(List<String> response) method has been called");
		int choice = 0;
		Scanner sc = null;
		do{
			System.out.println("\nMenu\n[1] Set client name\n[2] Enter number to query for prime\n"
								+ "[3] What is the server response?\n[4] Quit\n\n");
		
			try {
				System.out.println("Enter choice: ");
				sc = new Scanner(System.in);
				choice = sc.nextInt();;
			} catch (InputMismatchException e) {
				System.err.println("Caught InputMismatchException: "+e.getMessage());
			}finally{
				
			}
			switch(choice){
			case 1:
					getName();
					break;
			case 2:
					return getQueryNumber();

			case 3:
					printResponse(response);
					break;
				
			case 4:
					sc.close();
					System.out.println("Bye!");
					System.exit(0);
					break;
				
			default:
					System.err.println("Please enter a correct choice!");
					break;
			}
		}while(choice!=4);
		return null;
	}
}
