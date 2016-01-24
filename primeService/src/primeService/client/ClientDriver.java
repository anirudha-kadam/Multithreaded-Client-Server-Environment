package primeService.client;

import primeService.driver.DriverInterface;
import primeService.socket.PrimeClientSocket;
import primeService.socket.SocketInterface;
import primeService.util.Debug;

/**
 * Starts client
 * @author Anirudha
 *
 */
public class ClientDriver implements DriverInterface{
	private String hostName;
	private int portNumber, debugValue;
	
	
	/**
	 * default ClientDriver class constructor
	 */
	public ClientDriver() {
		Debug.singletonDebug().printToLog(3,"ClientDriver class default constructor has been called");
	}
	
	
	/**
	 * responsible to validate command line arguments
	 * @param args
	 * @return boolean true if arguments are valid
	 */
	public boolean validateArguments(String args[]){
		if(args.length == 4 && args[0].equals("client")){
			hostName = args[1];
			try{
			portNumber = Integer.parseInt(args[2]);
			debugValue = Integer.parseInt(args[3]);
			}catch(NumberFormatException e){
				System.err.println("port number and debug value should be integer");
				System.exit(0);
			}finally{
				Debug.setDEBUG_VALUE(debugValue);
				return true;
			}
		}else{
			return false;
		}
	}
	
	
	/**
	 * Responsible to start client
	 * @param args command line arguments
	 */
	public void drive(String args[]){
		
		if(validateArguments(args)){
			Debug.singletonDebug().printToLog(2,"ServerDriver class drive(String args[]) method has been called");
			ClientMenu clientMenu = new ClientMenu();
			SocketInterface primeClientSocket = new PrimeClientSocket(hostName, portNumber, clientMenu);
			primeClientSocket.createWorker();
		}
	}
}
