import java.sql.*;

public class createConncection {

  static Connection con;
  static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
  static final String DB_URL = "jdbc:mysql://localhost/dhruv";

  static final String USER = "root";
  static final String PASS = "*******";

  public static Connection getCon() {
    try {
      Class.forName(JDBC_DRIVER);
      con = DriverManager.getConnection(DB_URL, USER, PASS);
      System.out.println(con);
      if (con != null) {
        System.out.println("Conncetion Succeed");
      }
    } catch (Exception e) {
      System.out.println("Connction Falied");
    }
    return con;
  }
}
