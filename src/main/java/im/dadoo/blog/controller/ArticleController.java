/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package im.dadoo.blog.controller;

import im.dadoo.blog.domain.Article;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author codekitten
 */
@Controller
public class ArticleController extends BaseController {
  
  @RequestMapping(value = "/article/{id}", method = RequestMethod.GET)
  public String item(@PathVariable Integer id, ModelMap map) {
    Article article = this.articleService.findById(id);
    if (article != null) {
      this.articleService.click(id);
      map.addAttribute("article", article);
      return "item";
    } else {
      return "redirect:/404";
    }
  }
  
  @RequestMapping(value = "/admin/article/add", method = RequestMethod.POST)
  public String save(@RequestParam String title, @RequestParam String html) {
    if (title != null && html != null) {
      this.articleService.add(title, html);
      return "redirect:/admin/article";
    } else {
      return "redirect:/404";
    }
  }
  
  @RequestMapping(value = "/admin/article/{id}/update", method = RequestMethod.POST)
  public String update(@PathVariable Integer id, @RequestParam String title, 
          @RequestParam String html) {
    if (title != null && html != null) {
      this.articleService.update(id, title, html);
      return "redirect:/admin/article";
    } else {
      return "redirect:/404";
    }
  }
  
  @RequestMapping(value = "/admin/article/{id}/delete", method = RequestMethod.GET)
  public String delete(@PathVariable Integer id) {
    this.articleService.deleteById(id);
    return "redirect:/admin/article";
  }
}
