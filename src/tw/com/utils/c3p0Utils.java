package tw.com.utils;
import java.sql.Connection;
import java.sql.SQLException;
import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 封装c3p0 将c3p0中的连接返回出去供使用
 * @author tangwei
 *
 */
public class c3p0Utils {
	private static ComboPooledDataSource dataSource;
	static {
		dataSource = new ComboPooledDataSource("ztzPin");//
//		dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/ztzPin");
//		dataSource.setUser("root");
//		dataSource.setPassword("root");
//		dataSource.setInitialPoolSize(10);
//		dataSource.setMaxPoolSize(100);
	}
	
	public static Connection getConnection() {
		Connection conn = null;
		try {
			System.out.println(dataSource);
			conn = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void closeConnect(Connection conn) {
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
