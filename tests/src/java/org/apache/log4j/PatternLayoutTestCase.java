/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.log4j;

import junit.framework.TestCase;
import org.apache.log4j.util.AbsoluteDateAndTimeFilter;
import org.apache.log4j.util.AbsoluteTimeFilter;
import org.apache.log4j.util.Compare;
import org.apache.log4j.util.ControlFilter;
import org.apache.log4j.util.Filter;
import org.apache.log4j.util.ISO8601Filter;
import org.apache.log4j.util.JunitTestRunnerFilter;
import org.apache.log4j.util.LineNumberFilter;
import org.apache.log4j.util.RelativeTimeFilter;
import org.apache.log4j.util.SunReflectFilter;
import org.apache.log4j.util.Transformer;

import java.io.FileReader;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;


public class PatternLayoutTestCase extends TestCase {
  static String TEMP = "output/temp";
  static String FILTERED = "output/filtered";
  static String EXCEPTION1 = "java.lang.Exception: Just testing";
  static String EXCEPTION2 = "\\s*at .*\\(.*:\\d{1,4}\\)";
  static String EXCEPTION3 = "\\s*at .*\\(Native Method\\)";
  static String EXCEPTION4 = "\\s*at .*\\(.*Compiled Code\\)";

  static String PAT0 =
    "\\[main]\\ (DEBUG|INFO|WARN|ERROR|FATAL) .* - Message \\d{1,2}";
  static String PAT1 = Filter.ISO8601_PAT + " " + PAT0;
  static String PAT2 = Filter.ABSOLUTE_DATE_AND_TIME_PAT + " " + PAT0;
  static String PAT3 = Filter.ABSOLUTE_TIME_PAT + " " + PAT0;
  static String PAT4 = Filter.RELATIVE_TIME_PAT + " " + PAT0;
  static String PAT5 =
    "\\[main]\\ (DEBUG|INFO|WARN|ERROR|FATAL) .* : Message \\d{1,2}";
  static String PAT6 =
    "\\[main]\\ (DEBUG|INFO |WARN |ERROR|FATAL) org.apache.log4j.PatternLayoutTestCase.common\\(PatternLayoutTestCase.java:\\d{1,4}\\): Message \\d{1,2}";
  static String PAT11a =
    "^(DEBUG|INFO |WARN |ERROR|FATAL) \\[main]\\ log4j.PatternLayoutTest: Message \\d{1,2}";
  static String PAT11b =
    "^(DEBUG|INFO |WARN |ERROR|FATAL) \\[main]\\ root: Message \\d{1,2}";
  static String PAT12 =
    "^\\[main]\\ (DEBUG|INFO |WARN |ERROR|FATAL) "
    + "org.apache.log4j.PatternLayoutTestCase.common\\(PatternLayoutTestCase.java:\\d{3}\\): "
    + "Message \\d{1,2}";
  static String PAT13 =
    "^\\[main]\\ (DEBUG|INFO |WARN |ERROR|FATAL) "
    + "apache.log4j.PatternLayoutTestCase.common\\(PatternLayoutTestCase.java:\\d{3}\\): "
    + "Message \\d{1,2}";
  static String PAT14 =
    "^(TRACE|DEBUG| INFO| WARN|ERROR|FATAL)\\ \\d{1,2}\\ *- Message \\d{1,2}";
  static String PAT_MDC_1 = "";
  Logger root;
  Logger logger;

  public PatternLayoutTestCase(final String name) {
    super(name);
  }

  public void setUp() {
    root = Logger.getRootLogger();
    logger = Logger.getLogger(PatternLayoutTest.class);
  }

  public void tearDown() {
    root.getLoggerRepository().resetConfiguration();
  }

  public void test1() throws Exception {
    PropertyConfigurator.configure("input/pattern/patternLayout1.properties");
    common();
    Transformer.transform(
      TEMP, FILTERED,
      new Filter[] {
        new LineNumberFilter(), new SunReflectFilter(),
        new JunitTestRunnerFilter()
      });
    assertTrue(Compare.compare(FILTERED, "witness/pattern/patternLayout.1"));
  }

  public void test2() throws Exception {
    PropertyConfigurator.configure("input/pattern/patternLayout2.properties");
    common();

    ControlFilter cf1 =
      new ControlFilter(
        new String[] { PAT1, EXCEPTION1, EXCEPTION2, EXCEPTION3 });
    Transformer.transform(
      TEMP, FILTERED,
      new Filter[] {
        cf1, new LineNumberFilter(), new ISO8601Filter(),
        new SunReflectFilter(), new JunitTestRunnerFilter()
      });
    assertTrue(Compare.compare(FILTERED, "witness/pattern/patternLayout.2"));
  }

