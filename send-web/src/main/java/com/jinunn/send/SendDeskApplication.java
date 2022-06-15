package com.jinunn.send;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author : JinDun
 * @date : 2022/6/15 15:56
 */
@SpringBootApplication(scanBasePackages = {"com.jinunn.send"})
public class SendDeskApplication {
    public static void main(String[] args) {
        SpringApplication.run(SendDeskApplication.class, args);
    }
}
