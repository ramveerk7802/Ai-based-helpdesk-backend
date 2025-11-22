package com.company.Ai_Based_Helpdesk.repositories

import com.company.Ai_Based_Helpdesk.entities.Ticket
import jakarta.persistence.QueryHint
import org.hibernate.jpa.HibernateHints
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.QueryHints

interface TicketRepository : JpaRepository<Ticket, Long> {

    @QueryHints(value = [
        QueryHint(name = HibernateHints.HINT_READ_ONLY, value = "true")
    ])
    fun findByUsername(username:String): List<Ticket>

    /**
     * @QueryHints(value = [
     *         QueryHint(name = HibernateHints.HINT_FETCH_SIZE, value = "50"),
     *         QueryHint(name = HibernateHints.HINT_READONLY, value = "true")
     *     ])
     *     // Using Stream is better for massive data than List
     *     fun streamByStatus(status: String): Stream<Ticket>
     */
}