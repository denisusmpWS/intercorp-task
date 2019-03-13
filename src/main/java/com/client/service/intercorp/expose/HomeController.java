package com.client.service.intercorp.expose;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

@Controller
public class HomeController {

    @Value("${info.project.name}")
    private String name;
    @Value("${info.project.description}")
    private String description;
    @Value("${info.project.version}")
    private String version;

    private String status = "ENABLED";

    private Date date = new Date();

    @RequestMapping("/")
    public String getHome(Model model){
        model.addAttribute("name",name);
        model.addAttribute("description",description);
        model.addAttribute("version",version);
        model.addAttribute("date",date);
        model.addAttribute("status", status);
        return "index";
    }
}
