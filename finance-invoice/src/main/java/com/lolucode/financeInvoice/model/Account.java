package com.lolucode.financeInvoice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;

@Data
@Entity
public class Account extends RepresentationModel<Account> {

    private @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(unique = true)
    private String studentId;


    private Long userId;

    @Transient
    private boolean hasOutstandingBalance;

    public Account() {

    }

    public Account(String studentId) {
        this.studentId = studentId;
    }

    public void populateStudentId() {
        //if (this.studentId == null) {
            this.studentId = "c" +
                    RandomStringUtils.random(1, '7', '3') +
                    RandomStringUtils.randomNumeric(6);
       // }
    }

}


