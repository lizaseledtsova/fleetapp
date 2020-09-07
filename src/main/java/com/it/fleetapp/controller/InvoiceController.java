package com.it.fleetapp.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.it.fleetapp.models.Invoice;
import com.it.fleetapp.services.ClientService;
import com.it.fleetapp.services.InvoiceService;
import com.it.fleetapp.services.InvoiceStatusService;

@Controller
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private InvoiceStatusService invoiceStatusService;

    @GetMapping("/invoices")
    public String getInvoices(Model model) {

        model.addAttribute("invoices", invoiceService.getInvoices());
        model.addAttribute("invoiceStatus", invoiceStatusService.getInvoiceStatus());
        model.addAttribute("clients", clientService.getClients());

        return "invoice";
    }

    @PostMapping("/invoices/addNew")
    public String addNewInvoice(Invoice invoice) {
        invoiceService.saveInvoice(invoice);
        return "redirect:/invoices";
    }

    @RequestMapping("invoices/findById")
    @ResponseBody
    public Optional<Invoice> findById(Integer id) {
        return invoiceService.findInvoiceById(id);
    }

    @RequestMapping(value="invoices/update", method = {RequestMethod.PUT, RequestMethod.GET})
    public String update(Invoice invoice) {
        invoiceService.saveInvoice(invoice);
        return "redirect:/invoices";
    }

    @RequestMapping(value="invoices/delete", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String delete(Integer id) {
        invoiceService.deleteInvoice(id);
        return "redirect:/invoices";
    }
}
