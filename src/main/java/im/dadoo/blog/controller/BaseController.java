/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package im.dadoo.blog.controller;

import im.dadoo.blog.service.ArticleService;
import im.dadoo.blog.service.ConfigService;
import im.dadoo.blog.service.LinkService;
import im.dadoo.blog.service.TagService;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

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
  
  protected void renderSession(HttpSession session) {
    this.renderConfig(session);
  }
  
  protected void renderConfig(HttpSession session) {
    session.setAttribute("title", this.configService.getTitle());
  }
}
