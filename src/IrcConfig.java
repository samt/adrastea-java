/*
 * Adrastea IRC Bot
 * Version 2.0.0 (Java)
 *
 * (c) 2011 Sam Thompson <sam@websyntax.net>
 * License: http://www.opensource.org/licenses/mit-license.php The MIT License
 */

package adrastea;

import java.io.*;
import java.util.Properties;

public class IrcConfig {
	public static String host;
	public static String port;
	public static String nick;
	public static String pass;
	public static String name;
	public static String user;
	public static String channels;
	public static String passphrase;

	public static void load(String filename) throws Exception {
		Properties cfg = new Properties();
		InputStream is = new FileInputStream(filename);
		cfg.load(is);

		IrcConfig.host = cfg.getProperty("host");
		IrcConfig.port = cfg.getProperty("port");
		IrcConfig.nick = cfg.getProperty("nick");
		IrcConfig.pass = cfg.getProperty("pass");
		IrcConfig.name = cfg.getProperty("name");
		IrcConfig.user = cfg.getProperty("user");

		IrcConfig.channels = cfg.getProperty("channels");
		IrcConfig.passphrase = cfg.getProperty("passphrase");
	}
}
