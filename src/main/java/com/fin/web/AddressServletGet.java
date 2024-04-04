package com.fin.web;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fin.dao.BatchDao;
import com.fin.model.Member;
@WebServlet("/geting")
public class AddressServletGet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        BatchDao batchDao = new BatchDao();
        List<Member> members = batchDao.getAllTasks();

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Start HTML content
        out.println("<!DOCTYPE html>");
        out.println("<html lang=\"en\">");
        out.println("<head>");
        out.println("    <meta charset=\"UTF-8\">");
        out.println("    <title>Member List</title>");
        out.println("    <style>");
        out.println("        body {");
        out.println("            background-image: url('image1.avif');");
        out.println("            background-size: 900px 900px;");
        out.println("            background-repeat: no-repeat;");
        out.println("            background-position: 400px -120px;");
        out.println("        }");
        out.println("    </style>");
        out.println("</head>");
        out.println("<body>");
        out.println("    <h1>Member List</h1>");
        out.println("    <ul>");

        // Dynamically generate list items for each member
        for (Member member : members) {
            out.println("    <li>Name: " + member.getFirstName() + " " + member.getMiddleName() + " " + member.getLastName() + "</li>");
            out.println("    <li>Phone Number: " + member.getPhoneNumber() + "</li>");
            out.println("    <li>Email: " + member.getEmail() + "</li>");
            out.println("    <li>Address: " + member.getAddress() + "</li>");
            out.println("<br>");
        }

        out.println("    </ul>");
        out.println("    <a href=\"index.html\">Add more members</a>");
        out.println("</body>");
        out.println("</html>");
    }
}
