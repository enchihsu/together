package fcu.sep.fcushop.controller;
import fcu.sep.fcushop.model.Checkout;
import fcu.sep.fcushop.service.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CheckoutController {

  @Autowired
  CheckoutService checkoutManager;

  @RequestMapping(value="/checkout", method = RequestMethod.POST)
  @ResponseBody
  public String checkout(
      @RequestParam("invoice") String invoice,
      @RequestParam("delivery") String delivery,
      @RequestParam("address") String address,
      @RequestParam("payment") String payment
      ){
    return checkoutManager.checkout(invoice,delivery,address,payment);
  }
}

