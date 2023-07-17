package com.vanessa.ApartmentReservationCapstone.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/customer_list")
    public String viewCustomerList(Model model) {
        model.addAttribute("listCustomers", customerService.getAllCustomers());
        return "customer_list";
    }

    @GetMapping("/new_customer")
    public String showNewCustomerForm(Model model) {
        Customer customer = new Customer();
        model.addAttribute("customer", customer);
        return "new_customer";
    }

    @PostMapping("/saveCustomer")
    public String saveCustomer(@ModelAttribute("customer") Customer customer) {
        customerService.saveOrUpdateCustomer(customer);
        return "redirect:/customer_list";
    }

    @GetMapping("/update_customer/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") int id, Model model) {
        Customer customer = customerService.getCustomerById(id);
        model.addAttribute("customer", customer);
        return "update_customer";
    }

    @GetMapping("/deleteCustomer/{id}")
    public String deleteCustomer(@PathVariable(value = "id") int id) {
        this.customerService.deleteCustomer(id);
        return "redirect:/customer_list";
    }
}
