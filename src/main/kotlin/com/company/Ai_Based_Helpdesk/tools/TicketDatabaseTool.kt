package com.company.Ai_Based_Helpdesk.tools

import com.company.Ai_Based_Helpdesk.entities.Ticket
import com.company.Ai_Based_Helpdesk.services.TicketService
import org.springframework.ai.tool.annotation.Tool
import org.springframework.ai.tool.annotation.ToolParam
import org.springframework.stereotype.Component

//@Component
//class TicketDatabaseTool(private val ticketService: TicketService) {
//
//    @Tool(description = "This tool helps to create new Ticket in database")
//    fun createTicketTool(@ToolParam(description = "The ticket details. valid fields are: summary,category, description, priority (HIGH, MEDIUM, LOW),status (OPEN,CLOSE,RESOLVED) and username.") ticket: Ticket): Ticket{
//        return ticketService.createTicket(ticket = ticket)
//    }
//}

@Component
class TicketDatabaseTool(private val ticketService: TicketService) {

    @Tool(description = "Creates a new support ticket in the database. ONLY call this function AFTER you have collected the 'summary', 'description', 'priority', and 'username' from the user.")
    fun createTicketTool(
        @ToolParam(description = "The ticket object containing user-provided details.")
        ticket: Ticket
    ): Ticket {
        return ticketService.createTicket(ticket = ticket)
    }
}