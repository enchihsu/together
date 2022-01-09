package fcu.sep.fcushop.controller;

import fcu.sep.fcushop.model.Product;
import fcu.sep.fcushop.service.ProductService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * ProductController.
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

  @RequestMapping(value = "/add_product", method = RequestMethod.GET)
  @ResponseBody
  public String addProduct(
      @RequestParam("book_name") String bookname,
      @RequestParam("img_url") String imgurl,
      @RequestParam("price") Integer price,
      @RequestParam("quantity") Integer quantity,
      @RequestParam("description") String description
  ) {
    return productManager.aaAddProduct(bookname, imgurl, price, quantity, description);
  }

  @RequestMapping(value = "/update_product", method = RequestMethod.GET)
  @ResponseBody
  public String updateProduct(
      @RequestParam("book_name") String bookname,
      @RequestParam("price") Integer price
  ) {
    return productManager.aaUpdateProduct(bookname, price);
  }
}