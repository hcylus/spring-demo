package com.devops.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class InfoController {

    @GetMapping("/info")
    public String showInfo(Model model) throws UnknownHostException, SocketException {
        HashMap<String, String> ips = new HashMap<>();
        for (Map.Entry<String, String> entry : SystemUtil.getLocalIPs().entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            ips.put(key,value);
        }
        model.addAttribute("ip", ips);
        model.addAttribute("osTime", SystemUtil.osTime());
        model.addAttribute("osName", SystemUtil.osName());
        model.addAttribute("HostName", SystemUtil.getHostName());
        return "info";
    }

}