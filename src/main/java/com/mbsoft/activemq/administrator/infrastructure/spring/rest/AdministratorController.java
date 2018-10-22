package com.mbsoft.activemq.administrator.infrastructure.spring.rest;

import com.mbsoft.activemq.administrator.core.Administrator;
import com.mbsoft.activemq.administrator.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/queue")
public class AdministratorController {
    private final Administrator administrator;

    @Autowired
    public AdministratorController(Administrator administrator) {
        this.administrator = administrator;
    }

    @GetMapping("/list")
    List<Queue> list() {
        return administrator.list();
    }

    @DeleteMapping("/{queueName}")
    void delete(@PathVariable(name = "queueName") String queueName) {
        administrator.delete(queueName);
    }
}
