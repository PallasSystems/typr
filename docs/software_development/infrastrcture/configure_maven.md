# Configure Maven

This section will walk you though how to configure and secure Apache Maven.

## Pre-requisites
* [Install Maven](install_maven.md)

## Password Encryption
Maven supports server password encryption. We will use this to ensure the password token is not exposed if the device is compromised.

### How to create a master password
Use the following command line:
```bash
mvn --encrypt-master-password
```
Maven should request your 'master' password, this can be anything unique and is used as a salt to encrypt your other passwords. This command will produce an encrypted version of the password, something like
```bash
{jSMOWnoPFgsHVpMvz5VrIt5kRbzGpI8u+9EF1iFQyJQ=}
```
Store this password in the ```${user.home}/.m2/settings-security.xml``` it should look like
```xml
<settingsSecurity>
  <master>{jSMOWnoPFgsHVpMvz5VrIt5kRbzGpI8u+9EF1iFQyJQ=}</master>
</settingsSecurity>
```
When this is done, you can start encrypting existing server passwords.

### Deploy GitHub M2 Settings file.
Each project contains github actions which reference a [M2 Settings file](../../../.github/maven_settings.xml). This file is used so the build system knows where to to look for project dependencies. Copy this file to ```${user.home}/.m2/settings.xml```. Github actions injects usernames and passwords as environment variables. The server section:
```xml
<server>
  <id>github</id>
  <username>${env.GITHUB_USER}</username>
  <password>${env.GITHUB_TOKEN}</password>
</server>
```

Needs to be updated with your credentials. All instances of```${env.GITHUB_USER}``` should be replaced with your GitHub Username. Next we need to generate a Personal Access Token so it can be encrypted and stored.

### Creating a personal access token
Personal access tokens (PATs) are an alternative to using passwords for authentication to GitHub when using the GitHub API or the command line.

* In a web browser open [GitHub](https://www.github.com)
* On GitHub verify your email address, if it hasn't been verified yet.
* In the upper-right corner of any page, click your profile photo, then click Settings. Settings icon in the user bar
* In the left sidebar, click Developer settings. Developer settings
* In the left sidebar, click Personal access tokens. Personal access tokens
* Click Generate new token. Generate new token button
* Give your token a descriptive name. Token description field
* To give your token an expiration, select the Expiration drop-down menu, then click a default or use the calendar picker. Token expiration field
* Select the scopes, or permissions, you'd like to grant this token. To use your token to access repositories from the command line, select repo.
* Click Generate token.

Make note of the token we will encrypt it in the next step and it is not shown again.

### How to encrypt server passwords
Now we have a personal access token we can use as a password we need to encrypt it to use on the device. This is achieved using the following command:
```bash
mvn --encrypt-password
```
A dialog will appear asking for the password, please use the personal access token value. This command produces an encrypted version of it, something like
```
{COQLCE6DU6GtcS5P=}
```
Within your M2 Settings file ```${user.home}/.m2/settings.xml``` you will find a number of server elements like the following:
```xml
<server>
  <id>github</id>
  <username>${env.GITHUB_USER}</username>
  <password>${env.GITHUB_TOKEN}</password>
</server>
```
Replace all instances of ```${env.GITHUB_TOKEN}``` with the encrypted password you generated in the previous steps.