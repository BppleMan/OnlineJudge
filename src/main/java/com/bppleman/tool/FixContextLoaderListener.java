package com.bppleman.tool;

import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;
import org.springframework.web.context.ContextLoaderListener;

import javax.servlet.ServletContextEvent;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

/**
 * Created by BppleMan on 2017/11/22.
 */
public class FixContextLoaderListener extends ContextLoaderListener {

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        super.contextDestroyed(event);
//        注销驱动
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        Driver driver = null;
        while (drivers.hasMoreElements()) {
            try {
                driver = drivers.nextElement();
                DriverManager.deregisterDriver(driver);
                System.out.println(String.format("ContextFinalizer:Driver %s deregistered", driver));
            } catch (SQLException ex) {
                System.out.println(String.format("ContextFinalizer:Error deregistering driver %s", driver) + ":" + ex);
            }
        }
//        关闭线程
        try {
            AbandonedConnectionCleanupThread.shutdown();
        } catch (InterruptedException e) {
            System.out.println("ContextFinalizer:SEVERE problem cleaning up: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
