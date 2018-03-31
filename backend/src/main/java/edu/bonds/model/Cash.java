package edu.bonds.model;

public class Cash {
  int id;
  int accountId;
  String cash_move_date;
  double amount;
  String currency;
  String comments;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getAccountId() {
    return accountId;
  }

  public void setAccountId(int accountId) {
    this.accountId = accountId;
  }

  public String getCash_move_date() {
    return cash_move_date;
  }

  public void setCash_move_date(String cash_move_date) {
    this.cash_move_date = cash_move_date;
  }

  public double getAmount() {
    return amount;
  }

  public void setAmount(double amount) {
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
