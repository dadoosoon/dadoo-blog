/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package im.dadoo.blog.service;

import im.dadoo.blog.dao.ArticleDao;
import im.dadoo.blog.dao.TagArticleDao;
import im.dadoo.blog.dao.TagDao;
import im.dadoo.blog.domain.Article;
import im.dadoo.blog.domain.Tag;
import im.dadoo.blog.domain.TagArticle;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.transaction.Transactional;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
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
  
  @Resource
  private TagArticleDao taDao;
  
  public Article add(String title, String html, List<Integer> tagIds) {
    String text = Jsoup.parse(html).text();
    Article article = Article.create(title, html, text, System.currentTimeMillis(), 0);
    this.articleDao.add(article);
    if (article.getId() != null) {
      if (tagIds != null && !tagIds.isEmpty()) {
        for (Integer tagId : tagIds) {
          this.taDao.add(TagArticle.create(tagId, article.getId()));
        }
      }
    }
    return article;
  }
  
  public Article update(Integer id, String title, String html, List<Integer> tagIds) {
    String text = Jsoup.parse(html).text();
    Article article = this.articleDao.findById(id);
    article.setTitle(title);
    article.setHtml(html);
    article.setText(text);
    this.articleDao.update(article);
    this.taDao.deleteByArticleId(id);
    if (tagIds != null && !tagIds.isEmpty()) {
      for (Integer tagId : tagIds) {
        TagArticle ta = TagArticle.create(tagId, id);
        this.taDao.add(ta);
      }
    }
    return article;
  }
  
  public Article click(Integer id) {
    this.articleDao.click(id);
    return this.articleDao.findById(id);
  } 
  
  public void deleteById(Integer id) {
    this.articleDao.deleteById(id);
  }
  
  public Pair<Article, List<Tag>> findById(Integer id) {
    Article article = this.articleDao.findById(id);
    if (article != null) {
      List<Tag> tags = this.tagDao.listByArticleId(id);
      return ImmutablePair.of(article, tags);
    } else {
      return null;
    }
  }
  
  public List<Article> list(Integer limit) {
    return this.articleDao.list(limit);
  }
  
  public List<Pair<Article, List<Tag>>> list(Integer pagecount, Integer pagesize) {
    List<Article> articles = this.articleDao.list(pagecount, pagesize);
    if (articles != null && !articles.isEmpty()) {
      List<Pair<Article, List<Tag>>> pairs = new ArrayList<>();
      for (Article article : articles) {
        List<Tag> tags = this.tagDao.listByArticleId(article.getId());
        pairs.add(ImmutablePair.of(article, tags));
      }
      return pairs;
    } else {
      return null;
    }
  }
  
  public List<Article> listMostVisitArticles(Integer limit) {
    return this.articleDao.listOrderByClickDesc(limit);
  }
  
  public List<Pair<Article, List<Tag>>> listByTagId(Integer tagId, Integer pagecount, Integer pagesize) {
    List<Article> articles = this.articleDao.listByTagId(tagId, pagecount, pagesize);
    if (articles != null && !articles.isEmpty()) {
      List<Pair<Article, List<Tag>>> pairs = new ArrayList<>();
      for (Article article : articles) {
        List<Tag> tags = this.tagDao.listByArticleId(article.getId());
        pairs.add(ImmutablePair.of(article, tags));
      }
      return pairs;
    } else {
      return null;
    }
  }
  
  public Integer size() {
    return (Integer)this.articleDao.size();
  }
  
  public Integer sizeByTagId(Integer tagId) {
    return (Integer)this.taDao.sizeByTagId(tagId);
  }
}
