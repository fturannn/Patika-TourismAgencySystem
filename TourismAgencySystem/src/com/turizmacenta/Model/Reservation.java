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
    private Room room;
    private int room_id;

    public Reservation() {}

    public Reservation(int id, int room_id, String period, String hotel, String hostel, String night, String person, String name, String tc, String telephone, String email, String total) {
        this.id = id;
        this.room_id = room_id;
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
        this.room = Room.getFetchByRoomId(room_id);
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

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public static boolean add(int room_id, String period, String hotel, String hostel, String night, String person, String name, String tc, String telephone, String email, String total) {
        String query = "INSERT INTO reservation (room_id, period, hotel, hostel, night, person, name, tc, telephone, email, total) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,room_id);
            pr.setString(2,period);
            pr.setString(3,hotel);
            pr.setString(4,hostel);
            pr.setString(5,night);
            pr.setString(6,person);
            pr.setString(7,name);
            pr.setString(8,tc);
            pr.setString(9,telephone);
            pr.setString(10,email);
            pr.setString(11,total);
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
                obj.setRoom_id(rs.getInt("room_id"));
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

    public static boolean delete(int reservation_id) {
        String queryHostel = "DELETE FROM reservation where id = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(queryHostel);
            pr.setInt(1,reservation_id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Reservation getFetch(int id) {
        Reservation obj = null;
        String query = "SELECT * FROM reservation WHERE id = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                obj = new Reservation(rs.getInt("id"), rs.getInt("room_id"), rs.getString("period"), rs.getString("hotel"),
                        rs.getString("hostel"), rs.getString("night"), rs.getString("person"), rs.getString("name"),
                        rs.getString("tc"), rs.getString("telephone"), rs.getString("email"), rs.getString("total"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return obj;
    }
}
