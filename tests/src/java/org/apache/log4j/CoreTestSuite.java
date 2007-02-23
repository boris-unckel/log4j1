/*
 * Copyright 1999,2005 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
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

import junit.framework.Test;
import junit.framework.TestSuite;
import org.apache.log4j.spi.LoggingEventTest;


/**
 * Suite of log4j class level unit tests.
 *
 * @author Curt Arnold
 */
public class CoreTestSuite {
    /**
     * Constructs test suite.
     * @return test suite
     */
    public static Test suite() {
        TestSuite s = new TestSuite();
        s.addTestSuite(LoggingEventTest.class);
        s.addTestSuite(org.apache.log4j.LevelTest.class);
        s.addTestSuite(org.apache.log4j.PriorityTest.class);
        s.addTestSuite(org.apache.log4j.CategoryTest.class);
        s.addTestSuite(org.apache.log4j.FileAppenderTest.class);
        s.addTestSuite(org.apache.log4j.LogManagerTest.class);
        s.addTestSuite(org.apache.log4j.helpers.LogLogTest.class);
        s.addTestSuite(org.apache.log4j.LayoutTest.class);
        s.addTestSuite(org.apache.log4j.helpers.DateLayoutTest.class);
        s.addTestSuite(org.apache.log4j.TTCCLayoutTest.class);
        s.addTestSuite(org.apache.log4j.xml.XMLLayoutTest.class);
        s.addTestSuite(org.apache.log4j.HTMLLayoutTest.class);
        s.addTestSuite(org.apache.log4j.PatternLayoutTest.class);
        s.addTestSuite(org.apache.log4j.spi.LoggingEventTest.class);
        s.addTestSuite(org.apache.log4j.spi.ThrowableInformationTest.class);
        s.addTestSuite(org.apache.log4j.PropertyConfigurator.class);
        return s;
    }
}
