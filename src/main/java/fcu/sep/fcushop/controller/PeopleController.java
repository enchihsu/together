package fcu.sep.fcushop.controller;

import fcu.sep.fcushop.model.People;
import fcu.sep.fcushop.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PeopleController {

  @Autowired
  PeopleService peopleManager;

  @RequestMapping(value="/login", method = RequestMethod.POST)
  @ResponseBody
  public String login(
      @RequestParam("account") String account,
      @RequestParam("password") String password
  ){
    return peopleManager.login(account,password);
  }




}
