package edu.bonds.model;

public class TradeOrder {
  int accountId;
  int quantity;
  int bondId;
  double bidPrice;
  String direction;

  public int getAccountId() {
    return accountId;
  }

  public void setAccountId(int accountId) {
    this.accountId = accountId;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public int getBondId() {
    return bondId;
  }

  public void setBondId(int bondId) {
    this.bondId = bondId;
  }

  public double getBidPrice() {
    return bidPrice;
  }

  public void setBidPrice(double bidPrice) {
    this.bidPrice = bidPrice;
  }

  public String getDirection() {
    return direction;
  }

  public void setDirection(String direction) {
    this.direction = direction;
  }

  @Override
  public String toString() {
    return "TradeOrder{" +
      "accountId=" + accountId +
      ", quantity=" + quantity +
      ", bondId=" + bondId +
      ", bidPrice=" + bidPrice +
      ", direction='" + direction + '\'' +
      '}';
  }
}
