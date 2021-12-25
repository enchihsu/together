package fcu.sep.fcushop.model;

public class Like {
  private long id;

  private String account;

  private int quantity;

  private int price;

  private String name;

  private String imageUrl;

  public Like() {
  }

  public Like(long id, String account, int quantity, int price, String name, String imageUrl) {
    this.id = id;
    this.account = account;
    this.quantity = quantity;
    this.price = price;
    this.name = name;
    this.imageUrl = imageUrl;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getAccount() {
    return account;
  }

  public void setAccount(String account) {
    this.account = account;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }
}
