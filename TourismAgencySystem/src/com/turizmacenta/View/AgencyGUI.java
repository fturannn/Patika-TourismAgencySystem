package com.turizmacenta.View;

import com.turizmacenta.Helper.Config;
import com.turizmacenta.Helper.Helper;
import com.turizmacenta.Helper.Item;
import com.turizmacenta.Model.*;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private JTable tbl_room_list;
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
    private JScrollPane scrl_room_list;
    private JComboBox cmb_hotel;
    private JComboBox cmb_room_name;
    private JTable tbl_price_list;
    private JComboBox cmb_price_period;
    private JComboBox cmb_price_hostel;
    private JComboBox cmb_price_age;
    private JButton btn_price_add;
    private JPanel pnl_price_list;
    private JScrollPane scrl_price_list;
    private JPanel pnl_price_add_delete;
    private JComboBox cmb_price_hotel_name;
    private DefaultTableModel mdl_hotel_list;
    private Object [] row_hotel_list;
    private DefaultTableModel mdl_period_list;
    private Object [] row_period_list;
    private DefaultTableModel mdl_room_list;
    private Object [] row_room_list;
    private DefaultTableModel mdl_price_list;
    private Object [] row_price_list;

    private final Agency agency;

    public AgencyGUI(Agency agency) {
        this.agency = agency;
        add(wrapper);

        add(wrapper);
        setSize(1250,650);
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
            loadSearchHotelModel(Hotel.searchHotelList(query));
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
        mdl_period_list = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) { // ID satırının düzenlenebilir olmasını engelleme
                if (column == 0) {
                    return false;
                }
                return super.isCellEditable(row, column);
            }
        };

        Object[] col_period_list = {"ID", "Otel ID", "Dönem Başlangıcı", "Dönem Bitişi"};
        mdl_period_list.setColumnIdentifiers(col_period_list);

        row_period_list = new Object[col_period_list.length];

        tbl_period_list.setModel(mdl_period_list);
        tbl_period_list.getTableHeader().setReorderingAllowed(false); // Tablo başlıklarının düzenlenmesini engeller
        tbl_period_list.getColumnModel().getColumn(0).setMaxWidth(45);
        tbl_period_list.getColumnModel().getColumn(1).setMaxWidth(60);
        loadPeriodModel();
        loadPeriodHotelCombo();

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
            Item hotelItem = (Item) cmb_hotel.getSelectedItem();
            if(Helper.isFieldEmpty(fld_period_start) || Helper.isFieldEmpty(fld_period_end)) {
                Helper.showMsg("fill");
            } else {
                String period_start = fld_period_start.getText();
                String period_end = fld_period_end.getText();
                if (Period.add(hotelItem.getKey() ,period_start, period_end)) {
                    Helper.showMsg("done");
                    loadPeriodModel();
                    loadPricePeriodCombo();
                    fld_period_start.setText(null);
                    fld_period_end.setText(null);
                }
            }
        });
        // ## Period List and Operations

        // Room List and Operations
        mdl_room_list = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0) {
                    return false;
                }
                return super.isCellEditable(row, column);
            }
        };

        Object[] col_room_list = {"ID", "Otel ID", "Oda Adı", "Stok Durumu", "Yatak Sayısı", "Televizyon", "Minibar", "Oyun Konsolu"};
        mdl_room_list.setColumnIdentifiers(col_room_list);

        row_room_list = new Object[col_room_list.length];
        loadRoomModel();
        loadHotelCombo();

        tbl_room_list.setModel(mdl_room_list);
        tbl_room_list.getTableHeader().setReorderingAllowed(false);
        tbl_room_list.getColumnModel().getColumn(0).setMaxWidth(45);
        tbl_room_list.getColumnModel().getColumn(1).setMaxWidth(60);

        tbl_room_list.getSelectionModel().addListSelectionListener(e -> {
            try {
                String select_room_id = tbl_room_list.getValueAt(tbl_room_list.getSelectedRow(), 0).toString();
                fld_room_id.setText(select_room_id);
            } catch (Exception ignored){
            }
        });

        btn_delete_room.addActionListener(e -> {
            if(Helper.isFieldEmpty(fld_room_id)) {
                Helper.showMsg("fill");
            } else {
                if (Helper.confirm("sure")) {
                    int room_id = Integer.parseInt(fld_room_id.getText());
                    if(Room.delete(room_id)) {
                        Helper.showMsg("done");
                        loadRoomModel();
                        fld_room_id.setText(null);
                    } else {
                        Helper.showMsg("error");
                    }
                }
            }
        });

        btn_add_room.addActionListener(e -> {
            Item hotelItem = (Item) cmb_room_hotel_name.getSelectedItem();
            if(Helper.isFieldEmpty(fld_room_stock)) {
                Helper.showMsg("fill");
            } else {
                String bed = cmb_room_bed_number.getSelectedItem().toString();
                String tv = cmb_room_tv.getSelectedItem().toString();
                String minibar = cmb_room_minibar.getSelectedItem().toString();
                String game_console = cmb_room_game_console.getSelectedItem().toString();
                String room_name = cmb_room_name.getSelectedItem().toString();
                int room_stock = Integer.parseInt(fld_room_stock.getText().toString());
                if (Room.add(hotelItem.getKey(), room_name, room_stock, bed, tv, minibar, game_console)) {
                    Helper.showMsg("done");
                    loadRoomModel();
                    fld_room_stock.setText(null);
                }
            }
        });
        // ## Room List and Operations

        // Price List and Operations
        mdl_price_list = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0) {
                    return false;
                }
                return super.isCellEditable(row, column);
            }
        };

        Object[] col_price_list = {"ID", "Otel ID", "Dönem", "Pansiyon Tipi", "Yaş Dönemi"};
        mdl_price_list.setColumnIdentifiers(col_price_list);

        row_price_list = new Object[col_price_list.length];
        loadPriceModel();
        loadPriceHotelCombo();


        tbl_price_list.setModel(mdl_price_list);
        tbl_price_list.getTableHeader().setReorderingAllowed(false);
        tbl_price_list.getColumnModel().getColumn(0).setMaxWidth(45);
        tbl_price_list.getColumnModel().getColumn(1).setMaxWidth(60);
        loadPricePeriodCombo();
        loadPriceHostelCombo();

        // ## Price List and Operations
    }

    private void loadPricePeriodCombo() {
        cmb_price_period.removeAllItems();
        for (Period obj : Period.getList()) {
            if(obj.getHotel().getName().equals(cmb_price_hotel_name.getSelectedItem().toString())) {
                cmb_price_period.addItem(new Item(obj.getId(), obj.getPeriod()));
            }
        }
    }

    private void loadPriceHostelCombo() {
        cmb_price_hostel.removeAllItems();
        for (Hostel obj : Hostel.getList()) {
            if(obj.getHotel().getName().equals(cmb_price_hotel_name.getSelectedItem().toString())) {
                cmb_price_hostel.addItem(new Item(obj.getHotel().getId(), obj.getHostel()));
            }
        }
    }

    private void loadPriceHotelCombo() {
        cmb_price_hotel_name.removeAllItems();
        for (Hotel obj : Hotel.getList()) {
            cmb_price_hotel_name.addItem(new Item(obj.getId(), obj.getName()));
        }
    }

    private void loadPriceModel() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_price_list.getModel();
        clearModel.setRowCount(0);
        int i;
        for (Price obj : Price.getList()) {
            i = 0;
            row_price_list[i++] = obj.getId();
            row_price_list[i++] = obj.getHotel_id();
            row_price_list[i++] = obj.getPeriod();
            row_price_list[i++] = obj.getHostel();
            row_price_list[i++] = obj.getAge();
            mdl_price_list.addRow(row_price_list);
        }
    }

    private void loadRoomModel() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_room_list.getModel();
        clearModel.setRowCount(0);
        int i;
        for (Room obj : Room.getList()) {
            i = 0;
            row_room_list[i++] = obj.getId();
            row_room_list[i++] = obj.getHotel_id();
            row_room_list[i++] = obj.getRoom_name();
            row_room_list[i++] = obj.getStock();
            row_room_list[i++] = obj.getBed();
            row_room_list[i++] = obj.getTv();
            row_room_list[i++] = obj.getMinibar();
            row_room_list[i++] = obj.getGame_console();
            mdl_room_list.addRow(row_room_list);
        }
    }

    public void loadHotelCombo() {
        cmb_room_hotel_name.removeAllItems();
        for (Hotel obj : Hotel.getList()) {
            cmb_room_hotel_name.addItem(new Item(obj.getId(), obj.getName()));
        }
    }

    public void loadPeriodHotelCombo() {
        cmb_hotel.removeAllItems();
        for (Hotel obj : Hotel.getList()) {
            cmb_hotel.addItem(new Item(obj.getId(), obj.getName()));
        }
    }

    private void loadPeriodModel() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_period_list.getModel();
        clearModel.setRowCount(0);
        int i;
        for (Period obj : Period.getList()) {
            i = 0;
            row_period_list[i++] = obj.getId();
            row_period_list[i++] = obj.getHotel_id();
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
            System.out.println(obj.getName());
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

    public void loadSearchHotelModel(ArrayList<Hotel> list) {
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
