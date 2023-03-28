package org.example.Model.Request;

public class KategoriRequest {
    private String namaKategori;
    private String deskripsi;

    public KategoriRequest() {
    }

    public String  getNamaKategori() {
        return namaKategori;
    }

    public void setNamaKategori(String namaKategori) {
        this.namaKategori = namaKategori;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }
}
