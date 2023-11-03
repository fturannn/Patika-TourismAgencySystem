package com.turizmacenta.Model;

import com.turizmacenta.Helper.DBConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class Price {
    private int id;
    private int hotel_id;
    private Hotel hotel;
    private int period_id;
    private Period period;
    private String hostel;
    private String age;
    private int price;
    private String period_start;
    private String period_end;

    public Price(int id, int hotel_id, int period_id, String hostel, String age, int price) {
        this.id = id;
        this.hotel_id = hotel_id;
        this.period_id = period_id;
        this.hostel = hostel;
        this.age = age;
        this.price = price;
        this.hotel = Hotel.getFetch(hotel_id);
        this.period = Period.getFetch(period_id);
        this.period_start = period.getPeriod_start();
        this.period_end = period.getPeriod_end();
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

    public int getPeriod_id() {
        return period_id;
    }

    public void setPeriod_id(int period_id) {
        this.period_id = period_id;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    public String getHostel() {
        return hostel;
    }

    public void setHostel(String hostel) {
        this.hostel = hostel;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPeriod_start() {
        return period_start;
    }

    public void setPeriod_start(String period_start) {
        this.period_start = period_start;
    }

    public String getPeriod_end() {
        return period_end;
    }

    public void setPeriod_end(String period_end) {
        this.period_end = period_end;
    }

    public static ArrayList<Price> getList() {
        ArrayList<Price> priceList = new ArrayList<>();
        String query = "SELECT * FROM price";
        Price obj;
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                obj = new Price(rs.getInt("id"), rs.getInt("hotel_id"), rs.getInt("period_id"), rs.getString("hostel"), rs.getString("age"), rs.getInt("price"));
                priceList.add(obj);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return priceList;
    }

    public static boolean add(int hotel_id, String city, int period_id, String period_start, String period_end, String hostel, String age, int price) {
        String query = "INSERT INTO price (hotel_id, city, period_id, period_start, period_end, hostel, age, price) VALUES (?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,hotel_id);
            pr.setString(2,city);
            pr.setInt(3,period_id);
            pr.setString(4,period_start);
            pr.setString(5,period_end);
            pr.setString(6,hostel);
            pr.setString(7,age);
            pr.setInt(8,price);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean delete(int id) {
        String query = "DELETE FROM price WHERE id = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean deleteHotel(int hotel_id) {
        String query = "DELETE FROM price WHERE hotel_id = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,hotel_id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
