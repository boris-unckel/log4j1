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

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.spi.ErrorCode;
import org.apache.log4j.spi.LoggingEvent;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameNotFoundException;
import javax.naming.NamingException;
import java.util.Properties;

/**
 * No Operations. Avoid CVE-2021-4104
 * https://cve.mitre.org/cgi-bin/cvename.cgi?name=CVE-2021-4104
 **/
public class JMSAppender extends AppenderSkeleton {

  String securityPrincipalName;
  String securityCredentials;
  String initialContextFactoryName;
  String urlPkgPrefixes;
  String providerURL;
  String topicBindingName;
  String tcfBindingName;
  String userName;
  String password;
  boolean locationInfo;

  TopicConnection  topicConnection;
  TopicSession topicSession;
  TopicPublisher  topicPublisher;

  public
  JMSAppender() {
  }

  /**
     The <b>TopicConnectionFactoryBindingName</b> option takes a
     string value. Its value will be used to lookup the appropriate
     <code>TopicConnectionFactory</code> from the JNDI context.
   */
  public
  void setTopicConnectionFactoryBindingName(String tcfBindingName) {
    //NOP
  }

  /**
     Returns the value of the <b>TopicConnectionFactoryBindingName</b> option.
   */
  public
  String getTopicConnectionFactoryBindingName() {
    return null;
  }

  /**
     The <b>TopicBindingName</b> option takes a
     string value. Its value will be used to lookup the appropriate
     <code>Topic</code> from the JNDI context.
   */
  public
  void setTopicBindingName(String topicBindingName) {
    //NOP
  }

  /**
     Returns the value of the <b>TopicBindingName</b> option.
   */
  public
  String getTopicBindingName() {
    return null;
  }


  /**
     Returns value of the <b>LocationInfo</b> property which
     determines whether location (stack) info is sent to the remote
     subscriber. */
  public
  boolean getLocationInfo() {
    return false;
  }

  /**
   *  Options are activated and become effective only after calling
   *  this method.*/
  public void activateOptions() {
    //NOP
  }

  protected Object lookup(Context ctx, String name) throws NamingException {
    return null;
  }

  protected boolean checkEntryConditions() {
    return false;
  }

  /**
     Close this JMSAppender. Closing releases all resources used by the
     appender. A closed appender cannot be re-opened. */
  public synchronized void close() {
    //NOP
  }

  /**
     This method called by {@link AppenderSkeleton#doAppend} method to
     do most of the real appending work.  */
  public void append(LoggingEvent event) {
    //NOP
  }

  /**
   * Returns the value of the <b>InitialContextFactoryName</b> option.
   * See {@link #setInitialContextFactoryName} for more details on the
   * meaning of this option.
   * */
  public String getInitialContextFactoryName() {
    return null;
  }

  /**
   * Setting the <b>InitialContextFactoryName</b> method will cause
   * this <code>JMSAppender</code> instance to use the {@link
   * InitialContext#InitialContext(Hashtable)} method instead of the
   * no-argument constructor. If you set this option, you should also
   * at least set the <b>ProviderURL</b> option.
   *
   * <p>See also {@link #setProviderURL(String)}.
   * */
  public void setInitialContextFactoryName(String initialContextFactoryName) {
    //NOP
  }

  public String getProviderURL() {
    return null;
  }

  public void setProviderURL(String providerURL) {
    //NOP
  }

  String getURLPkgPrefixes( ) {
    return null;
  }

  public void setURLPkgPrefixes(String urlPkgPrefixes ) {
    //NOP
  }

  public String getSecurityCredentials() {
    return null;
  }

  public void setSecurityCredentials(String securityCredentials) {
    //NOP
  }


  public String getSecurityPrincipalName() {
    return null;
  }

  public void setSecurityPrincipalName(String securityPrincipalName) {
    //NOP
  }

  public String getUserName() {
    return null;
  }

  /**
   * The user name to use when {@link
   * TopicConnectionFactory#createTopicConnection(String, String)
   * creating a topic session}.  If you set this option, you should
   * also set the <b>Password</b> option. See {@link
   * #setPassword(String)}.
   * */
  public void setUserName(String userName) {
    //NOP
  }

  public String getPassword() {
    return null;
  }

  /**
   * The paswword to use when creating a topic session.
   */
  public void setPassword(String password) {
    //NOP
  }


  /**
      If true, the information sent to the remote subscriber will
      include caller's location information. By default no location
      information is sent to the subscriber.  */
  public void setLocationInfo(boolean locationInfo) {
    //NOP
  }

  /**
   * Returns the TopicConnection used for this appender.  Only valid after
   * activateOptions() method has been invoked.
   */
  protected TopicConnection  getTopicConnection() {
    return null;
  }

  /**
   * Returns the TopicSession used for this appender.  Only valid after
   * activateOptions() method has been invoked.
   */
  protected TopicSession  getTopicSession() {
    return null;
  }

  /**
   * Returns the TopicPublisher used for this appender.  Only valid after
   * activateOptions() method has been invoked.
   */
  protected TopicPublisher  getTopicPublisher() {
    return null;
  }

  /**
   * The JMSAppender sends serialized events and consequently does not
   * require a layout.
   */
  public boolean requiresLayout() {
    return false;
  }
}
