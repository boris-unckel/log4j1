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

import java.io.ObjectInputStream;
import java.net.Socket;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerRepository;

/**
 * No Operations. Avoid CVE-2017-5645
 * https://cve.mitre.org/cgi-bin/cvename.cgi?name=CVE-2017-5645
 **/
public class SocketNode implements Runnable {

  Socket socket;
  LoggerRepository hierarchy;
  ObjectInputStream ois;

  static Logger logger = Logger.getLogger(SocketNode.class);

  public SocketNode(Socket socket, LoggerRepository hierarchy) {
    //NOP
  }

  public void run() {
    //NOP
  }
}
