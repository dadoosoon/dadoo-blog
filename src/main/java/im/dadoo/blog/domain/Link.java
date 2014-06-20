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
public class Link implements Serializable {
  
  private static final long serialVersionUID = 1L;
  
  private Integer id;
  
  private String name;
  
  private String url;
  
  private String description;
  
  public Link() {}

  public static Link create(String name, String url, String description) {
    Link link = new Link();
    link.setName(name);
    link.setUrl(url);
    link.setDescription(description);
    return link;
  }
  
  @Override
  public String toString() {
    return Objects.toStringHelper(this).add("id", id).add("name", name)
            .add("url", url).add("description", description).toString();
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
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * @return the url
   */
  public String getUrl() {
    return url;
  }

  /**
   * @param url the url to set
   */
  public void setUrl(String url) {
    this.url = url;
  }

  /**
   * @return the description
   */
  public String getDescription() {
    return description;
  }

  /**
   * @param description the description to set
   */
  public void setDescription(String description) {
    this.description = description;
  }
  
}
