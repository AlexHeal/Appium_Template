# [Name]-Tests

## Requirements

* [Java 8 JDK](http://www.oracle.com/technetwork/java/javase/downloads/index.html)

* [Maven](https://maven.apache.org/download.cgi)

* [Appium server](http://appium.io/)

* [iOS emulator or real device]

* [Path variable JAVA_HOME, maven]

## Quickstart run test
1) Run emulator or connect you device
2) start appium server
```
appium --port 4723
```
3) edit config.json file set iOS version and other property like "iOSversion":"13.4"
4) run tests
```
mvn test
```
5) optional create report
```
mvn allure:serve
```

