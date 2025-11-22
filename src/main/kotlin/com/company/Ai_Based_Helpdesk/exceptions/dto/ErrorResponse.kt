package com.company.Ai_Based_Helpdesk.exceptions.dto

import java.time.LocalDateTime

data class ErrorResponse(
    val timeStamp : LocalDateTime = LocalDateTime.now(),
    val message: String?,
    val detail: String
)
