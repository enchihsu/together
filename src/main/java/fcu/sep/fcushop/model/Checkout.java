package fcu.sep.fcushop.model;

public class Checkout {
  private long cid;
  private String invoice;
  private String delivery;
  private String address;
  private String payment;

  public Checkout() {
  }
  public Checkout(String invoice,String delivery,String address,String payment,Long cid) {
    this.invoice = invoice;
    this.delivery = delivery;
    this.address = address;
    this.payment = payment;
    this.cid = cid;
  }
  public long getCId() {
    return cid;
  }

  public long getCid() {
    return cid;
  }

  public void setCid(Long cid) { this.cid = cid; }

  public String getInvoice(){ return invoice; }

  public void setInvoice(String invoice) { this.invoice = invoice; }

  public String getDelivery(){ return delivery; }

  public void setDelivery(String delivery) { this.delivery = delivery; }

  public String getAddress(){ return address; }

  public void setAddress(String address) { this.address = address; }

  public String getPayment(){ return payment; }

  public void setPayment(String payment) { this.payment = payment; }


}