  public void test3() throws Exception {
    PropertyConfigurator.configure("input/pattern/patternLayout3.properties");
    common();

    ControlFilter cf1 =
      new ControlFilter(
        new String[] { PAT1, EXCEPTION1, EXCEPTION2, EXCEPTION3 });
    Transformer.transform(
      TEMP, FILTERED,
      new Filter[] {
        cf1, new LineNumberFilter(), new ISO8601Filter(),
        new SunReflectFilter(), new JunitTestRunnerFilter()
      });
    assertTrue(Compare.compare(FILTERED, "witness/pattern/patternLayout.3"));
  }

  // Output format:
  // 06 avr. 2002 18:30:58,937 [main] DEBUG atternLayoutTest - Message 0  
  public void test4() throws Exception {
    PropertyConfigurator.configure("input/pattern/patternLayout4.properties");
    common();

    ControlFilter cf1 =
      new ControlFilter(
        new String[] { PAT2, EXCEPTION1, EXCEPTION2, EXCEPTION3 });
    Transformer.transform(
      TEMP, FILTERED,
      new Filter[] {
        cf1, new LineNumberFilter(), new AbsoluteDateAndTimeFilter(),
        new SunReflectFilter(), new JunitTestRunnerFilter()
      });
    assertTrue(Compare.compare(FILTERED, "witness/pattern/patternLayout.4"));
  }

  public void test5() throws Exception {
    PropertyConfigurator.configure("input/pattern/patternLayout5.properties");
    common();

    ControlFilter cf1 =
      new ControlFilter(
        new String[] { PAT2, EXCEPTION1, EXCEPTION2, EXCEPTION3 });
    Transformer.transform(
      TEMP, FILTERED,
      new Filter[] {
        cf1, new LineNumberFilter(), new AbsoluteDateAndTimeFilter(),
        new SunReflectFilter(), new JunitTestRunnerFilter()
      });
    assertTrue(Compare.compare(FILTERED, "witness/pattern/patternLayout.5"));
  }

  // 18:54:19,201 [main] DEBUG atternLayoutTest - Message 0
  public void test6() throws Exception {
    PropertyConfigurator.configure("input/pattern/patternLayout6.properties");
    common();

    ControlFilter cf1 =
      new ControlFilter(
        new String[] { PAT3, EXCEPTION1, EXCEPTION2, EXCEPTION3 });
    Transformer.transform(
      TEMP, FILTERED,
      new Filter[] {
        cf1, new LineNumberFilter(), new AbsoluteTimeFilter(),
        new SunReflectFilter(), new JunitTestRunnerFilter()
      });
    assertTrue(Compare.compare(FILTERED, "witness/pattern/patternLayout.6"));
  }

  public void test7() throws Exception {
    PropertyConfigurator.configure("input/pattern/patternLayout7.properties");
    common();

    ControlFilter cf1 =
      new ControlFilter(
        new String[] { PAT3, EXCEPTION1, EXCEPTION2, EXCEPTION3 });
    Transformer.transform(
      TEMP, FILTERED,
      new Filter[] {
        cf1, new LineNumberFilter(), new AbsoluteTimeFilter(),
        new SunReflectFilter(), new JunitTestRunnerFilter()
      });
    assertTrue(Compare.compare(FILTERED, "witness/pattern/patternLayout.7"));
  }

  public void test8() throws Exception {
    PropertyConfigurator.configure("input/pattern/patternLayout8.properties");
    common();

    ControlFilter cf1 =
      new ControlFilter(
        new String[] { PAT4, EXCEPTION1, EXCEPTION2, EXCEPTION3 });
    Transformer.transform(
      TEMP, FILTERED,
      new Filter[] {
        cf1, new LineNumberFilter(), new RelativeTimeFilter(),
        new SunReflectFilter(), new JunitTestRunnerFilter()
      });
    assertTrue(Compare.compare(FILTERED, "witness/pattern/patternLayout.8"));
  }

  public void test9() throws Exception {
    PropertyConfigurator.configure("input/pattern/patternLayout9.properties");
    common();

    ControlFilter cf1 =
      new ControlFilter(
        new String[] { PAT5, EXCEPTION1, EXCEPTION2, EXCEPTION3 });
    Transformer.transform(
      TEMP, FILTERED,
      new Filter[] {
        cf1, new LineNumberFilter(), new SunReflectFilter(),
        new JunitTestRunnerFilter()
      });
    assertTrue(Compare.compare(FILTERED, "witness/pattern/patternLayout.9"));
  }

