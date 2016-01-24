package primeService.socket;

import java.io.IOException; 
import java.net.ServerSocket;
import java.net.Socket;

import primeService.server.AllPrimeQueries;
import primeService.server.AllPrimeQueriesInterface;
import primeService.util.Debug;

public class PrimeServerSocket implements Runnable, SocketInterface {
	
	private ServerSocket serverSocket = null;
	private Socket clientSocket = null;
	private AllPrimeQueriesInterface apq = null;
	
	/**
	 * parameterized constructor 
	 * @param portNumberIn
	 * @param apqIn
	 * @throws IOException
	 */
	public PrimeServerSocket(int portNumberIn, AllPrimeQueriesInterface apqIn) throws IOException{
		Debug.singletonDebug().printToLog(3, "PrimeServerSocket class parameterized constructor has been called");
		apq = apqIn;
		serverSocket = new ServerSocket(portNumberIn);
	}
	
	/**
	 * default constructor
	 */
	public PrimeServerSocket(){
		Debug.singletonDebug().printToLog(3, "PrimeServerSocket class default constructor has been called");
	}
	
	/**
	 * creates new thread to serve client
	 */
	public synchronized void createWorker(){
		Debug.singletonDebug().printToLog(2, "PrimeServerSocket class createWorker() method has been called");
		try {
			
			while(true){
				clientSocket = serverSocket.accept();
				PrimeServerWorker serverWorker = new PrimeServerWorker(clientSocket,apq);
				Thread newServerWorker = new Thread(serverWorker);
				newServerWorker.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Caught IOException: "+e.getMessage());
		} finally{
			try {
				serverSocket.close();
				clientSocket.close();
			} catch (IOException e) {
				System.err.println("Caught IOException: "+e.getMessage());
			}finally{
				
			}
		}
	}

	/**
	 * run method
	 */
	public void run() {
		Debug.singletonDebug().printToLog(2, "PrimeServerSocket class run method has been called");
		createWorker();
	}
}
