/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package im.dadoo.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author dadoosoon
 */
@Controller
public class IndexController extends BaseController {
  
  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String index(ModelMap map) {
    map.addAttribute("articles", this.articleService.list(10));
    return "list";
  }
}
