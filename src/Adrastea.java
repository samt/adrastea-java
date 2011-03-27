/*
 * Adrastea IRC Bot
 * Version 2.0.0 (Java)
 *
 * (c) 2011 Sam Thompson <sam@websyntax.net>
 * License: http://www.opensource.org/licenses/mit-license.php The MIT License
 */

public class Adrastea {
	/*
	 * Main
	 */
	public static void main(String[] args) {
		// Declare our IRC environment vars
		IrcConnection irc = new IrcConnection(); // Connection object
		IrcMessage message; // Stores the message per tick
		
		// Connection loop
		while(true) {
			irc.connect();

			try {
				// Receive message loop
				while(true)
				{
					message = new IrcMessage(irc.recv());
				}
			}
			catch(IrcNullMessageException e) {
				System.out.println("Network Error, reconnecting...");
			}
			catch(IrcFailedConnectException e) {
				System.out.println("Failed to connect, retrying...");
			}
			catch(IrcExitException e) {
				System.out.println("System Shutdown...");
				return;
			}
		}
	}
}
