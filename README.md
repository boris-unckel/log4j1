Fork of Apache log4j 1
========================

Based on https://logging.apache.org/log4j/1.2/

Sources https://svn.apache.org/repos/asf/logging/log4j/tags/v1_2_17/

Goals
========================
Turn CVE affected classes to NOP classes.

Non-Goals
========================
Any further development. 

Recommendation
========================
Please use a logging facade in your code

* https://github.com/jboss-logging/jboss-logging
* https://mvnrepository.com/artifact/org.jboss.logging/jboss-logging

and simply use it. Don't extend it.

A modern log backend

* https://github.com/jboss-logging/jboss-logmanager
* https://mvnrepository.com/artifact/org.jboss.logmanager/jboss-logmanager

and simply use it. Don't extend it.
