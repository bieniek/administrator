package com.mbsoft.activemq.administrator.infrastructure.spring.rest

import com.mbsoft.activemq.administrator.infrastructure.activemq.ActiveMQAdministrator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.{GetMapping, RequestMapping, RestController}

@RestController
@RequestMapping(Array("/hotels"))
class HotelController @Autowired() (private val activeMQAdministrator: ActiveMQAdministrator) {

  @GetMapping(Array("/list"))
  def list(model: Model): String = {
    println("dupa")
    val result = "hello"
    result
  }
}