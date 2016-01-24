package primeService.server;

import java.util.concurrent.ConcurrentHashMap;

/**
 * provides methods to print output to console
 * @author Anirudha
 *
 */
public interface DisplayInterface {
	
	/**
	 * prints all data of clientQueryData {@link ConcurrentHashMap}
	 */
	public void displayClientNameQuery();
	
	/**
	 * takes key and prints corresponding value
	 */
	public void printAllQueries();
}
