
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Tester {

  @Test
  public void testHello () {
    System.out.println("true = " + true);
  }

  @Test
  public void oracleTest() throws SQLException {

    Connection c = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/orcl", "bond", "1234");
    ResultSet rs = c.createStatement().executeQuery("select firstname from users");

    while (rs.next()) {
      String name = rs.getString(1);
      System.out.println("name = " + name);
    }

    c.close();
  }
}
