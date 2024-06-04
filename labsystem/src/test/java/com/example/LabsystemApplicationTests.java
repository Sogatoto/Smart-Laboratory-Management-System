package com.example;

import com.example.config.JwtConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LabsystemApplicationTests {
    @Autowired
    private JwtConfig jwtConfig;
    @Test
    void contextLoads() {
        String token = jwtConfig.createToken("admin");
        System.out.println(token);
        System.out.println(jwtConfig.getSubject(token));
    }

}
