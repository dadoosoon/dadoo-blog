/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package im.dadoo.blog.domain;

import java.io.Serializable;

/**
 *
 * @author codekitten
 */
public class Article implements Serializable {
  
  private Integer id;
  
  private String title;
  
  private String html;
  
  private String text;
  
  private Long publishDatetime;
  
  private Integer click;

  public Article() {}
  
  public static Article create(String title, String html, String text, 
          Long publishDatetime, Integer click) {
    Article article = new Article();
    article.setTitle(title);
    article.setHtml(html);
    article.setText(text);
    article.setPublishDatetime(publishDatetime);
    article.setClick(click);
    return article;
  }
  
  /**
   * @return the id
   */
  public Integer getId() {
    return id;
  }

  /**
   * @param id the id to set
   */
  public void setId(Integer id) {
    this.id = id;
  }

  /**
   * @return the title
   */
  public String getTitle() {
    return title;
  }

  /**
   * @param title the title to set
   */
  public void setTitle(String title) {
    this.title = title;
  }

  /**
   * @return the html
   */
  public String getHtml() {
    return html;
  }

  /**
   * @param html the html to set
   */
  public void setHtml(String html) {
    this.html = html;
  }

  /**
   * @return the text
   */
  public String getText() {
    return text;
  }

  /**
   * @param text the text to set
   */
  public void setText(String text) {
    this.text = text;
  }

  /**
   * @return the publishDatetime
   */
  public Long getPublishDatetime() {
    return publishDatetime;
  }

  /**
   * @param publishDatetime the publishDatetime to set
   */
  public void setPublishDatetime(Long publishDatetime) {
    this.publishDatetime = publishDatetime;
  }

  /**
   * @return the click
   */
  public Integer getClick() {
    return click;
  }

  /**
   * @param click the click to set
   */
  public void setClick(Integer click) {
    this.click = click;
  }
  
}
