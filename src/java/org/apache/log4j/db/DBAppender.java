/*
 * Copyright 1999,2004 The Apache Software Foundation.
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
package org.apache.log4j.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.Set;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.db.dialect.MySQLDialect;
import org.apache.log4j.db.dialect.OracleDialect;
import org.apache.log4j.db.dialect.PostgreSQLDialect;
import org.apache.log4j.db.dialect.SQLDialect;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.spi.LoggingEvent;


/**
 *
 * @author Ceki G&uuml;lc&uuml;
 *
 */
public class DBAppender
       extends AppenderSkeleton {
  ConnectionSource connectionSource;
  SQLDialect sqlDialect;

  public void activateOptions() {
    if (connectionSource != null) {
      connectionSource.activateOptions();
    } else {
      throw new IllegalStateException("DBAppender cannot function without a connection source");
    }

    switch (connectionSource.getSQLDialect()) {
    case ConnectionSource.POSTGRES_DIALECT :
      sqlDialect = new PostgreSQLDialect();
      break;
    case ConnectionSource.MYSQL_DIALECT :
      sqlDialect = new MySQLDialect();
      break;
    case ConnectionSource.ORACLE_DIALECT :
      sqlDialect = new OracleDialect();
      break;
    }
  }


  /**
   * @return Returns the connectionSource.
   */
  public ConnectionSource getConnectionSource() {
    return connectionSource;
  }


  /**
   * @param connectionSource The connectionSource to set.
   */
  public void setConnectionSource(ConnectionSource connectionSource) {
    this.connectionSource = connectionSource;
  }


  protected void append(LoggingEvent event) {
    Set propertiesKeys = event.getPropertyKeySet();
    String[] throwableStrRep = event.getThrowableStrRep();
    
    try {
      Connection connection = connectionSource.getConnection();
      connection.setAutoCommit(false);

//      sequence_number BIGINT NOT NULL,
//      timestamp         BIGINT NOT NULL,
//      rendered_message  TEXT NOT NULL,
//      logger_name       VARCHAR(254) NOT NULL,
//      level_string      VARCHAR(254) NOT NULL,
//      ndc               TEXT,
//      thread_name       VARCHAR(254),
//      id                INT NOT NULL AUTO_INCREMENT PRIMARY KEY
      StringBuffer sql = new StringBuffer();
      sql.append("INSERT INTO logging_event (");
      sql.append("sequence_number, timestamp, rendered_message, ");
      sql.append("logger_name, level_string, ndc, thread_name, flag) ");
      sql.append(" VALUES (?, ?, ? ,?, ?, ?, ?)");

      PreparedStatement insertStatement = connection.prepareStatement(sql.toString());
      insertStatement.setLong(1, event.getSequenceNumber());
      insertStatement.setLong(2, event.getTimeStamp());
      insertStatement.setString(3, event.getRenderedMessage());
      insertStatement.setString(4, event.getLoggerName());
      insertStatement.setString(5, event.getLevel().toString());
      insertStatement.setString(6, event.getNDC());
      insertStatement.setString(7, event.getThreadName());
      insertStatement.setShort(8, computeFlag(event, propertiesKeys, throwableStrRep));
      
      int updateCount = insertStatement.executeUpdate();

      if (updateCount != 1) {
        LogLog.warn("Failed to insert loggingEvent");
      }

      Statement idStatement = connection.createStatement();
      idStatement.setMaxRows(1);

      ResultSet rs = idStatement.executeQuery(sqlDialect.getSelectInsertId());
      rs.first();

      int eventId = rs.getInt(1);
      LogLog.info("inserted id is " + eventId);

//      event_id        INT NOT NULL,
//      mapped_key        VARCHAR(254) NOT NULL,
//      mapped_value      VARCHAR(254),

      if (propertiesKeys.size() > 0) {
        String insertPropertiesSQL = "INSERT INTO  logging_event_property (event_id, mapped_key, mapped_value) VALUES (?, ?, ?)";
        PreparedStatement insertMDCStatement = connection.prepareStatement(insertPropertiesSQL);

        for (Iterator i = propertiesKeys.iterator(); i.hasNext();) {
          String key = (String)i.next();
          String value = (String)event.getProperty(key);
          LogLog.debug("id " + eventId + ", key " + key + ", value " + value);
          insertMDCStatement.setInt(1, eventId);
          insertMDCStatement.setString(2, key);
          insertMDCStatement.setString(3, value);
          insertMDCStatement.addBatch();
        }
        insertMDCStatement.executeBatch();
      }

      connection.commit();
    } catch (SQLException sqle) {
      LogLog.error("problem appending event", sqle);
    }
  }


  short computeFlag(LoggingEvent event, Set propertyJeys, String[] strRep) {

    return 0;
  }
  
  public void close() {
    // TODO Auto-generated method st  
  }


  /*
   * The DBAppender does not require a layout.
   */
  public boolean requiresLayout() {
    return false;
  }
}
