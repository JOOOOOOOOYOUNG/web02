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
import teamProject.util.ProjectMemberMapper;

@Log4j2
public enum ConnectionOracleUtil {
	INSTANCE;
	
	private HikariDataSource ds;
	
	private ConnectionOracleUtil() {
		
		HikariConfig config = new HikariConfig();
		
		config.setDriverClassName("oracle.jdbc.OracleDriver");
		config.setJdbcUrl("jdbc:oracle:thin:@localhost:1521/xe");
		config.setUsername("user2");
		config.setPassword("1234");
		
		config.addDataSourceProperty("cachePrepStmts", true);
		config.addDataSourceProperty("preStmtCachesize", "250");
		config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048"); 
		
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
		//configuration.addMapper(ProjectMemberMapper.class);
		
		log.info("SqlSessionFactory: " + new SqlSessionFactoryBuilder().build(configuration));
		
		return new SqlSessionFactoryBuilder().build(configuration);
	}
	
}
