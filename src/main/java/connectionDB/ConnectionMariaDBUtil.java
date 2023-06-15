package connectionDB;


import java.sql.Connection;

import javax.sql.DataSource;

import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import com.member.mapper.MemberMapper;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import lombok.extern.log4j.Log4j2;

@Log4j2
public enum ConnectionMariaDBUtil {
	INSTANCE;
	
	private HikariDataSource ds;
	
	private ConnectionMariaDBUtil() {
		
		HikariConfig config = new HikariConfig();
		
		config.setDriverClassName("org.mariadb.jdbc.Driver");
		config.setJdbcUrl("jdbc:mariadb://localhost:3306/mydb");
		config.setUsername("myUser");
		config.setPassword("1234");
		
		config.addDataSourceProperty("cachePrepStmts", true);
		config.addDataSourceProperty("preStmtCachesize", "250");
		config.addDataSourceProperty("prepStmtCacheSqlLimit", true); 
		
		ds = new HikariDataSource(config);
		
	}
	
	
	public Connection getConnection() throws Exception{
		return ds.getConnection();
	}
	
	
	
	
	// MyBatis 설정
	public SqlSessionFactory getSqlSessionFactory() {
		DataSource hDs = ds; 
		log.info("hikariDataSource: " + hDs);
		
		TransactionFactory transactionFactory = new JdbcTransactionFactory();
		Environment environment = new Environment("development", transactionFactory, hDs);
		Configuration configuration = new Configuration(environment);
		
		// xml sql
		configuration.addMapper(MemberMapper.class);	// MemberMapper.class
		
		return new SqlSessionFactoryBuilder().build(configuration);
	}
	
	
	
}
