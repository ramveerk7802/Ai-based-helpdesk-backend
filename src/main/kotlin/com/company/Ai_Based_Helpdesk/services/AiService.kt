package com.company.Ai_Based_Helpdesk.services

import com.company.Ai_Based_Helpdesk.tools.TicketDatabaseTool
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.ai.chat.client.ChatClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.Resource
import org.springframework.stereotype.Service

@Service
class AiService(private val chatClient: ChatClient,private val ticketDatabaseTool: TicketDatabaseTool) {

    private val logger: Logger = LoggerFactory.getLogger(AiService::class.java)
    @Value("classpath:/prompts/helpdesk-system-message.st")
    private lateinit var systemMessage: Resource
    fun getResponseFromAssistance(query: String): String?{
        logger.info("System prompt : ${this.systemMessage}")
        return chatClient.prompt()
            .system { s->s.text(this.systemMessage) }
            .user { u->u.text(query) }
            .tools(ticketDatabaseTool)
            .call()
            .content()
    }
}