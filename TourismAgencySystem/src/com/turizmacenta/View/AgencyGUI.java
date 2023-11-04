package com.turizmacenta.View;

import com.turizmacenta.Helper.Config;
import com.turizmacenta.Helper.Helper;
import com.turizmacenta.Helper.Item;
import com.turizmacenta.Model.*;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

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
    private JTextField fld_price_amount;
    private JTextField fld_price_id;
    private JButton btn_price_delete;
    private JTable tbl_search_room;
    private JPanel pnl_search_room;
    private JScrollPane scrl_search_room;
    private JPanel pnl_search;
    private JComboBox cmb_search_city;
    private JTextField fld_search_check_in;
    private JTextField fld_search_check_out;
    private JComboBox cmb_search_adult;
    private JComboBox cmb_search_child;
    private JButton btn_search_hotel;
    private JComboBox cmb_hotel_city;
    private JTextField fld_res_selected_id;
    private JComboBox cmb_res_hostel_type;
    private JTextField fld_res_night;
    private JButton btn_go_reservation;
    private DefaultTableModel mdl_hotel_list;
    private Object [] row_hotel_list;
    private DefaultTableModel mdl_period_list;
    private Object [] row_period_list;
    private DefaultTableModel mdl_room_list;
    private Object [] row_room_list;
    private DefaultTableModel mdl_price_list;
    private Object [] row_price_list;
    private DefaultTableModel mdl_room_search;
    private Object [] row_room_search;

    private final Agency agency;

    public AgencyGUI(Agency agency) {
        this.agency = agency;
        add(wrapper);

        add(wrapper);
        setSize(1400,650);
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
        loadPriceHotelCombo();

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
                    if(Hotel.delete(hotel_id) && Hostel.delete(hotel_id) && Hotel.deleteFeature(hotel_id) &&
                            Period.deleteHotel(hotel_id) && Room.deleteHotel(hotel_id) && Price.deleteHotel(hotel_id)) {
                        Helper.showMsg("done");
                        loadHotelModel();
                        loadPeriodModel();
                        loadPeriodHotelCombo();
                        loadHotelCombo();
                        loadPriceModel();
                        loadPriceHotelCombo();
                        loadRoomModel();
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
                loadPeriodModel();
                loadPeriodHotelCombo();
                loadHotelCombo();
                loadPriceModel();
                loadPriceHotelCombo();
                loadRoomModel();
            }
        });

        btn_feature_hostel.addActionListener(e -> {
            String hotelStar = cmb_hotel_star.getSelectedItem().toString();
            String hotelName = cmb_hotel_city.getSelectedItem().toString();
            if(Helper.isFieldEmpty(fld_hotel_email) || Helper.isFieldEmpty(fld_hotel_region) ||
                    Helper.isFieldEmpty(fld_hotel_address) || Helper.isFieldEmpty(fld_hotel_email) || Helper.isFieldEmpty(fld_hotel_telephone)) {
                Helper.showMsg("fill");
            } else {
                if (Hotel.add(fld_hotel_name.getText(), hotelName, fld_hotel_region.getText(), fld_hotel_address.getText(),
                        fld_hotel_email.getText(), fld_hotel_telephone.getText(), hotelStar)) {
                    fld_hotel_name.setText(null);
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
                            loadPeriodHotelCombo();
                            loadHotelCombo();
                            loadPriceHotelCombo();
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
                String period = period_start + "-" + period_end;
                if (Period.add(hotelItem.getKey() ,period_start, period_end, period)) {
                    Helper.showMsg("done");
                    loadPeriodModel();
                    fld_period_start.setText(null);
                    fld_period_end.setText(null);
                }
            }
        });

        fld_period_start.setForeground(Color.GRAY);
        fld_period_start.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (fld_period_start.getText().trim().equals("gg/aa/yyyy")) {
                    fld_period_start.setText("");
                }
                fld_period_start.setForeground(Color.BLACK);
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (fld_period_start.getText().trim().isEmpty()) {
                    fld_period_start.setText("gg/aa/yyyy");
                }
                fld_period_start.setForeground(Color.GRAY);
            }
        });

        fld_period_end.setForeground(Color.GRAY);
        fld_period_end.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (fld_period_end.getText().trim().equals("gg/aa/yyyy")) {
                    fld_period_end.setText("");
                }
                fld_period_end.setForeground(Color.BLACK);
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (fld_period_end.getText().trim().isEmpty()) {
                    fld_period_end.setText("gg/aa/yyyy");
                }
                fld_period_end.setForeground(Color.GRAY);
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

        Object[] col_price_list = {"ID", "Otel ID", "Dönem", "Pansiyon Tipi", "Yaş Dönemi", "Fiyat"};
        mdl_price_list.setColumnIdentifiers(col_price_list);

        row_price_list = new Object[col_price_list.length];
        loadPriceModel();

        tbl_price_list.setModel(mdl_price_list);
        tbl_price_list.getTableHeader().setReorderingAllowed(false);
        tbl_price_list.getColumnModel().getColumn(0).setMaxWidth(45);
        tbl_price_list.getColumnModel().getColumn(1).setMaxWidth(60);

        cmb_price_hotel_name.addActionListener(e -> {
            loadPriceHostelCombo();
            loadPricePeriodCombo();
        });

        btn_price_add.addActionListener(e -> {
            Item hotelItem = (Item) cmb_price_hotel_name.getSelectedItem();
            Item periodItem = (Item) cmb_price_period.getSelectedItem();
            String hostel = cmb_price_hostel.getSelectedItem().toString();
            String selected_age = cmb_price_age.getSelectedItem().toString();
            if(Helper.isFieldEmpty(fld_price_amount)) {
                Helper.showMsg("fill");
            } else {
                int price = Integer.parseInt(fld_price_amount.getText());
                if (Price.add(hotelItem.getKey(),Hotel.getFetch(hotelItem.getKey()).getCity(), periodItem.getKey(), Period.getFetch(periodItem.getKey()).getPeriod_start(), Period.getFetch(periodItem.getKey()).getPeriod_end(),hostel, selected_age, price)) {
                    Helper.showMsg("done");
                    loadPriceModel();
                    fld_price_amount.setText(null);
                }
            }
        });

        tbl_price_list.getSelectionModel().addListSelectionListener(e -> {
            try {
                String select_price_id = tbl_price_list.getValueAt(tbl_price_list.getSelectedRow(), 0).toString();
                fld_price_id.setText(select_price_id);
            } catch (Exception ignored){
            }
        });

        btn_price_delete.addActionListener(e -> {
            if(Helper.isFieldEmpty(fld_price_id)) {
                Helper.showMsg("fill");
            } else {
                if (Helper.confirm("sure")) {
                    int price_id = Integer.parseInt(fld_price_id.getText());
                    if(Price.delete(price_id)) {
                        Helper.showMsg("done");
                        loadPriceModel();
                        fld_price_id.setText(null);
                    } else {
                        Helper.showMsg("error");
                    }
                }
            }
        });

        // ## Price List and Operations

        // Room Search Table and Operations
        mdl_room_search = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0) {
                    return false;
                }
                return super.isCellEditable(row, column);
            }
        };

        Object[] col_room_search = {"Oda ID","Otel Adı", "Şehir", "Bölge", "Adres", "Telefon", "Email", "Yıldız", "Otel Hizmetleri", "Oda Adı", "Stok", "Yatak Sayısı", "TV", "Minibar", "Oyun Konsolu"};
        mdl_room_search.setColumnIdentifiers(col_room_search);

        row_room_search = new Object[col_room_search.length];

        tbl_search_room.setModel(mdl_room_search);
        tbl_search_room.getTableHeader().setReorderingAllowed(false);
        tbl_search_room.getColumnModel().getColumn(0).setMaxWidth(80);
        tbl_search_room.getColumnModel().getColumn(1).setMaxWidth(80);
        tbl_search_room.getColumnModel().getColumn(2).setMaxWidth(80);

        fld_search_check_in.setForeground(Color.GRAY);
        fld_search_check_in.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (fld_search_check_in.getText().trim().equals("gg/aa/yyyy")) {
                    fld_search_check_in.setText("");
                }
                fld_search_check_in.setForeground(Color.BLACK);
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (fld_search_check_in.getText().trim().isEmpty()) {
                    fld_search_check_in.setText("gg/aa/yyyy");
                }
                fld_search_check_in.setForeground(Color.GRAY);
            }
        });

        fld_search_check_out.setForeground(Color.GRAY);
        fld_search_check_out.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (fld_search_check_out.getText().trim().equals("gg/aa/yyyy")) {
                    fld_search_check_out.setText("");
                }
                fld_search_check_out.setForeground(Color.BLACK);
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (fld_search_check_out.getText().trim().isEmpty()) {
                    fld_search_check_out.setText("gg/aa/yyyy");
                }
                fld_search_check_out.setForeground(Color.GRAY);
            }
        });

        btn_search_hotel.addActionListener(e -> {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            String city = cmb_search_city.getSelectedItem().toString();
            String check_in = fld_search_check_in.getText().trim();
            String check_out = fld_search_check_out.getText().trim();

            Date firstDate = null;
            Date secondDate = null;

            try {
                firstDate = formatter.parse(check_in);
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }
            try {
                secondDate = formatter.parse(check_out);
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }
            long diffInMillies = Math.abs(secondDate.getTime() - firstDate.getTime());
            String diff = String.valueOf(TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS));
            fld_res_night.setText(diff);
            loadSearchModel(Room.getRoomList(city, check_in, check_out));
        });

        tbl_search_room.getSelectionModel().addListSelectionListener(e -> {
            try {
                String select_room_id = tbl_search_room.getValueAt(tbl_search_room.getSelectedRow(), 0).toString();
                fld_res_selected_id.setText(select_room_id);
            } catch (Exception ignored){
            }
            int select_hotel_id = Room.getFetchByRoomId(Integer.parseInt(tbl_search_room.getValueAt(tbl_search_room.getSelectedRow(),0).toString())).getHotel_id();
            loadHostelTypeCombo(select_hotel_id);
        });

        btn_go_reservation.addActionListener(e -> {
            int child_number = Integer.parseInt(cmb_search_child.getSelectedItem().toString());
            int adult_number = Integer.parseInt(cmb_search_adult.getSelectedItem().toString());
            int selected_room_id = Integer.parseInt(fld_res_selected_id.getText());
            String selected_hostel_type = cmb_res_hostel_type.getSelectedItem().toString();
            String total_night = fld_res_night.getText();
            String check_in = fld_search_check_in.getText().trim();
            String check_out = fld_search_check_out.getText().trim();
            ReservationGUI resGUI = new ReservationGUI(child_number, adult_number, selected_room_id, selected_hostel_type, total_night, check_in, check_out);
        });
        // ## Room Search Table and Operations

    }

    private void loadSearchModel(ArrayList<Room> list) {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_search_room.getModel();
        clearModel.setRowCount(0);
        int i;
        for (Room obj : list) {
            if (obj != null) {
                i = 0;
                row_room_search[i++] = obj.getId();
                row_room_search[i++] = obj.getHotel().getName();
                row_room_search[i++] = obj.getHotel().getCity();
                row_room_search[i++] = obj.getHotel().getRegion();
                row_room_search[i++] = obj.getHotel().getAddress();
                row_room_search[i++] = obj.getHotel().getTelephone();
                row_room_search[i++] = obj.getHotel().getEmail();
                row_room_search[i++] = obj.getHotel().getStar();
                row_room_search[i++] = obj.getHotel().getFeature();
                row_room_search[i++] = obj.getRoom_name();
                row_room_search[i++] = obj.getStock();
                row_room_search[i++] = obj.getBed();
                row_room_search[i++] = obj.getTv();
                row_room_search[i++] = obj.getMinibar();
                row_room_search[i++] = obj.getGame_console();
                mdl_room_search.addRow(row_room_search);
            } else {
                Helper.showMsg("Aranan kriterlerde oda bulunamadı!");
            }
        }
    }

    private void loadPricePeriodCombo() {
        cmb_price_period.removeAllItems();
        for (Period obj : Period.getList()) {
            if(cmb_price_hotel_name.getSelectedItem() == null) {
                cmb_price_period.removeAllItems();
            } else if(obj.getHotel().getName().equals(cmb_price_hotel_name.getSelectedItem().toString())) {
                cmb_price_period.addItem(new Item(obj.getId(), obj.getPeriod()));
            }
        }
    }

    private void loadPriceHostelCombo() {
        cmb_price_hostel.removeAllItems();
        for (Hostel obj : Hostel.getList()) {
             if(cmb_price_hotel_name.getSelectedItem() == null) {
                cmb_price_period.removeAllItems();
            } else if(obj.getHotel().getName().equals(cmb_price_hotel_name.getSelectedItem().toString())) {
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
            row_price_list[i++] = obj.getPeriod().getPeriod();
            row_price_list[i++] = obj.getHostel();
            row_price_list[i++] = obj.getAge();
            row_price_list[i++] = obj.getPrice();
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

    public void loadHostelTypeCombo(int hotel_id) {
        cmb_res_hostel_type.removeAllItems();
        for (Hostel obj : Hostel.getList()) {
            if(hotel_id == obj.getHotel_id()) {
                cmb_res_hostel_type.addItem(new Item(obj.getId(), obj.getHostel()));
            }
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
