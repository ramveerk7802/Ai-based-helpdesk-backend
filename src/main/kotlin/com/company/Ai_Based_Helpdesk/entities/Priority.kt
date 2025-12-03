package com.company.Ai_Based_Helpdesk.entities

import com.fasterxml.jackson.annotation.JsonCreator

enum class Priority {
    LOW,
    MEDIUM,
    HIGH;

    companion object {
        @JsonCreator // <--- USE THIS instead of @OptionalExpectation
        fun fromString(key: String?): Priority {
            return if (key.isNullOrBlank()) {
                LOW
            } else {
                try {
                    // This handles "High", "high", "HIGH" -> HIGH
                    valueOf(key.uppercase())
                } catch (e: IllegalArgumentException) {
                    // Fallback for "Critical", "Urgent", or typos -> LOW
                    LOW
                }
            }
        }
    }
}