package com.devops.demo;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Date;
import java.text.SimpleDateFormat;

public class SystemUtil {

    // 获取系统时间
    public static String osTime() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        return df.format(new Date());
    }

    /**
     * 获取主机名称
     *
     * @return
     * @throws UnknownHostException
     */
    public static String getHostName() throws UnknownHostException {
        return InetAddress.getLocalHost().getHostName();
    }

    /**
     * 获取系统首选IP
     *
     * @return
     * @throws UnknownHostException
     */
    public static String getLocalIP() throws UnknownHostException {
        return InetAddress.getLocalHost().getHostAddress();
    }

    /**
     * 获取所有网卡IP，排除回文地址、虚拟地址
     *
     * @return
     * @throws SocketException
     */
    public static HashMap<String, String> getLocalIPs() throws SocketException {
        HashMap<String, String> map = new HashMap<>();
        Enumeration<NetworkInterface> enumeration = NetworkInterface.getNetworkInterfaces();
        while (enumeration.hasMoreElements()) {
            NetworkInterface intf = enumeration.nextElement();
            if (intf.isLoopback() || intf.isVirtual()) { //
                continue;
            }
            Enumeration<InetAddress> inets = intf.getInetAddresses();
            while (inets.hasMoreElements()) {
                InetAddress addr = inets.nextElement();
                if (addr.isLoopbackAddress() || !addr.isSiteLocalAddress() || addr.isAnyLocalAddress()) {
                    continue;
                }
                map.put(intf.getDisplayName(),addr.getHostAddress());
            }
        }
        return map;

    }

    /**
     * 获取操作系统名称
     *
     * @return
     */
    public static String osName() {
        return System.getProperty("os.name");
    }

    public static HashMap<String, String> SystemInfo() {
        HashMap<String, String> map = new HashMap<>();
        try {
            map.put("OSTime",SystemUtil.osTime());
            map.put("OSName",SystemUtil.osName());
            map.put("HostName",SystemUtil.getHostName());
            HashMap<String, String> ips = SystemUtil.getLocalIPs();
            map.putAll(ips);
            //System.out.println("系统首选IP：" + LocalHostUtil.getLocalIP());
            for (Entry<String, String> entry : map.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                System.out.println(key + ": " + value);
            }

        } catch (UnknownHostException e) {
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

}
