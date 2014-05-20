/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package im.dadoo.blog.controller;

import im.dadoo.blog.cons.Constants;
import im.dadoo.blog.domain.Article;
import im.dadoo.blog.domain.Tag;
import java.util.List;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author dadoosoon
 */
@Controller
public class IndexController extends BaseController {
  
  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String index(ModelMap map, @RequestParam(required = false) Integer pagecount) {
    if (pagecount == null) {
      pagecount = 1;
    }
    List<Pair<Article, List<Tag>>> pairs = this.articleService.list(pagecount - 1, Constants.DEFAULT_PAGESIZE);
    if (pairs != null && !pairs.isEmpty()) {
      this.renderMostVisitArticles(map, Constants.DEFAULT_MOST_VISIT_ARTICLE_SIZE);
      this.renderTagWell(map);
      this.renderLinks(map);
      map.addAttribute("pairs", pairs);
      map.addAttribute("curPagecount", pagecount);
      map.addAttribute("maxPagecount", 1 + this.articleService.size() / Constants.DEFAULT_PAGESIZE);
      return "list";
    } else {
      return "404";
    }
  }
  
  @RequestMapping(value = "/version", method = RequestMethod.GET)
  public String version(ModelMap map) {
    this.renderMostVisitArticles(map, Constants.DEFAULT_MOST_VISIT_ARTICLE_SIZE);
    this.renderTagWell(map);
    this.renderLinks(map);
    return "version";
  }
}
