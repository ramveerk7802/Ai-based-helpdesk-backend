package com.company.Ai_Based_Helpdesk.exceptions

import com.company.Ai_Based_Helpdesk.exceptions.dto.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice


@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(TicketNotFoundException::class)
    fun ticketExceptionHandler(e: TicketNotFoundException): ResponseEntity<ErrorResponse>{
        val response = ErrorResponse(
            message = e.message,
            detail = e.localizedMessage
        )
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response)
    }
}