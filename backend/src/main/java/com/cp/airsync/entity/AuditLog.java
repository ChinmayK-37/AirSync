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
        private Long log_id;
        
        private String action;
        private String entity_type;
        private String  entity_id;
        private String timestamp;
        @OneToOne
        @JoinColumn(name="user_id")
        private User user;
        public Long getLog_id() {
                return log_id;
        }
        public void setLog_id(Long log_id) {
                this.log_id = log_id;
        }
        public String getAction() {
                return action;
        }
        public void setAction(String action) {
                this.action = action;
        }
        public String getEntity_type() {
                return entity_type;
        }
        public void setEntity_type(String entity_type) {
                this.entity_type = entity_type;
        }
        public String getEntity_id() {
                return entity_id;
        }
        public void setEntity_id(String entity_id) {
                this.entity_id = entity_id;
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