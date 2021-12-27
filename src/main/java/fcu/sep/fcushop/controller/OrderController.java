package fcu.sep.fcushop.controller;

import fcu.sep.fcushop.model.Order;
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

  @RequestMapping(value="/deleteproduct", method = RequestMethod.POST)
  @ResponseBody
  public String deleteproduct(
      @RequestParam("bookname") String bookname
  ){
    return orderManager.deleteproduct(bookname);
  }
}