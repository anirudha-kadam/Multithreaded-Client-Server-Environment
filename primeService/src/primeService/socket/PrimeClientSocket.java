package primeService.socket;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import primeService.util.Debug;
import primeService.client.ClientMenu;

/**
 * creates client worker
 * @author Anirudha
 *
 */
public class PrimeClientSocket implements SocketInterface{
	
	private Socket clienSocket = null;
	private String hostName;
	private int portNumber;
	private ClientMenu clientMenu = null;
	
	/**
	 * parameterized constructor
	 * @param hostNameIn
	 * @param portNumIn
	 * @param clientMenuIn
	 */
	public PrimeClientSocket(String hostNameIn, int portNumIn, ClientMenu clientMenuIn) {
		Debug.singletonDebug().printToLog(3, "PrimeClientSocket class parameterized constructor has been called");
		hostName = hostNameIn;
		portNumber = portNumIn;
		clientMenu = clientMenuIn;
	}
	
	/***
	 * default constructor
	 */
	public PrimeClientSocket(){
		Debug.singletonDebug().printToLog(3, "PrimeClientSocket class default constructor has been called");
	}
	
	/**
	 * creates a new thread to communicate with server
	 */
	public void createWorker(){
		Debug.singletonDebug().printToLog(2, "PrimeClientSocket class createWorker() method  has been called");
		try {
			clienSocket = new Socket(hostName, portNumber);
			PrimeClientWorker clientWorker = new PrimeClientWorker(clienSocket, clientMenu);
			Thread newClientWorker = new Thread(clientWorker);
			newClientWorker.start();
			newClientWorker.join();
		} catch (UnknownHostException e) {
			System.err.println("Caught UnknownHostException: "+e.getMessage());
		} catch (IOException e) {
			System.err.println("Caught IOException: "+e.getMessage());
		} catch (InterruptedException e) {
			System.err.println("Caught InterruptedException: "+e.getMessage());
		}
		
	}

}
