package edu.bonds.model;

public class Position {
  Integer bondId;
  String symbol;
  String currency;
  String price;
  String quantity;

  public Integer getBondId() {
    return bondId;
  }

  public void setBondId(Integer bondId) {
    this.bondId = bondId;
  }

  public String getSymbol() {
    return symbol;
  }

  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
    this.price = price;
  }

  public String getQuantity() {
    return quantity;
  }

  public void setQuantity(String quantity) {
    this.quantity = quantity;
  }
}
