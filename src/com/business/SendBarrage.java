package com.business;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

/**
 * Created by Andrew  on 2016/9/23.
 */
public class SendBarrage extends HttpServlet {
    String url="jdbc:mysql://localhost:3306/barrage?charset='utf-8'";
    String userName="root";
    String password="330606";
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection connection= null;
        PreparedStatement preparedStatement=null;
        boolean flag=false;

        response.setCharacterEncoding("UTF-8");
        String text=request.getParameter("text");
        String color=request.getParameter("color");
        String size=request.getParameter("size");
        String position=request.getParameter("position");
        String sql = "INSERT INTO barrage (text,color,size,position)VALUES" +
                " ('"+text+"','"+color+"',"+size+","+position+")";
        System.out.println(sql);
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection= DriverManager.getConnection(url,userName,password);
            preparedStatement=connection.prepareStatement(sql);
            flag=preparedStatement.execute(sql);

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (flag)
        response.getWriter().write(0);
    }
}
