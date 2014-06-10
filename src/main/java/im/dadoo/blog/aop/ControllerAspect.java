/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package im.dadoo.blog.aop;

import im.dadoo.log.Log;
import im.dadoo.log.LogMaker;
import im.dadoo.logger.client.LoggerClient;
import javax.annotation.Resource;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 *
 * @author shuwen.zsw
 */
@Aspect
@Component
public class ControllerAspect {
  
  private static final Logger logger = LoggerFactory.getLogger(ControllerAspect.class);
  
  @Resource
  private LoggerClient loggerClient;
  
  @Around("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
  public Object stat(ProceedingJoinPoint pjp) throws Throwable {
    long t1 = System.currentTimeMillis();
    Object ret = pjp.proceed();
    long t2 = System.currentTimeMillis();
    String sig = pjp.getSignature().toLongString();
		Object[] args = pjp.getArgs();
    logger.info("函数名:{}~~运行时间:{}", sig, t2 - t1);
    Log log = LogMaker.makeFunctionLog("dadooblog", sig, null, null, t2 - t1);
    this.loggerClient.send(log);
    return ret;
  }
}
