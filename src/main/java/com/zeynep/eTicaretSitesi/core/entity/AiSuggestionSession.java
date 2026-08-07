package com.zeynep.eTicaretSitesi.core.entity;

import com.zeynep.eTicaretSitesi.core.dao.BaseEntity;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "ai_suggestion_sessions")
public class AiSuggestionSession extends BaseEntity {

    @Column(nullable = false)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @OneToMany(mappedBy = "session", fetch = FetchType.LAZY)
    private List<AiSuggestion> suggestions;

    public AiSuggestionSession() {
    }

    public String getTitle() {return title;}
    public void setTitle(String title) {this.title = title;}

    public User getUser() {return user;}
    public void setUser(User user) {this.user = user;}

    public List<AiSuggestion> getSuggestions() {return suggestions;}
    public void setSuggestions(List<AiSuggestion> suggestions) {this.suggestions = suggestions;}
}