package fcu.sep.fcushop.service;

import fcu.sep.fcushop.database.Sql2oDbHandler;
import fcu.sep.fcushop.model.People;
import fcu.sep.fcushop.model.Product;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.sql2o.Connection;
import org.w3c.dom.html.HTMLBRElement;

/**
 * peopleservice.
 */

@Service
public class PeopleService {

  @Autowired
  private Sql2oDbHandler sql2oDbHandler;

  public PeopleService() {

  }

  /**
   * peopleservice.
   */

  public String login(String account, String password) {
    try (Connection connection = sql2oDbHandler.getConnector().open()) {
      String query = "select count(*)"
          + " from bookstore.people where account =:account and password =:password";
      int c;
      c = connection.createQuery(query)
          .addParameter("account", account)
          .addParameter("password", password)
          .executeScalar(Integer.class);
      if (c == 1) {
        return "OK";
      } else {
        return "NO";
      }
    }
  }

  /**
   * peopleservice.
   */

  public String aaRegister(String account, String password, String name,
                         String address, String birthday, String sex, String mail) {
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

  /**
   * peopleservice.
   */

  public String aaUpdatePeople(String account, String password, String orginpass) {
    try (Connection connection = sql2oDbHandler.getConnector().open()) {
      String query = "select PASSWORD" + " from bookstore.people where ACCOUNT =:account";
      var c = connection.createQuery(query)
          .addParameter("account", account)
          .executeScalar(String.class);
      int result = c.compareTo(orginpass);
      System.out.println(result);

      if (result == 0) {
        String query1 = "Update bookstore.people "
            + "SET PASSWORD=:password WHERE ACCOUNT = :account";
        System.out.println(query1);
        connection.createQuery(query1)
            .addParameter("account", account)
            .addParameter("password", password)
            .executeUpdate();
        return "OK";
      } else {
        return "NO";
      }
    }
  }
}








