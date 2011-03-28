/*
 * Adrastea IRC Bot
 * Version 2.0.0 (Java)
 *
 * (c) 2011 Sam Thompson <sam@websyntax.net>
 * License: http://www.opensource.org/licenses/mit-license.php The MIT License
 */

package adrastea;

import java.util.*; 

public class IrcEvent {
	private ArrayList resp;
	private String cmd = "";
	private int cntr = -1;

	/*
	 * Constructor
	 */
	public IrcEvent() {
		this.resp = new ArrayList(0);
	}

	/*
	 * Constructor and Shortcut for matchCmd
	 *
	 * @param String command
	 */
	public IrcEvent(String cmd) {
		this.resp = new ArrayList(0);
		this.cmd = cmd;
	}

	/*
	 * Set a command to match to
	 *
	 * @param String command
	 * @return IrcEvent
	 */
	public IrcEvent matchCmd(String cmd) {
		this.cmd = cmd;
		return this;
	}

	/*
	 * Check if a command equals another
	 *
	 * @param String command to compare
	 * @return boolean
	 */
	public boolean cmdEquals(String cmp) {
		return (this.cmd.length() > 0) ? this.cmd.equals(cmp) : true;
	}

	/*
	 * Get the next responce
	 *
	 * @return String to get the responce, null if none left
	 */
	public String getNextResponse() {
		this.cntr++;

		if (this.resp.size() < this.cntr) {
			return (String) this.resp.get(this.cntr);
		}
		else {
			this.cntr = -1;
			return null;
		}
	}
}
