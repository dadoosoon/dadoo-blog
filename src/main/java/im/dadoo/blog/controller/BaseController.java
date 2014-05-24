/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package im.dadoo.blog.controller;

import im.dadoo.blog.domain.Tag;
import im.dadoo.blog.service.ArticleService;
import im.dadoo.blog.service.ConfigService;
import im.dadoo.blog.service.LinkService;
import im.dadoo.blog.service.TagService;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
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
  
  @Resource
  protected LinkService linkService;
  
  @Resource
  protected ConfigService configService;
  
  protected void renderSidebar(ModelMap map) {
    this.renderMostVisitArticles(map);
    this.renderTagWell(map);
    this.renderLinks(map);
  }
  
  protected void renderMostVisitArticles(ModelMap map) {
    map.addAttribute("most-visit-articles", 
            this.articleService.listMostVisitArticles(this.configService.getMostVisitArticleSize()));
  }
  
  protected void renderTagWell(ModelMap map) {
    List<Tag> tags = this.tagService.list();
    if (tags != null && !tags.isEmpty()) {
      List<Pair<Tag, Integer>> pairs = new ArrayList<>();
      for (Tag tag : tags) {
        pairs.add(ImmutablePair.of(tag, this.tagService.sizeByTagId(tag.getId())));
      }
      map.addAttribute("tag-size-pairs", pairs);
    }
  }
  
  protected void renderLinks(ModelMap map) {
    map.addAttribute("links", this.linkService.list());
  }
  
  protected void renderSession(HttpSession session) {
    this.renderConfig(session);
  }
  
  protected void renderConfig(HttpSession session) {
    session.setAttribute("title", this.configService.getTitle());
  }
}
