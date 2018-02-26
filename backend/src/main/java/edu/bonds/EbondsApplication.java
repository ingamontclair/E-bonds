package edu.bonds;

import oracle.jdbc.pool.OracleDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("api")
public class EbondsApplication extends Application {

  public EbondsApplication() {
  }

  @Override
  public Set<Object> getSingletons() {
    HashSet<Object> set = new HashSet<Object>();
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

    set.add(new Users(db));
    set.add(new Bonds(db));
    return set;
  }
}

