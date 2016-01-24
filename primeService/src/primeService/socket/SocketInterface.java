package primeService.socket;

/**
 * forces createWroker method to both PrimeClientSocket and PrimeServerSocket
 * @author Anirudha
 *
 */
public interface SocketInterface {
	
	/**
	 * creates new thread to server client/server
	 */
	public void createWorker();
}
