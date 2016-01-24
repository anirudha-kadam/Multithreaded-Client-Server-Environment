package primeService.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Debug class
 * @author Anirudha
 *
 */
public class Debug {
	
	private static Debug singletonDebugInstance = null;
	private static int DEBUG_VALUE = 0;
	
	/**
	 * getter method for DEBUG_VALUE
	 * @return int DEBUG_VALUE
	 */
	public static int getDEBUG_VALUE() {
		Debug.singletonDebug().printToLog(2, "Debug class getDEBUG_VALUE() method has been called");
		return DEBUG_VALUE;
	}
	
	/**
	 * setter method for DEBUG_VALUE
	 * @return void
	 */
	public static void setDEBUG_VALUE(int dEBUG_VALUE) {
		Debug.singletonDebug().printToLog(2, "Debug class setDEBUG_VALUE(int dEBUG_VALUE) method has been called");
		DEBUG_VALUE = dEBUG_VALUE;
	} 
	
	/**
	 * default Debug class constructor
	 */
	private Debug(){
		
	}
	
	/**
	 * method that returns single instance of a Debug class
	 * @return singletonDebugInstance
	 */
	public static Debug singletonDebug(){
		if(singletonDebugInstance == null){
			synchronized (Debug.class) {
				if(singletonDebugInstance == null){
					singletonDebugInstance = new Debug();
				}
			}
		}
		return singletonDebugInstance;
	}
	
	/**
	 * @param level currently set DEBUG_VALUE
	 * @param debugMessage message to be logged corresponding to 
	 * current DEBUG_VALUE
	 * @return void
	 */
	public void printToLog(int level, String debugMessage){
		if(level == DEBUG_VALUE){
			BufferedWriter bw = null;
			try {
				bw = new BufferedWriter(new FileWriter("FileLog"));
				bw.write(debugMessage+"\n");
			} catch (IOException e) {
				System.err.println("Caught IOException: " + e.getMessage());
			} finally {
				try {
					if (bw != null)
						bw.close();
				} catch (IOException e) {
					System.err.println("Caught IOException: " + e.getMessage());
				} finally {

				}
			}

		}
	}	

}
