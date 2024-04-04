package com.fin.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fin.dao.BatchDao;
import com.fin.model.Member;

@WebServlet("/posting")
public class AddressServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String middleName = request.getParameter("middleName");
        String lastName = request.getParameter("lastName");
        String phoneNumber = request.getParameter("phoneNumber");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
         System.out.println(firstName);
        // Create a new Member object with the input details
        Member member = new Member(firstName, middleName, lastName, phoneNumber, email, address);
        BatchDao batchDao = new BatchDao();

        try {
            // Insert the member into the database
            int result = batchDao.post(member);

            if (result > 0) {
                // If insertion is successful, redirect to the homepage
                response.sendRedirect("index.html");
            } else {
                // Handle insertion failure
                response.getWriter().println("Failed to insert member.");
            }
        } catch (Exception e) {
            // Log or print the stack trace of the exception
            e.printStackTrace();
            response.getWriter().println("An error occurred while inserting the member.");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        BatchDao batchDao = new BatchDao();

        try {
            // Retrieve members from the database
            List<Member> members = batchDao.getAllTasks();

            // Set members as a request attribute
            request.setAttribute("members", members);

            // Forward the request to the address-entry-page.html page for rendering
            request.getRequestDispatcher("index.html").forward(request, response);
        } catch (Exception e) {
            // Log or print the stack trace of the exception
            e.printStackTrace();
            response.getWriter().println("An error occurred while retrieving members.");
        }
    }    
}
