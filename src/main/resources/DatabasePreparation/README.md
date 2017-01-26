Database Preparation
====================

For instructors and self-taught students, these instructions will walk you
through what is necessary to prepare an H2 database for use with this course.

Prerequisites
-------------

* Java 8

Instructions
------------

### For Instructors

* Acquire a shared machine that is accessible by all students. From  within 
  Union Pacific's network, a temporary, Linux server can be requested prior to 
  class start that fills this requirement. Be sure to submit a SID for Java 8 
  to be installed, and an IDM request for your user account.
* Copy the H2 jar (`h2-VERSION.jar`, next to this README) to the shared machine.
* Create a properties file for H2 so that the web console is started. Place the
  file in `~/.h2.server.properties`
  ```
  webAllowOthers=true
  webPort=8082
  webSSL=false
  ``` 
* From the shared machine, create an empty database directory and start the H2 
  server with the TCP protocol
  ```
  $ mkdir -p ~/lean-spring/db
  $ java -jar /home/david/Desktop/h2-1.4.192.jar -tcp -tcpAllowOthers -web
  ```
  This will start an H2 server on port `9092` with an administrator account 
  named `sa` and blank password.
* Add each of your student's user IDs (or names, or some unique string) to the
  `learn_spring_ddl_values.json` JSON file. Remove any examples that may be in
  the file to start with.
* Load all students' user accounts and schemas via the H2 database generator 
  application (included here, also found on GitHub [here][1])
  ```
  $ java -jar generate-h2-database-0.0.1-SNAPSHOT.jar \
      --host=<YOUR_SHARED_HOST> \
      --database=~/learn-spring/db/pokemon \
      --template=./learn_spring_ddl_template.sql \
      --values=./learn_spring_ddl_values.json
  ```
* If the server is accessible to the general Internet, be sure to shut it down
  when not in use, or reset the passwords to something secure (not a problem for
  temporary servers acquired via Union Pacific's SCP process).

### For Self-Taught Students

* Start the H2 server locally
* More coming soon... Or not...

[1]: https://github.com/DavidZemon/GenerateH2Database
