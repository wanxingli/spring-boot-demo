package com.sb.demo;

import com.sb.demo.threadPool.TaskThreadPoolConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;


import java.net.InetAddress;
import java.net.UnknownHostException;

@EnableScheduling
@EnableConfigurationProperties({TaskThreadPoolConfig.class})
@SpringBootApplication
@ServletComponentScan(basePackages = "com.sb.demo") // 扫描 Servlet filter listener
@MapperScan("com.sb.demo.dao")
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class);
        Environment environment = context.getBean(Environment.class);
        String hostAddress;
        try {
            hostAddress = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            hostAddress = "Unknown host address";
        }
        System.out.println("-------------------------------------------------------------------------------------\n\t"
            + "Application '" + environment.getProperty("spring.application.name") + "' is running! Access URLs:\n\t"
            + "Local: \t\t" + "http://localhost:" + environment.getProperty("server.port") + "\n\t"
            + "External: \t" + "http://" + hostAddress + ":" + environment.getProperty("server.port") + "\n\t"
            + "Profile(s): \t[" + String.join(",", environment.getActiveProfiles()) + "]\n"
                + "-------------------------------------------------------------------------------------"
        );
    }
}
