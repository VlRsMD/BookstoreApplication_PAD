package com.example.Gateway

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping()
class ApiGatewayController {
    @GetMapping()
    fun welcome(): String {
        return "Welcome to the API Gateway of the Bookstore Application!"
    }
}