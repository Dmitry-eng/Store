package com.shop.model.entity;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@MappedSuperclass
public class StandardEntity {
    @GeneratedValue
    @Id
    private UUID id;
    @Column( name = "UPDATE_TS")
    private Date updateTs;

    @Column(name = "UPDATED_BY")
    private String updatedBy;

    @Column(name = "DELETE",  nullable = false)
    private boolean delete;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Date getUpdateTs() {
        return updateTs;
    }

    public void setUpdateTs(Date updateTs) {
        this.updateTs = updateTs;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public boolean isDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StandardEntity that = (StandardEntity) o;
        return delete == that.delete && Objects.equals(id, that.id) && Objects.equals(updateTs, that.updateTs) && Objects.equals(updatedBy, that.updatedBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, updateTs, updatedBy, delete);
    }
}
