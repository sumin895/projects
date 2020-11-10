import java.sql.*;
import java.util.ArrayList;


public class MyConnection
{
	private Connection conn;
    private ArrayList<Statement> statementList;
    
    public MyConnection(Connection conn) {
        this.conn = conn;
        statementList = new ArrayList<Statement>();
    }
    
    public void closeStatements() {
    	for (Statement stmt : statementList)
    		try {
                stmt.close();
            } catch(SQLException ex) {}
    	statementList.clear();
    }
    
    public void close() {
    	try {
    		closeStatements();
            conn.close();
        } catch(SQLException ex) {}
    }
    
    public Statement createStatement() throws SQLException {
        Statement stmt = conn.createStatement();
        statementList.add(stmt);
        return stmt;
    }
    
    public CallableStatement prepareCall(String sql) throws SQLException {
        CallableStatement cstmt = conn.prepareCall(sql);
        statementList.add(cstmt);
        return cstmt;
    }
    
    public PreparedStatement prepareStatement(String sql) throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement(sql);
        statementList.add(pstmt);
        return pstmt;
    }
}
