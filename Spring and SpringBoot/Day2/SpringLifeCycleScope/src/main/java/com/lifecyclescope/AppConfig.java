package com.lifecyclescope;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;



//Spring container will know that this is configuration file with this annotation.
@Configuration

// This annotation will tell the package name where your bean classes are.
@ComponentScan(basePackages="com.lifecyclescope")
public class AppConfig {

}
