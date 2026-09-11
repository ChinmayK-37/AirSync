package com.cp.airsync.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


@Entity
@Table(name="audit_log")
public class AuditLog
{
        @Id
        @GeneratedValue(strategy=GenerationType.AUTO)
        @Column(name="log_id")
        private Long logId;
        
        private String action;
        
        @Column(name="entity_type")
        private String entityType;

        @Column(name="entity_id")
        private String  entityId;

        private String timestamp;
        
        @OneToOne
        @JoinColumn(name="user_id")
        private User user;
        public Long getLogId() {
                return logId;
        }
        public void setLogId(Long logId) {
                this.logId = logId;
        }
        public String getAction() {
                return action;
        }
        public void setAction(String action) {
                this.action = action;
        }
        public String getEntityType() {
                return entityType;
        }
        public void setEntityType(String entityType) {
                this.entityType = entityType;
        }
        public String getEntityId() {
                return entityId;
        }
        public void setEntityId(String entityId) {
                this.entityId = entityId;
        }
        public String getTimestamp() {
                return timestamp;
        }
        public void setTimestamp(String timestamp) {
                this.timestamp = timestamp;
        }
        public User getUser() {
                return user;
        }
        public void setUser(User user) {
                this.user = user;
        }
}