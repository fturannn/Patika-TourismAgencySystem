package com.turizmacenta.Model;

import com.turizmacenta.Helper.DBConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Hostel {
    private int id;
    private int hotel_id;
    private Hotel hotel;
    private String hostel;

    public Hostel(int id, int hotel_id, String hostel) {
        this.id = id;
        this.hotel_id = hotel_id;
        this.hostel = hostel;
        this.hotel = Hotel.getFetch(hotel_id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public String getHostel() {
        return hostel;
    }

    public void setHostel(String hostel) {
        this.hostel = hostel;
    }

    public static boolean add(int hotel_id, String hostel) {
        String query = "INSERT INTO hostel (hotel_id, hostel) VALUES (?,?)";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, hotel_id);
            pr.setString(2, hostel);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<Hostel> getList() {
        ArrayList<Hostel> hostelList = new ArrayList<>();
        String query = "SELECT * FROM hostel";
        Hostel obj;
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                obj = new Hostel(rs.getInt("id"), rs.getInt("hotel_id"), rs.getString("hostel"));
                hostelList.add(obj);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return hostelList;
    }

    public static Hostel getFetch(int id) {
        Hostel obj = null;
        String query = "SELECT * FROM hostel WHERE id = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                obj = new Hostel(rs.getInt("id"), rs.getInt("hotel_id"), rs.getString("hostel"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return obj;
    }
}
