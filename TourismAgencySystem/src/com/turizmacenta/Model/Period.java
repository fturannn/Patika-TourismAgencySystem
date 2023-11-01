package com.turizmacenta.Model;

import com.turizmacenta.Helper.DBConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Period {
    private int id;
    private String period_start;
    private String period_end;

    public Period(int id, String period_start, String period_end) {
        this.id = id;
        this.period_start = period_start;
        this.period_end = period_end;
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

    public static ArrayList<Period> getList() {
        ArrayList<Period> periodList = new ArrayList<>();
        String query = "SELECT * FROM period";
        Period obj;
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                obj = new Period(rs.getInt("id"), rs.getString("period_start"), rs.getString("period_end"));
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

    public static boolean add(String period_start, String period_end) {
        String query = "INSERT INTO period (period_start, period_end) VALUES (?,?)";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1, period_start);
            pr.setString(2, period_end);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
