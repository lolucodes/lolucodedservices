package com.lolucode.financeInvoice.service;

import com.lolucode.financeInvoice.dto.InvoiceDto;
import com.lolucode.financeInvoice.exception.InvoiceNotFoundException;
import com.lolucode.financeInvoice.model.Account;
import com.lolucode.financeInvoice.model.Invoice;
import com.lolucode.financeInvoice.model.Status;
import com.lolucode.financeInvoice.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class InvoiceServiceImpl implements InvoiceService{

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private AccountRepository accountRepository;


    @Autowired
    private AccountService accountService;



    @Override
    public InvoiceDto createNewInvoice(Invoice invoice) {
        invoice.setStatus(Status.OUTSTANDING);
        invoice.generateReference();
        invoice.setDueDate(LocalDate.now().plusWeeks(4));

       /* if(accountRepository.findAccountByUserId(invoice.getAccount().getUserId()).isPresent()) {
            System.out.println("User id found in account table"+invoice.getAccount().getUserId());
        }else{
            System.out.println("User id not found in account table"+invoice.getAccount().getUserId());
        }*/
        //invoice.setAccount(accountRepository.findAccountByUserId(invoice.getUserId()).get());
        Account account = new Account();
        account.setUserId(invoice.getUserId());
        invoice.setAccount(accountService.createAccountIfNotExist(account));

        List<Invoice> invoices = new ArrayList<>();
        invoices.add(invoiceRepository.save(invoice));
        List<InvoiceDto> invoiceDtoList = invoiceDtoList(invoices);
        //Invoice newInvoice = invoiceRepository.save(invoice);
        return invoiceDtoList.get(0);
    }


    @Override
    public List<InvoiceDto> viewAllInvoicesOfUser(Long userId) {
        List<Invoice> invoiceList = invoiceRepository.findAllByUserId(userId);
        if(invoiceList!=null){
            return invoiceDtoList(invoiceList);
        }else{
            return  new ArrayList<>();
        }
    }

    public  List<InvoiceDto> invoiceDtoList(List<Invoice> invoiceList){
        List<InvoiceDto> invoiceDtoList = new ArrayList<>();
        for(Invoice invoice : invoiceList){
            invoiceDtoList.add(new InvoiceDto(invoice.getId(),invoice.getUserId(),invoice.getReference(),invoice.getAmount(),
            invoice.getDueDate(),invoice.getType(),invoice.getStatus()
            ));
        }
        return invoiceDtoList;
    }

    @Override
    public Invoice getInvoiceByReference(String reference) {
        Invoice invoice = invoiceRepository.findInvoiceByReference(reference);
        if (invoice == null) {
            throw new IllegalStateException();
        }
        return invoiceRepository.save(invoice);
    }

    @Override
    public Invoice pay(String reference) {
        Invoice newInvoice = processPayment(reference);
        return invoiceRepository.save(newInvoice);
    }

    @Override
    public Invoice cancel(String reference) {
        Invoice invoice = invoiceRepository.findInvoiceByReference(reference);
        if (invoice == null) {
            throw new InvoiceNotFoundException(reference);
        }
        if (invoice.getStatus() == Status.OUTSTANDING) {
            invoice.setStatus(Status.CANCELLED);
        }
        return invoiceRepository.save(invoice);
    }


    @Override
    public Invoice processPayment(String reference) throws UnsupportedOperationException {
        Invoice invoice = invoiceRepository.findInvoiceByReference(reference);

        if (invoice == null) {
            throw new InvoiceNotFoundException(reference);
        }
        if (invoice.getStatus() == Status.OUTSTANDING) {
            invoice.setStatus(Status.PAID);
            return invoiceRepository.save(invoice);
        } else {
            throw new UnsupportedOperationException("You cant pay an invoice that is in the " + invoice.getStatus() + "status");
        }
    }

    @Override
    public Invoice payInvoice(Invoice invoice) {
        Invoice invoice1 = invoiceRepository.findById(invoice.getUserId()).get();

        if (invoice1 == null) {
            throw new InvoiceNotFoundException();
        }
        if (invoice.getStatus() == Status.OUTSTANDING) {
            invoice.setStatus(Status.PAID);
            return invoiceRepository.save(invoice1);
        }else {
            throw new UnsupportedOperationException("You cant pay an invoice that is in the " + invoice1.getStatus() + "status");
        }
    }

    @Override
    public Invoice processInvoice(Invoice invoice) {
        Invoice newInvoice = payInvoice(invoice);
        return invoiceRepository.save(newInvoice);
    }
}