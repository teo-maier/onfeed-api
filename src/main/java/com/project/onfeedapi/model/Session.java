package com.project.onfeedapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@SuperBuilder
@Entity(name = "sessions")
@Table(name = "sessions")
@EntityListeners(AuditingEntityListener.class)
public class Session extends Identity {
    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @OneToOne(fetch = FetchType.LAZY)
    private Form form;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator_id")
    private Employee creator;

    @Column(name = "is_anonymous")
    private boolean isAnonymous;

    @Column(name = "is_suggestion")
    private boolean isSuggestion;

    @Column(name = "is_draft")
    private boolean isDraft;

    @CreatedDate
    @Column (name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column (name = "updated_at", updatable = false)
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "session", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SessionRecipient> sessionRecipients = new ArrayList<>();

    public void addSessionRecipient(SessionRecipient sessionRecipient) {
        sessionRecipients.add(sessionRecipient);
        sessionRecipient.setSession(this);
    }

    public void removeSessionRecipient(SessionRecipient sessionRecipient) {
        sessionRecipients.remove(sessionRecipient);
        sessionRecipient.setSession(null);
    }
}
