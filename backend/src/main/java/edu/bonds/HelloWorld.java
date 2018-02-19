package edu.bonds;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

@Path("users")
public class HelloWorld {
  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public String hello() throws Exception{

    //return "{\"status\":200,\"data\":[{\"_id\":\"5a89f0705fc283fdf75f01ca\",\"type\":\"MY HDDDDD\",\"pricedate\":\"12/18/2017\",\"price\":\"964\",\"description\":\"NEW YORK HDD INDEX F18\",\"location\":\"New York\",\"monthlycode\":\"F\",\"year\":\"2018\"}],\"message\":null}";

    Connection c = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/orcl", "bond", "1234");
    ResultSet rs = c.createStatement().executeQuery("select firstname from users");

    String name="";
    while (rs.next()) {

      name = rs.getString(1);
      System.out.println("name = " + name);
    }

    c.close();
    return "{\"status\":200,\"hhh\":\"message\",\"data\":[{\"name\":\"" + name + "\"}]}";
  }
}

