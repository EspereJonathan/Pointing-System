package com.espere.pointing_system;

import org.springframework.boot.SpringApplication;

public class TestPointingSystemApplication {

    public static void main(String[] args) {
        SpringApplication.from(PointingSystemApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
