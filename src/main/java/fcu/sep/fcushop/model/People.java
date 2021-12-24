package fcu.sep.fcushop.model;

public class People{

  private String people_name;

  private String account;

  private String password;

  private String address;

  private String birthday;

  private String sex;

  private String mail;

  /**
   * 点对 (x,y) 的水平和垂直距离.
   */

  public People() {
  }

  /**
   * 点对 (x,y) 的水平和垂直距离.
   */

  public People(String people_name, String account, String password, String address, String birthday, String sex, String mail) {
    this.people_name = people_name;
    this.account = account;
    this.password = password;
    this.address = address;
    this.birthday = birthday;
    this.sex = sex;
    this.mail = mail;
  }

  public String getPeople_name() {
    return people_name;
  }

  public void setPeople_name(String people_name) {
    this.people_name = people_name;
  }

  public String getAccount() {
    return account;
  }

  public void setAccount(String account) { this.account = account; }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getBirthday() { return birthday; }

  public void setBirthday(String birthday) {
    this.birthday = birthday;
  }

  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }

  public String getMail() {
    return mail;
  }

  public void setMail(String mail) {
    this.mail = mail;
  }




}
