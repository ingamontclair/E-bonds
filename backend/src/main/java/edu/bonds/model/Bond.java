package edu.bonds.model;

public class Bond {
  Integer id;
  String symbol;
  String bondName;
  String country;
  String issuer;
  String issueVolume;
  String currency;
  String issuePrice;
  String issueDate;
  String coupon;
  String maturityDate;
  String couponStartDate;
  String couponPaymentDate;
  String specialCouponType;
  String paymentsPerYear;
  String subProductType;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getSymbol() {
    return symbol;
  }

  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }

  public String getBondName() {
    return bondName;
  }

  public void setBondName(String bondName) {
    this.bondName = bondName;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getIssuer() {
    return issuer;
  }

  public void setIssuer(String issuer) {
    this.issuer = issuer;
  }

  public String getIssueVolume() {
    return issueVolume;
  }

  public void setIssueVolume(String issueVolume) {
    this.issueVolume = issueVolume;
  }

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public String getIssuePrice() {
    return issuePrice;
  }

  public void setIssuePrice(String issuePrice) {
    this.issuePrice = issuePrice;
  }

  public String getIssueDate() {
    return issueDate;
  }

  public void setIssueDate(String issueDate) {
    this.issueDate = issueDate;
  }

  public String getCoupon() {
    return coupon;
  }

  public void setCoupon(String coupon) {
    this.coupon = coupon;
  }

  public String getMaturityDate() {
    return maturityDate;
  }

  public void setMaturityDate(String maturityDate) {
    this.maturityDate = maturityDate;
  }

  public String getCouponStartDate() {
    return couponStartDate;
  }

  public void setCouponStartDate(String couponStartDate) {
    this.couponStartDate = couponStartDate;
  }

  public String getCouponPaymentDate() {
    return couponPaymentDate;
  }

  public void setCouponPaymentDate(String couponPaymentDate) {
    this.couponPaymentDate = couponPaymentDate;
  }

  public String getSpecialCouponType() {
    return specialCouponType;
  }

  public void setSpecialCouponType(String specialCouponType) {
    this.specialCouponType = specialCouponType;
  }

  public String getPaymentsPerYear() {
    return paymentsPerYear;
  }

  public void setPaymentsPerYear(String paymentsPerYear) {
    this.paymentsPerYear = paymentsPerYear;
  }

  public String getSubProductType() {
    return subProductType;
  }

  public void setSubProductType(String subProductType) {
    this.subProductType = subProductType;
  }
}
