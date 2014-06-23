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
public class Link implements Serializable {
  
  private static final long serialVersionUID = 1L;
  
  private Integer id;
  
  private String name;
  
  private String url;
  
  private String description;

  public static Link create(String name, String url, String description) {
    Link link = new Link();
    link.setName(name);
    link.setUrl(url);
    link.setDescription(description);
    return link;
  }
}
