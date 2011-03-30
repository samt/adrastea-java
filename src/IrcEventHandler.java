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
	private ArrayList listeners = new ArrayList();
	private ArrayList msgQueue = new ArrayList();
	private int cntr = -1;

	public IrcEventHandler register(IrcListener lstnr) {
		listeners.add(lstnr);
		return this;
	}

	public IrcEventHandler run(IrcEvent e) {
		int size = this.listeners.size();
		IrcListener lstnr;
		String queueBuffer[];

		for (int i = 0; i < size; i++) {
			lstnr = (IrcListener) this.listeners.get(i);

			if( (e.location.equals(lstnr.location) || lstnr.location.equals("*")) &&
				(e.type.equals(lstnr.type) || lstnr.type.equals("*")) /*&& 
				(e.event.equals(lstnr.event) || lstnr.event.equals("*"))*/
			)
			{
				try {
					queueBuffer = lstnr.o.run(e.msg);

					for(int j = 0; j < queueBuffer.length; j++) {
						msgQueue.add(queueBuffer[j]);
					}
				}
				catch(NullPointerException exptn) {
				}
			}
		}

		return this;
	}

	/*
	 * Iterate the queue
	 *
	 * @return String - String if good, null 
	 */
	public String queue() {
		this.cntr++;
		return (msgQueue.size() > this.cntr) ? (String) msgQueue.get(this.cntr) : null;
	}
}
