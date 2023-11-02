package com.turizmacenta.Model;

import com.turizmacenta.Helper.DBConnector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Price {
    private int id;
    private int hotel_id;
    private Hotel hotel;
    private int period_id;
    private Period period;
    private String hostel;
    private String age;

    public Price(int id, int hotel_id, int period_id, String hostel, String age) {
        this.id = id;
        this.hotel_id = hotel_id;
        this.period_id = period_id;
        this.hostel = hostel;
        this.age = age;
        this.hotel = Hotel.getFetch(hotel_id);
        this.period = Period.getFetch(period_id);
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

    public static ArrayList<Price> getList() {
        ArrayList<Price> priceList = new ArrayList<>();
        String query = "SELECT * FROM price";
        Price obj;
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                obj = new Price(rs.getInt("id"), rs.getInt("hotel_id"), rs.getInt("period_id"), rs.getString("hostel"), rs.getString("age"));
                priceList.add(obj);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return priceList;
    }
}
