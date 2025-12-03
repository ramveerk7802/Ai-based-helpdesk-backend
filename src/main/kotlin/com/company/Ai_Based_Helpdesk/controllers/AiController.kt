package com.company.Ai_Based_Helpdesk.controllers

import com.company.Ai_Based_Helpdesk.services.AiService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/ai")
class AiController(private val aiService: AiService) {

    @PostMapping
    fun getResponse(@RequestBody query: String): ResponseEntity<String>{
        return ResponseEntity.status(HttpStatus.OK).body(aiService.getResponseFromAssistance(query = query))
    }
}