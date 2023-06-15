package connectionTest;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class ConnectionTest {

	// Oracle
	@Test
	public void testOracleHikariCPConnection() throws Exception {
		
		HikariConfig config = new HikariConfig();
		
		config.setDriverClassName("oracle.jdbc.OracleDriver");
		config.setJdbcUrl("jdbc:oracle:thin:@localhost:1521/xe");
		config.setUsername("user2");
		config.setPassword("1234");
		
		HikariDataSource ds = new HikariDataSource(config);
		Connection conn = ds.getConnection();
		
		Assertions.assertNotNull(conn);
		
		conn.close();
		
		System.out.println("연결성공");
		
		
		
		
	}
	
	
	// MariaDB
	@Test
	public void testConnection() throws Exception {
		Class.forName("org.mariadb.jdbc.Driver");
			
		Connection conn = DriverManager.getConnection(
					"jdbc:mariadb://localhost:3306/mydb", 
					"myUser", 
					"1234");
			
		Assertions.assertNotNull(conn);
			
		conn.close();
		
		System.out.println("MariaDB 연결성공");	
		
		}
	
	
	
	
	// MariaDB
	@Test
	public void testHikariCPConnection() throws Exception {
			
		HikariConfig config = new HikariConfig();
			
		config.setDriverClassName("org.mariadb.jdbc.Driver");
		config.setJdbcUrl("jdbc:mariadb://localhost:3306/mydb");
		config.setUsername("myUser");
		config.setPassword("1234");
			
		HikariDataSource ds = new HikariDataSource(config);
		Connection conn = ds.getConnection();
			
		Assertions.assertNotNull(conn);
			
		conn.close();
		
		}
	
	
}
