package com.turizmacenta.Model;

import com.turizmacenta.Helper.DBConnector;
import com.turizmacenta.Helper.Helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Hotel {
    private int id;
    private String name;
    private String city;
    private String region;
    private String adress;
    private String email;
    private String telephone;
    private String star;

    public Hotel(int id, String name, String city, String region, String adress, String email, String telephone, String star) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.region = region;
        this.adress = adress;
        this.email = email;
        this.telephone = telephone;
        this.star = star;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public static ArrayList<String> typeList() {
        ArrayList<String> typeList = new ArrayList<>();
        typeList.add("1");
        typeList.add("2");
        typeList.add("3");
        typeList.add("4");
        typeList.add("5");
        return typeList;
    }

    public static ArrayList<Hotel> getList() {
        ArrayList<Hotel> hotelList = new ArrayList<>();
        String query = "SELECT * FROM hotel";
        Hotel obj;
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                obj = new Hotel(rs.getInt("id"), rs.getString("name"), rs.getString("city"), rs.getString("region"), rs.getString("address"), rs.getString("email"), rs.getString("telephone"), rs.getString("star"));
                hotelList.add(obj);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return hotelList;
    }

    public static boolean add(String name, String city, String region, String address, String email, String telephone, String star) {
        String query = "INSERT INTO hotel (name, city, region, address, email, telephone, star) VALUES (?,?,?,?,?,?,?)";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1,name);
            pr.setString(2,city);
            pr.setString(3,region);
            pr.setString(4,address);
            pr.setString(5,email);
            pr.setString(6,telephone);
            pr.setString(7,star);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean delete(int id) {
        String query = "DELETE FROM hotel WHERE id = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static String searchQuery(String name, String address, String region, String star) {
        String query = "SELECT * FROM hotel WHERE name LIKE '%{{name}}%' AND address LIKE '%{{address}}%' AND region LIKE '%{{region}}%'";
        query = query.replace("{{name}}", name);
        query = query.replace("{{address}}", address);
        query = query.replace("{{region}}", region);
        if(!star.isEmpty()) {
            query += " AND star='{{star}}'";
            query = query.replace("{{star}}", star);
        }

        return query;
    }

    public static ArrayList<Hotel> searchHotelList(String query) {
        ArrayList<Hotel> hotelList = new ArrayList<>();
        Hotel obj;
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                obj = new Hotel(rs.getInt("id"), rs.getString("name"), rs.getString("city"),
                        rs.getString("region"), rs.getString("address"), rs.getString("email"),
                        rs.getString("telephone"), rs.getString("star"));
                hotelList.add(obj);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return hotelList;
    }

    public static boolean update(int hotel_id, String hotel_name, String hotel_city, String hotel_region, String hotel_address, String hotel_email, String hotel_telephone, String hotel_star) {
        String query = "UPDATE hotel SET name=?, city=?, region=?, address=?, email=?, telephone=?, star=? WHERE id=?";
        if (!Hotel.typeList().contains(hotel_star)) {
            Helper.showMsg("Lütfen geçerli bir yıldız seçiniz:\n1\n2\n3\n4\n5");
            return false;
        }
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1,hotel_name);
            pr.setString(2,hotel_city);
            pr.setString(3,hotel_region);
            pr.setString(4,hotel_address);
            pr.setString(5,hotel_email);
            pr.setString(6,hotel_telephone);
            pr.setString(7,hotel_star);
            pr.setInt(8,hotel_id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
