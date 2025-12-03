package com.company.Ai_Based_Helpdesk.entities

import com.fasterxml.jackson.annotation.JsonCreator

enum class Category {
    HARDWARE,
    SOFTWARE,
    NETWORK,
    ACCESS,    // Login/Password issues
    DEVELOPER, // Bug reports, code issues
    OTHER;

    companion object {
        @JsonCreator
        fun fromString(key: String?): Category {
            return if (key.isNullOrBlank()) {
                OTHER
            } else {
                try {
                    // "Software", "software" -> SOFTWARE
                    valueOf(key.uppercase())
                } catch (e: IllegalArgumentException) {
                    // If AI guesses something weird, default to OTHER
                    OTHER
                }
            }
        }
    }
}