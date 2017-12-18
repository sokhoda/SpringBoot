package com.businessdomain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
public class Cheque implements Serializable {
    @Transient
    public static final String DEFAULT_TITLE = "Simple Pizza Cheque #";
    @Id
    @TableGenerator(
            name = "chequeGen",
            table = "ID_GEN",
            pkColumnName = "GEN_KEY",
            pkColumnValue = "CHEQUE_ID",
            valueColumnName = "GEN_VALUE",
            initialValue = 0,
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "chequeGen")
    private Long id;

    private String title;

//    @Convert(converter = DateConverter.class)
    private LocalDateTime date;

    private Double totalSum;

    @OneToMany(mappedBy = "cheque", cascade = CascadeType.MERGE, fetch =
            FetchType.EAGER, orphanRemoval = true)
    private List<DiscountRecord> discountList = new ArrayList<>();


    public Cheque() {
        onCreateNew();
    }

    private void onCreateNew() {
        this.date = LocalDateTime.now();
    }

    public Cheque(String title, LocalDateTime date) {
        this.title = title;
        this.date = date;
    }

    public Cheque(LocalDateTime date) {
        this(DEFAULT_TITLE, date);
    }

    public Cheque(Cheque cheque) {
        if (cheque == null) {
            return;
        }
        date = Optional.ofNullable(cheque.getDate()).orElse(null);
        title = Optional.ofNullable(cheque.getTitle()).orElse(null);
        totalSum = Optional.ofNullable(cheque.getTotalSum()).orElse(null);
    }

    @Override
    public String toString() {
        return "\nCheque{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", date=" + date +
                ", discountList=" + discountList +
                ", totalSum=" + totalSum +
                ", dueSum=" + calcDueSum() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cheque cheque = (Cheque) o;

        if (title != null ? !title.equals(cheque.title) : cheque.title != null)
            return false;
        if (totalSum != null ? !totalSum.equals(cheque.totalSum) : cheque.totalSum != null)
            return false;
        return discountList != null ? discountList.equals(cheque.discountList) : cheque.discountList == null;

    }

    public Double getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(Double totalSum) {
        this.totalSum = totalSum;
    }

    public Double calcDueSum() {
        Double discountSum = 0.;
        for (DiscountRecord discountRecord : discountList) {
            discountSum += discountRecord.getSum();
        }
        if (totalSum != null) {
            Double dueSum = totalSum - discountSum;
            return dueSum > 0 ? dueSum : 0.;
        }
        return discountSum;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }


    public List<DiscountRecord> getDiscountList() {
        return discountList;
    }

    public void setDiscountList(List<DiscountRecord> discountList) {
        this.discountList = discountList;
    }


}
