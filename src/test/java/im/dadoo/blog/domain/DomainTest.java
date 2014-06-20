/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package im.dadoo.blog.domain;

import org.junit.Test;

/**
 *
 * @author shuwen.zsw
 */
public class DomainTest {
  
  @Test
  public void printTag() {
    Tag tag = Tag.create("abc");
    System.out.println(tag);
  }
}
