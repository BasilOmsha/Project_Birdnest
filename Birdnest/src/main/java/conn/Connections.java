package conn;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 * This is the connection class that handles database connection with a
 * connection pool. If there is a need to a new connection and there are
 * available connection in the pool, the application gets one. And if there are no
 * available connections in the pool, the application will create a new one.
 * The connection class uses the HikariConfig and the HikariDataSource classes.
 * @author Basil
 * version 0.3
 */
public class Connections {

	private static DataSource pool = null;

	public static Connection getConnection() throws SQLException {
		if (pool != null) {
			return pool.getConnection();
		}
		// The configuration object specifies behaviors for the connection pool.
		HikariConfig config = new HikariConfig();
		// Configure which instance and what database user to connect with.
		config.setDriverClassName(System.getProperty("drivername")); // see appengine-web.xml
		config.setJdbcUrl("jdbc:mysql://localhost:3306/" + System.getProperty("databasename")); // see appengine-web.xml
		config.setUsername(System.getProperty("localusername")); // see appengine-web.xml
		config.setPassword(System.getProperty("localpassword")); // see appengine-web.xml
		config.setMaximumPoolSize(10);
		config.setIdleTimeout(600000); // 10 minutes
		config.setConnectionTimeout(30000); // 30 seconds
		// Initialize the connection pool using the configuration object.
		pool = new HikariDataSource(config);

		return pool.getConnection();
	}

}
