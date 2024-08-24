package com.wladi.daily.infrastructure.persistences.phases;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="tb_phase")
public class PhaseEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long phaseId;

    @Column(unique = true)
    private String title;

    private String subtitle;
    private String className;

    private PhaseEntity(PhaseEntityBuilder phaseEntityBuilder) {
        this.title = phaseEntityBuilder.title;
        this.subtitle = phaseEntityBuilder.subtitle;
        this.className = phaseEntityBuilder.className;
    }

    public static PhaseEntityBuilder builder() {
        return new PhaseEntityBuilder();
    }

    public Long getPhaseId() {
        return phaseId;
    }

    public void setPhaseId(Long phaseId) {
        this.phaseId = phaseId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public static class PhaseEntityBuilder {

        private String title;
        private String subtitle;
        private String className;

        public PhaseEntity build() {
            return new PhaseEntity(this);
        }

        public PhaseEntityBuilder title(String title) {
            this.title = title;
            return this;
        }

        public PhaseEntityBuilder subtitle(String subtitle) {
            this.subtitle = subtitle;
            return this;
        }

        public PhaseEntityBuilder className(String className) {
            this.className = className;
            return this;
        }

    }

}