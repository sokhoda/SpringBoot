package com.businessdomain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@ToString
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
}
