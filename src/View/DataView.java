package View;

import ConfigDB.ConnectDB;
import Model.Classify;
import Model.Position;
import Model.Producer;
import Service.ClassifyService;
import Service.Impl.ClassifyServiceImpl;
import Service.Impl.PositionServiceImpl;
import Service.Impl.ProducerServiceImpl;
import Service.PositionService;
import Service.ProducerService;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class DataView extends javax.swing.JFrame {
    
    private Connection conn = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    private final ConnectDB connectDB = new ConnectDB();
    
    private Detail detail;
    private boolean isCheckedAdd = false, isCheckedChange = false;
    private PositionService positionService;
    private ProducerService producerService;
    private ClassifyService classifyService;
    
    String sql3 = "SELECT * FROM Classify";
    
    public DataView(Detail d) {
        initComponents();
        setResizable(false);
        setLocationRelativeTo(null);
        positionService = new PositionServiceImpl();
        producerService = new ProducerServiceImpl();
        classifyService = new ClassifyServiceImpl();
        
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        detail = new Detail(d);
        lblStatus.setForeground(Color.red);
        connection();
        loadPosition();
        loadProducer();
        loadClassify(sql3);
        setDisabledPosition();
        setDisabledClassify();
        setDisabledProducer();
    }
    
    private void connection() {
        conn = connectDB.getConnect();
    }
    
    private void loadPosition() {
        tablePosition.removeAll();
        List<Position> list = positionService.getListPosition();
        
        String[] arr = {"Mã Chức Vụ", "Chức Vụ", "Lương Cơ Bản"};
        DefaultTableModel modle = new DefaultTableModel(arr, 0);
        
        list.forEach((b) -> {
            Vector vector = new Vector();
            vector.add(b.getId().trim());
            vector.add(b.getPosition().trim());
            vector.add(b.getPayroll().trim());
            modle.addRow(vector);
        });
        tablePosition.setModel(modle);
        
    }
    
    private void loadProducer() {
        tableProducer.removeAll();
        List<Producer> list = producerService.getListProducer();
        
        String[] arr = {"Mã NSX", "Nhà Sản Xuất", "Địa Chỉ", "Số Điện Thoại", "Email"};
        DefaultTableModel modle = new DefaultTableModel(arr, 0);
        list.forEach((b) -> {
            Vector vector = new Vector();
            vector.add(b.getId().trim());
            vector.add(b.getName().trim());
            vector.add(b.getAddress().trim());
            vector.add(b.getPhone().trim());
            vector.add(b.getEmail().trim());
            modle.addRow(vector);
        });
        
        tableProducer.setModel(modle);
        
    }
    
    private void loadClassify(String sql) {
        tableClassify.removeAll();
        List<Classify> list = classifyService.getListClassify();
        
        String[] arr = {"Mã Loại", "Loại Linh Kiện"};
        DefaultTableModel modle = new DefaultTableModel(arr, 0);
        list.forEach((b) -> {
            Vector vector = new Vector();
            vector.add(b.getId().trim());
            vector.add(b.getClassify().trim());
            modle.addRow(vector);
        });
        tableClassify.setModel(modle);
        
    }
    
    private void backHome() {
        HomeManager homeManager = new HomeManager(detail);
        homeManager.setVisible(true);
        this.setVisible(false);
    }
    
    private void setEnabledPosition() {
        txbIDPosition.setEnabled(true);
        txbPosition.setEnabled(true);
        txbPayroll.setEnabled(true);
        lblStatus.setText("Trạng Thái!");
    }
    
    private void setEnabledProducer() {
        txbIDProducer.setEnabled(true);
        txbProducer.setEnabled(true);
        txbAdress.setEnabled(true);
        txbPhone.setEnabled(true);
        txbEmail.setEnabled(true);
        lblStatus.setText("Trạng Thái!");
    }
    
    private void setEnabledClassify() {
        txbIDClassify.setEnabled(true);
        txbClassify.setEnabled(true);
        lblStatus.setText("Trạng Thái!");
    }
    
    private void setDisabledPosition() {
        txbIDPosition.setEnabled(false);
        txbPosition.setEnabled(false);
        txbPayroll.setEnabled(false);
    }
    
    private void setDisabledProducer() {
        txbIDProducer.setEnabled(false);
        txbProducer.setEnabled(false);
        txbAdress.setEnabled(false);
        txbPhone.setEnabled(false);
        txbEmail.setEnabled(false);
    }
    
    private void setDisabledClassify() {
        txbIDClassify.setEnabled(false);
        txbClassify.setEnabled(false);
    }
    
    private void setRefresh() {
        isCheckedChange = false;
        isCheckedAdd = false;
        txbIDPosition.setText("");
        txbPosition.setText("");
        txbPayroll.setText("");
        txbIDProducer.setText("");
        txbProducer.setText("");
        txbAdress.setText("");
        txbPhone.setText("");
        txbEmail.setText("");
        txbIDClassify.setText("");
        txbClassify.setText("");
        btnAddPosition.setEnabled(true);
        btnChangePosition.setEnabled(false);
        btnDeletePosition.setEnabled(false);
        btnSavePosition.setEnabled(false);
        btnAddProducer.setEnabled(true);
        btnChangeProducer.setEnabled(false);
        btnDeleteProducer.setEnabled(false);
        btnSaveProducer.setEnabled(false);
        btnAddClassify.setEnabled(true);
        btnChangeClassify.setEnabled(false);
        btnDeleteClassify.setEnabled(false);
        btnSaveClassify.setEnabled(false);
        setDisabledClassify();
        setDisabledPosition();
        setDisabledProducer();
    }
    
    private boolean checkNullPosition() {
        boolean kq = true;
        if (String.valueOf(this.txbIDPosition.getText()).length() == 0) {
            lblStatus.setText("Bạn chưa ID cho chức vụ!");
            return false;
        }
        if (String.valueOf(this.txbPosition.getText()).length() == 0) {
            lblStatus.setText("Bạn chưa nhập chức vụ!");
            return false;
        }
        if (String.valueOf(this.txbPayroll.getText()).length() == 0) {
            lblStatus.setText("Bạn chưa nhập lương cơ bản của chức vụ!");
            return false;
        }
        return kq;
    }
    
    private boolean checkNullProducer() {
        boolean kq = true;
        if (String.valueOf(this.txbIDProducer.getText()).length() == 0) {
            lblStatus.setText("Bạn chưa ID cho nhà sản xuất!");
            return false;
        }
        if (String.valueOf(this.txbProducer.getText()).length() == 0) {
            lblStatus.setText("Bạn chưa nhập tên nhà sản xuất!");
            return false;
        }
        if (String.valueOf(this.txbAdress.getText()).length() == 0) {
            lblStatus.setText("Bạn chưa nhập địa chỉ của nhà sản xuất!");
            return false;
        }
        if (String.valueOf(this.txbPhone.getText()).length() == 0) {
            lblStatus.setText("Bạn chưa nhập số điện thoại của nhà sản xuất!");
            return false;
        }
        if (String.valueOf(this.txbEmail.getText()).length() == 0) {
            lblStatus.setText("Bạn chưa nhập Email của nhà sản xuất!");
            return false;
        }
        return kq;
    }
    
    private boolean checkNullClassify() {
        boolean kq = true;
        if (String.valueOf(this.txbIDClassify.getText()).length() == 0) {
            lblStatus.setText("Bạn chưa ID cho loại linh kiện!");
            return false;
        }
        if (String.valueOf(this.txbClassify.getText()).length() == 0) {
            lblStatus.setText("Bạn chưa nhập loại linh kiện!");
            return false;
        }
        return kq;
    }
    
    private void addPosition() {
        if (checkNullPosition()) {
            Position p = new Position();
            p.setId(txbIDPosition.getText());
            p.setPosition(txbPosition.getText());
            p.setPayroll(txbPayroll.getText() + " " + "VND");
            positionService.insertPosition(p);
            lblStatus.setText("Thêm Chức vụ thành công!");
            setDisabledPosition();
            setRefresh();
            loadPosition();
        }
    }
    
    private void addProducer() {
        if (checkNullProducer()) {
            Producer p = new Producer();
            p.setId(txbIDProducer.getText());
            p.setName(txbProducer.getText());
            p.setAddress(txbAdress.getText());
            p.setPhone(txbPhone.getText());
            p.setEmail(txbEmail.getText());
            producerService.addProducer(p);
            lblStatus.setText("Thêm nhà sản xuất thành công!");
            setDisabledProducer();
            setRefresh();
            loadProducer();
            
        }
    }
    
    private void addClassify() {
        if (checkNullClassify()) {
            Classify c = new Classify();
            c.setId(txbIDClassify.getText());
            c.setClassify(txbClassify.getText());
            classifyService.insertClasstify(c);
            lblStatus.setText("Thêm loại linh kiện thành công!");
            setDisabledClassify();
            setRefresh();
            loadClassify(sql3);
            
        }
    }
    
    private void changedPosition() {
        int selectedRow = tablePosition.getSelectedRow();
        TableModel model = tablePosition.getModel();
        if (checkNullPosition()) {
            String sqlChange = "UPDATE Position SET ID=?, Position=?,Payroll=? WHERE ID='" + model.getValueAt(selectedRow, 0).toString().trim() + "'";
            try {
                pst = conn.prepareStatement(sqlChange);
                pst.setString(1, txbIDPosition.getText());
                pst.setString(2, txbPosition.getText());
                pst.setString(3, txbPayroll.getText() + " " + "VND");
                pst.executeUpdate();
                lblStatus.setText("Lưu thay đổi thành công!");
                setDisabledPosition();
                setRefresh();
                loadPosition();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    private void changedClassify() {
        int selectedRow = tableClassify.getSelectedRow();
        TableModel model = tableClassify.getModel();
        if (checkNullClassify()) {
            String sqlChange = "UPDATE Classify SET ID=?, Classify=? WHERE ID='" + model.getValueAt(selectedRow, 0).toString().trim() + "'";;
            try {
                pst = conn.prepareStatement(sqlChange);
                pst.setString(1, txbIDClassify.getText());
                pst.setString(2, txbClassify.getText());
                pst.executeUpdate();
                lblStatus.setText("Lưu thay đổi thành công!");
                setDisabledClassify();
                setRefresh();
                loadClassify(sql3);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    private void changedProducer() {
        int selectedRow = tableProducer.getSelectedRow();
        TableModel model = tableProducer.getModel();
        
        if (checkNullProducer()) {
            String sqlChange = "UPDATE Producer SET ID=?, ProducerName=?, Address=?, Phone=?,Email=? WHERE ID='" + model.getValueAt(selectedRow, 0).toString().trim() + "'";;
            try {
                pst = conn.prepareStatement(sqlChange);
                pst.setString(1, txbIDProducer.getText());
                pst.setString(2, txbProducer.getText());
                pst.setString(3, txbAdress.getText());
                pst.setString(4, txbPhone.getText());
                pst.setString(5, txbEmail.getText());
                pst.executeUpdate();
                lblStatus.setText("Lưu thay đổi thành công!");
                setDisabledProducer();
                setRefresh();
                loadProducer();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    private double convertedToNumbers(String s) {
        String number = "";
        String[] array = s.replace(",", " ").split("\\s");
        for (String i : array) {
            number = number.concat(i);
        }
        return Double.parseDouble(number);
    }
    
    private String cutChar(String arry) {
        return arry.replaceAll("\\D+", "");
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableProducer = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        btnRefreshProducer = new javax.swing.JButton();
        btnAddProducer = new javax.swing.JButton();
        btnChangeProducer = new javax.swing.JButton();
        btnDeleteProducer = new javax.swing.JButton();
        btnSaveProducer = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txbIDProducer = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txbProducer = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txbAdress = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txbPhone = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txbEmail = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableClassify = new javax.swing.JTable();
        txbIDClassify = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        txbClassify = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jPanel26 = new javax.swing.JPanel();
        btnRefreshClassify = new javax.swing.JButton();
        btnAddClassify = new javax.swing.JButton();
        btnChangeClassify = new javax.swing.JButton();
        btnDeleteClassify = new javax.swing.JButton();
        btnSaveClassify = new javax.swing.JButton();
        jPanel27 = new javax.swing.JPanel();
        jPanel28 = new javax.swing.JPanel();
        jPanel29 = new javax.swing.JPanel();
        jPanel30 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablePosition = new javax.swing.JTable();
        txbPayroll = new javax.swing.JTextField();
        jPanel19 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        txbPosition = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        txbIDPosition = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        btnRefreshPosition = new javax.swing.JButton();
        btnAddPosition = new javax.swing.JButton();
        btnChangePosition = new javax.swing.JButton();
        btnDeletePosition = new javax.swing.JButton();
        btnSavePosition = new javax.swing.JButton();
        jPanel18 = new javax.swing.JPanel();
        jPanel31 = new javax.swing.JPanel();
        jPanel32 = new javax.swing.JPanel();
        jPanel33 = new javax.swing.JPanel();
        lblStatus = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        btnBackHome = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        tableProducer.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tableProducer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableProducerMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tableProducer);

        btnRefreshProducer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Refresh-icon.png"))); // NOI18N
        btnRefreshProducer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRefreshProducerMouseClicked(evt);
            }
        });

        btnAddProducer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Add.png"))); // NOI18N
        btnAddProducer.setText("Thêm");
        btnAddProducer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddProducerActionPerformed(evt);
            }
        });

        btnChangeProducer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Change.png"))); // NOI18N
        btnChangeProducer.setText("Sửa");
        btnChangeProducer.setEnabled(false);
        btnChangeProducer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChangeProducerActionPerformed(evt);
            }
        });

        btnDeleteProducer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Delete.png"))); // NOI18N
        btnDeleteProducer.setText("Xóa");
        btnDeleteProducer.setEnabled(false);
        btnDeleteProducer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteProducerActionPerformed(evt);
            }
        });

        btnSaveProducer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Save.png"))); // NOI18N
        btnSaveProducer.setText("Lưu");
        btnSaveProducer.setEnabled(false);
        btnSaveProducer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveProducerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnRefreshProducer, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAddProducer, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnChangeProducer, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDeleteProducer, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSaveProducer, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnRefreshProducer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAddProducer, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                    .addComponent(btnSaveProducer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnChangeProducer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDeleteProducer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel7.setText("Mã NSX:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel8.setText("Nhà Sản Xuất:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel9.setText("Địa Chỉ:");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel10.setText("Số Điện Thoại:");

        txbPhone.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txbPhoneKeyReleased(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel11.setText("Email:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                        .addComponent(txbIDProducer, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txbProducer, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txbAdress, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txbPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txbEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jScrollPane2)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txbIDProducer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(txbProducer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(txbAdress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(txbPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(txbEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 329, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Nhà Sản Xuất", jPanel2);

        tableClassify.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tableClassify.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableClassifyMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tableClassify);

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel12.setText("Mã Loại:");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 153, Short.MAX_VALUE)
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel13.setText("Loại linh kiện:");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 153, Short.MAX_VALUE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 22, Short.MAX_VALUE)
        );

        btnRefreshClassify.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Refresh-icon.png"))); // NOI18N
        btnRefreshClassify.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRefreshClassifyMouseClicked(evt);
            }
        });

        btnAddClassify.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Add.png"))); // NOI18N
        btnAddClassify.setText("Thêm");
        btnAddClassify.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddClassifyActionPerformed(evt);
            }
        });

        btnChangeClassify.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Change.png"))); // NOI18N
        btnChangeClassify.setText("Sửa");
        btnChangeClassify.setEnabled(false);
        btnChangeClassify.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChangeClassifyActionPerformed(evt);
            }
        });

        btnDeleteClassify.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Delete.png"))); // NOI18N
        btnDeleteClassify.setText("Xóa");
        btnDeleteClassify.setEnabled(false);
        btnDeleteClassify.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteClassifyActionPerformed(evt);
            }
        });

        btnSaveClassify.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Save.png"))); // NOI18N
        btnSaveClassify.setText("Lưu");
        btnSaveClassify.setEnabled(false);
        btnSaveClassify.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveClassifyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addComponent(btnRefreshClassify, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAddClassify, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnChangeClassify, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDeleteClassify, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSaveClassify, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnRefreshClassify, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAddClassify, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                    .addComponent(btnSaveClassify, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel27, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel29, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel30, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnChangeClassify, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDeleteClassify, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txbIDClassify, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txbClassify, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel12)
                        .addComponent(txbIDClassify, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txbClassify, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel13)))
                .addGap(18, 18, 18)
                .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 329, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Loại Linh Kiện", jPanel3);

        tablePosition.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tablePosition.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablePositionMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablePosition);

        txbPayroll.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txbPayrollKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 67, Short.MAX_VALUE)
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel6.setText("Lương Cơ Bản:");

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 67, Short.MAX_VALUE)
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel5.setText("Chức Vụ:");

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 67, Short.MAX_VALUE)
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel4.setText("Mã Chức Vụ:");

        btnRefreshPosition.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Refresh-icon.png"))); // NOI18N
        btnRefreshPosition.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRefreshPositionMouseClicked(evt);
            }
        });

        btnAddPosition.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Add.png"))); // NOI18N
        btnAddPosition.setText("Thêm");
        btnAddPosition.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddPositionActionPerformed(evt);
            }
        });

        btnChangePosition.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Change.png"))); // NOI18N
        btnChangePosition.setText("Sửa");
        btnChangePosition.setEnabled(false);
        btnChangePosition.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChangePositionActionPerformed(evt);
            }
        });

        btnDeletePosition.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Delete.png"))); // NOI18N
        btnDeletePosition.setText("Xóa");
        btnDeletePosition.setEnabled(false);
        btnDeletePosition.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeletePositionActionPerformed(evt);
            }
        });

        btnSavePosition.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Save.png"))); // NOI18N
        btnSavePosition.setText("Lưu");
        btnSavePosition.setEnabled(false);
        btnSavePosition.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSavePositionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        jPanel32Layout.setVerticalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel33Layout = new javax.swing.GroupLayout(jPanel33);
        jPanel33.setLayout(jPanel33Layout);
        jPanel33Layout.setHorizontalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        jPanel33Layout.setVerticalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addComponent(btnRefreshPosition, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAddPosition, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnChangePosition, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDeletePosition, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSavePosition, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnRefreshPosition, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAddPosition, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                    .addComponent(btnSavePosition, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel18, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel32, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel33, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnChangePosition, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDeletePosition, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txbIDPosition, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txbPosition, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txbPayroll, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txbPayroll, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6))
                                .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txbPosition, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5))))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txbIDPosition, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)))
                .addGap(18, 18, 18)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 329, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Chức Vụ", jPanel1);

        lblStatus.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblStatus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblStatus.setText("Trạng Thái");

        jLabel14.setFont(new java.awt.Font("Times New Roman", 0, 28)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Cập Nhật Thông Tin");

        btnBackHome.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnBackHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Back.png"))); // NOI18N
        btnBackHome.setText("Hệ Thống");
        btnBackHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBackHomeMouseClicked(evt);
            }
        });
        btnBackHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackHomeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnBackHome, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(lblStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTabbedPane1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnBackHome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblStatus))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tablePositionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablePositionMouseClicked
        int selectedRow = tablePosition.getSelectedRow();
        TableModel model = tablePosition.getModel();
        
        txbIDPosition.setText(model.getValueAt(selectedRow, 0).toString());
        txbPosition.setText(model.getValueAt(selectedRow, 1).toString());
        String[] s = model.getValueAt(selectedRow, 2).toString().split("\\s");
        txbPayroll.setText(s[0]);
        
        btnChangePosition.setEnabled(true);
        btnDeletePosition.setEnabled(true);
    }//GEN-LAST:event_tablePositionMouseClicked

    private void btnAddProducerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddProducerActionPerformed
        setRefresh();
        isCheckedAdd = true;
        btnAddProducer.setEnabled(false);
        btnSaveProducer.setEnabled(true);
        setEnabledProducer();
    }//GEN-LAST:event_btnAddProducerActionPerformed

    private void btnChangeProducerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChangeProducerActionPerformed
        isCheckedAdd = false;
        isCheckedChange = true;
        btnAddProducer.setEnabled(false);
        btnChangeProducer.setEnabled(false);
        btnDeleteProducer.setEnabled(false);
        btnSaveProducer.setEnabled(true);
        setEnabledProducer();
    }//GEN-LAST:event_btnChangeProducerActionPerformed

    private void btnDeleteProducerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteProducerActionPerformed
        int chooseNumber = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa nhà sản xuất hay không?", "Thông Báo", 2);
        if (chooseNumber == JOptionPane.YES_OPTION) {
            String sqlDelete = "DELETE FROM Producer WHERE ID=?";
            try {
                pst = conn.prepareStatement(sqlDelete);
                pst.setString(1, txbIDProducer.getText());
                pst.executeUpdate();
                lblStatus.setText("Xóa loại nhà sản xuất thành công!");
                setDisabledProducer();
                setRefresh();
                loadProducer();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnDeleteProducerActionPerformed

    private void btnSaveProducerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveProducerActionPerformed
        if (isCheckedAdd == true) {
            String id = this.txbIDProducer.getText();
            if (producerService.findProducerById(id)) {
                addProducer();
            } else {
                lblStatus.setText("Mã nhà sản xuất bạn nhập đã tồn tại!");
            }
        } else {
            if (isCheckedChange == true) {
                changedProducer();
            }
        }
    }//GEN-LAST:event_btnSaveProducerActionPerformed

    private void btnRefreshProducerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRefreshProducerMouseClicked
        setRefresh();
        loadProducer();
    }//GEN-LAST:event_btnRefreshProducerMouseClicked

    private void tableProducerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableProducerMouseClicked
        int selectedRow = tableProducer.getSelectedRow();
        TableModel model = tableProducer.getModel();
        
        txbIDProducer.setText(model.getValueAt(selectedRow, 0).toString());
        txbProducer.setText(model.getValueAt(selectedRow, 1).toString());
        txbAdress.setText(model.getValueAt(selectedRow, 2).toString());
        txbPhone.setText(model.getValueAt(selectedRow, 3).toString());
        txbEmail.setText(model.getValueAt(selectedRow, 4).toString());
        
        btnChangeProducer.setEnabled(true);
        btnDeleteProducer.setEnabled(true);
    }//GEN-LAST:event_tableProducerMouseClicked

    private void tableClassifyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableClassifyMouseClicked
        int Click = tableClassify.getSelectedRow();
        TableModel model = tableClassify.getModel();
        
        txbIDClassify.setText(model.getValueAt(Click, 0).toString());
        txbClassify.setText(model.getValueAt(Click, 1).toString());
        
        btnChangeClassify.setEnabled(true);
        btnDeleteClassify.setEnabled(true);
    }//GEN-LAST:event_tableClassifyMouseClicked

    private void btnBackHomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBackHomeMouseClicked
        backHome();
    }//GEN-LAST:event_btnBackHomeMouseClicked

    private void txbPayrollKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txbPayrollKeyReleased
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        txbPayroll.setText(cutChar(txbPayroll.getText()));
        if (txbPayroll.getText().equals("")) {
            return;
        } else {
            txbPayroll.setText(formatter.format(convertedToNumbers(txbPayroll.getText())));
        }
    }//GEN-LAST:event_txbPayrollKeyReleased

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        int isClose = JOptionPane.showConfirmDialog(null, "Bạn Có Muốn Thoát Khỏi Chương Trình Hay Không?", "Thông Báo", 2);
        if (isClose == JOptionPane.OK_OPTION) {
            System.exit(0);
        } else {
            if (isClose == JOptionPane.CANCEL_OPTION) {
                this.setVisible(true);
            }
        }
    }//GEN-LAST:event_formWindowClosing

    private void btnRefreshClassifyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRefreshClassifyMouseClicked
        setRefresh();
    }//GEN-LAST:event_btnRefreshClassifyMouseClicked

    private void btnAddClassifyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddClassifyActionPerformed
        setRefresh();
        isCheckedAdd = true;
        btnAddClassify.setEnabled(false);
        btnSaveClassify.setEnabled(true);
        setEnabledClassify();
    }//GEN-LAST:event_btnAddClassifyActionPerformed

    private void btnChangeClassifyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChangeClassifyActionPerformed
        isCheckedAdd = false;
        isCheckedChange = true;
        btnAddClassify.setEnabled(false);
        btnChangeClassify.setEnabled(false);
        btnDeleteClassify.setEnabled(false);
        btnSaveClassify.setEnabled(true);
        setEnabledClassify();
    }//GEN-LAST:event_btnChangeClassifyActionPerformed

    private void btnDeleteClassifyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteClassifyActionPerformed
        int Click = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa loại linh kiện hay không?", "Thông Báo", 2);
        if (Click == JOptionPane.YES_OPTION) {
            String sqlDelete = "DELETE FROM Classify WHERE ID=?";
            try {
                pst = conn.prepareStatement(sqlDelete);
                pst.setString(1, txbIDClassify.getText());
                pst.executeUpdate();
                lblStatus.setText("Xóa loại nhà sản xuất thành công!");
                setDisabledClassify();
                setRefresh();
                loadClassify(sql3);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnDeleteClassifyActionPerformed

    private void btnSaveClassifyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveClassifyActionPerformed
        if (isCheckedAdd == true) {
            if (classifyService.findClasstifyById(this.txbIDClassify.getText())) {
                addClassify();
            } else {
                lblStatus.setText("Mã loại linh kiện bạn nhập đã tồn tại!");
            }
        } else {
            if (isCheckedChange == true) {
                changedClassify();
            }
        }
    }//GEN-LAST:event_btnSaveClassifyActionPerformed

    private void btnRefreshPositionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRefreshPositionMouseClicked
        setRefresh();
    }//GEN-LAST:event_btnRefreshPositionMouseClicked

    private void btnAddPositionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddPositionActionPerformed
        setRefresh();
        isCheckedAdd = true;
        btnAddPosition.setEnabled(false);
        btnSavePosition.setEnabled(true);
        setEnabledPosition();
    }//GEN-LAST:event_btnAddPositionActionPerformed

    private void btnChangePositionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChangePositionActionPerformed
        isCheckedAdd = false;
        isCheckedChange = true;
        btnAddPosition.setEnabled(false);
        btnChangePosition.setEnabled(false);
        btnDeletePosition.setEnabled(false);
        btnSavePosition.setEnabled(true);
        setEnabledPosition();
    }//GEN-LAST:event_btnChangePositionActionPerformed

    private void btnDeletePositionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeletePositionActionPerformed
        int Click = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa chức vụ hay không?", "Thông Báo", 2);
        if (Click == JOptionPane.YES_OPTION) {
            String sqlDelete = "DELETE FROM Position WHERE ID=?";
            try {
                pst = conn.prepareStatement(sqlDelete);
                pst.setString(1, txbIDPosition.getText());
                pst.executeUpdate();
                lblStatus.setText("Xóa loại chức vụ thành công!");
                setDisabledPosition();
                setRefresh();
                loadPosition();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnDeletePositionActionPerformed

    private void btnSavePositionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSavePositionActionPerformed
        if (isCheckedAdd == true) {
            String id = this.txbIDPosition.getText();
            if (positionService.findPositionById(id)) {
                addPosition();
            } else {
                lblStatus.setText("Mã chức vụ bạn nhập đã tồn tại!");
            }
        } else {
            if (isCheckedChange == true) {
                changedPosition();
            }
        }
    }//GEN-LAST:event_btnSavePositionActionPerformed

    private void txbPhoneKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txbPhoneKeyReleased
        txbPhone.setText(cutChar(txbPhone.getText()));
    }//GEN-LAST:event_txbPhoneKeyReleased

    private void btnBackHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackHomeActionPerformed
        // TODO add your handling code here:
        HomeManager homeManager = new HomeManager(detail);
        homeManager.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnBackHomeActionPerformed
    
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                    
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DataView.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DataView.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DataView.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DataView.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Detail detail = new Detail();
                new DataView(detail).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddClassify;
    private javax.swing.JButton btnAddPosition;
    private javax.swing.JButton btnAddProducer;
    private javax.swing.JButton btnBackHome;
    private javax.swing.JButton btnChangeClassify;
    private javax.swing.JButton btnChangePosition;
    private javax.swing.JButton btnChangeProducer;
    private javax.swing.JButton btnDeleteClassify;
    private javax.swing.JButton btnDeletePosition;
    private javax.swing.JButton btnDeleteProducer;
    private javax.swing.JButton btnRefreshClassify;
    private javax.swing.JButton btnRefreshPosition;
    private javax.swing.JButton btnRefreshProducer;
    private javax.swing.JButton btnSaveClassify;
    private javax.swing.JButton btnSavePosition;
    private javax.swing.JButton btnSaveProducer;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JTable tableClassify;
    private javax.swing.JTable tablePosition;
    private javax.swing.JTable tableProducer;
    private javax.swing.JTextField txbAdress;
    private javax.swing.JTextField txbClassify;
    private javax.swing.JTextField txbEmail;
    private javax.swing.JTextField txbIDClassify;
    private javax.swing.JTextField txbIDPosition;
    private javax.swing.JTextField txbIDProducer;
    private javax.swing.JTextField txbPayroll;
    private javax.swing.JTextField txbPhone;
    private javax.swing.JTextField txbPosition;
    private javax.swing.JTextField txbProducer;
    // End of variables declaration//GEN-END:variables
}
