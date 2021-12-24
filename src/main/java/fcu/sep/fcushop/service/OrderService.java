package fcu.sep.fcushop.service;

import fcu.sep.fcushop.database.Sql2oDbHandler;
import fcu.sep.fcushop.model.Order;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.sql2o.Connection;
import org.w3c.dom.html.HTMLBRElement;

@Service
public class OrderService {

  @Autowired
  private Sql2oDbHandler sql2oDbHandler;

  public OrderService() {

  }
  /**
   * 点对 (x,y) 的水平和垂直距离.
   */

  public List<Order> getOrders() {
    try (Connection connection = sql2oDbHandler.getConnector().open()) {
      String query = "select ID id, ACCOUNT account, BOOK book,IMAGE_URL imageUrl, PRICE price, AMOUNT amount"
          + " from bookstore.order";

      return connection.createQuery(query).executeAndFetch(Order.class);
    }
  }

}