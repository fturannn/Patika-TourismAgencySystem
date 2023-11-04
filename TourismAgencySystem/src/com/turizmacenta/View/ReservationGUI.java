package com.turizmacenta.View;

import com.turizmacenta.Helper.Config;
import com.turizmacenta.Helper.Helper;
import com.turizmacenta.Model.Hotel;
import com.turizmacenta.Model.Period;
import com.turizmacenta.Model.Price;
import com.turizmacenta.Model.Room;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ReservationGUI extends JFrame {
    private JPanel wrapper;
    private JTextField fld_hotel;
    private JTextField fld_hostel;
    private JTextField fld_person;
    private JTextField fld_night;
    private JTextField fld_price;
    private JButton btn_reservation;
    private int child_number;
    private int adult_number;
    private int selected_room_id;
    private String selected_hostel_type;
    private String total_night;
    private String check_in;
    private String check_out;

    public ReservationGUI(int child_number, int adult_number, int selected_room_id, String selected_hostel_type, String total_night, String check_in, String check_out) {
        this.child_number = child_number;
        this.adult_number = adult_number;
        this.selected_room_id = selected_room_id;
        this.selected_hostel_type = selected_hostel_type;
        this.total_night = total_night;
        this.check_in = check_in;
        this.check_out = check_out;

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        add(wrapper);
        setSize(300, 350);
        setLocation(Helper.screenCenterPoint("x", getSize()), Helper.screenCenterPoint("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);

        fld_hotel.setText(Hotel.getFetch(Room.getFetchByRoomId(selected_room_id).getHotel_id()).getName());
        fld_hostel.setText(selected_hostel_type);
        fld_night.setText(total_night);
        fld_person.setText(adult_number + " Yetişkin " + child_number + " Çocuk");
        
        int adult_price = 0;
        int child_price = 0;

        for (Price obj : getHotel(Price.getList(), selected_room_id)) {
            if(obj.getHostel().equals(selected_hostel_type) && obj.getAge().equals("Yetişkin 12+")) {
                adult_price = obj.getPrice();
            } else if(obj.getHostel().equals(selected_hostel_type) && obj.getAge().equals("Çocuk 4-11")) {
                child_price = obj.getPrice();
            }
        }

        int total_price = Integer.parseInt(total_night) * (adult_price * adult_number + child_price * child_number);
        fld_price.setText(String.valueOf(total_price));
    }

    public ArrayList<Price> getHotel(ArrayList<Price> list, int room_id) {
        ArrayList<Price> hotelList = new ArrayList<>();
        for(Price obj : list) {
            if(obj.getHotel_id() == Room.getFetchByRoomId(room_id).getHotel_id()) {
                hotelList.add(obj);
            }
        }
        return hotelList;
    }
}
