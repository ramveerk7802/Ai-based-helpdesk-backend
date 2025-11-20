package com.company.Ai_Based_Helpdesk.config

import org.springframework.ai.chat.client.ChatClient
import org.springframework.ai.ollama.api.OllamaChatOptions
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AiConfig {

    @Bean
    fun chatClient(builder: ChatClient.Builder): ChatClient{
        return builder
            .defaultOptions(
                OllamaChatOptions.builder()
                    .model("llama3.2:latest")
                    .temperature(0.6)
                    .build()
            )
            .build()
    }
}