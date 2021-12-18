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

package org.apache.log4j.net;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.spi.LoggingEvent;
import org.apache.log4j.xml.DOMConfigurator;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameNotFoundException;
import javax.naming.NamingException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * No Operations. Avoid CVE-2021-4104
 * https://cve.mitre.org/cgi-bin/cvename.cgi?name=CVE-2021-4104
 *
 *
 * */
public class JMSSink implements javax.jms.MessageListener {

  static Logger logger = Logger.getLogger(JMSSink.class);

  static public void main(String[] args) throws Exception {
    //NOP
  }

  public JMSSink( String tcfBindingName, String topicBindingName, String username,
		  String password) {
    //NOP
  }

  public void onMessage(javax.jms.Message message) {
    //NOP
  }


  protected static Object lookup(Context ctx, String name) throws NamingException {
    return null;
  }

  static void usage(String msg) {
    //NOP
  }
}
