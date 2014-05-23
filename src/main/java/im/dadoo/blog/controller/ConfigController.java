/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package im.dadoo.blog.controller;

import org.apache.commons.configuration.ConfigurationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author codekitten
 */
@Controller
public final class ConfigController extends BaseController {
  
  @RequestMapping(value = "/admin/config", method = RequestMethod.POST)
  public String config(@RequestParam String title) {
    if (title != null) {
      this.configService.setTitle(title);
    }
    try {
      this.configService.save();
    } catch (ConfigurationException ex) {
      
    }
    return "redirect:/admin/config";
  }
}
