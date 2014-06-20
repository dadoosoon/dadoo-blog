/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package im.dadoo.blog.domain;

import com.google.common.base.Objects;
import java.io.Serializable;

/**
 *
 * @author codekitten
 */
public class TagArticle implements Serializable {
  
  private Integer tagId;
  
  private Integer articleId;

  public TagArticle() {}
  
  public static TagArticle create(Integer tagId, Integer articleId) {
    TagArticle ta = new TagArticle();
    ta.setTagId(tagId);
    ta.setArticleId(articleId);
    return ta;
  }
  
  @Override
  public String toString() {
    return Objects.toStringHelper(this).add("tagId", tagId).add("articleId", articleId).toString();
  }
  
  /**
   * @return the tagId
   */
  public Integer getTagId() {
    return tagId;
  }

  /**
   * @param tagId the tagId to set
   */
  public void setTagId(Integer tagId) {
    this.tagId = tagId;
  }

  /**
   * @return the articleId
   */
  public Integer getArticleId() {
    return articleId;
  }

  /**
   * @param articleId the articleId to set
   */
  public void setArticleId(Integer articleId) {
    this.articleId = articleId;
  }
  
  
}
