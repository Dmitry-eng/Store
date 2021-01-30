package com.shop.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "DISCOUNT")
public class Discount extends  StandardEntity{
    @Column(name = "NUMBER_DISCOUNT", nullable = false)
    private String numberDiscount;
    @Column(name = "ACTIVE")
    private boolean active;
    @Column(name = "BONUSES")
    private int bonuses;

    public String getNumberDiscount() {
        return numberDiscount;
    }

    public void setNumberDiscount(String numberDiscount) {
        this.numberDiscount = numberDiscount;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getBonuses() {
        return bonuses;
    }

    public void setBonuses(int bonuses) {
        this.bonuses = bonuses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Discount discount = (Discount) o;
        return active == discount.active && bonuses == discount.bonuses && Objects.equals(numberDiscount, discount.numberDiscount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), numberDiscount, active, bonuses);
    }
}
