package com.hotel.controller;

import com.hotel.bean.LoginBean;
import com.hotel.model.Employee;
import com.hotel.dao.LoginDAO;
import com.hotel.dao.sessionDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author FAHMI
 */
public class loginServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private PreparedStatement pstmt;
    
    /**
     *
     * @throws ServletExcepti


    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userName=request.getParameter("username");
        String password=request.getParameter("password");
        
        LoginBean loginBean = new LoginBean();
        
        loginBean.setUserName(userName);
        loginBean.setUserPassword(password);
        
        LoginDAO loginDao = new LoginDAO();
        String userValidate = loginDao.authenticateUser(loginBean);
        
        int ID = sessionDAO.getInfo(userName);
        
        if(userValidate.equals("SUCCESS")){
            HttpSession session=request.getSession();
            session.setAttribute("userName", userName);
            session.setAttribute("ID", ID);
            request.setAttribute("username", userName);
            request.getRequestDispatcher("JSPmenu.jsp").forward(request, response);
        }
        else
        {
            request.setAttribute("errMessage", userValidate);
            request.getRequestDispatcher("index.html").forward(request, response);
        }
        
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
