# User Guide

This guide provides details on each field and how to incorporate the skin

## site.xml configuration

You will need to define a ```site.xml``` file and define 

```xml
<project xmlns="http://maven.apache.org/DECORATION/1.8.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/DECORATION/1.8.0 http://maven.apache.org/xsd/decoration-1.8.0.xsd">
    <edit>${project.scm.url}</edit>
    <skin>
        <groupId>uk.pallas</groupId>
        <artifactId>maven-pallas-skin</artifactId>
        <version>${project.version}</version>
    </skin>
    <custom>
        <pallasSkin>
            <sourceLineNumbersEnabled>true</sourceLineNumbersEnabled>
            <sideBarEnabled>false</sideBarEnabled>
            <topBarEnabled>true</topBarEnabled>
        </pallasSkin>
    </custom>

    <body>
        <menu ref="parent"/>
        <menu ref="modules" />
        <menu ref="reports" />
    </body>
</project>
```
### TopBar and/or sideBar
The skin supports different layouts, enabling/disabling the left sidebar menu and the topbar menu.  Users have just play with flags in the ```custom.pallasSkin``` element in ```site.xml```:

```xml
<project name="xxx">
    <custom>
        <pallasSkin>
            <topBarEnabled>true</topBarEnabled>
            <sideBarEnabled>false</sideBarEnabled>
        </pallasSkin>
    </custom>
</project>
```

That allows users having 4 skins in only 1!

* sidebar only;
* topbar only;
* both side/top bars;
* none at all.

### TopBarIcon

When users decide to enable the topbar, they can plug a small icon/logo there (typically 108*20 px) just configuring
the ```custom.pallasSkin.topBarIcon``` element in ```site.xml```:

```xml
<project name="xxx">
    [...]
    <custom>
        <pallasSkin>
            <topBarEnabled>true</topBarEnabled>
            <topBarIcon>
                <name>Maven Pallas Skin</name>
                <alt>Maven Pallas Skin</alt>
                <src>/images/topbar-logo.png</src>
                <href>/index.html</href>
            </topBarIcon>
        </pallasSkin>
    </custom>
    [...]
</project>
```

### TopBarContainerStyle

You can configure the style attribute value of the div container for your topbar

```xml
<project name="xxx">
    [...]
    <custom>
        <pallasSkin>
            <topBarEnabled>true</topBarEnabled>
            <topBarContainerStyle>width: 90%;</topBarContainerStyle>
        </pallasSkin>
        </custom>
    [...]
</project>
```

### NavBarStyle
You can configure style of your top bar nav (since bootstrap 2.1.0 is not anymore black by default).
Use navbar-inverse to have the black one.

```xml
<project name="xxx">
    [...]
    <custom>
        <pallasSkin>
            <topBarEnabled>true</topBarEnabled>
            <navBarStyle>navbar-inverse</navBarStyle>
        </pallasSkin>
    </custom>
    [...]
</project>
```
### SourceLineNumbers

