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

  @GetMapping("/deleteproduct/{bookname}")
  public String deleteproduct(@PathVariable("bookname") String bookname) {
    return orderManager.deleteproduct(bookname);
  }

  @GetMapping("/plus/{id}")
  public String plus(@PathVariable("id") String id) {
    return orderManager.plus(id);
  }

  @GetMapping("/minus/{id}")
  public String minus(@PathVariable("id") String id) {
    return orderManager.minus(id);
  }


}