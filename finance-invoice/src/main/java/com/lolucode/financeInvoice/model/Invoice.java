package com.lolucode.financeInvoice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;
import org.apache.commons.lang.RandomStringUtils;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Locale;

@Data
@Entity
@Table(name = "invoice")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;
    @Column(unique = true)
    @NotBlank(message = "{reference.required}")
    @Size(min = 10, max = 10, message = "{reference.size}")
    @Pattern(regexp = "[A-M0-9]*", message = "{reference.format}")
    private String reference;

    private Double amount;

    private LocalDate dueDate;

    private Type type;

    private Status status;

    @ManyToOne
    @JoinColumn(name = "account_fk", referencedColumnName = "id")
    @ToString.Exclude
    private Account account;

    public Invoice() {

    }

    //but we had upted the response with dto yesters in which the account details was not comming?

   /* @JsonProperty
    public String getStudentId() {
        return account.getStudentId();
    }

    @JsonProperty
    public void setAccount(Account account) {
        this.account = account;
    }

    @JsonIgnore
    public Account getAccount() {
        return this.account;
    }
*/

    public Invoice(Double amount, LocalDate dueDate, Type type, Account account) {
        this.amount = amount;
        this.dueDate = dueDate;
        this.type = type;
        this.account = account;
        generateReference();
    }

    public void generateReference() {
        if (this.reference == null) {
            this.reference = RandomStringUtils.random(
                    10, true, true).toUpperCase(Locale.UK);
        }
    }
}
