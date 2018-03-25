package edu.bonds.model;

public class Trade {
  int id;
  int account_id;
  String trade_date;
  int amount;
  int bond_id;
  int quantity;
  double bond_price;
  String direction;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getAccount_id() {
    return account_id;
  }

  public void setAccount_id(int account_id) {
    this.account_id = account_id;
  }

  public String getTrade_date() {
    return trade_date;
  }

  public void setTrade_date(String trade_date) {
    this.trade_date = trade_date;
  }

  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }

  public int getBond_id() {
    return bond_id;
  }

  public void setBond_id(int bond_id) {
    this.bond_id = bond_id;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public double getBond_price() {
    return bond_price;
  }

  public void setBond_price(double bond_price) {
    this.bond_price = bond_price;
  }

  public String getDirection() {
    return direction;
  }

  public void setDirection(String direction) {
    this.direction = direction;
  }
}
