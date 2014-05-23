/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package im.dadoo.blog.service;

import javax.annotation.Resource;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.springframework.stereotype.Service;

/**
 *
 * @author codekitten
 */
@Service
public final class ConfigService {
  
  @Resource
  private PropertiesConfiguration config;
  
  public String getTitle() {
    return this.config.getString("title");
  }
  
  public void setTitle(String title) {
    this.config.setProperty("title", title);
  }
  
  public void save() throws ConfigurationException {
    this.config.save();
  }
}
