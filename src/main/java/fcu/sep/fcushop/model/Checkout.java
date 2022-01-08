package fcu.sep.fcushop.model;

public class Checkout {
  private long id;
  private int cid;
  private String invoice;
  private String delivery;
  private String address;
  private String payment;
  private String account;
  private String book;
  private String amount;
  public Checkout() {
  }
  public Checkout(Long id,String invoice,String delivery,String address,String payment,Integer cid,String account,String book,String amount) {
    this.id =id;
    this.invoice = invoice;
    this.delivery = delivery;
    this.address = address;
    this.payment = payment;
    this.cid = cid;
    this.account = account;
    this.book = book;
    this.amount = amount;
  }
  public Long getId(){ return id;}

  public void setId(long id) {
    this.id = id;
  }

  public Integer getCid() {
    return cid;
  }

  public void setCid(Integer cid) { this.cid = cid; }

  public String getInvoice(){ return invoice; }

  public void setInvoice(String invoice) { this.invoice = invoice; }

  public String getDelivery(){ return delivery; }

  public void setDelivery(String delivery) { this.delivery = delivery; }

  public String getAddress(){ return address; }

  public void setAddress(String address) { this.address = address; }

  public String getPayment(){ return payment; }

  public void setPayment(String payment) { this.payment = payment; }

  public String getAccount() {
    return account;
  }

  public void setAccount(String account) {
    this.account = account;
  }

  public void setBook(String book) {
    this.book = book;
  }

  public String getBook() {
    return book;
  }

  public String getAmount() {
    return amount;
  }

  public void setAmount(String amount) {
    this.amount = amount;
  }
}