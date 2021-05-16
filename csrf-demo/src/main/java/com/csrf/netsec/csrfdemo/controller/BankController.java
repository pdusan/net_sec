package com.csrf.netsec.csrfdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class BankController {
	
    @GetMapping
    public String transferMoney() {
        return "account";
    }
    
    @PostMapping("/transfer")
    public String transfer() {
        return "success";
    }
}