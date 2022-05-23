package com.lolucode.financeInvoice.dto;

import com.lolucode.financeInvoice.model.Status;
import com.lolucode.financeInvoice.model.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDto {


    private Long id;
    private Long userId;
    private String reference;
    private Double amount;
    private LocalDate dueDate;
    private Type type;
    private Status status;


}
