package primeService.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import primeService.util.Debug;
import primeService.client.ClientMenu;

public class PrimeClientWorker implements Runnable{
	
	private Socket clientSocket = null; 
	private String primeClientQuery = null;
	private String serverResponse = null;
	private List<String> serverResponceData = new ArrayList<String>();
	private ClientMenu clientMenu = null;
	
	/**
	 * parameterized constructor 
	 * @param clienSocketIn
	 * @param clientMenuIn
	 */
	public PrimeClientWorker(Socket clienSocketIn, ClientMenu clientMenuIn) {
		Debug.singletonDebug().printToLog(3, "PrimeClientWorker class parametarized constructor has been called");
		clientSocket = clienSocketIn;
		clientMenu = clientMenuIn;
	}
	
	/**
	 * default constructor
	 */
	public PrimeClientWorker() {
		Debug.singletonDebug().printToLog(3, "PrimeClientWorker class default constructor has been called");
	}
	
	/**
	 * getter method for serverResponceData 
	 * @return arraylist 
	 */
	public List<String> getServerResponceData() {
		Debug.singletonDebug().printToLog(2, "PrimeClientWorker class getServerResponceData() method has been called");
		return serverResponceData;
	}

	/**
	 * setter method for serverResponceData
	 * @param serverResponceData
	 */
	public void setServerResponceData(List<String> serverResponceData) {
		Debug.singletonDebug().printToLog(2, "PrimeClientWorker class setServerResponceData(List<String> serverResponceData) method has been called");
		this.serverResponceData = serverResponceData;
	}
	
	/**
	 * run method
	 */
	public void run() {
		Debug.singletonDebug().printToLog(2, "PrimeClientWorker class run() method has been called");
		synchronized (clientSocket) {
			PrintWriter out = null;
			BufferedReader in = null;
			try {	
				out = new PrintWriter(clientSocket.getOutputStream(), true);
				in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				while(true){
					primeClientQuery = clientMenu.printClientMenu(serverResponceData);			
					if(primeClientQuery!= null){
						out.println(primeClientQuery);
					}
					out.flush();
					if((serverResponse = in.readLine()) != null){
						getServerResponceData().add(serverResponse);
					}
				}
				} catch (Exception e) {
					System.err.println("Server Not Responding: "+e.getMessage());
				}finally{
					try {
						in.close();
						out.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
		}
		
	}
}
