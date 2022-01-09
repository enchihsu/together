package fcu.sep.fcushop.controller;

import fcu.sep.fcushop.model.Like;
import fcu.sep.fcushop.model.Product;
import fcu.sep.fcushop.service.LikeService;
import fcu.sep.fcushop.service.ProductService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * likecontroller.
 */

@RestController
public class LikeController {

  @Autowired
  LikeService LikeManager;

  @GetMapping("/likes")
  public List<Like> getLikes(
      @RequestParam("account") String account
  ) {
    return LikeManager.getLikes(account);
  }

  @GetMapping("/addlike/{like}")
  public String addlike(
      @RequestParam("like") Integer like,
      @RequestParam("account") String account
  ) {
    return LikeManager.addlike(like, account);
  }

  @GetMapping("/addliketocart/{id}")
  public String addliketocart(
      @RequestParam("id") Integer id,
      @RequestParam("account") String account
  ) {
    return LikeManager.addliketocart(id, account);
  }
}