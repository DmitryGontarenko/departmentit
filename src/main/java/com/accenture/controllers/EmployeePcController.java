package com.accenture.controllers;

import com.accenture.service.employeepc.EmployeePcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pc")
public class EmployeePcController {

    @Autowired
    private EmployeePcService employeePcService;

    @GetMapping
    public String pcList(Model model) {

        model.addAttribute("employeepc", employeePcService.findAll());

        return "pcList";
    }
}