  public void test10() throws Exception {
    PropertyConfigurator.configure("input/pattern/patternLayout10.properties");
    common();

    ControlFilter cf1 =
      new ControlFilter(
        new String[] { PAT6, EXCEPTION1, EXCEPTION2, EXCEPTION3 });
    Transformer.transform(
      TEMP, FILTERED,
      new Filter[] {
        cf1, new LineNumberFilter(), new SunReflectFilter(),
        new JunitTestRunnerFilter()
      });
    assertTrue(Compare.compare(FILTERED, "witness/pattern/patternLayout.10"));
  }

  public void test11() throws Exception {
    PropertyConfigurator.configure("input/pattern/patternLayout11.properties");
    common();

    ControlFilter cf1 =
      new ControlFilter(
        new String[] { PAT11a, PAT11b, EXCEPTION1, EXCEPTION2, EXCEPTION3 });
    Transformer.transform(
      TEMP, FILTERED,
      new Filter[] {
        cf1, new LineNumberFilter(), new SunReflectFilter(),
        new JunitTestRunnerFilter()
      });
    assertTrue(Compare.compare(FILTERED, "witness/pattern/patternLayout.11"));
  }

  public void test12() throws Exception {
    PropertyConfigurator.configure("input/pattern/patternLayout12.properties");
    common();

    ControlFilter cf1 =
      new ControlFilter(
        new String[] { PAT12, EXCEPTION1, EXCEPTION2, EXCEPTION3 });
    Transformer.transform(
      TEMP, FILTERED,
      new Filter[] {
        cf1, new LineNumberFilter(), new SunReflectFilter(),
        new JunitTestRunnerFilter()
      });
    assertTrue(Compare.compare(FILTERED, "witness/pattern/patternLayout.12"));
  }

  public void test13() throws Exception {
    PropertyConfigurator.configure("input/pattern/patternLayout13.properties");
    common();

    ControlFilter cf1 =
      new ControlFilter(
        new String[] { PAT13, EXCEPTION1, EXCEPTION2, EXCEPTION3 });
    Transformer.transform(
      TEMP, FILTERED,
      new Filter[] {
        cf1, new LineNumberFilter(), new SunReflectFilter(),
        new JunitTestRunnerFilter()
      });
    assertTrue(Compare.compare(FILTERED, "witness/pattern/patternLayout.13"));
  }

    /**
     * Test of class abbreviation.
     *
     * @throws Exception
     */
    public void test14() throws Exception {
      PropertyConfigurator.configure("input/pattern/patternLayout14.properties");
      common();

      Transformer.transform(
        TEMP, FILTERED,
        new Filter[] {
          new LineNumberFilter(), new SunReflectFilter(),
          new JunitTestRunnerFilter()
        });
      assertTrue(Compare.compare(FILTERED, "witness/pattern/patternLayout.14"));
    }


  public void testMDC1() throws Exception {
    PropertyConfigurator.configure("input/pattern/patternLayout.mdc.1.properties");
    MDC.put("key1", "va11");
    MDC.put("key2", "va12");
    logger.debug("Hello World");
    MDC.clear();

    Transformer.transform(
      TEMP, FILTERED,
      new Filter[] {
        new LineNumberFilter(), new SunReflectFilter(),
        new JunitTestRunnerFilter()
      });
    assertTrue(Compare.compare(FILTERED, "witness/pattern/patternLayout.mdc.1"));
  }

    /**
     * Tests log4j 1.2 style extension of PatternLayout.
     * Was test14 in log4j 1.2.
     * @throws Exception
     */
    public void test15() throws Exception {
      PropertyConfigurator.configure("input/pattern/patternLayout15.properties");
      common();
      ControlFilter cf1 = new ControlFilter(new String[]{PAT14, EXCEPTION1,
                                 EXCEPTION2, EXCEPTION3, EXCEPTION4});
      Transformer.transform(
        TEMP, FILTERED,
        new Filter[] {
          cf1, new LineNumberFilter(), new SunReflectFilter(),
          new JunitTestRunnerFilter()
        });
      assertTrue(Compare.compare(FILTERED, "witness/pattern/patternLayout.15"));
    }

