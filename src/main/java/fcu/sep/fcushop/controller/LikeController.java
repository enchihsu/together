package fcu.sep.fcushop.controller;

import fcu.sep.fcushop.model.Like;
import fcu.sep.fcushop.model.Product;
import fcu.sep.fcushop.service.LikeService;
import fcu.sep.fcushop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LikeController {

  @Autowired
  LikeService LikeManager;

  @GetMapping("/likes")
  public List<Like> getLikes() {
    return LikeManager.getLikes();
  }

  @GetMapping("/addlike/{like}")
  public String addlike(@PathVariable("like") String like) {
    return LikeManager.addlike(like);
  }
}
