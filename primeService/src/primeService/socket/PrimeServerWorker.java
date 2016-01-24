package primeService.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import primeService.server.AllPrimeQueries;
import primeService.server.AllPrimeQueriesInterface;
import primeService.util.CheckPrime;
import primeService.util.Debug;

/**
 * provides new thread for a client
 * @author Anirudha
 *
 */
public class PrimeServerWorker implements Runnable{
	private  String inputLine, outputLine;
	private Socket clientSocket = null;
	private AllPrimeQueriesInterface apq = null;
	
	/**
	 * parametarized PrimeServerWorker class constructor
	 * @param clientSocketIn
	 * @param apqIn
	 */
	public PrimeServerWorker(Socket clientSocketIn, AllPrimeQueriesInterface apqIn){
		Debug.singletonDebug().printToLog(3, "PrimeServerWorker class parametarized constructor has been called");
		clientSocket = clientSocketIn;
		apq = apqIn;
	}

	/**
	 * default constructor
	 */
	public PrimeServerWorker(){
		Debug.singletonDebug().printToLog(3, "PrimeServerWorker class default constructor has been called");
	}
	
	/**
	 * processes input from client
	 * @param input
	 * @return process input
	 */
	private String[] processInputLine(String input){
		Debug.singletonDebug().printToLog(2, "PrimeServerWorker class processInputLine(String input) method has been called");
		String [] formattedInput = input.split(" ");
		return formattedInput;
	}
	
	/**
	 * process output to client
	 * @param s
	 * @param primeResponce
	 * @return process output
	 */
	private String processOutputLine(String [] s, String primeResponce){
		Debug.singletonDebug().printToLog(2, "PrimeServerWorker class processOutputLine(String [] s, String primeResponce) method has been called");
		return "<primeQueryResponse><intValue>"+s[1]+"</intValue><isPrime>"+primeResponce+"</isPrime></primeQueryResponse>";
	}
	
	
	/**
	 * run method
	 */
	public void run() {
		Debug.singletonDebug().printToLog(2, "PrimeServerWorker class run() method has been called");
		synchronized (clientSocket) {
			BufferedReader in = null;
			PrintWriter out = null;
			try {
				in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				out = new PrintWriter(clientSocket.getOutputStream(), true);
				while(true){
					String []formattedInput = null;
					
					if((inputLine = in.readLine()) != null){
						formattedInput = processInputLine(inputLine);
						if(apq.checkThreshold(Integer.parseInt(formattedInput[1]))){
							apq.put(formattedInput[0],Integer.parseInt(formattedInput[1]));
							outputLine = processOutputLine(formattedInput,new CheckPrime().isPrime(Integer.parseInt(formattedInput[1])));
							
						}
						else{
							outputLine = processOutputLine(formattedInput, "Less than Threshold value 3!");
						}
						out.println(outputLine); 
					} 
					out.flush();
				}
			} catch (IOException e) {
				System.err.println("Client Disconnected: "+e.getMessage());
			} finally{
				try {
					in.close();
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
				
	}
	
	
}
