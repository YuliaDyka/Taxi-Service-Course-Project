package ua.lviv.iot.Server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = { "ua.lviv.iot.Server.Controllers",
        "ua.lviv.iot.Server.Services"})

public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
