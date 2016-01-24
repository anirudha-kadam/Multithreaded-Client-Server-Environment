package primeService.server;

import java.io.IOException;

import primeService.driver.DriverInterface;
import primeService.socket.PrimeServerSocket;
import primeService.socket.SocketInterface;
import primeService.util.Debug;

/**
 * starts server
 * @author Anirudha
 *
 */
public class ServerDriver implements DriverInterface{
	
	private int portNumber, debugValue;
	
	public ServerDriver() {
		Debug.singletonDebug().printToLog(3,"ServerDriver class default constructor has been called");
	}
	
	/**
	 * responsible to validate command line arguments
	 * @param args command line arguments
	 * @return boolean true if arguments are valid
	 */
	public boolean validateArguments(String args[]){
		if(args.length == 4 && args[0].equals("server")){
			try{
			portNumber = Integer.parseInt(args[2]);
			debugValue = Integer.parseInt(args[3]);
			}catch(NumberFormatException e){
				System.err.println("port number and debug value should be integers");
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
	 * Responsible to start server
	 * @param args command line arguments
	 */
	public void drive(String args[]){
		
		if(validateArguments(args)){
			Debug.singletonDebug().printToLog(2,"ServerDriver class drive(String args[]) method has been called");
			AllPrimeQueriesInterface apq = new AllPrimeQueries();
			ServerMenu serverMenu = new ServerMenu(apq);
			SocketInterface primeServerSocket = null;
			try {
				primeServerSocket = new PrimeServerSocket(portNumber, apq);
				Thread newServer = new Thread((Runnable) primeServerSocket);
				newServer.start();
				serverMenu.printServerMenu();
				newServer.join();
			} catch (NumberFormatException e) {
				System.err.println("Caught NumberFormatException: "+e.getMessage());
			} catch (IOException e) {
				System.err.println("Caught IOException: "+e.getMessage());
			} catch (InterruptedException e) {
				System.err.println("Caught InterruptedException: "+e.getMessage());
			}
		}
	}

}
