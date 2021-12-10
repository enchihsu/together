package fcu.sep.fcushop.database;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.sql2o.Sql2o;
/**
 * 点对 (x,y) 的水平和垂直距离.
 */

@Service
@PropertySource("classpath:application.properties")
public class Sql2oDbHandler {

  @Value("${db.jdbc_url}")
  private String jdbcUrl;

  @Value("${db.username}")
  private String userName;

  @Value("${db.password}")
  private String password;


  private Sql2o connector;

  /**
   * Get sql2o connector.
   *
   * @return connector
   */
  public Sql2o getConnector() {
    if (connector == null) {
      connector = new Sql2o(jdbcUrl, userName, password);
    }
    return connector;
  }
}