Source code sections are enhanced by {{{https://github.com/google/code-prettify}Google Code Prettify}}, users can
optionally enable line numbers rendering (disabled by default):

```xml
<project name="xxx">
    [...]
    <custom>
        <pallasSkin>
            <sourceLineNumbersEnabled>true</sourceLineNumbersEnabled>
        </pallasSkin>
        </custom>
    [...]
</project>
```
### GitHub ribbons

Pallas-skin supports [GitHub ribbons](https://github.com/blog/273-github-ribbons) to simplify the `Fork me on GitHub` banner integration. Users must define:

* ```custom.pallasSkin.gitHub.projectId```: required, it is the project id on GitHub and will be used to build the project page URL;
* ```custom.pallasSkin.gitHub.ribbonOrientation```: optional, it is the the ribbon position, ```left```/```right``` only accepted (```left``` by default);
* ```custom.pallasSkin.gitHub.ribbonColor```: optional, it is the the ribbon color, ```red```/```green```/```black```/<<darkblue>>/```orange```/```gray``` only accepted only accepted (```red``` by default).

```xml
<project name="xxx">
    [...]
    <custom>
        <pallasSkin>
            <gitHub>
                <projectId>apache/maven-skins</projectId>
                <ribbonOrientation>right</ribbonOrientation>
                <ribbonColor>black</ribbonColor>
            </gitHub>
        </pallasSkin>
    </custom>
    [...]
</project>
```

### Project profile

As shown in (MojoHaus Maven plugins list)[http://www.mojohaus.org/plugins.html) plugins life-cycle are characterized by
different status/stages:

* Production
* Pre-release
* Sandbox
* Graveyard (retired projects)

Users can configure different backgrounds depending on the project status (except the production) to warrant users they
are not using a production ready software:

```xml
<project name="xxx">
    [...]
    <custom>
        <pallasSkin>
            <profile>(pre-release|sandbox|retired)</profile>
        </pallasSkin>
    </custom>
    [...]
</project>
```

## Maven configuration

The maven site plugin currently expect documentation to sit in specific paths [as documented on the maven-site-plugin website](https://maven.apache.org/plugins/maven-site-plugin/examples/creating-content.html).

This creates a problem as the maven-site-plugin converts the documentation path as part of constructing the Maven site. So for example if you have a document on the following path ```src/site/markdown/example.md``` it might reference an image found under ```src/site/resources/images/diagram.png```. For this to be navigatable through markdown viewers built into code repositories you would construct your image reference like so 

```
this is some example text
![Diagram](../resources/images/diagram.png)
here is the image caption
```
The problem is in the resulting Maven site the directory will look like the following:
```
+- target/
   +- site/
      +- example.html
      +- images/
         +- diagram.png
```
The maven-site-plugin will retrain the reference and so example.html will have an image tag like the following:
```html
<img alt="Diagram" src="../resources/images/diagram.png" />
```

We need to hide the folder structure quirk found in the Apache Maven plugin, we can do this by configuring the maven-resources-plugin to scan a directory for specific supported document types and copy it into an interim

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
   <modelVersion>4.0.0</modelVersion>   
    
   <properties>
      <project.docs.directory>${project.basedir}/docs</project.docs.directory>
      <project.build.interim.site.directory>${project.build.directory}/docs-site</project.build.interim.site.directory>
      <maven-resources-plugin-version>3.2.0</maven-resources-plugin-version>
      <maven-site-plugin-version>4.0.0-M1</maven-site-plugin-version>
   </properties>

   <build>
        <pluginManagement>
            <plugins>
                <plugin>
                    <groupId>org.apache.maven.plugins</groupId>
                    <artifactId>maven-site-plugin</artifactId>
                    <version>${maven-site-plugin-version}</version>
                    <configuration>
                        <siteDirectory>${project.build.interim.site.directory}</siteDirectory>
                    </configuration>
                </plugin>
                <plugin>
                    <groupId>org.apache.maven.plugins</groupId>
                    <artifactId>maven-resources-plugin</artifactId>
                    <version>${maven-resources-plugin-version}</version>
                </plugin>
            </plugins>
        </pluginManagement>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-resources-plugin</artifactId>
                <executions>
                    <execution>
                        <id>copy-markdown-into-target</id>
                        <goals>
                            <goal>copy-resources</goal>
                        </goals>
                        <phase>validate</phase>
                        <configuration>
                            <resources>
                                <resource>
                                    <directory>${project.docs.directory}/</directory>
                                    <includes>
                                      <include>**/*.md</include>
                                    </includes>
                                    <filtering>true</filtering>
                                </resource>
                            </resources>
                            <outputDirectory>${project.build.interim.site.directory}/markdown/</outputDirectory>
                        </configuration>
                    </execution>
                    <execution>
                        <id>copy-resources-into-target</id>
                        <goals>
                            <goal>copy-resources</goal>
                        </goals>
                        <phase>validate</phase>
                        <configuration>
                            <resources>
                                <resource>
                                    <directory>${project.docs.directory}/</directory>
                                    <excludes>
                                        <exclude>**/*.md</exclude>
                                        <exclude>site.xml</exclude>
                                    </excludes>
                                    <filtering>false</filtering>
                                </resource>
                            </resources>
                            <outputDirectory>${project.build.interim.site.directory}/resources/</outputDirectory>
                        </configuration>
                    </execution>
                    <execution>
                        <id>copy-sitexml-into-target</id>
                        <goals>
                            <goal>copy-resources</goal>
                        </goals>
                        <phase>validate</phase>
                        <configuration>
                            <resources>
                                <resource>
                                    <directory>${project.docs.directory}/</directory>
                                    <includes>
                                        <include>site.xml</include>
                                    </includes>
                                    <filtering>false</filtering>
                                </resource>
                            </resources>
                            <outputDirectory>${project.build.interim.site.directory}/</outputDirectory>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-site-plugin</artifactId>
                <version>${maven-site-plugin-version}</version>
            </plugin>
        </plugins>
    </build>
</project>
```