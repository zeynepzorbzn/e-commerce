package com.zeynep.eTicaretSitesi.core.entity;

import com.zeynep.eTicaretSitesi.core.dao.BaseEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "ai_suggestions")
public class AiSuggestion extends BaseEntity {

    @Column(columnDefinition = "TEXT")
    private String prompt;
    @Column(columnDefinition = "TEXT")
    private String response;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "session_id", nullable = false)
    private AiSuggestionSession session;

    public AiSuggestion() {
    }

    public String getPrompt() {return prompt;}
    public void setPrompt(String prompt) {this.prompt = prompt;}

    public String getResponse() {return response;}
    public void setResponse(String response) {this.response = response;}

    public AiSuggestionSession getSession() {return session;}
    public void setSession(AiSuggestionSession session) {this.session = session;}
}