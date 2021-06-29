package com.csrf.netsec.csrfdemo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.view.RedirectView;

import com.csrf.netsec.csrfdemo.models.Customer;

@Controller
public class BankController {
	
	Customer a = new Customer("John", 1000);
	Customer bob = new Customer("Bob", 20000);
	Customer alice = new Customer("Alice", 100);
	Customer attacker = new Customer("Friend", 0);
	List<Customer> customers = new ArrayList<Customer>();
	
	@RequestMapping("/account") 
    @GetMapping
    public String display(Model model) {
		model.addAttribute("balance", a.getBalance());
		model.addAttribute("name", a.getName());
		if (customers.size() < 1) {
			customers.add(a);
			customers.add(bob);
			customers.add(alice);
			customers.add(attacker);
			
		}
		return "account";
	}
    
	@RequestMapping(value = "/account/transfer", method = RequestMethod.GET)
	@ResponseBody
	public RedirectView getTransfer(@RequestParam("person") String name, @RequestParam("amount") int amount) {
		for (Customer c : customers) {
			if (c.getName().equals(name)) {
				c.setBalance(c.getBalance() + amount);
				a.removeMoney(amount);
			}
		}
		return new RedirectView("localhost:8080/success");
	}
	
    @RequestMapping(value = "/account/transfer", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public String postTransfer(@RequestParam("person") String name, @RequestParam("amount") int amount) {
    	a.removeMoney(amount);
    	for (Customer c : customers) {
			if (c.getName().equals(name)) {
				c.setBalance(c.getBalance() + amount);
				a.removeMoney(amount);
			}
		}
    	return "success";
    }
    
    @RequestMapping("/success")
    public String success() {
    	return "account";
    }
    
    @RequestMapping("/users")
    public String showUsers(Model model) {
    	model.addAttribute("aname", a.getName());
    	model.addAttribute("abalance", a.getBalance());
    	model.addAttribute("bobname", bob.getName());
    	model.addAttribute("bobbalance", bob.getBalance());
    	model.addAttribute("alicename", alice.getName());
    	model.addAttribute("alicebalance", alice.getBalance());
    	model.addAttribute("attackname", attacker.getName());
    	model.addAttribute("attackbalance", attacker.getBalance());
    	return "users";
    }
    
    @RequestMapping("/safe")
    public String safe() {
    	return "not_an_attack";
    }
    
    @RequestMapping("/puppied")
    public String safePost() {
    	return "post-attack";
    }
}
