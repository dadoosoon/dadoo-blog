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
public class Tag implements Serializable {
  
  private Integer id;
  
  private String name;
  
  public Tag() {}
  
  public static Tag create(String name) {
    Tag tag = new Tag();
    tag.setName(name);
    return tag;
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(this).add("id", id).add("name", name).toString();
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
  
  
}
