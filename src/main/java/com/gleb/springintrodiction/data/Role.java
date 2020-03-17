package com.gleb.springintrodiction.data;

import javax.persistence.*;
import java.util.Collection;

@Table(name = "roles")
@Entity
public class Role {
    @Id
    @GeneratedValue
    @Column
    private Long id;
    @Column
    private String roleName;
    @ManyToMany(mappedBy = "roles")
    private Collection<User> users;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }
}
