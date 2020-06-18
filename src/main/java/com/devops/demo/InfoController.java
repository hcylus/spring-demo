package com.devops.demo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.*;

@Controller
public class InfoController {

    private static final Logger logger = LogManager.getLogger(DemoApplication.class.getName());

    @Autowired
    private HttpServletRequest request; //自动注入request

    @Value("${spring.application.name}")
    private String applicationName;

    @RequestMapping("/show")
    public String showInfo(Model model) {
        logger.info("Access show page");
        String pkg = System.getProperty("java.class.path");
        pkg = pkg.substring(pkg.lastIndexOf(File.separator) + 1);
        HashMap<String, String> map = new HashMap<>();
        map.put("AppName",applicationName);
        map.put("PkgName",pkg);
        map.put("ClientIP", SystemUtil.clientIp(request));
        HashMap<String, String> info = SystemUtil.systemInfo();
        info.putAll(map);
        model.addAttribute("show", info);
        return "show";
    }

}
