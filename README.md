# Splash Focus

SplashFocus is an application built on Java Spring 3.0 to provide users the ability to search in realtime for, and to fill seasonal jobs.

## Key Features

* Ability to see when a user was last active to reduce the amount of time messaging inactive users
* Ability to search your area for jobs, and users
* Free job postings to permit small businesses an option to search and fill positions

## Getting Started

Download/Setup Code
+ Clone git
+ Import project
+ Update maven project
+ Build maven: "mvn clean install" through terminal in project root directory or build rules "clean install"
+ Ensure Runtimes is selecting Tomcat 8.0
+ [Download](https://bitbucket.org/awhipp/bitsngigs/downloads/mysql-connector-java-5.1.38-bin.jar) and add MySQLConnector*.java to your TomcatDirectory's /lib folder

Tomcat Settings in Eclipse
+ Change Module root to /
+ Auto reload disabled


```
#!xml

<Resource name="jdbc/jsearch" auth="Container" type="javax.sql.DataSource"
    username="[USERNAME]" password="[PASSWORD]" driverClassName="com.mysql.jdbc.Driver" description="Global Address Database"
    url="jdbc:mysql://localhost:3306/[DBNAME]" maxActive="15" maxIdle="3"/>
```
^ context.xml

#### System Requirements

* JDK 1.8 / JRE 1.8 (Point JAVA_HOME to JRE)
* Install Maven 3.3.x or higher
* Install Apache Tomcat 8
* Install Eclipse IDE for Java EE Developers
* Maven Integration for Eclipse: http://download.eclipse.org/m2e-wtp/milestones/neon/1.3/
* MySQL 5.7.x or higher (http://dev.mysql.com/downloads/mysql/)


#### Some Websites Used

WebApp based on [this tutorial.](http://crunchify.com/simplest-spring-mvc-hello-world-example-tutorial-spring-model-view-controller-tips/) Follow along for more information.

For AJAX calls [here.](http://crunchify.com/how-to-use-ajax-jquery-in-spring-web-mvc-jsp-example/)

Spring Security [follows this.](http://www.beingjavaguys.com/2014/05/spring-security-authentication-and.html?m=1)

####  Common Issues in Eclipse

+  Window > Preferences > Java > Installed JREs > and check your installed JREs. You should have an entry with a JDK there. Select the Execution Env as show below. Click OK 
+  Then RightClick Project -> Maven -> Update Project
+  [java.lang.ClassNotFoundException: org.springframework.web.context.ContextLoaderListener](http://stackoverflow.com/questions/6210757/java-lang-classnotfoundexception-org-springframework-web-context-contextloaderl)
+ [The superclass "javax.servlet.http.HttpServlet" was not found on the Java Build Path](http://stackoverflow.com/questions/22756153/the-superclass-javax-servlet-http-httpservlet-was-not-found-on-the-java-build)

## Contribution guidelines

* Write JUnit tests after completing any class
* All code commits go to the dev branch.
* Master branch only updated through pull request on regular intervals
