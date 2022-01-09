package fcu.sep.fcushop.service;

import fcu.sep.fcushop.database.Sql2oDbHandler;
import fcu.sep.fcushop.model.Checkout;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.sql2o.Connection;
import org.w3c.dom.html.HTMLBRElement;
import java.util.List;

@Service
public class CheckoutService {
  @Autowired
  private Sql2oDbHandler sql2oDbHandler;

  public CheckoutService() {

  }

  public List<Checkout> getCheckouts(String account) {
    try (Connection connection = sql2oDbHandler.getConnector().open()) {
      String query = "select product.NAME book,product.IMAGE_URL imageUrl,product.PRICE price,checkout.AMOUNT amount,checkout.CID cid,checkout.DELIVERY delivery,checkout.ADDRESS address,checkout.INVOICE invoice,checkout.PAYMENT payment"
          + " from  bookstore.product inner join bookstore.checkout on (product.ID=checkout.BOOK) and checkout.ACCOUNT =:account ";

      return connection.createQuery(query).addParameter("account", account).executeAndFetch(Checkout.class);
    }
  }
  //public List<Checkout> getCheckouts() {
    //try (Connection connection = sql2oDbHandler.getConnector().open()) {
    //  String query = "select distinct checkout.CID cid"
     //     + " from  bookstore.checkout where checkout.ACCOUNT = 6 ";

      //return connection.createQuery(query).executeAndFetch(Checkout.class);
    //}
  //}
    public String checkout(String invoice,String delivery,String address,String payment,String account){
      try (Connection connection = sql2oDbHandler.getConnector().open()) {
        String query3 = "insert into bookstore.checkout(ACCOUNT,BOOK,AMOUNT) select ACCOUNT account,BOOK book,AMOUNT amount from bookstore.order1 where bookstore.order1.ACCOUNT =:account";
        connection.createQuery(query3).addParameter("account", account).executeUpdate();
        String query1 ="update bookstore.checkout set PAYMENT =:payment,DELIVERY =:delivery,INVOICE =:invoice,ADDRESS =:address where  bookstore.checkout.ACCOUNT=:account and bookstore.checkout.CID IS NULL";
        //String query1 ="insert into bookstore.checkout (PAYMENT, DELIVERY, INVOICE, ADDRESS) VALUES(:payment, :delivery, :invoice, :address)";
        System.out.println(query1);
        connection.createQuery(query1)
            .addParameter("payment", payment)
            .addParameter("delivery", delivery)
            .addParameter("invoice", invoice)
            .addParameter("address", address)
            .addParameter("account", account)
            .executeUpdate();
        String query2="update bookstore.checkout set cid = (select * from (select max(CID) cid FROM bookstore.checkout) as a)+1 where bookstore.checkout.ACCOUNT =:account and bookstore.checkout.CID IS NULL";
        connection.createQuery(query2)
            .addParameter("account", account)
            .executeUpdate();

        String query4 ="delete from bookstore.order1 where bookstore.order1.ACCOUNT =:account";
        connection.createQuery(query4)
            .addParameter("account", account)
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