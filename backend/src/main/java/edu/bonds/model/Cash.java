package edu.bonds.model;

public class Cash {
  int id;
  int account_id;
  String cash_move_date;
  int amount;
  String currency;
  String comments;

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

  public String getCash_move_date() {
    return cash_move_date;
  }

  public void setCash_move_date(String cash_move_date) {
    this.cash_move_date = cash_move_date;
  }

  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public String getComments() {
    return comments;
  }

  public void setComments(String comments) {
    this.comments = comments;
  }
}
