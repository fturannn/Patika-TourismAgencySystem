package com.turizmacenta.Model;

import com.turizmacenta.Helper.DBConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Reservation {
    private int id;
    private String period;
    private String hotel;
    private String hostel;
    private String night;
    private String person;
    private String name;
    private String tc;
    private String telephone;
    private String email;
    private String total;

    public Reservation() {}

    public Reservation(int id, String period, String hotel, String hostel, String night, String person, String name, String tc, String telephone, String email, String total) {
        this.id = id;
        this.period = period;
        this.hotel = hotel;
        this.hostel = hostel;
        this.night = night;
        this.person = person;
        this.name = name;
        this.tc = tc;
        this.telephone = telephone;
        this.email = email;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getHotel() {
        return hotel;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }

    public String getHostel() {
        return hostel;
    }

    public void setHostel(String hostel) {
        this.hostel = hostel;
    }

    public String getNight() {
        return night;
    }

    public void setNight(String night) {
        this.night = night;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTc() {
        return tc;
    }

    public void setTc(String tc) {
        this.tc = tc;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public static boolean add(String period, String hotel, String hostel, String night, String person, String name, String tc, String telephone, String email, String total) {
        String query = "INSERT INTO reservation (period, hotel, hostel, night, person, name, tc, telephone, email, total) VALUES (?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1,period);
            pr.setString(2,hotel);
            pr.setString(3,hostel);
            pr.setString(4,night);
            pr.setString(5,person);
            pr.setString(6,name);
            pr.setString(7,tc);
            pr.setString(8,telephone);
            pr.setString(9,email);
            pr.setString(10,total);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<Reservation> getList() {
        ArrayList<Reservation> reservationList = new ArrayList<>();
        String query = "SELECT * FROM reservation";
        Reservation obj;
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                obj = new Reservation();
                obj.setId(rs.getInt("id"));
                obj.setPeriod(rs.getString("period"));
                obj.setHotel(rs.getString("hotel"));
                obj.setHostel(rs.getString("hostel"));
                obj.setNight(rs.getString("night"));
                obj.setPerson(rs.getString("person"));
                obj.setName(rs.getString("name"));
                obj.setTc(rs.getString("tc"));
                obj.setTelephone(rs.getString("telephone"));
                obj.setEmail(rs.getString("email"));
                obj.setTotal(rs.getString("total"));
                reservationList.add(obj);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return reservationList;
    }
}
