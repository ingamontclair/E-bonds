package edu.bonds;
import edu.bonds.model.*;
import javafx.geometry.Pos;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.List;

@Path("bonds")
public class Bonds {
  JdbcTemplate db;

  public Bonds(JdbcTemplate db) {
    this.db = db;
  }


  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public String bonds() throws Exception{
    Connection c = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/orcl", "bond", "1234");
    String sql = "SELECT\n" +
      "    id,\n" +
      "    isin,\n" +
      "    cusip,\n" +
      "    bond_name,\n" +
      "    country,\n" +
      "    issuer,\n" +
      "    issue_volume,\n" +
      "    currency,\n" +
      "    issue_price,\n" +
      "    TO_CHAR(issue_date,'MM-DD-YYYY') AS issue_date,\n" +
      "    coupon,\n" +
      "    special_coupon_type,\n" +
      "    TO_CHAR(maturity_date,'MM-DD-YYYY') AS maturity_date,\n" +
      "    TO_CHAR(coupon_payment_date,'MM-DD-YYYY') AS coupon_payment_date,\n" +
      "    payments_per_year,\n" +
      "    TO_CHAR(coupon_start_date,'MM-DD-YYYY') AS coupon_start_date,\n" +
      "    sub_product_type\n" +
      "FROM\n" +
      "    bonds";
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
      data.append("isin:\""+rs.getString("ISIN")+"\",");
      data.append("cusip:\""+rs.getString("CUSIP")+"\",");
      data.append("bond_name:\""+rs.getString("BOND_NAME")+"\",");
      data.append("country:\""+rs.getString("COUNTRY")+"\",");
      data.append("issuer:\""+rs.getString("ISSUER")+"\",");
      data.append("issue_volume:\""+rs.getString("ISSUE_VOLUME")+"\",");
      data.append("currency:\""+rs.getString("CURRENCY")+"\",");
      data.append("issue_price:\""+rs.getString("ISSUE_PRICE")+"\",");
      data.append("issue_date:\""+rs.getString("ISSUE_DATE")+"\",");
      data.append("coupon:\""+rs.getString("COUPON")+"\",");
      data.append("special_coupon_type:\""+rs.getString("SPECIAL_COUPON_TYPE")+"\",");
      data.append("maturity_date:\""+rs.getString("MATURITY_DATE")+"\",");
      data.append("coupon_payment_date:\""+rs.getString("COUPON_PAYMENT_DATE")+"\",");
      data.append("payments_per_year:\""+rs.getString("PAYMENTS_PER_YEAR")+"\",");
      data.append("coupon_start_date:\""+rs.getString("COUPON_START_DATE")+"\",");
      data.append("sub_product_type:\""+rs.getString("SUB_PRODUCT_TYPE")+"\"");
      data.append("}");
    }
    c.close();
    result = "{\"status\":200,\"data\":[" + data.toString() + "]}";
    System.out.println("result = " + result);
    return result;
  }

  @Path("positions/{account_id}")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response getPositions(@PathParam("account_id") Integer account_id) throws Exception {
    //Connection c = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/orcl", "bond", "1234");
    String sql = "select b.id as bondId, nvl (b.isin, b.cusip) as symbol, b.currency, p.price, sum(t.quantity*\n" +
      "case when t.direction = 'sell' then -1\n" +
      "else 1\n" +
      "end\n" +
      ") as quantity\n" +
      "from  trades t\n" +
      "join bonds b on t.bond_id = b.id\n" +
      "join prices p on p.bond_id = b.id\n" +
      "\n" +
      "where t.account_id = ? \n" +
      "and p.price_date = (select max (price_date) from prices p2 where p2.bond_id = b.id)\n" +
      "group by b.id, nvl (b.isin, b.cusip), b.currency, p.price";
    List<Position> result = db.query(sql, new BeanPropertyRowMapper<Position>(Position.class), account_id);
    return Response.status(200).entity(new Data(result)).build();
  }

  @Path("positions")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response getAllBonds() throws Exception {
    //Connection c = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/orcl", "bond", "1234");
    String sql = "select b.id as bondId, nvl (b.isin, b.cusip) as symbol, b.currency, p.price, sum(t.quantity*\n" +
      "case when t.direction = 'sell' then -1\n" +
      "else 1\n" +
      "end\n" +
      ") as quantity\n" +
      "from  trades t\n" +
      "join bonds b on t.bond_id = b.id\n" +
      "join prices p on p.bond_id = b.id\n" +
      "\n" +
      "where  \n" +
      "p.price_date = (select max (price_date) from prices p2 where p2.bond_id = b.id)\n" +
      "group by b.id, nvl (b.isin, b.cusip), b.currency, p.price";
    List<Position> result = db.query(sql, new BeanPropertyRowMapper<Position>(Position.class));
    return Response.status(200).entity(new Data(result)).build();
  }

  @Path("positions/{account_id}/{b_id}")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response getBondById(@PathParam("account_id") Integer account_id, @PathParam("b_id") Integer b_id) throws Exception {
    //Connection c = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/orcl", "bond", "1234");
    System.out.println("account_id = " + account_id);

    List<Position> result = getPositions(account_id, b_id);
    return Response.status(200).entity(new Data(result)).build();
  }

  @Path("{id}")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response getBondInfo(@PathParam("id") Integer id) throws Exception {
    //Connection c = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/orcl", "bond", "1234");
    String sql = "SELECT\n" +
      "    b.id,\n" +
      "    nvl(isin,cusip) AS symbol,\n" +
      "    bond_name,\n" +
      "    country,\n" +
      "    issuer,\n" +
      "    issue_volume,\n" +
      "    b.currency,\n" +
      "    issue_price,\n" +
      "    TO_CHAR(issue_date,'MM-DD-YYYY') AS issue_date,\n" +
      "    coupon,\n" +
      "    TO_CHAR(maturity_date,'MM-DD-YYYY') AS maturity_date,\n" +
      "    TO_CHAR(coupon_start_date,'MM-DD-YYYY') AS coupon_start_date,\n" +
      "    TO_CHAR(coupon_payment_date,'MM-DD-YYYY') AS coupon_payment_date,\n" +
      "    special_coupon_type,\n" +
      "    payments_per_year,\n" +
      "    sub_product_type,\n" +
      "    p.price AS currentPrice\n"+

      "FROM\n" +
      "    bonds b\n" +
      "join prices p on p.bond_id = b.id\n" +
      "WHERE\n" +
      "p.price_date = (select max (price_date) from prices p2 where p2.bond_id = b.id)\n" +
      "and\n" +
      "    b.id = ?";
    List<Bond> result = db.query(sql, new BeanPropertyRowMapper<Bond>(Bond.class), id);
    return Response.status(200).entity(new Data(result)).build();
  }

  @POST
  @Path("sellbonds")
  //@Consumes(MediaType.APPLICATION_JSON)
  public Response sellBond(TradeOrder tradeOrder) {
    List<Position> tmp = getPositions(tradeOrder.getAccountId(), tradeOrder.getBondId());

    if ( tmp.isEmpty()){
      throw new RuntimeException("No positions returned");
    }
    Position position = tmp.get(0);
    //check quantity
    if (tradeOrder.getQuantity() > Integer.parseInt(position.getQuantity())){
      throw new RuntimeException("Not enough bonds available");
    }
    //check the price
    if (tradeOrder.getBidPrice() < Double.parseDouble(position.getPrice())){
      String message = "Bond can't be sold at this price now, saving a back order";
      System.out.println(message);
      return Response.status(200).entity(message).build();
    }
    double amount = Double.parseDouble(position.getPrice()) * tradeOrder.getQuantity();
    //insert into trades
    String sqlTrade = "insert into TRADES(\n" +
      "BOND_ID\n" +
      ",ACCOUNT_ID\n" +
      ",AMOUNT\n" +
      ",QUANTITY\n" +
      ",DIRECTION\n" +
      ",BOND_PRICE\n" +
      ",TRADE_DATE\n" +
      ") values (\n" +
      "?\n" +
      ",?\n" +
      ",?\n" +
      ",?\n" +
      ",'sell'\n" +
      ",?\n" +
      ",sysdate\n" +
      ")";
    db.update(sqlTrade,tradeOrder.getBondId(),tradeOrder.getAccountId(), amount, tradeOrder.getQuantity(), position.getPrice());
    //insert into cash_movements

    String sql = "insert into CASH_MOVEMENTS(\n" +
      "CURRENCY\n" +
      ",ACCOUNT_ID\n" +
      ",AMOUNT\n" +
      ",COMMENTS\n" +
      ",CASH_MOVE_DATE\n" +
      ") values (\n" +
      "'USD'\n" +
      ",?\n" +
      ",?\n" +
      ",'Bond "+position.getSymbol()+" Sold'\n" +
      ",sysdate\n" +
      ")";
    db.update(sql,tradeOrder.getAccountId(), amount);
    String msg = "Sold";
    return Response.status(200).entity(msg).build();
  }


  @POST
  @Path("buybonds")
  //@Consumes(MediaType.APPLICATION_JSON)
  public Response buyBond(TradeOrder tradeOrder) {
    List<Position> tmp = getPositions(tradeOrder.getAccountId(), tradeOrder.getBondId());

    String sqlBalance = "select sum(amount) from cash_movements where account_id = ?";
    Double result = db.queryForObject(sqlBalance, Double.class, tradeOrder.getAccountId());


    if ( tmp.isEmpty()){
      throw new RuntimeException("No positions returned");
    }
    Position position = tmp.get(0);
    //check quantity
/*    if (tradeOrder.getQuantity() > Integer.parseInt(position.getQuantity())){
      throw new RuntimeException("Not enough bonds available");
    }*/
    //check the price
    if (tradeOrder.getBidPrice() >= Double.parseDouble(position.getPrice())){
      String message = "Bond can't be buy at this price now, saving a back order";
      System.out.println(message);
      return Response.status(200).entity(message).build();
    }
    double amount = Double.parseDouble(position.getPrice()) * tradeOrder.getQuantity();

    if (amount > result) {
      throw new RuntimeException("Not enough money available");
    }
    else {
      amount = amount * (-1);
    }
    //insert into trades
    String sqlTrade = "insert into TRADES(\n" +
      "BOND_ID\n" +
      ",ACCOUNT_ID\n" +
      ",AMOUNT\n" +
      ",QUANTITY\n" +
      ",DIRECTION\n" +
      ",BOND_PRICE\n" +
      ",TRADE_DATE\n" +
      ") values (\n" +
      "?\n" +
      ",?\n" +
      ",?\n" +
      ",?\n" +
      ",'buy'\n" +
      ",?\n" +
      ",sysdate\n" +
      ")";
    db.update(sqlTrade,tradeOrder.getBondId(),tradeOrder.getAccountId(), amount, tradeOrder.getQuantity(), position.getPrice());
    //insert into cash_movements

    String sql = "insert into CASH_MOVEMENTS(\n" +
      "CURRENCY\n" +
      ",ACCOUNT_ID\n" +
      ",AMOUNT\n" +
      ",COMMENTS\n" +
      ",CASH_MOVE_DATE\n" +
      ") values (\n" +
      "'USD'\n" +
      ",?\n" +
      ",?\n" +
      ",'Bond "+position.getSymbol()+" Bought'\n" +
      ",sysdate\n" +
      ")";
    db.update(sql,tradeOrder.getAccountId(), amount);
    String msg = "Bought";
    return Response.status(200).entity(msg).build();
  }


  @Path("balance/{account_id}")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response getBalance(@PathParam("account_id") Integer account_id) throws Exception {
    //Connection c = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/orcl", "bond", "1234");
    String sql = "select sum(amount) from cash_movements where account_id = ?";
    Double result = db.queryForObject(sql, Double.class, account_id);
    return Response.status(200).entity(result).build();
  }
  //-----------------------------------------
  // DataBase queries
  //-----------------------------------------

  private List<Position> getPositions(int accountId, int bondId){
    String sql = "select b.id as bondId, nvl (b.isin, b.cusip) as symbol, b.currency, p.price, sum(t.quantity*\n" +
      "case when t.direction = 'sell' then -1\n" +
      "else 1\n" +
      "end\n" +
      ") as quantity\n" +
      "from  trades t\n" +
      "join bonds b on t.bond_id = b.id\n" +
      "join prices p on p.bond_id = b.id\n" +
      "\n" +
      "where t.account_id = ? and b.id = ?\n" +
      "and p.price_date = (select max (price_date) from prices p2 where p2.bond_id = b.id)\n" +
      "group by b.id, nvl (b.isin, b.cusip), b.currency, p.price";
    List<Position> result = db.query(sql, new BeanPropertyRowMapper<Position>(Position.class), accountId, bondId);
    return result;
  }
}

