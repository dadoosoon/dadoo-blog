/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package im.dadoo.blog.controller;

import im.dadoo.blog.service.ArticleService;
import im.dadoo.blog.service.TagService;
import javax.annotation.Resource;
import org.springframework.ui.ModelMap;

/**
 *
 * @author codekitten
 */
public class BaseController {
  
  @Resource
  protected ArticleService articleService;
  
  @Resource
  protected TagService tagService;
  
  protected void renderMostVisitArticles(ModelMap map, Integer limit) {
    map.addAttribute("mostVisitArticles", this.articleService.listMostVisitArticles(limit));
  }
}