    /**
     * Tests explicit UTC time zone in pattern.
     * @throws Exception
     */
    public void test16() throws Exception {
      final long start = new Date().getTime();
      PropertyConfigurator.configure("input/pattern/patternLayout16.properties");
      common();
      final long end = new Date().getTime();
      FileReader reader = new FileReader("output/patternLayout16.log");
      char chars[] = new char[50];
      reader.read(chars, 0, chars.length);
      reader.close();
      SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      format.setTimeZone(TimeZone.getTimeZone("GMT+0"));
      String utcStr = new String(chars, 0, 19);
      Date utcDate = format.parse(utcStr, new ParsePosition(0));
      assertTrue(utcDate.getTime() >= start - 1000 && utcDate.getTime() < end + 1000);
      String cstStr = new String(chars, 21, 19);
      format.setTimeZone(TimeZone.getTimeZone("GMT-6"));
      Date cstDate = format.parse(cstStr, new ParsePosition(0));
      assertFalse(cstStr.equals(utcStr));
      assertTrue(cstDate.getTime() >= start - 1000 && cstDate.getTime() < end + 1000);
    }


  void common() {
    int i = -1;

    logger.debug("Message " + ++i);
    root.debug("Message " + i);

    logger.info("Message " + ++i);
    root.info("Message " + i);

    logger.warn("Message " + ++i);
    root.warn("Message " + i);

    logger.error("Message " + ++i);
    root.error("Message " + i);

    logger.log(Level.FATAL, "Message " + ++i);
    root.log(Level.FATAL, "Message " + i);

    Exception e = new Exception("Just testing");

    logger.debug("Message " + ++i, e);
    logger.info("Message " + ++i, e);
    logger.warn("Message " + ++i, e);
    logger.error("Message " + ++i, e);
    logger.log(Level.FATAL, "Message " + ++i, e);
  }

  /**
    Test case for MDC conversion pattern. */
  public void testMDC2() throws Exception {
    String OUTPUT_FILE   = "output/patternLayout.mdc.2";
    String WITNESS_FILE  = "witness/pattern/patternLayout.mdc.2";
    
    String mdcMsgPattern1 = "%m : %X%n";
    String mdcMsgPattern2 = "%m : %X{key1}%n";
    String mdcMsgPattern3 = "%m : %X{key2}%n";
    String mdcMsgPattern4 = "%m : %X{key3}%n";
    String mdcMsgPattern5 = "%m : %X{key1},%X{key2},%X{key3}%n";
    
    // set up appender
    PatternLayout layout = new PatternLayout("%m%n");
    Appender appender = new FileAppender(layout, OUTPUT_FILE, false);
            
    // set appender on root and set level to debug
    root.addAppender(appender);
    root.setLevel(Level.DEBUG);
    
    // output starting message
    root.debug("starting mdc pattern test");
 
    layout.setConversionPattern(mdcMsgPattern1);
    layout.activateOptions();
    root.debug("empty mdc, no key specified in pattern");
    
    layout.setConversionPattern(mdcMsgPattern2);
    layout.activateOptions();
    root.debug("empty mdc, key1 in pattern");
    
    layout.setConversionPattern(mdcMsgPattern3);
    layout.activateOptions();
    root.debug("empty mdc, key2 in pattern");
    
    layout.setConversionPattern(mdcMsgPattern4);
    layout.activateOptions();
    root.debug("empty mdc, key3 in pattern");
    
    layout.setConversionPattern(mdcMsgPattern5);
    layout.activateOptions();
    root.debug("empty mdc, key1, key2, and key3 in pattern");

    MDC.put("key1", "value1");
    MDC.put("key2", "value2");

    layout.setConversionPattern(mdcMsgPattern1);
    layout.activateOptions();
    root.debug("filled mdc, no key specified in pattern");
    
    layout.setConversionPattern(mdcMsgPattern2);
    layout.activateOptions();
    root.debug("filled mdc, key1 in pattern");
    
    layout.setConversionPattern(mdcMsgPattern3);
    layout.activateOptions();
    root.debug("filled mdc, key2 in pattern");
    
    layout.setConversionPattern(mdcMsgPattern4);
    layout.activateOptions();
    root.debug("filled mdc, key3 in pattern");
    
    layout.setConversionPattern(mdcMsgPattern5);
    layout.activateOptions();
    root.debug("filled mdc, key1, key2, and key3 in pattern");

    MDC.remove("key1");
    MDC.remove("key2");

    layout.setConversionPattern("%m%n");
    layout.activateOptions();
    root.debug("finished mdc pattern test");

    assertTrue(Compare.compare(OUTPUT_FILE, WITNESS_FILE));
  }
}
