package com.business.noDatabaseVersion;

import com.alibaba.fastjson.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Andrew  on 2016/9/23.
 */
@WebServlet(name = "GetBarrage")
public class GetBarrage extends HttpServlet {
    BlockingQueue<BarragePo> barrageQueue=new LinkedBlockingQueue();

    @Override
    public void init() throws ServletException {
        super.init();
        getServletContext().setAttribute("barrageQueue",barrageQueue);
        System.out.println("初始化阻塞队列");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("getBarrage GET ");
        response.setCharacterEncoding("UTF-8");
        boolean flag=true;
        JSONArray barrageArray=new JSONArray();
        while (flag) {
            flag= false;
            BarragePo barragePo=barrageQueue.poll();
            if (barragePo!=null){
                flag=true;
                barrageArray.add(barragePo);
            }
        }
        response.getWriter().write(barrageArray.toJSONString());
        System.out.println(barrageArray.toJSONString());
    }
}
