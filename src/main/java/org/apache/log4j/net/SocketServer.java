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

import java.io.File;
import java.net.InetAddress;
import java.util.Hashtable;


import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerRepository;

/**
 * No Operations. Avoid CVE-2019-17571
 * https://cve.mitre.org/cgi-bin/cvename.cgi?name=CVE-2019-17571
 **/

public class SocketServer  {

  static String GENERIC = "generic";
  static String CONFIG_FILE_EXT = ".lcf";

  static Logger cat = Logger.getLogger(SocketServer.class);
  static SocketServer server;
  static int port;

  // key=inetAddress, value=hierarchy
  Hashtable hierarchyMap;
  LoggerRepository genericHierarchy;
  File dir;

  public
  static
  void main(String argv[]) {
      //NOP
  }


  static
  void  usage(String msg) {
      //NOP
  }

  static
  void init(String portStr, String configFile, String dirStr) {
      //NOP
  }


  public
  SocketServer(File directory) {
      //NOP
  }

  // This method assumes that there is no hiearchy for inetAddress
  // yet. It will configure one and return it.
  LoggerRepository configureHierarchy(InetAddress inetAddress) {
    return null;
  }

  LoggerRepository  genericHierarchy() {
      return null;
  }
}
