/**
 * Licensed under the GPL License. You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   https://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 *
 * THIS PACKAGE IS PROVIDED "AS IS" AND WITHOUT ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING,
 * WITHOUT LIMITATION, THE IMPLIED WARRANTIES OF MERCHANTIBILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE.
 */
package psiprobe.mappers;

import com.opensymphony.module.sitemesh.Config;
import com.opensymphony.module.sitemesh.DecoratorMapper;
import com.opensymphony.module.sitemesh.Page;

import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import mockit.Expectations;
import mockit.Mocked;

/**
 * The Class AjaxDecoratorMapperTest.
 */
public class AjaxDecoratorMapperTest {

  /** The mapper. */
  AjaxDecoratorMapper mapper;

  /** The config. */
  @Mocked
  Config config;

  /** The properties. */
  Properties properties;

  /** The decorator mapper. */
  @Mocked
  DecoratorMapper decoratorMapper;

  /** The request. */
  @Mocked
  HttpServletRequest request;

  /** The page. */
  @Mocked
  Page page;

  /**
   * Before.
   */
  @Before
  public void before() {
    mapper = new AjaxDecoratorMapper();
    properties = new Properties();
  }

  /**
   * Ajax decorator mapper test.
   *
   * @throws InstantiationException the instantiation exception
   */
  @Test
  public void ajaxDecoratorMapperTest() throws InstantiationException {
    properties.setProperty("ajaxExtension", ".ajax");
    mapper.init(config, properties, decoratorMapper);

    new Expectations() {
      {
        request.getAttribute("javax.servlet.error.request_uri");
        result = "http://localhost:8080/probe";

        request.getServletPath();
        result = "probe/ws";
      }
    };
    Assert.assertNotNull(mapper.getDecorator(request, page));
  }

}
