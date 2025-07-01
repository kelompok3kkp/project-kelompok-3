/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.app.view.master;

import main.java.app.database.Koneksi;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.sql.ResultSet;
import java.sql.Statement;
import main.java.app.view.formreport.ReportKaryawan;


/**
 *
 * @author aliframadhan
 */
public class FormKaryawan extends javax.swing.JFrame {
    String tanggal;
    
    private Connection koneksi = new Koneksi().connect();
    private DefaultTableModel model;

    /**
     * Creates new form FormKaryawan
     */
    public FormKaryawan() {
        initComponents();
        
        jtanggal.setModel(new javax.swing.SpinnerDateModel());
        JSpinner.DateEditor tgl = new JSpinner.DateEditor(jtanggal, "yyyy-MM-dd");
        jtanggal.setEditor(tgl);
        jtanggal.setValue(new Date());
        
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        
        kosong();
        aktif();
        datatable();
        loadComboBoxData();
        
        txtalamat.setLineWrap(true);
        txtalamat.setWrapStyleWord(true);
        txtcari.addActionListener(new java.awt.event.ActionListener(){
           public void actionPerformed(java.awt.event.ActionEvent ect){
               datatable();
           } 
        });
    }
    
    public void loadComboBoxData(){
        try {
            Connection koneksi = Koneksi.connect();
            Statement stat = koneksi.createStatement();
            
            ResultSet rsShift = stat.executeQuery("SELECT shift FROM shift");
            while (rsShift.next()){
                cbshift.addItem(rsShift.getString("shift"));
            }
            rsShift.close();
        } catch (Exception e) {
             e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Gagal load data combobox: "+ e.getMessage());
        }
    }
    
    protected void aktif(){
        txtnama.requestFocus();
    }
    
    protected void kosong(){
        txtid.setText(generateIdKaryawan());
        txtnama.setText("");
        buttonGroup1.clearSelection();
        txttelp.setText("");
        txtalamat.setText("");
        cbjabatan.setSelectedItem(null);
        txtpass.setText("");
        jtanggal.setValue(new Date());
    }

