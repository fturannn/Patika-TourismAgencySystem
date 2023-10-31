package com.turizmacenta.View;

import com.turizmacenta.Helper.Config;
import com.turizmacenta.Helper.Helper;
import com.turizmacenta.Model.Admin;
import com.turizmacenta.Model.Agency;
import com.turizmacenta.Model.Hotel;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
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
    private JButton btn_hotel_add;
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
    private DefaultTableModel mdl_hotel_list;
    private Object [] row_hotel_list;

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
        tbl_hotel_list.getColumnModel().getColumn(0).setMaxWidth(75);

        // Seçtiğimiz satırdaki id'yi fld_user_id kutucuğuna getirir.
        tbl_hotel_list.getSelectionModel().addListSelectionListener(e -> {
            try {
                String select_hotel_id = tbl_hotel_list.getValueAt(tbl_hotel_list.getSelectedRow(), 0).toString();
                fld_hotel_id.setText(select_hotel_id);
            } catch (Exception ignored){
            }
        });

        btn_hotel_add.addActionListener(e -> {
            String hotelItem = cmb_hotel_star.getSelectedItem().toString();
            if(Helper.isFieldEmpty(fld_hotel_email) || Helper.isFieldEmpty(fld_hotel_city) || Helper.isFieldEmpty(fld_hotel_region) ||
                    Helper.isFieldEmpty(fld_hotel_address) || Helper.isFieldEmpty(fld_hotel_email) || Helper.isFieldEmpty(fld_hotel_telephone)) {
                Helper.showMsg("fill");
            } else {
                if (Hotel.add(fld_hotel_name.getText(), fld_hotel_city.getText(), fld_hotel_region.getText(), fld_hotel_address.getText(),
                        fld_hotel_email.getText(), fld_hotel_telephone.getText(), hotelItem)) {
                    Helper.showMsg("done");
                    loadHotelModel();
                    fld_hotel_name.setText(null);
                    fld_hotel_city.setText(null);
                    fld_hotel_region.setText(null);
                    fld_hotel_address.setText(null);
                    fld_hotel_email.setText(null);
                    fld_hotel_telephone.setText(null);
                } else {
                    Helper.showMsg("error");
                }
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

                if(Hotel.update(hotel_id, hotel_name, hotel_city, hotel_region, hotel_address, hotel_email, hotel_telephone, hotel_star)) {
                    Helper.showMsg("done");
                }
                loadHotelModel();
            }
        });
        // ## Hotel List and Operations
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
            row_hotel_list[i++] = obj.getAdress();
            row_hotel_list[i++] = obj.getEmail();
            row_hotel_list[i++] = obj.getTelephone();
            row_hotel_list[i++] = obj.getStar();
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
            row_hotel_list[i++] = obj.getAdress();
            row_hotel_list[i++] = obj.getEmail();
            row_hotel_list[i++] = obj.getTelephone();
            row_hotel_list[i++] = obj.getStar();
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
