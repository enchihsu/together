package fcu.sep.fcushop.controller;

import fcu.sep.fcushop.model.Checkout;
import fcu.sep.fcushop.model.Product;
import fcu.sep.fcushop.service.CheckoutService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * checkoutcontroller.
 */

@RestController
public class CheckoutController {

  @Autowired
  CheckoutService checkoutManager;

  @GetMapping("/checkouts")
  public List<Checkout> getCheckouts(@RequestParam("account") String account) {
    return checkoutManager.getCheckouts(account);
  }


  @RequestMapping(value = "/reallycheckout", method = RequestMethod.POST)
  @ResponseBody
  public String checkout(
        @RequestParam("account") String account,
        @RequestParam("invoice") String invoice,
        @RequestParam("delivery") String delivery,
        @RequestParam("address") String address,
        @RequestParam("payment") String payment
  ) {
    return checkoutManager.checkout(invoice, delivery, address, payment, account);
  }
}

