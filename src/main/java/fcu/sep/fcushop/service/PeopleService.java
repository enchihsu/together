package fcu.sep.fcushop.service;

import fcu.sep.fcushop.database.Sql2oDbHandler;
import fcu.sep.fcushop.model.People;
import java.util.List;

import fcu.sep.fcushop.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.sql2o.Connection;
import org.w3c.dom.html.HTMLBRElement;

@Service
public class PeopleService {

  @Autowired
  private Sql2oDbHandler sql2oDbHandler;

  public PeopleService() {

  }
  /**
   * 点对 (x,y) 的水平和垂直距离.
   */

  public String login(String account, String password){
    try (Connection connection = sql2oDbHandler.getConnector().open()) {
      String query = "select count(*)"
          + " from PEOPLE where account =:account and password =:password";
      int c;
      c=connection.createQuery(query)
          .addParameter("account", account)
          .addParameter("password", password)
          .executeScalar(Integer.class);
      if(c==1) {
        return "OK";
      } else {
        return "NO";
      }
    }
  }

  public String Register(String account, String password, String name, String address, String birthday, String sex, String mail)
  {
    try (Connection connection = sql2oDbHandler.getConnector().open()) {
      String query = "insert into people (ACCOUNT, PASSWORD, NAME, ADDRESS, BIRTHDAY, SEX, MAIL) "
          + "VALUES(:account, :password, :name, :address, :birthday, :sex, :mail)";

      System.out.println(query);
      connection.createQuery(query)
          .addParameter("account", account)
          .addParameter("password", password)
          .addParameter("name", name)
          .addParameter("address", address)
          .addParameter("birthday", birthday)
          .addParameter("sex", sex)
          .addParameter("mail", mail)
          .executeUpdate();
      return "Success";
    }
  }

}
