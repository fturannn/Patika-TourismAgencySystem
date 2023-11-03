package com.turizmacenta.Model;

import com.turizmacenta.Helper.DBConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Period {
    private int id;
    private int hotel_id;
    private Hotel hotel;
    private String period_start;
    private String period_end;
    private String period;

    public Period(int id, int hotel_id, String period_start, String period_end) {
        this.id = id;
        this.hotel_id = hotel_id;
        this.period_start = period_start;
        this.period_end = period_end;
        this.hotel = Hotel.getFetch(hotel_id);
        this.period = period_start + "-" + period_end;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public static ArrayList<Period> getList() {
        ArrayList<Period> periodList = new ArrayList<>();
        String query = "SELECT * FROM period";
        Period obj;
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                obj = new Period(rs.getInt("id"), rs.getInt("hotel_id"), rs.getString("period_start"), rs.getString("period_end"));
                periodList.add(obj);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return periodList;
    }

    public static boolean delete(int id) {
        String query = "DELETE FROM period WHERE id = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean add(int hotel_id, String period_start, String period_end, String period) {
        String query = "INSERT INTO period (hotel_id, period_start, period_end, period) VALUES (?,?,?,?)";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, hotel_id);
            pr.setString(2, period_start);
            pr.setString(3, period_end);
            pr.setString(4, period);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Period getFetch(int id) {
        Period obj = null;
        String query = "SELECT * FROM period WHERE id = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                obj = new Period(rs.getInt("id"), rs.getInt("hotel_id"), rs.getString("period_start"), rs.getString("period_end"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return obj;
    }

    public static Period getFetchByHotelId(int hotel_id) {
        Period obj = null;
        String query = "SELECT * FROM period WHERE hotel_id = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,hotel_id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                obj = new Period(rs.getInt("id"), rs.getInt("hotel_id"), rs.getString("period_start"), rs.getString("period_end"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return obj;
    }

    public static boolean deleteHotel(int hotel_id) {
        String query = "DELETE FROM period WHERE hotel_id = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, hotel_id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
