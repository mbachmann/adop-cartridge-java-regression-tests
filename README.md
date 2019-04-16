# Spring Petclinic with Tymeleaf Regression Test Repository 

This repository is used for testing with selenium and cucumber against spring petclinic application with a Thymeleaf template engine.

https://github.engineering.zhaw.ch/bacn/spring-petclinic-maven-java11


## Documentation
Documentation will be captured within this README.md and this repository's Wiki.

## Petclinic instance
The tests need a running instance of petclinc. The url can be set under main/resources/config.properties. 
The default is http:://localhost:8080

## Selenium Tests
The selenium tests are using the chrome driver. In between the calls is a 2 second break to show each individual 
call. Due to the dependency of webdrivermanager we do not need to install the crome or firefox driver. Using the 
selenium remote driver needs an external selenium grid.

## Cucumber Tests
The cucumber tests are using the HtmlUnitDriver. 