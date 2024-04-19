# Install

To include the resources pack you will need to add the following code to the Parent POM file:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <parent>
    <groupId>uk.pallas.systems</groupId>
    <artifactId>parent</artifactId>
    <version>1.0.3</version>
  </parent>

  <properties>
    <github_repository_name>typr</github_repository_name>
    <scmpublish.content>${project.build.directory}/staging/parent</scmpublish.content>
  </properties>

</project>
```
