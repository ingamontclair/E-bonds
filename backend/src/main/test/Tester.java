
import edu.bonds.Bonds;
import edu.bonds.Users;
import edu.bonds.model.User;
import oracle.jdbc.pool.OracleDataSource;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

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

  @Test
  public void testUsers () throws Exception {
/*    Users u = new Users();
    System.out.println("u = " + u.users());*/
  }

  @Test
  public void testBonds () throws Exception {
/*    Bonds b = new Bonds();
    System.out.println("b = " + b.bonds());*/
  }

  @Test
  public void testLogin () throws Exception {
    //Users u = new Users();
    //System.out.println("u = " + u.loginUser("inga@gmail.com","1234"));
  }

  @Test
  public void testReg () throws Exception {
    User u = new User();
    u.setFirstName("test");
    u.setLastName("test last");
    u.seteMail("test@gmail.com");
    u.setPass("1234");
    u.setUserRole("customer");
    u.setRegistrationDay("02-25-2018");
    u.setApproval("no");

    OracleDataSource ds;
    try {
      ds = new OracleDataSource();
      ds.setURL("jdbc:oracle:thin:@localhost:1521/orcl");
      ds.setUser("bond");
      ds.setPassword("1234");
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

    JdbcTemplate db = new JdbcTemplate(ds);
    Users users = new Users(db);
    users.registerUser(u);

    System.out.println("u = inserted");
  }
}
