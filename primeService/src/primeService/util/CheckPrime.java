package primeService.util;

/**
 * provides method to check if the number is prime
 * @author Anirudha
 *
 */
public class CheckPrime {
	
	/**
	 * default constructor
	 */
	public CheckPrime(){
		Debug.singletonDebug().printToLog(3, "CheckPrime class default constructor has been called");
	}
	
	/**
	 * checks if the number is prime. returns yes if number is odd else no
	 * @param number
	 * @return string "Yes" or "No"
	 */
	public synchronized String isPrime(int number){
		Debug.singletonDebug().printToLog(2, "CheckPrime class isPrime method has been called");
		if(number % 2 == 0)
			return "No";
		else
			return "Yes";	
	}
}
