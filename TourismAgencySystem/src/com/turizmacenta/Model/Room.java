package com.turizmacenta.Model;

import com.turizmacenta.Helper.DBConnector;
import com.turizmacenta.Helper.Helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Room {
    private int id;
    private int hotel_id;
    private Hotel hotel;
    private String room_name;
    private int stock;
    private String bed;
    private String tv;
    private String minibar;
    private String game_console;
    private Period period;
    private int period_id;

    public Room(int id, int hotel_id, int period_id, String room_name, int stock, String bed, String tv, String minibar, String game_console) {
        this.id = id;
        this.hotel_id = hotel_id;
        this.period_id = period_id;
        this.room_name = room_name;
        this.stock = stock;
        this.bed = bed;
        this.tv = tv;
        this.minibar = minibar;
        this.game_console = game_console;
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

    public String getRoom_name() {
        return room_name;
    }

    public void setRoom_name(String room_name) {
        this.room_name = room_name;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getBed() {
        return bed;
    }

    public void setBed(String bed) {
        this.bed = bed;
    }

    public String getTv() {
        return tv;
    }

    public void setTv(String tv) {
        this.tv = tv;
    }

    public String getMinibar() {
        return minibar;
    }

    public void setMinibar(String minibar) {
        this.minibar = minibar;
    }

    public String getGame_console() {
        return game_console;
    }

    public void setGame_console(String game_console) {
        this.game_console = game_console;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    public int getPeriod_id() {
        return period_id;
    }

    public void setPeriod_id(int period_id) {
        this.period_id = period_id;
    }

    public static ArrayList<Room> getList() {
        ArrayList<Room> roomList = new ArrayList<>();
        String query = "SELECT * FROM room";
        Room obj;
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                obj = new Room(rs.getInt("id"), rs.getInt("hotel_id"), rs.getInt("period_id"), rs.getString("room_name"), rs.getInt("stock"), rs.getString("bed"), rs.getString("tv"), rs.getString("minibar"), rs.getString("game_console"));
                roomList.add(obj);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return roomList;
    }

    public static boolean add(int hotel_id, int period_id, String room_name, int stock, String bed, String tv, String minibar, String game_console) {
        String query = "INSERT INTO room (hotel_id, period_id, room_name, stock, bed, tv, minibar, game_console) VALUES (?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,hotel_id);
            pr.setInt(2, period_id);
            pr.setString(3,room_name);
            pr.setInt(4,stock);
            pr.setString(5,bed);
            pr.setString(6,tv);
            pr.setString(7,minibar);
            pr.setString(8,game_console);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean delete(int id) {
        String query = "DELETE FROM room WHERE id = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean deleteHotel(int hotel_id) {
        String query = "DELETE FROM room WHERE hotel_id = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,hotel_id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Room getFetchByRoomId(int room_id) {
        Room obj = null;
        String query = "SELECT * FROM room WHERE id = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,room_id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                obj = new Room(rs.getInt("id"), rs.getInt("hotel_id"), rs.getInt("period_id"),
                        rs.getString("room_name"), rs.getInt("stock"),
                        rs.getString("bed"), rs.getString("tv"),
                        rs.getString("minibar"), rs.getString("game_console"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return obj;
    }

    public static ArrayList<Room> getRoomList(String city, String check_in_date, String check_out_date) {
        ArrayList<Room> roomList = new ArrayList<>();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date fieldPeriodStart;
        Date fieldPeriodEnd;
        try {
            fieldPeriodStart = formatter.parse(check_in_date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        try {
            fieldPeriodEnd = formatter.parse(check_out_date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        for(Room obj : Room.getList()) {
            if(obj.getHotel().getCity().equals(city)) {
                Date dataPeriodStart;
                Date dataPeriodEnd;
                try {
                    dataPeriodStart = formatter.parse(obj.getPeriod().getPeriod_start());
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                try {
                    dataPeriodEnd = formatter.parse(obj.getPeriod().getPeriod_end());
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                if (dataPeriodStart.before(fieldPeriodStart) && fieldPeriodEnd.before(dataPeriodEnd)) {
                    roomList.add(Room.getFetchByRoomId(obj.getId()));
                }
            }
        }
        return roomList;
    }

    public static boolean updateStock(int room_stock, int id) {
        String query = "UPDATE room SET stock=? WHERE id=?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,room_stock);
            pr.setInt(2,id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean increaseStock(int room_stock, int id) {
        String query = "UPDATE room SET stock=? WHERE id=?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,room_stock + 1);
            pr.setInt(2,id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean deletePeriod(int period_id) {
        String query = "DELETE FROM room WHERE period_id = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,period_id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
