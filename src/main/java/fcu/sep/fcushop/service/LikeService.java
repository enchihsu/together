package fcu.sep.fcushop.service;

import fcu.sep.fcushop.database.Sql2oDbHandler;
import fcu.sep.fcushop.model.Like;
import fcu.sep.fcushop.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sql2o.Connection;

import java.util.List;


@Service
public class LikeService {

  @Autowired
  private Sql2oDbHandler sql2oDbHandler;

  public LikeService() {

  }

  public List<Like> getLikes() {
    try (Connection connection = sql2oDbHandler.getConnector().open()) {
      String query = "select product.PRICE price, product.NAME name, product.IMAGE_URL imageUrl, like1.QUANTITY quantity\n" +
          "    FROM bookstore.product\n" +
          "    INNER JOIN bookstore.like1\n" +
          "    ON bookstore.product.ID=bookstore.like1.ID;";

      return connection.createQuery(query).executeAndFetch(Like.class);
    }
  }
}

  //select product.PRICE price, product.NAME name, like1.ACCOUNT account, like1.QUANTITY quantity
  //  FROM bookstore.product
  //  INNER JOIN bookstore.like1
    //ON bookstore.product.ID=bookstore.like1.ID;

   // select ID id,ACCOUNT account,QUANTITY quantity from bookstore.like