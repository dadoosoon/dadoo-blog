/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package im.dadoo.blog.domain;

import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author codekitten
 */
@NoArgsConstructor
@Data
public class Article implements Serializable {
  
  private Integer id;
  
  private String title;
  
  private String html;
  
  private String text;
  
  private Long publishDatetime;
  
  private Integer click;
  
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

}
