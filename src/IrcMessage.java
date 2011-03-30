/*
 * Adrastea IRC Bot
 * Version 2.0.0 (Java)
 *
 * (c) 2011 Sam Thompson <sam@websyntax.net>
 * License: http://www.opensource.org/licenses/mit-license.php The MIT License
 */

package adrastea;

public class IrcMessage {
	/*
	 * All the pieces to the message
	 */
	public String raw		= "";
	public String prefix	= "";
	public String type		= "";
	public String target	= "";
	public String message	= "";
	public String nick		= "";
	public String vhost		= "";
	public String ctcp		= "";
	public String ctcpArg	= "";

	/*
	 * The only constructor as anything else would be utterly pointless
	 *
	 * @param String - the raw message from IRC
	 */
	public IrcMessage(String msg) {
		this.raw = msg;
		int nextspace = msg.indexOf(' ');

		// Check for our prefix
		if (msg.charAt(0) == ':') {
			this.prefix = new String(msg.substring(1, nextspace)).trim();
			msg = msg.substring(nextspace + 1).trim();
			nextspace = msg.indexOf(" ");
		}

		// The type of message will exist.
		this.type = new String(msg.substring(0, nextspace)).trim();
		msg = msg.substring(nextspace + 1);

		// Extract the target and the message
		if (msg.indexOf(':') == -1) {
			// Command does not exist.
			this.target = msg.trim();
		}
		else if (msg.indexOf(':') > 0) {
			this.target = msg.substring(0, msg.indexOf(':')).trim();
			this.message = msg.substring(msg.indexOf(':') + 1);
		}
		else {
			this.message = msg.substring(1);
		}

		// Now we need to extract items such as the nickname and vhost
		if (this.prefix.length() > 0 && this.prefix.indexOf('!') > 0) {
			this.nick = this.prefix.substring(0, this.prefix.indexOf('!')).trim();
			this.vhost = this.prefix.substring(this.prefix.indexOf('@')).trim();
		}

		// Lastly, get any CTCP information
		if (this.message.startsWith("\001")) {
			this.ctcp = this.message.contains(" ") ? this.message.substring(1, this.message.indexOf(' ')).trim().toLowerCase() : this.message.substring(1).toLowerCase();;
			this.ctcpArg = (this.message.indexOf(' ') > 0) ? this.message.substring(this.message.indexOf(' ')).trim() : "";
			
			this.ctcp.toLowerCase();
		}
	}
}
