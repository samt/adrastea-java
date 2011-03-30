/*
 * Adrastea IRC Bot
 * Version 2.0.0 (Java)
 *
 * (c) 2011 Sam Thompson <sam@websyntax.net>
 * License: http://www.opensource.org/licenses/mit-license.php The MIT License
 */

package adrastea;

import java.io.*;
import java.net.*;

public class IrcConnection {
	/*
	 * @var socket
	 */
	private Socket sock;

	/*
	 * @vars reader and writer
	 */
	private BufferedWriter writer;
	private BufferedReader reader;

	/*
	 * Does nothing
	 */
	public IrcConnection() {
	}

	/*
	 * Connect to the server normally
	 *
	 * @param String - Host of the IRC server
	 * @param int - Port number
	 * @return IrcConnection
	 */
	public IrcConnection connect(String host, int port) throws IrcFailedConnectException {
		try {
			this.sock = new Socket(host, port);

			this.writer = new BufferedWriter(new OutputStreamWriter(this.sock.getOutputStream()));
			this.reader = new BufferedReader(new InputStreamReader(this.sock.getInputStream()));
		}
		catch(Exception e) {
			throw new IrcFailedConnectException();
		}

		return this;
	}

	/*
	 * Connect to the server with the default port
	 *
	 * @param String - Host of the IRC server
	 * @return IrcConnection
	 */
	public IrcConnection connect(String host) throws IrcFailedConnectException {
		return this.connect(host, 6667);
	}

	/*
	 * Connect to the server with a stringy port 
	 *
	 * @param String - Host of the IRC server
	 * @param String - Port number
	 * @return IrcConnection
	 */
	public IrcConnection connect(String host, String port) throws IrcFailedConnectException {
		return this.connect(host, Integer.parseInt(port.trim()));
	}

	/*
	 * Send a raw message (without the \r\n)
	 *
	 * @param String - Message/cmd to send
	 * @return IrcConnection
	 */
	public IrcConnection sendRaw(String msg) throws Exception {
		this.writer.write(msg + "\r\n");
		this.writer.flush();

		return this;
	}

	/*
	 * Receive a message
	 *
	 * @return String - Received message
	 */
	public String recv() throws IrcNullMessageException {
		try  {
			return this.reader.readLine();
		}
		catch(Exception e) {
			throw new IrcNullMessageException();
		}
	}

	/*
	 * Send message to a target
	 *
	 * @param String - target
	 * @param String - message (without \r\n)
	 * @return IrcConnection
	 */
	public IrcConnection sendTo(String target, String message) throws Exception {
		return this.sendRaw("PRIVMSG " + target + " :" + message);
	}

	/*
	 * Send CTCP message
	 *
	 * @param String - target
	 * @param String - message (without \r\n or soroudning \001)
	 * @return IrcConnection
	 */
	public IrcConnection sendCtcp(String target, String message) throws Exception {
		return this.sendRaw("PRIVMSG " + target + " :\001" + message + '\001');
	}

	/*
	 * Send CTCP message with a query as a param 
	 *
	 * @param String - target
	 * @param String - query 
	 * @param String - message (without \r\n or soroudning \001)
	 * @return IrcConnection
	 */
	public IrcConnection sendCtcp(String target, String query, String message) throws Exception {
		return this.sendCtcp(target, query + " " + message);
	}

	/*
	 * Close the socket
	 */
	public void close() {
		try {
			this.sock.close();
		}
		catch(Exception e) {
		}
	}
}
