package primeService.server;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public interface AllPrimeQueriesInterface {
	
	/**
	 * puts data into clientQueryData {@link ConcurrentHashMap}
	 */
	public void put(String key, Integer value);
	
	/**
	 * 
	 * gets data from clientQueryData {@link ConcurrentHashMap}
	 */
	public ArrayList<Integer> get(String key);
	
	/**
	 * checks if client input is greater than threshold value
	 * @return boolean
	 */
	public boolean checkThreshold(int number);
}