    private String generateIdKaryawan(){
        String prefix = "K";
        String sql = "SELECT id_karyawan FROM data_karyawan ORDER BY id_karyawan DESC LIMIT 1";
        try {
            Statement stat = koneksi.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            if (rs.next()) {
                String lastId = rs.getString("id_karyawan");
                int number = Integer.parseInt(lastId.substring(1));
                number++;
                return String.format(prefix + "%03d", number);
            } else {
                return prefix + "001";
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal generate ID: " + e);
            return null;
        }
    }
    
    protected void datatable(){
        Object[] Baris = {"ID Karyawan", "Nama Karyawan", "Jenis Kelamin", "No. Telepon", "Alamat", "Jabatan","Shift", "Password", "Tanggal Masuk"};
            model = new DefaultTableModel(null, Baris);
            String cariitem = txtcari.getText();
            
            try {
                String sql = "SELECT *FROM data_karyawan WHERE id_karyawan LIKE '%" + cariitem + "%' " 
                        + "OR nama_karyawan LIKE '%" + cariitem + "%' " 
                        + "OR jenis_kelamin LIKE '%" + cariitem + "%' " 
                        + "OR no_telp LIKE '%" + cariitem + "%' " 
                        + "OR alamat LIKE '%" + cariitem + "%' " 
                        + "OR jabatan LIKE '%" + cariitem + "%' " 
                        + "OR shift LIKE '%" + cariitem + "%' "
                        + "OR password LIKE '%" + cariitem + "%' " 
                        + "ORDER BY id_karyawan ASC";
                Statement stat = koneksi.createStatement();
                ResultSet hasil = stat.executeQuery(sql);
                
                while (hasil.next()){
                    model.addRow(new Object[]{
                        hasil.getString(1),
                        hasil.getString(2),
                        hasil.getString(3),
                        hasil.getString(4),
                        hasil.getString(5),
                        hasil.getString(6),
                        hasil.getString(7),
                        hasil.getString(8),
                        hasil.getString(9),
                    });
                }
                tablekaryawan.setModel(model);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data gagal dipanggil"+e);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtid = new javax.swing.JTextField();
        txtnama = new javax.swing.JTextField();
        txttelp = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtalamat = new javax.swing.JTextArea();
        klaki = new javax.swing.JRadioButton();
        kperempuan = new javax.swing.JRadioButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cbjabatan = new javax.swing.JComboBox<>();
        txtpass = new javax.swing.JPasswordField();
        btnbatal = new javax.swing.JButton();
        btnsimpan = new javax.swing.JButton();
        btnedit = new javax.swing.JButton();
        btnhapus = new javax.swing.JButton();
        jtanggal = new javax.swing.JSpinner();
        jPanel2 = new javax.swing.JPanel();
        txtcari = new javax.swing.JTextField();
        btncari = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablekaryawan = new javax.swing.JTable();
        btncetak = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        cbshift = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        btnkembali = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("ID Karyawan");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Nama Karyawan");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Jenis Kelamin");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("No. Telepon");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Alamat");

        txtid.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtid.setEnabled(false);

        txtnama.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txttelp.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtalamat.setColumns(20);
        txtalamat.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtalamat.setRows(5);
        jScrollPane1.setViewportView(txtalamat);

        buttonGroup1.add(klaki);
        klaki.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        klaki.setText("Laki-Laki");

        buttonGroup1.add(kperempuan);
        kperempuan.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        kperempuan.setText("Perempuan");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Jabatan");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Password");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Tanggal Masuk");

        cbjabatan.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cbjabatan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin", "Washerman" }));

        txtpass.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        btnbatal.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnbatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/java/assets/icons/batal.png"))); // NOI18N
        btnbatal.setText("Batal");
        btnbatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbatalActionPerformed(evt);
            }
        });

        btnsimpan.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnsimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/java/assets/icons/save.png"))); // NOI18N
        btnsimpan.setText("Simpan");
        btnsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsimpanActionPerformed(evt);
            }
        });

        btnedit.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnedit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/java/assets/icons/edit.png"))); // NOI18N
        btnedit.setText("Edit");
        btnedit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneditActionPerformed(evt);
            }
        });

        btnhapus.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnhapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/java/assets/icons/hapus.png"))); // NOI18N
        btnhapus.setText("Hapus");
        btnhapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhapusActionPerformed(evt);
            }
        });

        jtanggal.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jtanggal.setModel(new javax.swing.SpinnerDateModel());
        jtanggal.setEnabled(false);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Data Karyawan", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        txtcari.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        btncari.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btncari.setText("Cari");
        btncari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncariActionPerformed(evt);
            }
        });

        tablekaryawan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tablekaryawan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tablekaryawan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablekaryawanMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tablekaryawan);

        btncetak.setText("Cetak");
        btncetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncetakActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(txtcari, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btncari)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btncetak))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 505, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtcari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btncari)
                    .addComponent(btncetak))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Shift");

        cbshift.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addGap(62, 62, 62)
                            .addComponent(txtid))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3)
                                .addComponent(jLabel4)
                                .addComponent(jLabel5))
                            .addGap(39, 39, 39)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(klaki)
                                    .addGap(18, 18, 18)
                                    .addComponent(kperempuan))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtnama, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
                                    .addComponent(txttelp))))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel6)
                                .addComponent(jLabel7)
                                .addComponent(jLabel8))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(85, 85, 85)
                                    .addComponent(jScrollPane1))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(cbjabatan, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jtanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtpass)
                                        .addComponent(cbshift, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnsimpan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnedit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnhapus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnbatal, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnsimpan)
                                .addGap(18, 18, 18)
                                .addComponent(btnedit))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(txtnama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel3)
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel4)
                                            .addComponent(klaki)
                                            .addComponent(kperempuan))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel5)
                                            .addComponent(txttelp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(cbjabatan)
                                        .addGap(58, 58, 58))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(24, 24, 24)
                                        .addComponent(jLabel7)
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel10)
                                            .addComponent(cbshift, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8)
                                    .addComponent(txtpass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9)
                                    .addComponent(jtanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(633, 633, 633))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(btnhapus)
                                .addGap(18, 18, 18)
                                .addComponent(btnbatal)
                                .addGap(675, 675, 675))))))
        );

        jPanel3.setBackground(new java.awt.Color(51, 255, 255));

        btnkembali.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnkembali.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/java/assets/icons/kembali.png"))); // NOI18N
        btnkembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnkembaliActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Data Karyawan");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnkembali)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(85, 85, 85))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnkembali, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 825, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsimpanActionPerformed
        // TODO add your handling code here:
        String jenis = null;
        String nama = txtnama.getText().trim();
        String password = new String(txtpass.getPassword());
        if (klaki.isSelected()){
            jenis = "Laki-Laki";
        }else if (kperempuan.isSelected()){
            jenis = "Perempuan";
        }
        if (nama.isEmpty()){
            JOptionPane.showMessageDialog(null, "Data karyawan harus terisi semua.");
            txtnama.requestFocus();
            return;
        }
        String cekSql = "SELECT id_karyawan FROM data_karyawan WHERE id_karyawan = ?";
        try {
            PreparedStatement cekStat = koneksi.prepareStatement(cekSql);
            cekStat.setString(1, nama);
            ResultSet rs = cekStat.executeQuery();
            if (rs.next()){
                JOptionPane.showMessageDialog(null, "ID sudah digunakan.");
                txtnama.requestFocus();
                return;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat mengecek ID: " + e);
            return;
        }
        String sql = "INSERT into data_karyawan values (?,?,?,?,?,?,?,?,?)";
        try{
            PreparedStatement stat = koneksi.prepareStatement(sql);
            stat.setString(1, txtid.getText());
            stat.setString(2, txtnama.getText());
            stat.setString(3, jenis);
            stat.setString(4, txttelp.getText());
            stat.setString(5, txtalamat.getText());
            stat.setString(6, cbjabatan.getSelectedItem().toString());
            stat.setString(7, cbshift.getSelectedItem().toString());
            stat.setString(8, password);

            
            Date tanggal = (Date) jtanggal.getValue();
            SimpleDateFormat tgl = new SimpleDateFormat("yyyy-MM-dd");
            stat.setString(9, tgl.format(tanggal));

            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil disimpan.");
            kosong();
            txtnama.requestFocus();
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Data gagal disimpan."+e);
        }
        datatable();
    }//GEN-LAST:event_btnsimpanActionPerformed

    private void btneditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditActionPerformed
        // TODO add your handling code here:
        String jenis = null;
        String nama = txtnama.getText().trim();
        String password = new String(txtpass.getPassword());
        if (klaki.isSelected()){
            jenis = "Laki-Laki";
        } else if (kperempuan.isSelected()){
            jenis = "Perempuan";
        }
        if (nama.isEmpty()){
            JOptionPane.showMessageDialog(null, "Data karyawan harus terisi semua.");
            txtnama.requestFocus();
            return;
        }
        try{
            Date tanggal = (Date) jtanggal.getValue();
            SimpleDateFormat tgl = new SimpleDateFormat("yyy-MM-dd");
            String tanggalFormatted = tgl.format(tanggal);
            String sql = "UPDATE data_karyawan set nama_karyawan=?, jenis_kelamin=?, no_telp=?, alamat=?, jabatan=?, shift=?, password=? WHERE id_karyawan='"+txtid.getText()+"'";
            PreparedStatement stat = koneksi.prepareStatement(sql);
            stat.setString(1, txtnama.getText());
            stat.setString(2, jenis);
            stat.setString(3, txttelp.getText());
            stat.setString(4, txtalamat.getText());
            stat.setString(5, cbjabatan.getSelectedItem().toString());
            stat.setString(6, cbshift.getSelectedItem().toString());
            stat.setString(7, password);

            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil diubah");
            kosong();
            txtnama.requestFocus();
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Data gagal diubah" +e);
        }
        datatable();
    }//GEN-LAST:event_btneditActionPerformed

    private void btnhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhapusActionPerformed
        // TODO add your handling code here:
        int ok = JOptionPane.showConfirmDialog(null, "Hapus data?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (ok == 0){
            String id = txtid.getText().trim();
            if (id.isEmpty()){
                JOptionPane.showMessageDialog(null, "ID karyawan tidak boleh kosong.");
                txtnama.requestFocus();
                return;
            }
            String sql = "DELETE FROM data_karyawan WHERE id_karyawan = ?";
            try {
                PreparedStatement stat = koneksi.prepareStatement(sql);
                stat.setString(1, id);

                int rowsAffected = stat.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Data berhasil dihapus.");
                    kosong();
                    jtanggal.setValue(new Date());
                    txtnama.requestFocus();
                } else {
                    JOptionPane.showMessageDialog(null, "Data tidak ditemukan atau sudah dihapus.");
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Data gagal dihapus: " + e.getMessage());
            }
            datatable();
        }
    }//GEN-LAST:event_btnhapusActionPerformed
    
    private void btnbatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbatalActionPerformed
        // TODO add your handling code here:
        kosong();
        jtanggal.setValue(new Date());
        txtnama.requestFocus();
    }//GEN-LAST:event_btnbatalActionPerformed
    
    private void btnkembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnkembaliActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btnkembaliActionPerformed

    private void btncariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncariActionPerformed
        // TODO add your handling code here:
        datatable();
    }//GEN-LAST:event_btncariActionPerformed

    private void tablekaryawanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablekaryawanMouseClicked
        // TODO add your handling code here:
        int bar = tablekaryawan.getSelectedRow();
    
    if (bar < 0) {
        // Tidak ada baris terpilih
        return;
    }
    
    if (model == null) {
        JOptionPane.showMessageDialog(this, "Model tabel belum diisi!");
        return;
    }
    
    try {
        String a = model.getValueAt(bar, 0) != null ? model.getValueAt(bar, 0).toString() : "";
        String b = model.getValueAt(bar, 1) != null ? model.getValueAt(bar, 1).toString() : "";
        String c = model.getValueAt(bar, 2) != null ? model.getValueAt(bar, 2).toString() : "";
        String d = model.getValueAt(bar, 3) != null ? model.getValueAt(bar, 3).toString() : "";
        String e = model.getValueAt(bar, 4) != null ? model.getValueAt(bar, 4).toString() : "";
        String f = model.getValueAt(bar, 5) != null ? model.getValueAt(bar, 5).toString() : "";
        String g = model.getValueAt(bar, 6) != null ? model.getValueAt(bar, 6).toString() : "";
        String h = model.getValueAt(bar, 7) != null ? model.getValueAt(bar, 7).toString() : "";
        String i = model.getValueAt(bar, 8) != null ? model.getValueAt(bar, 8).toString() : "";

        txtid.setText(a);
        txtnama.setText(b);

        if (c.equalsIgnoreCase("Laki-laki")) {
            klaki.setSelected(true);
            kperempuan.setSelected(false);
        } else {
            kperempuan.setSelected(true);
            klaki.setSelected(false);
        }

        txttelp.setText(d);
        txtalamat.setText(e);
        cbjabatan.setSelectedItem(f);
        cbshift.setSelectedItem(g);
        txtpass.setText(h);

        if (!i.isEmpty()) {
            SimpleDateFormat tgl = new SimpleDateFormat("yyyy-MM-dd");
            Date tanggal = tgl.parse(i);
            jtanggal.setValue(tanggal);
        } else {
            jtanggal.setValue(new Date()); // default ke hari ini kalau kosong
        }
        
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error saat memuat data: " + ex.getMessage());
        }
    }//GEN-LAST:event_tablekaryawanMouseClicked

    private void btncetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncetakActionPerformed
        // TODO add your handling code here:
        ReportKaryawan rt = new ReportKaryawan();
        rt.setVisible(true);
        rt.setLocationRelativeTo(null);
    }//GEN-LAST:event_btncetakActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormKaryawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormKaryawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormKaryawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormKaryawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormKaryawan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnbatal;
    private javax.swing.JButton btncari;
    private javax.swing.JButton btncetak;
    private javax.swing.JButton btnedit;
    private javax.swing.JButton btnhapus;
    private javax.swing.JButton btnkembali;
    private javax.swing.JButton btnsimpan;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbjabatan;
    private javax.swing.JComboBox<String> cbshift;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSpinner jtanggal;
    private javax.swing.JRadioButton klaki;
    private javax.swing.JRadioButton kperempuan;
    private javax.swing.JTable tablekaryawan;
    private javax.swing.JTextArea txtalamat;
    private javax.swing.JTextField txtcari;
    private javax.swing.JTextField txtid;
    private javax.swing.JTextField txtnama;
    private javax.swing.JPasswordField txtpass;
    private javax.swing.JTextField txttelp;
    // End of variables declaration//GEN-END:variables
}
