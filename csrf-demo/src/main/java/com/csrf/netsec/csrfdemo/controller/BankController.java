package com.csrf.netsec.csrfdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String transfer(@RequestParam(value="amount") int amount) {
    	a.removeMoney(amount);
        return "success";
    }
    
    @RequestMapping("/safe")
    public String safe() {
    	return "not_an_attack";
    }
}
