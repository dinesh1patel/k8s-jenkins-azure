package com.studentdb.Studentdbk8s.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("api/v1")
public class JhooqDockerDemoController {

    @GetMapping("/hello")
    public String hello() {
        return "Jenkins Pipeline Test: Docker -> DockerHub -> AKS -> Semi Automated";
    }
}
