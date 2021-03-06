/*
 * Adrastea IRC Bot
 * Version 2.0.0 (Java)
 *
 * (c) 2011 Sam Thompson <sam@websyntax.net>
 * License: http://www.opensource.org/licenses/mit-license.php The MIT License
 */

package adrastea;

public class IrcEvent {
	/*
	 * Breaking up the event into 3 pieces
	 */
	public String location	= "";
	public String type		= "";
	public String event		= "";

	public IrcMessage msg;

	/*
	 * Event
	 *
	 * @param IrcMessage message
	 */
	public IrcEvent(IrcMessage m) {
		this.msg = m;
	
		if (m.type.equals("PRIVMSG")) {
			this.location = (m.target.startsWith("#")) ? "channel" : "user";
			this.type = (m.ctcp.length() > 0) ? "ctcp" : "message";
			
			if(m.ctcp.length() > 0) {
				this.event = m.ctcp;
			}
		}
		else if (m.type.equals("JOIN") || m.type.equals("PART") || m.type.equals("KICK") || m.type.equals("NICK") || m.type.equals("QUIT") || m.type.equals("INVITE")) {
			this.location = "channel";
			this.type = "event";
			this.event = m.type.toLowerCase();
		}
		else {
			this.location = "system";
			this.type = m.type.toLowerCase();
		}
	}
}
