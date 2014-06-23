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
public class Tag implements Serializable {
  
  private Integer id;
  
  private String name;
  
  public static Tag create(String name) {
    Tag tag = new Tag();
    tag.setName(name);
    return tag;
  }

}
