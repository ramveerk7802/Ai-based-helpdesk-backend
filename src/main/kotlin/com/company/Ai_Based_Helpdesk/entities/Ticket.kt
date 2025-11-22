package com.company.Ai_Based_Helpdesk.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Lob
import jakarta.persistence.Table
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import org.jetbrains.annotations.NotNull
import java.time.LocalDateTime


@Entity
@Table(name = "tickets")
class Ticket(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TicketId")
    var id:Long?=null,

    @Lob
    @Column(columnDefinition = "TEXT")
    var summary: String?=null,

    @Enumerated(value = EnumType.STRING)
    var priority: Priority?=null,

    @Column(unique = true, nullable = false)
    var username: String?=null,
    var description: String?=null,

    @Enumerated(EnumType.STRING)
    var status: Status?=null,

    @CreationTimestamp
    @Column(updatable = false)
    var createdAt: LocalDateTime?=null,

    @UpdateTimestamp
    var updatedAt: LocalDateTime?=null
) {

}