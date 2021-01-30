package com.shop.model.entity;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "\"USER\"")
@SQLDelete(sql = "update \"user\" set delete = true where id=?")
@Where(clause = "delete !='true'")
@Inheritance
public class User extends StandardEntity implements UserDetails {
    @Column(name = "LOGIN",  nullable = false, unique = true)
    private String login;
    @Column(name = "PASSWORD", nullable = false)
    private String password;
    @Column(name = "BIRTH_DATE")
    private Date birthDate;
    @Column(name = "NAME", nullable = false)
    private String name;
    @Column(name = "SURNAME", nullable = false)
    private String surname;
    @Column(name = "MIDDLE_NAME", nullable = false)
    private String middleName;
    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;
    @Column(name = "EMAIL", unique = true)
    private String email;
    @Column(name = "overwrite_password")
    private Boolean overwritePassword;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn
    private Set<Role> roles;

    public Set<Role> getRoles() {
        return roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }
    //TODO
    @Override
    public String getUsername() {
        return login;
    }

//является ли учетная запись не просроченной
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // Пользователь заблокирован
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
// срок действия учетных данных не истек
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
// Пользователь отключен
    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getOverwritePassword() {
        return overwritePassword;
    }

    public void setOverwritePassword(Boolean overwritePassword) {
        this.overwritePassword = overwritePassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return Objects.equals(login, user.login) &&
                Objects.equals(password, user.password) &&
                Objects.equals(birthDate, user.birthDate) &&
                Objects.equals(name, user.name) &&
                Objects.equals(surname, user.surname) &&
                Objects.equals(middleName, user.middleName) &&
                Objects.equals(phoneNumber, user.phoneNumber) &&
                Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), login, password, birthDate, name, surname, middleName, phoneNumber, email);
    }

}
