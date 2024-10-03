package com.sirajul.Accomez.Entities;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.Set;
import java.util.UUID;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String useremail;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    @Size(min = 8,max = 20)
    private String password;

    @Column(nullable = false)
    private String contact ;

    @Column(nullable = false)
    private boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns =
            @JoinColumn(name = "user_id",referencedColumnName = "id"),inverseJoinColumns = @JoinColumn(name = "role_id",referencedColumnName = "id")
    )
    private Set<Role> role;

    public User() {}

    public User(String useremail, String username, String password, String contact, Set<Role> role) {
        try {
            this.useremail = useremail;
            this.username = username;
            this.password = password;
            this.contact = contact;
            this.enabled = true;
            this.role = role;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Set<Role> getRole() {
        return role;
    }

    public void setRole(Set<Role> role) {
        this.role = role;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public  String getPassword() {
        return password;
    }
    public void setPassword(@Size(min = 8, max = 20) String password) {
        if(password == null)
        this.password = password;

    }


    public void setPassword(@Size(min = 8, max = 20) String password,@Size(min = 8, max = 20) String newPassword) {

        if(this.password.equals(password))
            this.password = newPassword;

    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }




}
