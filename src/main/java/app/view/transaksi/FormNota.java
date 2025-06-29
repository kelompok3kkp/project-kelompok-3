/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.app.view.transaksi;

import java.sql.*;
import javax.swing.JOptionPane;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.table.DefaultTableModel;
import javax.swing.JSpinner;
import main.java.app.database.Koneksi;
import main.java.app.model.UserID;
import main.java.app.view.popuptable.PopupPelanggan;
import main.java.app.view.popuptable.PopupKaryawan;
import main.java.app.view.popuptable.PopupLayanan;
import main.java.app.view.popuptable.PopupKendaraan;

/**
 *
 * @author frdn1120
 */
public class FormNota extends javax.swing.JFrame {

        public String id, nama, notlp, alamat;
        public String idk, namak, jenisk, notlpk, alamatk, jabatank, shiftk, passwordk, tanggalmasukk;
        public String idl, jeniskl, modell, jenisl, keteranganl, hargal; 
        public String idkk, jeniskk, modelk, pltnmr;
        private Connection Koneksi = new Koneksi().connect();
        private DefaultTableModel model;
        
        
    /**
     * Creates new form FormNota
     */
    public FormNota() {
        initComponents();
        jtanggal.setModel(new javax.swing.SpinnerDateModel());
        JSpinner.DateEditor tgl = new JSpinner.DateEditor(jtanggal, "yyyy-MM-dd");
        jtanggal.setEditor(tgl);
        jtanggal.setValue(new Date());
        
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        
        String idUser = UserID.getuserLogin();
        jLabelID.setText(idUser);
        kosong();
        aktif();
        autonumber();
        nama();
    }
    
