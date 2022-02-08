Fork of Apache log4j 1
========================

Based on https://logging.apache.org/log4j/1.2/

Sources https://svn.apache.org/repos/asf/logging/log4j/tags/v1_2_17/

Alternative Fork Reload4J
========================
A better alternative fork, keeping functions, hardening more:

https://reload4j.qos.ch/

```xml
<!-- https://mvnrepository.com/artifact/ch.qos.reload4j/reload4j -->
<dependency>
    <groupId>ch.qos.reload4j</groupId>
    <artifactId>reload4j</artifactId>
    <version>1.2.18.5</version>
</dependency>
```

(Check for more recent version)

Goals
========================
Turn CVE affected classes to NOP classes.

Non-Goals
========================
Any further development. 

Build instructions
========================

```mvn clean install```

Standalone Ant build or other targets are not supported.

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
