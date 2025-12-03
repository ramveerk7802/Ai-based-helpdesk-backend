package com.company.Ai_Based_Helpdesk.repositories

import com.company.Ai_Based_Helpdesk.entities.Ticket
import jakarta.persistence.QueryHint
import org.hibernate.jpa.HibernateHints
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.jpa.repository.QueryHints
import org.springframework.data.repository.query.Param

interface TicketRepository : JpaRepository<Ticket, Long> {

    @QueryHints(value = [
        QueryHint(name = HibernateHints.HINT_READ_ONLY, value = "true")
    ])
    fun findByName(name:String): List<Ticket>


    @QueryHints(value = [
        QueryHint(name = HibernateHints.HINT_READ_ONLY, value = "true")
    ])
    @Query("SELECT t FROM Ticket t WHERE t.status IN('OPEN','IN_PROGRESS')")
    fun findActiveTickets(): List<Ticket>

    /**
     * @QueryHints(value = [
     *         QueryHint(name = HibernateHints.HINT_FETCH_SIZE, value = "50"),
     *         QueryHint(name = HibernateHints.HINT_READONLY, value = "true")
     *     ])
     *     // Using Stream is better for massive data than List
     *     fun streamByStatus(status: String): Stream<Ticket>
     */
}