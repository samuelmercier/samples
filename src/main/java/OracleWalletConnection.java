import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import oracle.jdbc.pool.OracleDataSource;

/**
 * this sample file demonstrates how to connect to an oracle database using an
 * oracle wallet. there is a dependency to the com.oracle.ojdbc:ojdbc8 artifact
 * (check pom.xml for more details).
 *
 * 1. configure a user. for example:
 *  - CREATE USER SAMPLEUSER IDENTIFIED BY UserSample1234;
 *  - GRANT CONNECT TO SAMPLEUSER;
 *
 * 2. from the oracle database page choose the TNS Name (in this sample
 * DB1_HIGH) and download and unzip the wallet to a folder (in this sample
 * 'WalletFolder').
 */
public class OracleWalletConnection {

	public static void main(String[] args) throws SQLException {
		OracleDataSource dataSource = new OracleDataSource();
		dataSource.setURL("jdbc:oracle:thin:SAMPLEUSER/UserSample1234@DB1_HIGH?TNS_ADMIN=WalletFolder&oracle.jdbc.fanEnabled=false");
		try (Connection connection = dataSource.getConnection()) {
			try (Statement statement = connection.createStatement()) {
				try (ResultSet resultSet = statement.executeQuery("SELECT 42 FROM SYS.DUAL a")) {
					resultSet.next();
					System.out.println(resultSet.getInt(1));
				}
			}
		}
	}

}
