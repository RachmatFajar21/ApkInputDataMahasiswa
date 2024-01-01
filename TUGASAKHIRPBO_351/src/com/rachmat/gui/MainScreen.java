package com.rachmat.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class MainScreen extends JFrame{
    private JPanel panelMain;
    private JList jListMahasiswa;
    private JTextField textFieldNama;
    private JTextField textFieldNim;
    private JTextField textFieldProdi;
    private JTextField textFieldAngkatan;
    private JButton buttonInsert;
    private JButton buttonClear;
    private JTextField hasilKeluar;
    private JTextField hasilProdi;
    private JTextField haslNim;
    private JTextField hasilNama;
    private DefaultListModel defaultListModel = new DefaultListModel<>();
    private List<String> arrayMahasiswa = new ArrayList<>();

    private LinkedList<Mahasiswa> listMahasiswa = new LinkedList<>();


    class Mahasiswa{
        private String nama;
        private String nim;
        private String prodi;
        private String angkatan;

        public String getNama() {
            return nama;
        }

        public void setNama(String nama) {
            this.nama = nama;
        }

        public String getNim() {
            return nim;
        }

        public void setNim(String nim) {
            this.nim = nim;
        }

        public String getProdi() {
            return prodi;
        }

        public void setProdi(String prodi) {
            this.prodi = prodi;
        }

        public String getAngkatan() {
            return angkatan;
        }

        public void setAngkatan(String angkatan) {
            this.angkatan = angkatan;
        }
    }
    public MainScreen(){
        this.setContentPane(panelMain);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        buttonClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFrom();
            }
        });
        buttonInsert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nama = textFieldNama.getText();
                String nim = textFieldNim.getText();
                String prodi = textFieldProdi.getText();
                String angkatan = textFieldAngkatan.getText();

                Mahasiswa mahasiswa = new Mahasiswa();
                mahasiswa.setNama(nama);
                mahasiswa.setNim(nim);
                mahasiswa.setProdi(prodi);
                mahasiswa.setAngkatan(angkatan);

                insert(mahasiswa);
                clearFrom();
                refreshDataModel();

            }
        });
        jListMahasiswa.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int index =  jListMahasiswa.getSelectedIndex();

                Mahasiswa resultSearch = search(arrayMahasiswa.get(index));

                if(resultSearch != null)
                    hasilNama.setText("Nama : "+ resultSearch.getNama());
                    haslNim.setText("NIM :" +resultSearch.getNim());
                    hasilProdi.setText("Prodi : " +resultSearch.getProdi());
                    hasilKeluar.setText("Angkatan : " +resultSearch.getAngkatan());
            }
        });
    }
    private Mahasiswa search(String nama){
        for (Mahasiswa mahasiswa : listMahasiswa){
            if (mahasiswa == null)
                break;
            if (mahasiswa.getNama().equals(nama))
                return mahasiswa;
        }
        return null;
    }

    private void insert(Mahasiswa mahasiswa){
        listMahasiswa.insert(mahasiswa);
    }

    private void refreshDataModel (){
        arrayMahasiswa.clear();

        for (Mahasiswa mahasiswa : listMahasiswa){
            if (mahasiswa == null)
                break;
            arrayMahasiswa.add(mahasiswa.getNama());
        }
        defaultListModel.clear();
        defaultListModel.addAll(arrayMahasiswa);
        jListMahasiswa.setModel(defaultListModel);
    }


    private void clearFrom(){
        textFieldNama.setText("");
        textFieldNim.setText("");
        textFieldProdi.setText("");
        textFieldAngkatan.setText("");
    }

    public static void main(String[] args) {
        MainScreen mainScreen = new MainScreen();
        mainScreen.setVisible(true);

    }
}
