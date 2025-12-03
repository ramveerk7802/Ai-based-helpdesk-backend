package com.company.Ai_Based_Helpdesk.tools

import com.company.Ai_Based_Helpdesk.entities.Category
import com.company.Ai_Based_Helpdesk.entities.Priority
import com.company.Ai_Based_Helpdesk.entities.Status
import com.company.Ai_Based_Helpdesk.entities.Ticket
import com.company.Ai_Based_Helpdesk.services.TicketService
import org.springframework.ai.tool.annotation.Tool
import org.springframework.ai.tool.annotation.ToolParam
import org.springframework.stereotype.Component

@Component
class TicketDatabaseTool(private val ticketService: TicketService) {

    // --- 1. FIXED: Flattened arguments for better AI reliability ---
    @Tool(name = "createTicketTool", description = "Creates a new support ticket. Use this AFTER collecting details.")
    fun createTicketTool(
        @ToolParam(description = "The name of the person reporting the issue") name: String,
        @ToolParam(description = "Detailed description of the problem") description: String,
        @ToolParam(description = "The priority level (LOW, MEDIUM, HIGH). Determine this automatically based on the severity of the 'description'.")
        priority: String,
        @ToolParam(description = "Category (HARDWARE, SOFTWARE, NETWORK, ACCESS, DEVELOPER,OTHER). Infer this automatically based on the 'description'.")
        category: String,
        @ToolParam(description = "The user's email address") email: String
    ): Ticket {
        // You need to construct the object manually here
        val newTicket = Ticket(
            name = name,
            description = description,
            priority = Priority.fromString(priority),
            category = Category.fromString(category),
            email = email,
            status = Status.OPEN // Default status
        )
        return ticketService.createTicket(newTicket)
    }

    // --- 2. EXISTING: Find by ID (Looks good) ---
    @Tool(
        name = "findTicketById",
        description = "Retrieves the details of a specific ticket using its unique ID."
    )
    fun findTicketById(
        @ToolParam(description = "The ID of the ticket to find") id: Long
    ): Ticket? {
        return ticketService.getTicketById(id)
    }

    // --- 3. EXISTING: Find Active (Looks good) ---
    @Tool(
        name = "findActiveTickets",
        description = "Retrieves a list of all currently open or active support tickets from the database."
    )
    fun findActiveTickets(): List<Ticket> {
        return ticketService.getActiveTickets()
    }

    // --- 4. MISSING: You need this for your System Prompt to work! ---
    @Tool(name = "updateTicketTool", description = "Updates an existing ticket's details or status.")
    fun updateTicketTool(
        @ToolParam(description = "The ID of the ticket to update") id: Long,
        @ToolParam(description = "New status (optional)") status: String?,
        @ToolParam(description = "New description comment (optional)") description: String?
    ): Ticket {
        // Assuming your service handles nulls
        return ticketService.updateTicket(id, status, description)
    }
}