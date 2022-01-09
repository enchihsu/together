package fcu.sep.fcushop.model;

/**
 * Order.
 */

public class Order {

  private String id;

  private String account;

  private String book;

  private String imageUrl;

  private String price;

  private String amount;

  /**
   * 点对 (x,y) 的水平和垂直距离.
   */

  public Order() {
  }

  /**
   * 点对 (x,y) 的水平和垂直距离.
   */

  public Order(String id, String account, String book, String imageUrl, String price, String amount) {
    this.id = id;
    this.account = account;
    this.book = book;
    this.imageUrl = imageUrl;
    this.price = price;
    this.amount = amount;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getAccount() {
    return account;
  }

  public void setAccount(String account) {
    this.account = account;
  }

  public String getBook() {
    return book;
  }

  public void setBook(String book) {
    this.book = book;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
    this.price = price;
  }

  public String getAmount() {
    return amount;
  }

  public void setAmount(String amount) {
    this.amount = amount;
  }
}