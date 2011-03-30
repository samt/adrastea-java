/*
 * Adrastea IRC Bot
 * Version 2.0.0 (Java)
 *
 * (c) 2011 Sam Thompson <sam@websyntax.net>
 * License: http://www.opensource.org/licenses/mit-license.php The MIT License
 */

package adrastea;

import java.util.ArrayList;

public class IrcEventHandler {
	private ArrayList events = new ArrayList();
	private ArrayList msgQueue = new ArrayList();

	public IrcEventHandler register(IrcListener lstnr) {
		events.add(lstnr);
		return this;
	}

	public IrcEventHandler run(IrcEvent e) {
		// give e.msg to the callback
		return this;
	}

	public String queue() {
		return null;
	}
}
