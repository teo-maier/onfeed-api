package com.project.onfeedapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@SuperBuilder
@Entity(name = "session_recipients")
@Table(name = "session_recipients")
public class SessionRecipient extends Identity {
    @ManyToOne(fetch = FetchType.LAZY)
    private Session session;

    @OneToOne(fetch = FetchType.LAZY)
    private Employee employee;

    @Column(name = "is_completed")
    private boolean isCompleted;

    @Column(name = "is_reviewed")
    private boolean isReviewed;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", updatable = false)
    private LocalDateTime updatedAt;
}
