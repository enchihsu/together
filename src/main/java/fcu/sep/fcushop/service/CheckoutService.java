package fcu.sep.fcushop.service;

import com.sun.tools.jconsole.JConsolePlugin;
import fcu.sep.fcushop.database.Sql2oDbHandler;
import fcu.sep.fcushop.model.Checkout;
import fcu.sep.fcushop.model.Like;
import fcu.sep.fcushop.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sql2o.Connection;

import java.util.List;
@Service
public class CheckoutService {
  @Autowired
  private Sql2oDbHandler sql2oDbHandler;

  public CheckoutService() {

  }
  //insert into bookstore.checkout(ACCOUNT, BOOKID, AMOUNT) select ACCOUNT,BOOK,AMOUNT FROM bookstore.order1 where account = 4;
  //
  public List<Checkout> getCheckouts() {
    try (Connection connection = sql2oDbHandler.getConnector().open()) {
      String query = "select PAYMENT payment, DELIVERY delivery,INVOICE invoice, CID cid, ADDRESS address"
          + " from bookstore.checkout";

      return connection.createQuery(query).executeAndFetch(Checkout.class);
    }
  }

    public String checkout(String invoice,String delivery,String address,String payment){
      try (Connection connection = sql2oDbHandler.getConnector().open()) {
        String query3 = "insert into bookstore.checkout(ACCOUNT,BOOK,AMOUNT) select ACCOUNT account,BOOK book,AMOUNT amount from bookstore.order1 where bookstore.order1.ACCOUNT = 2";
        connection.createQuery(query3).executeUpdate();
        String query1 ="update bookstore.checkout set PAYMENT =:payment,DELIVERY =:delivery,INVOICE =:invoice,ADDRESS =:address where  bookstore.checkout.ACCOUNT= 2 and bookstore.checkout.CID IS NULL";
        //String query1 ="insert into bookstore.checkout (PAYMENT, DELIVERY, INVOICE, ADDRESS) VALUES(:payment, :delivery, :invoice, :address)";
        System.out.println(query1);
        connection.createQuery(query1)
            .addParameter("payment", payment)
            .addParameter("delivery", delivery)
            .addParameter("invoice", invoice)
            .addParameter("address", address)
            .executeUpdate();
        String query2="update bookstore.checkout set cid = (select * from (select max(CID) cid FROM bookstore.checkout) as a)+1 where bookstore.checkout.ACCOUNT = 2 and bookstore.checkout.CID IS NULL";
        connection.createQuery(query2)
            .executeUpdate();
        String query4 ="delete from bookstore.order1 where bookstore.order1.ACCOUNT = 2";
        connection.createQuery(query4)
            .executeUpdate();
        //String query ="select max(CID) cid"
           // " FROM bookstore.checkout";
        //int result= connection.createQuery(query).executeScalar(Integer.class);
        //System.out.println(result);
        //String query2 = "update bookstore.order1 set cid = (select max(CID) cid FROM bookstore.checkout) WHERE bookstore.order1.ACCOUNT = 6 and bookstore.order1.CID IS NULL";
        //connection.createQuery(query2).executeUpdate();
        return "Success";
    }
  }
}