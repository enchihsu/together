package fcu.sep.fcushop.model;

/**
 * Product.
 */

public class Product {

  private long id;

  private String name;

  private String imageUrl;

  private int price;

  private int quantity;

  private String description;

  /**
   * Product.
   */

  public Product() {
  }

  /**
   * Product.
   */

  public Product(long id, String name, String imageUrl, int price, int quantity, String description) {
    this.id = id;
    this.name = name;
    this.imageUrl = imageUrl;
    this.price = price;
    this.quantity = quantity;
    this.description = description;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
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

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}