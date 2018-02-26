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
  @Produces(MediaType.TEXT_PLAIN)
  public String loginUser(@PathParam("e_mail") String e_mail, @PathParam("pass") String pass) throws Exception{
    Connection c = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/orcl", "bond", "1234");
    String sql = "SELECT\n" +
      "    id,\n" +
      "    first_name,\n" +
      "    last_name,\n" +
      "    e_mail,\n" +
      "    pass,\n" +
      "    TO_CHAR(registration_day,'MM-DD-YYYY') AS registration_day,\n" +
      "    user_role,\n" +
      "    approval\n" +
      "FROM\n" +
      "    users\n" +
      "WHERE\n" +
      "    e_mail = '"+ e_mail +"'\n" +
      "    AND   pass = '"+ pass +"'";

    ResultSet rs = c.createStatement().executeQuery(sql);
    StringBuilder data = new StringBuilder();
    String result="";
    Boolean isFirst=true;
    while (rs.next()) {
      if (isFirst) {
        isFirst = false;
      }
      else {
        data.append(",");
      }
      data.append("{");
      data.append("id:"+rs.getString("ID")+",");
      data.append("first_name:\""+rs.getString("FIRST_NAME")+"\",");
      data.append("last_name:\""+rs.getString("LAST_NAME")+"\",");
      data.append("e_mail:\""+rs.getString("E_MAIL")+"\",");
      data.append("registration_day:\""+rs.getString("REGISTRATION_DAY")+"\",");
      data.append("user_role:\""+rs.getString("USER_ROLE")+"\",");
      data.append("approval:\""+rs.getString("APPROVAL")+"\"");
      data.append("}");
    }
    c.close();
    result = "{\"status\":200,\"data\":[" + data.toString() + "]}";
    System.out.println("result = " + result);
    return result;
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
  @Path("/register")
  @Consumes(MediaType.APPLICATION_JSON)
  public Response registerUser(User user) {
    String sql = "INSERT INTO users (first_name, last_name, e_mail, pass, registration_day, user_role)\n" +
      "   VALUES (?,?,?,?,to_date(?,'MM-DD-YYYY'),?)";
    db.update(sql,new Object[]{user.getFirstName(),user.getLastName(),user.geteMail(),user.getPass(),user.getRegistrationDay(),user.getUserRole()});
    String result = "Product created : " + user;
    return Response.status(200).entity(result).build();

  }

}

