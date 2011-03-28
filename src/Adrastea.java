/*
 * Adrastea IRC Bot
 * Version 2.0.0 (Java)
 *
 * (c) 2011 Sam Thompson <sam@websyntax.net>
 * License: http://www.opensource.org/licenses/mit-license.php The MIT License
 */

package adrastea;

public class Adrastea {
	/*
	 * Main
	 */
	public static void main(String[] args) {
		System.out.println("Starting up Adrastea IRC Bot");

		// Declare our IRC environment vars
		IrcConnection irc = new IrcConnection(); // Connection object
		IrcMessage message; // Stores the message per tick

		System.out.println("Loading configuration file");
		// Load all our config stuffs
		try {
			IrcConfig.load("config.txt");
		}
		catch(Exception e) {
			System.out.println("[ERROR] Unable to load configuration file.");
			System.exit(0);
		}

		// Connection loop
		while(true) {
			try {
				// let us connect
				System.out.println("Connecting to " + IrcConfig.host + ":" + IrcConfig.port + "...");
				irc.connect(IrcConfig.host, IrcConfig.port);
				
				// Send our nickname and user
				irc.sendRaw("NICK " + IrcConfig.nick);
				irc.sendRaw("USER " + IrcConfig.user + " 8 * :" + IrcConfig.name);

				// Receive message loop
				while(true) {
					message = new IrcMessage(irc.recv());
					System.out.println(message.raw);

					// temp
					if(message.type.equals("PING"))
					{
						irc.sendRaw("PONG :" + message.message);
						System.out.println("PONG :" + message.message);
						continue;
					}
					else if(message.type.equals("376"))
					{
						irc.sendRaw("JOIN " + IrcConfig.channels);
						System.out.println("JOIN " + IrcConfig.channels);
						continue;
					}

					// This will eventually check a flag to see if an exit
					// command has been given
					if (false) {
						throw new IrcExitException();
					}
				}
			}
			catch(IrcNullMessageException e) {
				System.out.println("\n[ERROR] Network Error, reconnecting...");
			}
			catch(IrcFailedConnectException e) {
				System.out.println("\n[ERROR] Failed to connect, retrying...");
			}
			catch(IrcExitException e) {
				System.out.println("\nSystem Shutdown.");
				System.exit(0);
			}
			catch(Exception e) {
				System.out.println("[ERROR] Unknown Error, reconnecting...");
			}
		}
	}
}
