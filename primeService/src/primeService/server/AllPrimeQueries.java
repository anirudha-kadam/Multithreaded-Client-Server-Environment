package primeService.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import primeService.util.Debug;

/**
 * provides data structure to store queries from client
 * @author Anirudha
 *
 */
public class AllPrimeQueries implements AllPrimeQueriesInterface, DisplayInterface {
		/**
		 * {@link ConcurrentHashMap} to store client queries
		 */
		private Map<String, ArrayList<Integer>> clientQueryData = new ConcurrentHashMap<String, ArrayList<Integer>>();
		
		/**
		 * AllPrimeQueries class default constructor
		 */
		public AllPrimeQueries(){
			Debug.singletonDebug().printToLog(3, "AllPrimeQueries class default constructor has been called");
		}
		
		/**
		 * getter method for clientQueryData
		 * @return clientQueryData {@link ConcurrentHashMap}
		 */
		public Map<String, ArrayList<Integer>> getClientQueryData() {
			Debug.singletonDebug().printToLog(2, "AllPrimeQueries class getClientQueryData() method has been called");
			return clientQueryData;
		}
		
		/**
		 * setter method for clientQueryData
		 * @param clientQueryData {@link ConcurrentHashMap}
		 */
		public void setClientQueryData(Map<String, ArrayList<Integer>> clientQueryData) {
			Debug.singletonDebug().printToLog(2, "AllPrimeQueries class setClientQueryData(Map<String, ArrayList<Integer>> clientQueryData) method has been called");
			this.clientQueryData = clientQueryData;
		}
		
		/**
		 * checks if client input is greater than threshold value
		 * @return boolean
		 */
		public synchronized boolean checkThreshold(int number){
			Debug.singletonDebug().printToLog(2, "AllPrimeQueries class checkThreshold(int number) method has been called");
			if(number > 3)
				return true;
			else
				return false;	
		}
		
		/**
		 * puts data into clientQueryData {@link ConcurrentHashMap}
		 */
		public synchronized void put(String key, Integer value){
			Debug.singletonDebug().printToLog(2, "AllPrimeQueries class put(String key, Integer value) method has been called");
			if(getClientQueryData().containsKey(key))
				getClientQueryData().get(key).add(value);
			else{
				ArrayList<Integer> newArrayList = new ArrayList<Integer>();
				newArrayList.add(value);
				getClientQueryData().put(key, newArrayList);
			}
		}
		
		/**
		 * 
		 * gets data from clientQueryData {@link ConcurrentHashMap}
		 */
		public ArrayList<Integer> get(String key) {
			Debug.singletonDebug().printToLog(2, "AllPrimeQueries class get(String key) method has been called");
			return getClientQueryData().get(key);
		}
		
		/**
		 * prints all data of clientQueryData {@link ConcurrentHashMap}
		 */
		public void printAllQueries(){
			Debug.singletonDebug().printToLog(2, "AllPrimeQueries class printAllQueries() method has been called");
			if(getClientQueryData().size()>0){
				for (Entry<String, ArrayList<Integer>> e : getClientQueryData().entrySet()) {
					System.out.println(e.getKey()+": "+e.getValue());
				}
			}
			else
				System.out.println("No Data Found\n");	
		}
		
		/**
		 * takes key and prints corresponding value
		 */
		public void displayClientNameQuery(){
			Debug.singletonDebug().printToLog(2, "AllPrimeQueries class displayClientNameQuery() method has been called");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("\nEnter client name: ");
			String key;
			try {
				key = br.readLine();
				if(getClientQueryData().containsKey(key))
					System.out.println(key+" "+get(key)+"\n");
				else
					System.out.println("No Data Found");
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
			}
		}
}
