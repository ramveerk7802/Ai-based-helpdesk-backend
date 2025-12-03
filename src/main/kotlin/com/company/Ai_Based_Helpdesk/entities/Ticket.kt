package com.company.Ai_Based_Helpdesk.entities

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyDescription
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime

@Entity
@Table(name = "tickets")
data class Ticket(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TicketId")
    // 1. Tell AI this is Read-Only. It should not try to generate an ID.
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    var id: Long? = null,


    @Enumerated(EnumType.STRING)
    // 3. CRITICAL FIX: List the allowed values explicitly.
    // This stops the AI from sending "" (empty string) or making up values.
    @JsonPropertyDescription("The priority level. MUST be one of: LOW, MEDIUM, HIGH.")
    var priority: Priority? = null,

    @Column(name = "email",nullable = false)
    @JsonPropertyDescription("The email address of the person requesting support")
    var email: String?=null,

    @Column(nullable = false)
    @JsonPropertyDescription("The name of the person requesting support")
    var name: String? = null,

    @JsonPropertyDescription("The detailed explanation of the issue")
    var description: String? = null,



    @Enumerated(EnumType.STRING)
    // 4. List status values too, just to be safe.
    @JsonPropertyDescription("The current status of the ticket. Values: OPEN, IN_PROGRESS,RESOLVED.")
    var status: Status? = null,

    @JsonPropertyDescription("The category of the issue (e.g., Hardware, Software, Network)")
    var category: Category? = null,

    @CreationTimestamp
    @Column(updatable = false)
    // 5. Hide system fields from the AI input
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    var createdAt: LocalDateTime? = null,

    @UpdateTimestamp
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    var updatedAt: LocalDateTime? = null
)