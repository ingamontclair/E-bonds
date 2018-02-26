package edu.bonds;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

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
}

