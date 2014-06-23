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
@Data
@NoArgsConstructor
public class TagArticle implements Serializable {
  
  private Integer tagId;
  
  private Integer articleId;
  
  public static TagArticle create(Integer tagId, Integer articleId) {
    TagArticle ta = new TagArticle();
    ta.setTagId(tagId);
    ta.setArticleId(articleId);
    return ta;
  }
 
}
