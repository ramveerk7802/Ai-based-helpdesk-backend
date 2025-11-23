package com.company.Ai_Based_Helpdesk.entities

import com.fasterxml.jackson.annotation.JsonCreator

enum class Priority {
    LOW,
    MEDIUM,
    HIGH;

    companion object {
        // This acts like a "translator" for the AI
        @JsonCreator
        @JvmStatic
        fun fromString(key: String?): Priority? {
            return key?.let {
                try {
                    // This fixes the error by forcing "Medium" -> "MEDIUM"
                    Priority.valueOf(it.uppercase())
                } catch (e: Exception) {
                    // If the AI sends garbage, default to LOW
                    LOW
                }
            }
        }
    }
}