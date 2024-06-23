package com.lucasterencio.todosimple.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Objects;


@Entity //ou seja devemos tratar esse user como uma tabela
@Table(name = User.TableName)
public class User {
    public static final String TableName = "user";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "username", length = 100, nullable = false, unique = true)
    @NotNull
    @NotEmpty
    @Size(min = 2, max = 100)
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) //ou seja so vai ser de leitura
    @Column(name = "password", length = 60, nullable = false)
    @NotNull
    @NotEmpty
    @Size(min = 8, max = 60)
    private String password;


    public User() {
    }

    public User(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull @NotEmpty @Size(min = 2, max = 100) String getUsername() {
        return username;
    }

    public void setUsername(@NotNull @NotEmpty @Size(min = 2, max = 100) String username) {
        this.username = username;
    }

    public @NotNull @NotEmpty @Size(min = 8, max = 60) String getPassword() {
        return password;
    }

    public void setPassword(@NotNull @NotEmpty @Size(min = 8, max = 60) String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User other = (User) o;
        if(this.id == null)
            if(other.id != null) return false;
        else if(!this.id.equals(other.id)) return false;

        return Objects.equals(this.id, other.id) && Objects.equals(this.username, other.username)
                && Objects.equals(this.password, other.password);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }
}
