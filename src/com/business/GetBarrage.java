package com.business;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.sql.*;

/**
 * Created by Andrew  on 2016/9/22.
 */
public class GetBarrage extends javax.servlet.http.HttpServlet {
    String url="jdbc:mysql://localhost:3306/barrage";
    String userName="root";
    String password="330606";
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        this.doGet(request,response);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        Connection connection= null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        response.setCharacterEncoding("UTF-8");
        String sql = "SELECT barrageId,text,color,size,position FROM barrage WHERE barrageId>"+request.getParameter("barrageId");
        JSONArray result=new JSONArray();
        System.out.println(sql);
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection= DriverManager.getConnection(url,userName,password);
            preparedStatement=connection.prepareStatement(sql);
            resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                JSONObject jsonObject=new JSONObject();
                jsonObject.put("barrageId",resultSet.getInt("barrageId"));
                jsonObject.put("text",resultSet.getString("text"));
                jsonObject.put("size",resultSet.getInt("size"));
                jsonObject.put("position",resultSet.getInt("position"));
                jsonObject.put("color",resultSet.getString("color"));
                result.add(jsonObject);
                System.out.println("servlet get barrage info");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (resultSet!=null) try {
                resultSet.close();
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        response.getWriter().write(result.toJSONString());
    }
}
