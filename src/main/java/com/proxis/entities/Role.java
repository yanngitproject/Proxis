package com.proxis.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * @author Jeanyannick
 *
 */
@Entity
@Table(name = "roles")

public class Role {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false)
    private String code;

    protected String label;

    @ManyToMany(mappedBy = "roles")
    protected List<User> users = new ArrayList<User>();

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    public Role ()
    {
    }

    public Role (String code)
    {
        this.code = code;
    }

    public Role (String code, String label)
    {
        this(code);
        this.label = label;
    }

    public Long getId() {

        return id;
    }

    public String getCode() {

        return code;
    }

    public void setCode(String code) {
 
        this.code = code;
    }

    public String getLabel() {

        return label;
    }

    public void setLabel(String label) {
        
        this.label = label;
    }

    public void addUser(User user) {
        
        if (!users.contains(user)) {
            
            users.add(user);
        }
    }

    public void removeUser(User user) {
        
        users.remove(user);
    }

    public List<User> getUsers() {
        
        return users;
    }

    public void setUsers(List<User> newUsers) {
        
        for (User user : users) {
            
            if (!newUsers.contains(user)) {
                
                this.removeUser(user);
            }
        }

        for (User user : newUsers) {
            
            user.addRole(this);
        }

        this.users = newUsers;
    }

    public void setCreatedAt(final Date date) {

        createdAt = date;
    }

    public Date getCreatedAt() {

        return createdAt;
    }

    public void setUpdatedAt(final Date date) {

        updatedAt = date;
    }

    public Date getUpdatedAt() {

        return updatedAt;
    }

    @Override
    public String toString() {

        return code;
    }
}