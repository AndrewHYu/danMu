package com.business.noDatabaseVersion;

import javax.servlet.ServletContext;

/**
 * Created by Andrew  on 2016/9/23.
 */
public class MaintainBarrageThread implements Runnable {
    ServletContext servletContext;
    public MaintainBarrageThread(ServletContext servletContext){
        this.servletContext=servletContext;
    }
    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            servletContext.getAttribute("tempBarrageStore");
            servletContext.getAttribute("barrageStore");
        }
    }
}
