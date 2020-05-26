package com.devops.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
public class InfoController {

    @Autowired
    private HttpServletRequest request; //自动注入request

    @Value("${spring.application.name}")
    private String applicationName;

    @RequestMapping("/show")
    public String showInfo(Model model) {
        HashMap<String, String> map = new HashMap<>();
        map.put("AppName",applicationName);
        map.put("RemoteIP", SystemUtil.ClientIp(request));
        HashMap<String, String> info = SystemUtil.SystemInfo();
        info.putAll(map);
        model.addAttribute("show", info);
        return "show";
    }

}
