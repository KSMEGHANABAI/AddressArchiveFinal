package com.fin.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fin.model.Member;


public class BatchDao {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/address_archive";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "rootmags";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC Driver not found", e);
        }
    }

    public int post(Member member) {
        int result = 0;
        String sql = "INSERT INTO addresses (first_name, middle_name, last_name,phone_number,email, address) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, member.getFirstName());
            pst.setString(2, member.getMiddleName());
            pst.setString(3, member.getLastName());
            pst.setString(4, member.getEmail());
            pst.setString(5, member.getPhoneNumber());
            pst.setString(6, member.getAddress());
            result = pst.executeUpdate();
            if (result > 0) {
                System.out.println("Address submitted successfully! " + member.getFirstName() + " " + member.getMiddleName() + " " + member.getLastName() + " " + member.getEmail() + " " + member.getPhoneNumber() + " " + member.getAddress());
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error inserting member", e);
        }
        return result;
    }

    public List<Member> getAllTasks() {
        List<Member> members = new ArrayList<>();
        String sql = "SELECT * FROM addresses";

        try (Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                String firstName = rs.getString("first_name");
                String middleName = rs.getString("middle_name");
                String lastName = rs.getString("last_name");
                String email = rs.getString("email");
                String phoneNumber = rs.getString("phone_number");
                String address = rs.getString("address");
                Member member = new Member(firstName, middleName, lastName, email, phoneNumber, address);
                members.add(member);
            }
            System.out.println("Retrieved " + members.size() + " address from the database.");
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving members", e);
        }
        return members;
    }
}
