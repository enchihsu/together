package fcu.sep.fcushop.controller;

import fcu.sep.fcushop.model.Product;
import fcu.sep.fcushop.service.ProductService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 点对 (x,y) 的水平和垂直距离.
 */

@RestController
public class ProductController {

  @Autowired
  ProductService productManager;

  @GetMapping("/products")
  public List<Product> getProducts() {
    return productManager.getProducts();
  }

  @GetMapping("/products/{keyword}")
  public List<Product> getProducts(@PathVariable("keyword") String keyword) {
    return productManager.getProducts(keyword);
  }

  @RequestMapping(value="/add_product", method = RequestMethod.GET)
  @ResponseBody
  public String addProduct(
      @RequestParam("game_name") String game_name,
      @RequestParam("img_url") String img_url,
      @RequestParam("price") Integer price,
      @RequestParam("description") String description
  )
  {
    return productManager.AddProduct(game_name, img_url, price, description);
  }

  @RequestMapping(value="/update_product", method = RequestMethod.GET)
  @ResponseBody
  public String updateProduct(
      @RequestParam("game_name") String game_name,
      @RequestParam("img_url") String img_url,
      @RequestParam("price") Integer price,
      @RequestParam("description") String description
  )
  {
    return productManager.UpdateProduct(game_name, price);
  }
}