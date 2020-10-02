package com.csis3275.jwitter;

import java.util.List;
import lombok.Data;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
@Controller
public class JwitterApplication {

    public static void main(String[] args) {
        SpringApplication.run(JwitterApplication.class, args);
    }

    @GetMapping("/api/employees")
    @ResponseBody
    public List<JwitterApplication.Employee> getEmp() {
        return List.of(
            new JwitterApplication.Employee("bla", "bla", "Douglas")
        );
    }

    @Data
    private static class Employee {
        private final String firstName;
        private final String lastName;
        private final String description;

    }

}
