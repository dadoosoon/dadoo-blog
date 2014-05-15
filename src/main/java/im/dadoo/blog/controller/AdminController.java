/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package im.dadoo.blog.controller;

import im.dadoo.blog.domain.Article;
import im.dadoo.blog.domain.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author codekitten
 */
@Controller
public class AdminController extends BaseController {
  
  @RequestMapping(value = "/admin", method = RequestMethod.GET)
  public String getAdminPage() {
    return "admin/admin";
  }
  
  @RequestMapping(value = "/admin/tag", method = RequestMethod.GET)
  public String getTagAdminPage(ModelMap map) {
    map.addAttribute("tags", this.tagService.list());
    return "admin/tag";
  }
  
  @RequestMapping(value = "/admin/tag/add", method = RequestMethod.GET)
  public String getTagAddAdminPage() {
    return "admin/tag-add";
  }
  
  @RequestMapping(value = "/admin/tag/{id}/update", method = RequestMethod.GET)
  public String getTagUpdateAdminPage(@PathVariable Integer id, ModelMap map) {
    Tag tag = this.tagService.findById(id);
    if (tag != null) {
      map.addAttribute("tag", tag);
      return "admin/tag-update";
    } else {
      return "redirect:/404";
    }
  }
  
  @RequestMapping(value = "/admin/article", method = RequestMethod.GET)
  public String getArticleAdminPage(ModelMap map) {
    map.addAttribute("articles", this.articleService.list(Integer.SIZE));
    return "admin/article";
  }
  
  @RequestMapping(value = "/admin/article/add", method = RequestMethod.GET)
  public String getArticleAddAdminPage(ModelMap map) {
    return "admin/article-add";
  }
  
  @RequestMapping(value = "/admin/article/{id}/update", method = RequestMethod.GET)
  public String getArticleUpdateAdminPage(@PathVariable Integer id, ModelMap map) {
    Article article = this.articleService.findById(id);
    if (article != null) {
      map.addAttribute("article", article);
      return "admin/article-update";
    } else {
      return "redirect:/404";
    }
  }
}
