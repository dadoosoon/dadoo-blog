/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package im.dadoo.blog.service;

import im.dadoo.blog.dao.ArticleDao;
import im.dadoo.blog.dao.TagDao;
import im.dadoo.blog.domain.Article;
import java.util.List;
import javax.annotation.Resource;
import javax.transaction.Transactional;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;

/**
 *
 * @author codekitten
 */
@Service
@Transactional
public class ArticleService {
  
  @Resource
  private TagDao tagDao;
  
  @Resource
  private ArticleDao articleDao;
  
  public Article add(String title, String html) {
    String text = Jsoup.parse(html).text();
    Article article = Article.create(title, html, text, System.currentTimeMillis(), 0);
    this.articleDao.add(article);
    return article;
  }
  
  public Article update(Integer id, String title, String html) {
    String text = Jsoup.parse(html).text();
    Article article = this.articleDao.findById(id);
    article.setTitle(title);
    article.setHtml(html);
    article.setText(text);
    this.articleDao.update(article);
    return article;
  }
  
  public Article click(Integer id) {
    this.articleDao.click(id);
    return this.articleDao.findById(id);
  } 
  
  public void deleteById(Integer id) {
    this.articleDao.deleteById(id);
  }
  
  public Article findById(Integer id) {
    return this.articleDao.findById(id);
  }
  
  public List<Article> list(Integer limit) {
    return this.articleDao.list(limit);
  }
  
  public List<Article> list(Integer pagecount, Integer pagesize) {
    return this.articleDao.list(pagecount, pagesize);
  }
  
  public Integer size() {
    return (Integer)this.articleDao.size();
  }
}
