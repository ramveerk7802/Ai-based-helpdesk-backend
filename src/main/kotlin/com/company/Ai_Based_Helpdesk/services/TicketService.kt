package com.company.Ai_Based_Helpdesk.services

import com.company.Ai_Based_Helpdesk.entities.Ticket
import com.company.Ai_Based_Helpdesk.exceptions.TicketNotFoundException
import com.company.Ai_Based_Helpdesk.repositories.TicketRepository
import org.slf4j.LoggerFactory

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TicketService(private val repository: TicketRepository) {

    private val logger = LoggerFactory.getLogger(TicketService::class.java)

//    create ticket

    fun createTicket(ticket: Ticket): Ticket{
        return repository.save(ticket)
    }

//    update ticket
    @Transactional
    fun updateTicket(id: Long,ticket:Ticket): Ticket{
        val existingTicket = repository.findById(id).orElseThrow { TicketNotFoundException("Ticket not found with id : $id") }
        existingTicket.status=ticket.status
        /**
         * if used the @Transactional annotation then no need explicitly
         * save the entity Jpa Automatically changes in field detect and save it
         * if not used annotation then need to explicitly save entity
         * repository.save(existingTicket)
         */

        return existingTicket
    }


//    getTicket

    @Transactional(readOnly = true)
    fun getTicket(ticketId: Long): Ticket{
        return repository.findById(ticketId).orElseThrow { TicketNotFoundException("Ticket not Found  with id : $ticketId ") }
    }

//    get Ticket by username

    @Transactional(readOnly = true)
fun getTicketByUsername(username: String): List<Ticket>{
    return repository.findByUsername(username = username)
}
}