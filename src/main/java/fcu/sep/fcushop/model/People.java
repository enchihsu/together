package fcu.sep.fcushop.model;

/**
 * People.
 */

public class People {

  private String peoplename;

  private String account;

  private String password;

  private String address;

  private String birthday;

  private String sex;

  private String mail;

  /**
   * People.
   */

  public People() {
  }

  /**
   * People.
   */

  public People(String peoplename, String account, String password,
                String address, String birthday, String sex, String mail) {
    this.peoplename = peoplename;
    this.account = account;
    this.password = password;
    this.address = address;
    this.birthday = birthday;
    this.sex = sex;
    this.mail = mail;
  }

  public String getPeople_name() {
    return peoplename;
  }

  public void setPeople_name(String peoplename) {
    this.peoplename = peoplename;
  }

  public String getAccount() {
    return account;
  }

  public void setAccount(String account) {
    this.account = account;
  }

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

  public String getBirthday() {
    return birthday;
  }

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