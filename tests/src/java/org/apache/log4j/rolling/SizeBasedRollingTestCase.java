/*
 * ============================================================================
 *                   The Apache Software License, Version 1.1
 * ============================================================================
 *
 *    Copyright (C) 1999 The Apache Software Foundation. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modifica-
 * tion, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of  source code must  retain the above copyright  notice,
 *    this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * 3. The end-user documentation included with the redistribution, if any, must
 *    include  the following  acknowledgment:  "This product includes  software
 *    developed  by the  Apache Software Foundation  (http://www.apache.org/)."
 *    Alternately, this  acknowledgment may  appear in the software itself,  if
 *    and wherever such third-party acknowledgments normally appear.
 *
 * 4. The names "log4j" and  "Apache Software Foundation"  must not be used to
 *    endorse  or promote  products derived  from this  software without  prior
 *    written permission. For written permission, please contact
 *    apache@apache.org.
 *
 * 5. Products  derived from this software may not  be called "Apache", nor may
 *    "Apache" appear  in their name,  without prior written permission  of the
 *    Apache Software Foundation.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED WARRANTIES,
 * INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS  FOR A PARTICULAR  PURPOSE ARE  DISCLAIMED.  IN NO  EVENT SHALL  THE
 * APACHE SOFTWARE  FOUNDATION  OR ITS CONTRIBUTORS  BE LIABLE FOR  ANY DIRECT,
 * INDIRECT, INCIDENTAL, SPECIAL,  EXEMPLARY, OR CONSEQUENTIAL  DAMAGES (INCLU-
 * DING, BUT NOT LIMITED TO, PROCUREMENT  OF SUBSTITUTE GOODS OR SERVICES; LOSS
 * OF USE, DATA, OR  PROFITS; OR BUSINESS  INTERRUPTION)  HOWEVER CAUSED AND ON
 * ANY  THEORY OF LIABILITY,  WHETHER  IN CONTRACT,  STRICT LIABILITY,  OR TORT
 * (INCLUDING  NEGLIGENCE OR  OTHERWISE) ARISING IN  ANY WAY OUT OF THE  USE OF
 * THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * This software  consists of voluntary contributions made  by many individuals
 * on  behalf of the Apache Software  Foundation.  For more  information on the
 * Apache Software Foundation, please see <http://www.apache.org/>.
 *
 */

package org.apache.log4j.rolling;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.util.Compare;


/**
 *
 * @author Ceki G&uuml;lc&uuml;
 *
 */
public class SizeBasedRollingTestCase extends TestCase {
  Logger logger = Logger.getLogger(SizeBasedRollingTestCase.class);

  public SizeBasedRollingTestCase(String name) {
    super(name);
  }

  public void setUp() {
  }

  public void tearDown() {
    LogManager.shutdown();
  }

  public void test1() throws Exception {
    Logger root = Logger.getRootLogger();
    root.addAppender(new ConsoleAppender(new PatternLayout()));

    // We purposefully use the \n as the line separator. 
    // This makes the regression test system indepent.
    PatternLayout layout = new PatternLayout("%m\n");
    RollingFileAppender rfa = new RollingFileAppender();
    rfa.setLayout(layout);

    SlidingWindowRollingPolicy swrp = new SlidingWindowRollingPolicy();
    SizeBasedTriggeringPolicy sbtp = new SizeBasedTriggeringPolicy();
    sbtp.setMaxFileSize(100);
    swrp.setFileNamePattern("output/sizeBased-test1.%i");
    rfa.setRollingPolicy(swrp);
    rfa.setTriggeringPolicy(sbtp);
    rfa.activateOptions();
    root.addAppender(rfa);

    // Write exactly 10 bytes with each log
    for (int i = 0; i < 22; i++) {
      Thread.sleep(1000);
      if (i < 10) {
        logger.debug("Hello---" + i);
      } else if (i < 100) {
        logger.debug("Hello--" + i);
      } else {
        logger.debug("Hello-" + i);
      }
    }

    // The File.length() method is not accurate under Windows    
    if (!isWindows()) {
      assertTrue(
        Compare.compare(
          "output/sizeBased-test1.1", "witness/sizeBased-test1.1"));
      assertTrue(
        Compare.compare(
          "output/sizeBased-test1.2", "witness/sizeBased-test1.2"));
      assertTrue(
        Compare.compare(
          "output/sizeBased-test1.3", "witness/sizeBased-test1.3"));
    }
  }

  public void test2() throws Exception {
    Logger root = Logger.getRootLogger();
    root.addAppender(new ConsoleAppender(new PatternLayout()));

    PatternLayout layout = new PatternLayout("%m\n");
    RollingFileAppender rfa = new RollingFileAppender();
    rfa.setLayout(layout);

    SlidingWindowRollingPolicy swrp = new SlidingWindowRollingPolicy();
    SizeBasedTriggeringPolicy sbtp = new SizeBasedTriggeringPolicy();

    sbtp.setMaxFileSize(100);
    swrp.setActiveFileName("output/sizeBased-test2");
    swrp.setFileNamePattern("output/sizeBased-test2.%i");
    rfa.setRollingPolicy(swrp);
    rfa.setTriggeringPolicy(sbtp);
    rfa.activateOptions();
    root.addAppender(rfa);

    // Write exactly 10 bytes with each log
    for (int i = 0; i < 25; i++) {
      Thread.sleep(1000);
      if (i < 10) {
        logger.debug("Hello   " + i);
      } else if (i < 100) {
        logger.debug("Hello  " + i);
      }
    }

    // The File.length() method is not accurate under Windows    

    /**
     *
     if(!isWindows()) {

      assertTrue(Compare.compare("output/sizeBased-test2.1",
         "witness/sizeBased-test1.1"));
      assertTrue(Compare.compare("output/sizeBased-test2.2",
         "witness/sizeBased-test1.2"));
      assertTrue(Compare.compare("output/sizeBased-test2.3",
         "witness/sizeBased-test1.3"));
         }

         */
  }

  boolean isWindows() {
    return System.getProperty("os.name").indexOf("Windows") != -1;
  }

  public static Test suite() {
    TestSuite suite = new TestSuite();

    suite.addTest(new SizeBasedRollingTestCase("test1"));
    suite.addTest(new SizeBasedRollingTestCase("test2"));

    return suite;
  }
}