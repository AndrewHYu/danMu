package com.business.noDatabaseVersion;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Andrew  on 2016/9/23.
 */
public class SendBarrage extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("sendBarrage get");

        response.setCharacterEncoding("UTF-8");
        String text=request.getParameter("text");
        String color=request.getParameter("color");
        String size=request.getParameter("size");
        String position=request.getParameter("position");

        System.out.println(color);
        BarragePo barragePo=new BarragePo();
        barragePo.setText(text);
        barragePo.setColor(color);
        barragePo.setSize(size);
        barragePo.setPosition(position);

        BlockingQueue<BarragePo> barrageQueue=(LinkedBlockingQueue)getServletContext().getAttribute("barrageQueue");
        boolean flag=barrageQueue.offer(barragePo);

        response.getWriter().write(flag?1:0);
    }
}
