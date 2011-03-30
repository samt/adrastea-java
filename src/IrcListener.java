/*
 * Adrastea IRC Bot
 * Version 2.0.0 (Java)
 *
 * (c) 2011 Sam Thompson <sam@websyntax.net>
 * License: http://www.opensource.org/licenses/mit-license.php The MIT License
 */

package adrastea;

public class IrcListener {
	public IrcListenerInterface o;

	public String location	= "*";
	public String type		= "*";
	public String event		= "*";

	/*
	 * Constructor - set the event type
	 *
	 * @param String parts
	 */
	public IrcListener(String e) {
		String[] parts = e.split("\\.");
		if (parts.length >= 1) {
			this.location = parts[0];
		}

		if (parts.length >= 2) {
			this.type = parts[1];
		}

		if (parts.length >= 3) {
			this.event = parts[2];
		}
	}

	public IrcListener handler(IrcListenerInterface obj) {
		this.o = obj;
		return this;
	}
}
