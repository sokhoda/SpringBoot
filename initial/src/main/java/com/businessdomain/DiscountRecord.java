package com.businessdomain;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.TableGenerator;
import java.io.Serializable;

@Component
@Scope("prototype")
@Entity
@NamedQueries({
        @NamedQuery(name = "DiscountRecord.findByCheque", query = "SELECT dr from DiscountRecord dr " +
                "WHERE dr.cheque = :cheque")
})
public class DiscountRecord implements Serializable {
    @Id
    @TableGenerator(
            name = "discountRecGen",
            table = "ID_GEN",
            pkColumnName = "GEN_KEY",
            pkColumnValue = "DISCOUNTREC_ID",
            valueColumnName = "GEN_VALUE",
            initialValue = 0,
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "discountRecGen")
    private Long id;

    private String name;
    private Double sum;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "Cheque_ID")
    private Cheque cheque;

    public DiscountRecord() {
    }

    public DiscountRecord(String name, Double sum, Cheque cheque) {
        this.name = name;
        this.sum = sum;
        this.cheque = cheque;
    }

    @Override
    public String toString() {
        return "\nDiscountRecord{" +
                "name='" + name + '\'' +
                ", sum=" + sum +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DiscountRecord that = (DiscountRecord) o;

        if (name != null ? !name.equals(that.name) : that.name != null)
            return false;
        if (sum != null ? !sum.equals(that.sum) : that.sum != null)
            return false;
        return cheque != null ? cheque.equals(that.cheque) : that.cheque == null;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cheque getCheque() {
        return cheque;
    }

    public void setCheque(Cheque cheque) {
        this.cheque = cheque;
    }
}
