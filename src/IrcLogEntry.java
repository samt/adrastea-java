/*
 * Adrastea IRC Bot
 * Version 2.0.0 (Java)
 *
 * (c) 2011 Sam Thompson <sam@websyntax.net>
 * License: http://www.opensource.org/licenses/mit-license.php The MIT License
 */

package adrastea;

public class IrcLogEntry {
	public String channel;
	public String nick;
	public String type;
	public String message;

	public IrcLogEntry(String channel, String nick, String type, String message) {
		this.channel = channel;
		this.nick = nick;
		this.type = type.toLowerCase();
		this.message = message;
	}
}
