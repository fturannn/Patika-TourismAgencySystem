package com.turizmacenta.View;

import com.turizmacenta.Helper.Config;
import com.turizmacenta.Helper.DBConnector;
import com.turizmacenta.Helper.Helper;
import com.turizmacenta.Model.Hostel;
import com.turizmacenta.Model.Hotel;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class AddFeatureAndHostelGUI extends JFrame {

    private Hotel hotel;
    private JPanel wrapper;
    private JCheckBox chk_add_free_park;
    private JCheckBox chk_add_ultra;
    private JCheckBox chk_add_pool;
    private JCheckBox chk_add_allin;
    private JCheckBox chk_add_free_wifi;
    private JCheckBox chk_add_room_breakfast;
    private JCheckBox chk_add_fitness;
    private JCheckBox chk_add_concierge;
    private JCheckBox chk_add_spa;
    private JCheckBox chk_add_724;
    private JCheckBox chk_add_all_hostel;
    private JCheckBox chk_add_half_hostel;
    private JCheckBox chk_add_just_bed;
    private JCheckBox chk_add_full_credit;
    private JButton btn_add_facility;
    private JPanel pnl_top;

    public AddFeatureAndHostelGUI(Hotel hotel) {
        this.hotel = hotel;
        add(wrapper);
        setSize(600, 300);
        setLocation(Helper.screenCenterPoint("x", getSize()), Helper.screenCenterPoint("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);

        btn_add_facility.addActionListener(e -> {
            ArrayList<JCheckBox> feature = new ArrayList<>();
            ArrayList<JCheckBox> hostel = new ArrayList<>();
            feature.add(chk_add_free_park);
            feature.add(chk_add_pool);
            feature.add(chk_add_fitness);
            feature.add(chk_add_concierge);
            feature.add(chk_add_spa);
            feature.add(chk_add_724);
            feature.add(chk_add_free_wifi);
            hostel.add(chk_add_allin);
            hostel.add(chk_add_room_breakfast);
            hostel.add(chk_add_all_hostel);
            hostel.add(chk_add_half_hostel);
            hostel.add(chk_add_just_bed);
            hostel.add(chk_add_full_credit);
            hostel.add(chk_add_ultra);
            String hostels = "";
            String features = "";

            for (JCheckBox i : feature) {
                if (i.isSelected()) {
                    features = features + i.getText() + " ";
                    hotel.addFeature(hotel.getId(), i.getText());
                }
            }

            for (JCheckBox i : hostel) {
                if (i.isSelected()) {
                    hostels = hostels + i.getText() + " ";
                    Hostel.add(hotel.getId(), i.getText());
                }
            }

            Hotel.update(features, hostels, hotel.getId());
            dispose();
        });
    }
}
