package fcu.sep.fcushop.controller;

import fcu.sep.fcushop.model.Order;
import fcu.sep.fcushop.model.Product;
import fcu.sep.fcushop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

  @Autowired
  OrderService orderManager;

  @GetMapping("/orders")
  public List<Order> getOrders() {
    return orderManager.getOrders();
  }

  @GetMapping("/addcart/{book}")
  public String addcart(@PathVariable("book") String book) {
    return orderManager.addcart(book);
  }

 /* @RequestMapping(value="/addcart", method = RequestMethod.GET)
  @ResponseBody
  public String addProduct(
      @RequestParam("book_name") String book_name
      //@RequestParam("img_url") String img_url,
      //@RequestParam("price") Integer price,
      //@RequestParam("quantity") Integer quantity
      //@RequestParam("description") String description
  )
  {
    return orderManager.addCart(book_name);
  }*/

}