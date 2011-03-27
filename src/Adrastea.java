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

		// Load all our config stuffs
		try {
			IrcConfig.load("C:\\java\\adrastea\\config.txt");
		}
		catch(Exception e) {
			System.out.println("Unable to load configuration file");
			return;
		}
	
		// Connection loop
		while(true) {
			try {
				// let us connect
				System.out.println("Connecting to " + IrcConfig.host + ":" + IrcConfig.port + "...");
				irc.connect(IrcConfig.host, IrcConfig.port);

				// Receive message loop
				while(true)
				{
					message = new IrcMessage(irc.recv());
					System.out.println(message.raw);

					if (false)
					{
						throw new IrcExitException();
					}
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
			catch(Exception e) {
				System.out.println("Unknown Error, reconnecting...");
			}
		}
	}
}
