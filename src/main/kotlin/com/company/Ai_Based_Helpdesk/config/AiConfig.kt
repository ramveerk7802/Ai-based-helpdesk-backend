package com.company.Ai_Based_Helpdesk.config

import org.slf4j.LoggerFactory
import org.springframework.ai.chat.client.ChatClient
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor
import org.springframework.ai.chat.memory.ChatMemory

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AiConfig {

    private val logger = LoggerFactory.getLogger(AiConfig::class.java)


    @Bean
    fun chatClient(builder: ChatClient.Builder,chatMemory: ChatMemory): ChatClient{
        logger.info("Chat memory name : ${chatMemory.javaClass.name}")
        val messageChatMemoryAdvisor = MessageChatMemoryAdvisor.builder(chatMemory).build()
        return builder
            .defaultAdvisors(messageChatMemoryAdvisor,SimpleLoggerAdvisor())
            .build()
    }
}