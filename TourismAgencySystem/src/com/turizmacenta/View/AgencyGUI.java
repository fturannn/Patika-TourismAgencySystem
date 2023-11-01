package com.turizmacenta.View;

import com.turizmacenta.Helper.Config;
import com.turizmacenta.Helper.Helper;
import com.turizmacenta.Model.Agency;
import com.turizmacenta.Model.Hotel;
import com.turizmacenta.Model.Period;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class AgencyGUI extends JFrame{
    private JTabbedPane pnl_agency;
    private JPanel wrapper;
    private JButton btn_logout;
    private JTable tbl_hotel_list;
    private JTextField fld_hotel_name;
    private JTextField fld_hotel_city;
    private JTextField fld_hotel_region;
    private JTextField fld_hotel_address;
    private JTextField fld_hotel_email;
    private JTextField fld_hotel_telephone;
    private JComboBox cmb_hotel_star;
    private JButton btn_feature_hostel;
    private JPanel pnl_hotel_list;
    private JScrollPane scrl_hotel_list;
    private JPanel pnl_add_delete;
    private JLabel lbl_welcome;
    private JButton btn_hotel_delete;
    private JTextField fld_hotel_id;
    private JTextField fld_sh_hotel_name;
    private JTextField fld_sh_hotel_city;
    private JTextField fld_sh_hotel_region;
    private JComboBox cmb_sh_hotel_star;
    private JButton btn_sh_hotel;
    private JPanel pnl_hotel_sh;
    private JPanel pnl_top;
    private JTable tbl_period_list;
    private JPanel pnl_period_list;
    private JScrollPane scrl_period_list;
    private JTextField fld_period_start;
    private JTextField fld_period_end;
    private JButton btn_add_period;
    private JTextField fld_period_id;
    private JButton btn_delete_period;
    private JPanel pnl_period_add_delete;
    private JPanel pnl_room_list;
    private JTable table1;
    private JComboBox cmb_room_hotel_name;
    private JTextField fld_room_stock;
    private JComboBox cmb_room_bed_number;
    private JComboBox cmb_room_tv;
    private JComboBox cmb_room_minibar;
    private JComboBox cmb_room_game_console;
    private JTextField fld_room_name;
    private JButton btn_add_room;
    private JTextField fld_room_id;
    private JButton btn_delete_room;
    private JPanel pnl_room_add_delete;
    private DefaultTableModel mdl_hotel_list;
    private Object [] row_hotel_list;
    private DefaultTableModel mdl_period_list;
    private Object [] row_period_list;

    private final Agency agency;

    public AgencyGUI(Agency agency) {
        this.agency = agency;
        add(wrapper);

        add(wrapper);
        setSize(1250,750);
        int x = Helper.screenCenterPoint("x",getSize());
        int y = Helper.screenCenterPoint("y",getSize());
        setLocation(x, y);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);

        lbl_welcome.setText("Hoşgeldiniz " + agency.getName());

        btn_logout.addActionListener(e -> {
            dispose();
            LoginGUI login = new LoginGUI();
        });

        // Hotel List and Operations
        mdl_hotel_list = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) { // ID satırının düzenlenebilir olmasını engelleme
                if (column == 0) {
                    return false;
                }
                return super.isCellEditable(row, column);
            }
        };

        Object[] col_hotel_list = {"ID", "Otel Adı", "Şehir", "Bölge", "Tam Adres", "E-Posta", "Telefon", "Yıldız", "Tesis Özellikleri", "Pansiyon Tipi"};
        mdl_hotel_list.setColumnIdentifiers(col_hotel_list);

        row_hotel_list = new Object[col_hotel_list.length];
        loadHotelModel();

        tbl_hotel_list.setModel(mdl_hotel_list);
        tbl_hotel_list.getTableHeader().setReorderingAllowed(false); // Tablo başlıklarının düzenlenmesini engeller
        tbl_hotel_list.getColumnModel().getColumn(0).setMaxWidth(45);
        tbl_hotel_list.getColumnModel().getColumn(7).setMaxWidth(45);

        // Seçtiğimiz satırdaki id'yi fld_user_id kutucuğuna getirir.
        tbl_hotel_list.getSelectionModel().addListSelectionListener(e -> {
            try {
                String select_hotel_id = tbl_hotel_list.getValueAt(tbl_hotel_list.getSelectedRow(), 0).toString();
                fld_hotel_id.setText(select_hotel_id);
            } catch (Exception ignored){
            }
        });

        btn_hotel_delete.addActionListener(e -> {
            if(Helper.isFieldEmpty(fld_hotel_id)) {
                Helper.showMsg("fill");
            } else {
                if (Helper.confirm("sure")) {
                    int hotel_id = Integer.parseInt(fld_hotel_id.getText());
                    if(Hotel.delete(hotel_id)) {
                        Helper.showMsg("done");
                        loadHotelModel();
                        fld_hotel_id.setText(null);
                    } else {
                        Helper.showMsg("error");
                    }
                }
            }
        });

        btn_sh_hotel.addActionListener(e -> {
            String name = fld_sh_hotel_name.getText();
            String city = fld_sh_hotel_city.getText();
            String region = fld_sh_hotel_region.getText();
            String star = cmb_sh_hotel_star.getSelectedItem().toString();
            String query = Hotel.searchQuery(name, city, region, star);
            loadHotelModel(Hotel.searchHotelList(query));
        });

        tbl_hotel_list.getModel().addTableModelListener(e -> {
            if(e.getType() == TableModelEvent.UPDATE) {
                int hotel_id = Integer.parseInt(tbl_hotel_list.getValueAt(tbl_hotel_list.getSelectedRow(), 0).toString());
                String hotel_name = tbl_hotel_list.getValueAt(tbl_hotel_list.getSelectedRow(), 1).toString();
                String hotel_city = tbl_hotel_list.getValueAt(tbl_hotel_list.getSelectedRow(), 2).toString();
                String hotel_region = tbl_hotel_list.getValueAt(tbl_hotel_list.getSelectedRow(), 3).toString();
                String hotel_address = tbl_hotel_list.getValueAt(tbl_hotel_list.getSelectedRow(), 4).toString();
                String hotel_email = tbl_hotel_list.getValueAt(tbl_hotel_list.getSelectedRow(), 5).toString();
                String hotel_telephone = tbl_hotel_list.getValueAt(tbl_hotel_list.getSelectedRow(), 6).toString();
                String hotel_star = tbl_hotel_list.getValueAt(tbl_hotel_list.getSelectedRow(), 7).toString();
                String hotel_feature = tbl_hotel_list.getValueAt(tbl_hotel_list.getSelectedRow(), 8).toString();
                String hotel_hostel = tbl_hotel_list.getValueAt(tbl_hotel_list.getSelectedRow(), 9).toString();

                if(Hotel.update(hotel_id, hotel_name, hotel_city, hotel_region, hotel_address, hotel_email, hotel_telephone, hotel_star, hotel_feature, hotel_hostel)) {
                    Helper.showMsg("done");
                }
                loadHotelModel();
            }
        });

        btn_feature_hostel.addActionListener(e -> {
            String hotelItem = cmb_hotel_star.getSelectedItem().toString();
            if(Helper.isFieldEmpty(fld_hotel_email) || Helper.isFieldEmpty(fld_hotel_city) || Helper.isFieldEmpty(fld_hotel_region) ||
                    Helper.isFieldEmpty(fld_hotel_address) || Helper.isFieldEmpty(fld_hotel_email) || Helper.isFieldEmpty(fld_hotel_telephone)) {
                Helper.showMsg("fill");
            } else {
                if (Hotel.add(fld_hotel_name.getText(), fld_hotel_city.getText(), fld_hotel_region.getText(), fld_hotel_address.getText(),
                        fld_hotel_email.getText(), fld_hotel_telephone.getText(), hotelItem)) {
                    fld_hotel_name.setText(null);
                    fld_hotel_city.setText(null);
                    fld_hotel_region.setText(null);
                    fld_hotel_address.setText(null);
                    fld_hotel_email.setText(null);
                    fld_hotel_telephone.setText(null);
                    int select_last_id = Hotel.getList().get(Hotel.getList().size()-1).getId();
                    AddFeatureAndHostelGUI featureAndHostelGUI = new AddFeatureAndHostelGUI(Hotel.getFetch(select_last_id));
                    featureAndHostelGUI.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosed(WindowEvent e) {
                            loadHotelModel();
                            Helper.showMsg("done");
                        }
                    });
                } else {
                    Helper.showMsg("error");
                }
            }
        });
        // ## Hotel List and Operations

        // Period List and Operations
        mdl_period_list = new DefaultTableModel();
        mdl_hotel_list = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) { // ID satırının düzenlenebilir olmasını engelleme
                if (column == 0) {
                    return false;
                }
                return super.isCellEditable(row, column);
            }
        };

        Object[] col_period_list = {"ID", "Dönem Başlangıcı", "Dönem Bitişi"};
        mdl_period_list.setColumnIdentifiers(col_period_list);

        row_period_list = new Object[col_period_list.length];
        loadPeriodModel();

        tbl_period_list.setModel(mdl_period_list);
        tbl_period_list.getTableHeader().setReorderingAllowed(false); // Tablo başlıklarının düzenlenmesini engeller
        tbl_period_list.getColumnModel().getColumn(0).setMaxWidth(45);

        tbl_period_list.getSelectionModel().addListSelectionListener(e -> {
            try {
                String select_period_id = tbl_period_list.getValueAt(tbl_period_list.getSelectedRow(), 0).toString();
                fld_period_id.setText(select_period_id);
            } catch (Exception ignored){
            }
        });

        btn_delete_period.addActionListener(e -> {
            if(Helper.isFieldEmpty(fld_period_id)) {
                Helper.showMsg("fill");
            } else {
                if (Helper.confirm("sure")) {
                    int period_id = Integer.parseInt(fld_period_id.getText());
                    if(Period.delete(period_id)) {
                        Helper.showMsg("done");
                        loadPeriodModel();
                        fld_period_id.setText(null);
                    } else {
                        Helper.showMsg("error");
                    }
                }
            }
        });

        btn_add_period.addActionListener(e -> {
            if(Helper.isFieldEmpty(fld_period_start) || Helper.isFieldEmpty(fld_period_end)) {
                Helper.showMsg("fill");
            } else {
                String period_start = fld_period_start.getText();
                String period_end = fld_period_end.getText();
                if (Period.add(period_start, period_end)) {
                    Helper.showMsg("done");
                    loadPeriodModel();
                    fld_period_start.setText(null);
                    fld_period_end.setText(null);
                }
            }
        });
        // ## Period List and Operations
    }

    private void loadPeriodModel() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_period_list.getModel();
        clearModel.setRowCount(0);
        int i;
        for (Period obj : Period.getList()) {
            i = 0;
            row_period_list[i++] = obj.getId();
            row_period_list[i++] = obj.getPeriod_start();
            row_period_list[i++] = obj.getPeriod_end();
            mdl_period_list.addRow(row_period_list);
        }
    }

    private void loadHotelModel() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_hotel_list.getModel();
        clearModel.setRowCount(0);
        int i;
        for (Hotel obj : Hotel.getList()) {
            i = 0;
            row_hotel_list[i++] = obj.getId();
            row_hotel_list[i++] = obj.getName();
            row_hotel_list[i++] = obj.getCity();
            row_hotel_list[i++] = obj.getRegion();
            row_hotel_list[i++] = obj.getAddress();
            row_hotel_list[i++] = obj.getEmail();
            row_hotel_list[i++] = obj.getTelephone();
            row_hotel_list[i++] = obj.getStar();
            row_hotel_list[i++] = obj.getFeature();
            row_hotel_list[i++] = obj.getHostel();
            mdl_hotel_list.addRow(row_hotel_list);
        }
    }

    public void loadHotelModel(ArrayList<Hotel> list) {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_hotel_list.getModel();
        clearModel.setRowCount(0);
        int i;
        for (Hotel obj : list) {
            i = 0;
            row_hotel_list[i++] = obj.getId();
            row_hotel_list[i++] = obj.getName();
            row_hotel_list[i++] = obj.getCity();
            row_hotel_list[i++] = obj.getRegion();
            row_hotel_list[i++] = obj.getAddress();
            row_hotel_list[i++] = obj.getEmail();
            row_hotel_list[i++] = obj.getTelephone();
            row_hotel_list[i++] = obj.getStar();
            row_hotel_list[i++] = obj.getFeature();
            row_hotel_list[i++] = obj.getHostel();
            mdl_hotel_list.addRow(row_hotel_list);
        }
    }

    public static void main(String[] args) {
        Helper.setLayout();

        Agency ag = new Agency();

        ag.setId(1);
        ag.setName("Furkan Turan");
        ag.setUname("fturan");
        ag.setPass("1234");
        ag.setType("Admin");
        AgencyGUI agGUI = new AgencyGUI(ag);


    }
}
