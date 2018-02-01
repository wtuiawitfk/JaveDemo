package com.yuhh.demo.reflect;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * 主板
 */
public class MotherBoard {
    private static Properties p = new Properties();     //配置文件
    private static Map<String, IUSB> plugins = new HashMap<>();     //用来存放配置文件内的类

    /**
     * 初始化
     */
    static{
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try {
            p.load(classLoader.getResourceAsStream("plugins.properties"));
            System.out.println(p);
            init();  //初始化
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void init() {
        Set<Object> objects = p.keySet();   //获取配置文件内所有的KEY
        for (Object obj :
                objects) {
            String name = (String) obj;
            String className = p.getProperty(name);
            try {
                Object o = Class.forName(className).newInstance();  //通过类名使用反射得到对象

                //判断当前对象是否实现的IUSB规范
                if (!(o instanceof IUSB)) {
                    throw new RuntimeException(name + "此设备没有实现IUSB规范！");
                }
                plugins.put(name, (IUSB) o);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public static void work() {
        for (IUSB usb :
                plugins.values()) {
            usb.swapDate();
        }
    }
}
