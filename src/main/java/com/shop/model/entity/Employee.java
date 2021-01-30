package com.shop.model.entity;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@SQLDelete(sql = "update \"user\" set delete = true where id=?")
@Where(clause = "delete !='true'")
public class Employee extends User {
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn
    private List<Branch> branch;

    public List<Branch> getBranch() {
        return branch;
    }

    public void setBranch(List<Branch> branch) {
        this.branch = branch;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Employee employee = (Employee) o;
        return Objects.equals(branch, employee.branch);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), branch);
    }
}