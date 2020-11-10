import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Queue;


public class ConnectionPool {
	private static final int INIT_SIZE = 3;
	private static final String USER_ID = "user1";
	private static final String USER_PW = "1234";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	
	private Queue<MyConnection> connQueue;
	
	public ConnectionPool()
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			for (int i = 0; i < INIT_SIZE; i++)
			{
				MyConnection conn = new MyConnection(DriverManager.getConnection(URL, USER_ID, USER_PW));
				connQueue.add(conn);
			}
		} catch (ClassNotFoundException cnfe) {
			System.out.println("DB 드라이버 로딩 실패 :" + cnfe.toString());
		} catch (SQLException sqle) {
			System.out.println("DB 접속실패 : " + sqle.toString());
		} catch (Exception e) {
			System.out.println("Unkonwn error");
			e.printStackTrace();
		}
	}
	
	public MyConnection getConnection() {
		if (!connQueue.isEmpty())
			return connQueue.poll();
		
		try {
//			Class.forName("oracle.jdbc.driver.OracleDriver");
			return new MyConnection(DriverManager.getConnection(URL, USER_ID, USER_PW));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void closeAll()
	{
		while (!connQueue.isEmpty())
			connQueue.poll().close();
	}
	
	public void freeConnection(MyConnection conn) {
		conn.closeStatements();
		connQueue.add(conn);
	}

}
