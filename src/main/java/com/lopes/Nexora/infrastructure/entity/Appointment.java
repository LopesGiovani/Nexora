package com.lopes.Nexora.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.NotFound;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "appointment", uniqueConstraints = {
        @UniqueConstraint(
                name = "unique_client_datetime",
                columnNames = {"client", "scheduledDateTime"}
        )
})
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotFound
    private String service;
    private String professional;
    private LocalDateTime scheduledDateTime;
    private String client;
    private String clientPhone;
    @CreationTimestamp
    private LocalDateTime createdAt = LocalDateTime.now();

}
