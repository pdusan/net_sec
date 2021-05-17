package com.csrf.netsec.csrfdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.csrf.netsec.csrfdemo.models.Customer;

@Controller
public class BankController {
	
	Customer a = new Customer();
	
	@RequestMapping("/account") 
    @GetMapping
    public String display(Model model) {
		model.addAttribute("balance", a.getBalance());
		return "account";
	}
    
    @PostMapping("/account/transfer")
    public String transfer() {
    	a.removeMoney(100);
        return "success";
    }
}
