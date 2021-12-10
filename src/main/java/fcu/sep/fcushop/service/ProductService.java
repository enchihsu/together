package fcu.sep.fcushop.service;

import fcu.sep.fcushop.database.Sql2oDbHandler;
import fcu.sep.fcushop.model.Product;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.sql2o.Connection;
/**
 * 点对 (x,y) 的水平和垂直距离.
 */


@Service
public class ProductService {

  @Autowired
  private Sql2oDbHandler sql2oDbHandler;

  public ProductService() {

  }
  /**
   * 点对 (x,y) 的水平和垂直距离.
   */

  public List<Product> getProducts() {
    try (Connection connection = sql2oDbHandler.getConnector().open()) {
      String query = "select ID id,NAME name,IMAGE_URL imageUrl,PRICE price,DESCRIPTION description"
          + " from PRODUCT";

      return connection.createQuery(query).executeAndFetch(Product.class);
    }
  }


  /**
   * 点对 (x,y) 的水平和垂直距离.
   */

  public List<Product> getProducts(String keyword) {
    try (Connection connection = sql2oDbHandler.getConnector().open()) {
      String query = "select ID id, NAME name, IMAGE_URL imageUrl, PRICE price, DESCRIPTION description"
          + " from PRODUCT where name like :keyword";

      return connection.createQuery(query)
          .addParameter("keyword", "%"+keyword+"%")
          .executeAndFetch(Product.class);
    }
  }

  public String AddProduct(String game_name, String img_url, int price, String description)
  {
    try (Connection connection = sql2oDbHandler.getConnector().open()) {
      String query = "insert into PRODUCT (NAME, IMAGE_URL, PRICE, DESCRIPTION) "
          + "VALUES(:game_name, :img_url, :price, :description)";

      System.out.println(query);
      connection.createQuery(query)
          .addParameter("game_name", game_name)
          .addParameter("img_url", img_url)
          .addParameter("price", price)
          .addParameter("description", description)
          .executeUpdate();

      return "Success";

    }
  }

  public String UpdateProduct(String game_name, int price)
  {
    try (Connection connection = sql2oDbHandler.getConnector().open()) {
      String query = "Update PRODUCT "
          + "SET PRICE= :price WHERE NAME = :game_name";

      System.out.println(query);
      connection.createQuery(query)
          .addParameter("game_name", game_name)
          .addParameter("price", price)
          .executeUpdate();
      return "Success";

    }
  }

}