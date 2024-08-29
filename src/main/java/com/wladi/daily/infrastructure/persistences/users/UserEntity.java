package com.wladi.daily.infrastructure.persistences.users;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="tb_user")
public class UserEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String name;
    private String password;

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String email;

    public UserEntity() {
        
    }

    private UserEntity(UserEntityBuilder userEntityBuilder) {
        this.name = userEntityBuilder.name;
        this.username = userEntityBuilder.username;
        this.email = userEntityBuilder.email;
        this.password = userEntityBuilder.password;
    }

    public static UserEntityBuilder builder() {
        return new UserEntityBuilder();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static class UserEntityBuilder {

        private String name;
        private String username;
        private String email;
        private String password;

        public UserEntity build() {
            return new UserEntity(this);
        }

        public UserEntityBuilder name(String name) {
            this.name = name;
            return this;
        }

        public UserEntityBuilder username(String username) {
            this.username = username;
            return this;
        }

        public UserEntityBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UserEntityBuilder password(String password) {
            this.password = password;        
            return this;
        }

    }

}