package com.devops.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
public class InfoController {

    @RequestMapping("/info")
    public String showInfo(Model model) {
        model.addAttribute("info", SystemUtil.SystemInfo());
        return "info";
    }

}

