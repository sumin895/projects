
package movies;

import com.oracle.DBConnection;

import javax.print.DocFlavor;
import java.util.ArrayList;
import java.sql.*;
import java.text.*;
import java.time.LocalDateTime;

public class MOVIES_DAO {
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        //PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList mvlist = null;

        try {
            String SQL = "SELECT * from MOVIES WHERE OPEN_DATE < SYSDATE";

            conn = DBConnection.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(SQL);



            while (rs.next()) {
                int mvcode = rs.getInt(1);
                String mvName = rs.getString(2);
                int runtime = rs.getInt(3);
                Date date = rs.getDate(4);
                String director = rs.getString(5);
                String actors = rs.getString(6);
                String summary = rs.getString(7);
                int ageRate = rs.getInt(8);
                int avgRate = rs.getInt(9);
                System.out.println(mvcode + " | " + mvName + " | " + runtime + " | " + date +
                        " | " + director + " | " + actors + " | " + summary +
                        " | " + ageRate + " | " + avgRate);
            }
        } catch (SQLException sqle) {
            System.out.println("SELECT문에서 예외 발생");
            sqle.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        }
    }
}
