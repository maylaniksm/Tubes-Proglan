// SewaLapangan.java
package com.example.tubes;

import javafx.beans.property.SimpleStringProperty;

public class SewaLapangan {
    private final SimpleStringProperty namaPenyewa;
    private final SimpleStringProperty tanggal;
    private final SimpleStringProperty jamMulai;
    private final SimpleStringProperty jamSelesai;

    public SewaLapangan(String namaPenyewa, String tanggal, String jamMulai, String jamSelesai) {
        this.namaPenyewa = new SimpleStringProperty(namaPenyewa);
        this.tanggal = new SimpleStringProperty(tanggal);
        this.jamMulai = new SimpleStringProperty(jamMulai);
        this.jamSelesai = new SimpleStringProperty(jamSelesai);
    }

    public String getNamaPenyewa() {
        return namaPenyewa.get();
    }

    public String getTanggal() {
        return tanggal.get();
    }

    public String getJamMulai() {
        return jamMulai.get();
    }

    public String getJamSelesai() {
        return jamSelesai.get();
    }
}
