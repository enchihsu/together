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
  public List<Order> getOrders(
      @RequestParam("account") String account
  ) {
    return orderManager.getOrders(account);
  }

  @GetMapping("/addcart/{book}")
  public String addcart(
      @RequestParam("book") Integer book,
      @RequestParam("account") String account
  ) {
    return orderManager.addcart(book,account);
  }

  @GetMapping("/deleteproduct/{book}")
  public String deleteproduct(
      @RequestParam("book") Integer book,
      @RequestParam("account") String account
  ) {
    return orderManager.deleteproduct(book,account);
  }

  @GetMapping("/plus/{id}")
  public String plus(
      @RequestParam("id") Integer id,
      @RequestParam("account") String account
  ) {
    return orderManager.plus(id,account);
  }

  @GetMapping("/minus/{id}")
  public String minus(
      @RequestParam("id") Integer id,
      @RequestParam("account") String account
  ) {
    return orderManager.minus(id,account);
  }


}