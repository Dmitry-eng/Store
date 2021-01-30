package com.shop.model.entity;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "BRANCH")
public class Branch extends StandardEntity {
    @Column(name = "ADDRESS", nullable = false)
    private String address;
    @Column(name = "START_WORK")
    private Long startWork;
    @Column(name = "END_WORK")
    private Long  endWork;
    @Column(name = "CONTACT_NUMBER", nullable = false)
    private String contactNumber;
    @Transient
    @ManyToMany(mappedBy = "employees")
    private Set<Employee> employees;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getStartWork() {
        return startWork;
    }

    public void setStartWork(Long startWork) {
        this.startWork = startWork;
    }

    public Long getEndWork() {
        return endWork;
    }

    public void setEndWork(Long endWork) {
        this.endWork = endWork;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Branch branch = (Branch) o;
        return Objects.equals(address, branch.address) && Objects.equals(startWork, branch.startWork) && Objects.equals(endWork, branch.endWork) && Objects.equals(contactNumber, branch.contactNumber) && Objects.equals(employees, branch.employees);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), address, startWork, endWork, contactNumber, employees);
    }
}
