package edu.bonds;

import edu.bonds.model.Data;
import edu.bonds.model.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Path("users")
public class Users {
  JdbcTemplate db;

  public Users(JdbcTemplate db) {
    this.db = db;
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response users() throws Exception{
    String sql = "SELECT\n" +
      "    id,\n" +
      "    first_name as firstName,\n" +
      "    last_name as lastName,\n" +
      "    e_mail as eMail,\n" +
      "    pass,\n" +
      "    TO_CHAR(registration_day,'MM-DD-YYYY') AS registrationDay,\n" +
      //"    registration_day AS registrationDay,\n" +
      "    user_role as userRole,\n" +
      "    approval\n" +
      "FROM\n" +
      "users";
    List <User> result = db.query(sql,new BeanPropertyRowMapper<User>(User.class));
    return Response.status(200).entity(new Data(result)).build();
  }

  @Path("{e_mail}/{pass}")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response loginUser(@PathParam("e_mail") String e_mail, @PathParam("pass") String pass) throws Exception{
    Connection c = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/orcl", "bond", "1234");
    String sql = "SELECT\n" +
      "    u.id,\n" +
      "    u.first_name as firstName,\n" +
      "    u.last_name as lastName,\n" +
      "    u.e_mail as eMail,\n" +
      "    u.pass,\n" +
      "    TO_CHAR(registration_day,'MM-DD-YYYY') AS registration_day,\n" +
      "    u.user_role as userRole,\n" +
      "    u.approval,\n" +
      "    a.ID as accountId \n"+
      "FROM\n" +
      "    users u\n" +
      "left join ACCOUNTS a on u.id = a.USER_ID\n" +
      "WHERE\n" +
      "    e_mail = '"+ e_mail +"'\n" +
      "    AND   pass = '"+ pass +"'";
    List <User> result = db.query(sql,new BeanPropertyRowMapper<User>(User.class));
    return Response.status(200).entity(new Data(result)).build();

  }

  @Path("delete/{id}")
  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public Response deleteUser(@PathParam("id") Integer id) throws Exception{
    //Connection c = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/orcl", "bond", "1234");
    String sql = "delete from users where id=" + id;
    db.execute(sql);
    //c.close();
    System.out.println("Customer deleted");
    return Response.status(200).build();
  }



  @Path("test")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response test() throws Exception{
    User u = new User();
    u.setId(3);
    u.setFirstName("Liza");
    ArrayList data = new ArrayList();
    data.add(u);
    return Response.status(200).entity(new Data(data)).build();
  }

  @POST
  @Path("register")
  //@Consumes(MediaType.APPLICATION_JSON)
  public Response registerUser(User user) {
    String sql = "INSERT INTO users (first_name, last_name, e_mail, pass, registration_day, user_role)\n" +
      "   VALUES (?,?,?,?,to_date(?,'MM-DD-YYYY'),?)";
    db.update(sql,user.getFirstName(),user.getLastName(),user.geteMail(),user.getPass(),user.getRegistrationDay(),user.getUserRole());

// get an ID for a new created user
    sql = "SELECT\n" +
      "    u.id\n" +
      "FROM\n" +
      "    users u\n" +
      "WHERE\n" +
      "    e_mail = '"+ user.geteMail() +"'\n" +
      "    AND   pass = '"+ user.getPass() +"'";
    List <User> newUser = db.query(sql,new BeanPropertyRowMapper<User>(User.class));
    System.out.println("user id "+ newUser.get(0).getId());
    sql = "INSERT INTO accounts (user_id) VALUES (?)";
    db.update(sql,newUser.get(0).getId());
    String res = "User created : " + user;
    System.out.println("res = " + user.getId());
    return Response.status(200).entity(res).build();

  }

  @POST
  @Path("update")
  //@Consumes(MediaType.APPLICATION_JSON)
  public Response updateUser(User user) {
    String sql = "update USERS set\n" +
      "FIRST_NAME = ?\n" +
      ",LAST_NAME = ?\n" +
      ",E_MAIL = ?\n" +
      ",PASS = ?\n" +
      "where ID = ?";
    db.update(sql, user.getFirstName(),user.getLastName(),user.geteMail(),user.getPass(),user.getId());
    String result = "User updated : " + user;
    return Response.status(200).entity(result).build();

  }

}