    protected void nama(){
        try {
            String sql = "SELECT * FROM data_karyawan WHERE id_karyawan='"+jLabelID.getText()+"'";
            Statement stat = Koneksi.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            
            if(hasil.next()){
                jLabelNama.setText(hasil.getString("nama_karyawan"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "data gagal dipanggil"+e);
        }
    }
    
     protected void aktif(){
        txtidl.requestFocus();
        jtanggal.setEditor(new JSpinner.DateEditor(jtanggal,"yyy/MM/dd"));
        Object[] Baris = {"ID Layanan", "Jenis Layanan", "Keterangan", "Harga"};
        model = new DefaultTableModel(null, Baris);
        tabletransaksi.setModel(model);
    }
    
     protected void kosong(){
        txtidp.setText("");
        txtnamap.setText("");
        txtalamatp.setText("");
        txtidkaryawan.setText("");
        txtnamakaryawan.setText("");
        txtidl.setText("");
        txtjenisl.setText("");
        txtketerangan.setText("");
        txtharga.setText("");
        txtidk.setText("");
        txtjenisk.setText("");
        txtmodelk.setText("");
        txtplatk.setText("");
    }
    
     protected void autonumber(){
        try {
            String sql = "SELECT id_nota FROM nota order by id_nota asc";
            Statement stat = Koneksi.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            txtidnota.setText("IN0001");
            while(rs.next()){
                String id_nota = rs.getString("id_nota").substring(2);
                int AN = Integer.parseInt(id_nota) +1;
                String Nol = "";
                
                if(AN<10){
                    Nol = "000";
                }else if(AN<100){
                    Nol = "00";
                }else if(AN<1000){
                    Nol = "0";
                }else if(AN<10000){
                    Nol = "";
                }
                
                txtidnota.setText("IN" + Nol + AN);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Auto Number Gagal" +e);
        }
     }
     
      public void itemTerpilih(){
        PopupPelanggan pp = new PopupPelanggan();
        pp.pelanggan = this;
        txtidp.setText(id);
        txtnamap.setText(nama);
        txtalamatp.setText(alamat);
    }
      
      public void itemTerpilihKrywn(){
        PopupKaryawan pp = new PopupKaryawan();
        pp.karyawan = this;
        txtidkaryawan.setText(idk);
        txtnamakaryawan.setText(namak);
    }
      
      public void itemTerpilihLayanan(){
        PopupLayanan pp = new PopupLayanan();
        pp.layanan = this;
        txtidl.setText(idl);
        txtjenisl.setText(jenisl);
        txtketerangan.setText(keteranganl);
        txtharga.setText(hargal);
    }
      
      public void itemTerpilihKndrn(){
        PopupKendaraan pp = new PopupKendaraan();
        pp.kendaraan = this;
        txtidk.setText(idkk);
        txtjenisk.setText(jeniskk);
        txtmodelk.setText(modelk);
        txtplatk.setText(pltnmr);
    }
      
      public void hitung(){
          int total = 0;
          for(int i = 0; i < tabletransaksi.getRowCount(); i++){
              double amount = Double.valueOf(tabletransaksi.getValueAt(i, 3).toString());
              total += amount;
          }
          txttotal.setText(Double.toString(total));
      }
      
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        btnkembali = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabelID = new javax.swing.JLabel();
        txtidnota = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtidp = new javax.swing.JTextField();
        txtnamap = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtalamatp = new javax.swing.JTextArea();
        btncarip = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtmodelk = new javax.swing.JTextField();
        txtjenisk = new javax.swing.JTextField();
        txtidk = new javax.swing.JTextField();
        txtplatk = new javax.swing.JTextField();
        btncarik = new javax.swing.JButton();
        jtanggal = new javax.swing.JSpinner();
        jLabelNama = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txtjenisl = new javax.swing.JTextField();
        txtidl = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtketerangan = new javax.swing.JTextArea();
        btncaril = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        txtharga = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        btncarikaryawan = new javax.swing.JButton();
        txtidkaryawan = new javax.swing.JTextField();
        txtnamakaryawan = new javax.swing.JTextField();
        btntambah = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabletransaksi = new javax.swing.JTable();
        btnhapus = new javax.swing.JButton();
        btnsimpan = new javax.swing.JButton();
        btnbatal = new javax.swing.JButton();
        btnkeluar = new javax.swing.JButton();
        txttotal = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel3.setBackground(new java.awt.Color(51, 255, 255));

        btnkembali.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnkembali.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/java/assets/icons/kembali.png"))); // NOI18N
        btnkembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnkembaliActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Nota");

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
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnkembali)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("ID Karyawan");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("ID Nota");

        jLabelID.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelID.setText("jLabel4");

        txtidnota.setEnabled(false);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Nama Karyawan");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Data Pelanggan", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("ID Pelanggan");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Nama");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Alamat");

        txtidp.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtidp.setEnabled(false);

        txtnamap.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtnamap.setEnabled(false);

        txtalamatp.setColumns(20);
        txtalamatp.setRows(5);
        jScrollPane1.setViewportView(txtalamatp);

        btncarip.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btncarip.setText("Cari");
        btncarip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncaripActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtnamap)
                    .addComponent(jScrollPane1)
                    .addComponent(txtidp))
                .addGap(18, 18, 18)
                .addComponent(btncarip)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtidp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btncarip))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtnamap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Tanggal Nota");

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Data Kendaraan", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText("ID Kendaraan");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setText("Jenis Kendaraan");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setText("Model Kendaraan");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setText("Plat Nomor");

        txtmodelk.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtjenisk.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtjenisk.setEnabled(false);

        txtidk.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtidk.setEnabled(false);

        txtplatk.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        btncarik.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btncarik.setText("Cari");
        btncarik.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncarikActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(jLabel13)
                    .addComponent(jLabel12)
                    .addComponent(jLabel15))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtplatk, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtmodelk, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtjenisk, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtidk, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(18, 18, 18)
                .addComponent(btncarik)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtidk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btncarik))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtjenisk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtmodelk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel15)
                    .addComponent(txtplatk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jtanggal.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jtanggal.setModel(new javax.swing.SpinnerDateModel());

        jLabelNama.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelNama.setText("jLabel6");

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Data Layanan", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("ID Layanan");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel16.setText("Jenis Layanan");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel17.setText("Keterangan");

        txtjenisl.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtidl.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtidl.setEnabled(false);

        txtketerangan.setColumns(20);
        txtketerangan.setRows(5);
        jScrollPane2.setViewportView(txtketerangan);

        btncaril.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btncaril.setText("Cari");
        btncaril.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncarilActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel20.setText("Harga");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(jLabel10)
                    .addComponent(jLabel17)
                    .addComponent(jLabel20))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtjenisl)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                    .addComponent(txtidl)
                    .addComponent(txtharga))
                .addGap(18, 18, 18)
                .addComponent(btncaril)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtidl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btncaril))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txtjenisl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(txtharga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Data Karyawan", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel18.setText("ID Karyawan");

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel19.setText("Nama Karyawan");

        btncarikaryawan.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btncarikaryawan.setText("Cari");
        btncarikaryawan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncarikaryawanActionPerformed(evt);
            }
        });

        txtidkaryawan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtidkaryawan.setEnabled(false);
        txtidkaryawan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtidkaryawanActionPerformed(evt);
            }
        });

        txtnamakaryawan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19)
                    .addComponent(jLabel18))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtidkaryawan)
                    .addComponent(txtnamakaryawan))
                .addGap(18, 18, 18)
                .addComponent(btncarikaryawan)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(txtidkaryawan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btncarikaryawan))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(txtnamakaryawan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btntambah.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btntambah.setText("Tambah");
        btntambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntambahActionPerformed(evt);
            }
        });

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Transaksi", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        tabletransaksi.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(tabletransaksi);

        btnhapus.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnhapus.setText("Hapus");
        btnhapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhapusActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnhapus)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnhapus)
                .addContainerGap())
        );

        btnsimpan.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnsimpan.setText("Simpan");
        btnsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsimpanActionPerformed(evt);
            }
        });

        btnbatal.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnbatal.setText("Batal");
        btnbatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbatalActionPerformed(evt);
            }
        });

        btnkeluar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnkeluar.setText("Keluar");
        btnkeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnkeluarActionPerformed(evt);
            }
        });

        txttotal.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel21.setText("Total Harga");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnsimpan)
                                .addGap(18, 18, 18)
                                .addComponent(btnbatal, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnkeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel21)
                                .addGap(18, 18, 18)
                                .addComponent(txttotal, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(txtidnota, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabelID)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel9))
                                        .addGap(44, 44, 44)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabelNama)
                                            .addComponent(jtanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btntambah, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabelID)
                    .addComponent(jLabel5)
                    .addComponent(jLabelNama))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtidnota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(jtanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btntambah)))
                .addGap(18, 18, 18)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnsimpan)
                    .addComponent(btnbatal)
                    .addComponent(btnkeluar)
                    .addComponent(txttotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21))
                .addContainerGap(68, Short.MAX_VALUE))
        );

        jScrollPane4.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 960, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(jScrollPane4))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnkembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnkembaliActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btnkembaliActionPerformed

    private void btncaripActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncaripActionPerformed
        // TODO add your handling code here:
        PopupPelanggan pp = new PopupPelanggan();
        pp.pelanggan = this;
        pp.setVisible(true);
        pp.setResizable(false);
        pp.setLocationRelativeTo(null);
    }//GEN-LAST:event_btncaripActionPerformed

    private void btncarikActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncarikActionPerformed
        // TODO add your handling code here:
        PopupKendaraan pp = new PopupKendaraan();
        pp.kendaraan = this;
        pp.setVisible(true);
        pp.setResizable(false);
        pp.setLocationRelativeTo(null);
    }//GEN-LAST:event_btncarikActionPerformed

    private void btncarikaryawanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncarikaryawanActionPerformed
        // TODO add your handling code here:
        PopupKaryawan pp = new PopupKaryawan();
        pp.karyawan = this;
        pp.setVisible(true);
        pp.setResizable(false);
        pp.setLocationRelativeTo(null);
    }//GEN-LAST:event_btncarikaryawanActionPerformed

    private void btncarilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncarilActionPerformed
        // TODO add your handling code here:
        PopupLayanan pp = new PopupLayanan();
        pp.layanan = this;
        pp.setVisible(true);
        pp.setResizable(false);
        pp.setLocationRelativeTo(null);
    }//GEN-LAST:event_btncarilActionPerformed

    private void txtidkaryawanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtidkaryawanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtidkaryawanActionPerformed

    private void btntambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntambahActionPerformed
        // TODO add your handling code here:
         try {
            String kode = txtidl.getText();
            String jenislayanan = txtjenisl.getText();
            String keterangan = txtketerangan.getText();
            String harga = txtharga.getText();
            
            model.addRow(new Object[]{id, jenisl, keterangan, harga});
            tabletransaksi.setModel(model);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        txtidl.setText("");
        txtjenisl.setText("");
        txtketerangan.setText("");
        txtharga.setText("");
        hitung();
    }//GEN-LAST:event_btntambahActionPerformed

    private void btnsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsimpanActionPerformed
        // TODO add your handling code here:
        Date tanggal = (Date) jtanggal.getValue();
         SimpleDateFormat tgl = new SimpleDateFormat("yyyy-MM-dd");
         String sql = "insert into nota value(?,?,?,?)";
         String sql2 = "insert into isi value(?,?,?,?)";
         try {
            PreparedStatement stat = Koneksi.prepareStatement(sql);
            stat.setString(1, txtidnota.getText());
            stat.setString(2, tgl.format(tanggal));
            stat.setString(3, txtidp.getText());
            stat.setString(4, txtidkaryawan.getText());
            
            stat.executeUpdate();
            
            int tbl = tabletransaksi.getRowCount();
            for(int i = 0; i < tbl; i++){
                String xid = tabletransaksi.getValueAt(i, 0).toString();
                String xnama = tabletransaksi.getValueAt(i, 1).toString();
                String xharga = tabletransaksi.getValueAt(i, 3).toString();
                Double hargaDouble = Double.parseDouble(xharga);
                
                PreparedStatement stat2 = Koneksi.prepareStatement(sql2);
                stat2.setString(1, txtidnota.getText());
                stat2.setString(2, xid);
                stat2.setString(3, xnama);
                stat2.setDouble(4, hargaDouble);
                
                stat2.executeUpdate();
            }
            JOptionPane.showMessageDialog(null, "Data berhasil disimpan");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data gagal disimpan" + e);
        }
         kosong();
         aktif();
         autonumber();
    }//GEN-LAST:event_btnsimpanActionPerformed

    private void btnbatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbatalActionPerformed
        // TODO add your handling code here:
        kosong();
        aktif();
        autonumber();
    }//GEN-LAST:event_btnbatalActionPerformed

    private void btnkeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnkeluarActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btnkeluarActionPerformed

    private void btnhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhapusActionPerformed
        // TODO add your handling code here:
        int index = tabletransaksi.getSelectedRow();
        model.removeRow(index);
        tabletransaksi.setModel(model);
        hitung();
    }//GEN-LAST:event_btnhapusActionPerformed

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
            java.util.logging.Logger.getLogger(FormNota.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormNota.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormNota.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormNota.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormNota().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnbatal;
    private javax.swing.JButton btncarik;
    private javax.swing.JButton btncarikaryawan;
    private javax.swing.JButton btncaril;
    private javax.swing.JButton btncarip;
    private javax.swing.JButton btnhapus;
    private javax.swing.JButton btnkeluar;
    private javax.swing.JButton btnkembali;
    private javax.swing.JButton btnsimpan;
    private javax.swing.JButton btntambah;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelID;
    private javax.swing.JLabel jLabelNama;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSpinner jtanggal;
    private javax.swing.JTable tabletransaksi;
    private javax.swing.JTextArea txtalamatp;
    private javax.swing.JTextField txtharga;
    private javax.swing.JTextField txtidk;
    private javax.swing.JTextField txtidkaryawan;
    private javax.swing.JTextField txtidl;
    private javax.swing.JTextField txtidnota;
    private javax.swing.JTextField txtidp;
    private javax.swing.JTextField txtjenisk;
    private javax.swing.JTextField txtjenisl;
    private javax.swing.JTextArea txtketerangan;
    private javax.swing.JTextField txtmodelk;
    private javax.swing.JTextField txtnamakaryawan;
    private javax.swing.JTextField txtnamap;
    private javax.swing.JTextField txtplatk;
    private javax.swing.JTextField txttotal;
    // End of variables declaration//GEN-END:variables
}
