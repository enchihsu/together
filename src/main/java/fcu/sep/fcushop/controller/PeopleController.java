package fcu.sep.fcushop.controller;

import fcu.sep.fcushop.model.People;
import fcu.sep.fcushop.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PeopleController {

  @Autowired
  PeopleService peopleManager;

  @RequestMapping(value="/register_product", method = RequestMethod.GET)
  @ResponseBody
  public String registerProduct(
      @RequestParam("account") String account,
      @RequestParam("password") String password,
      @RequestParam("name") String name,
      @RequestParam("address") String address,
      @RequestParam("birthday") String birthday,
      @RequestParam("sex") String sex,
      @RequestParam("mail") String mail
  )
  {
    return peopleManager.Register(account, password, name, address, birthday, sex, mail);
  }

  @RequestMapping(value="/profile", method = RequestMethod.GET)
  @ResponseBody
  public String updatePeople(
      @RequestParam("account") String account,
      @RequestParam("password") String password,
	  @RequestParam("orginpass") String orginpass
  )
  {
    return peopleManager.UpdatePeople(account, password, orginpass);
  }






}
