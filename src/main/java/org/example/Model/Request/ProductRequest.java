package org.example.Model.Request;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.example.Model.Entity.Kategori;
import org.example.Model.Entity.Transaksi;

import java.util.Set;

public class ProductRequest {
    private String namaProduk;
    private Long harga;
    private Long IdKategori;

    public ProductRequest() {
    }

    public String getNamaProduk() {
        return namaProduk;
    }

    public void setNamaProduk(String namaProduk) {
        this.namaProduk = namaProduk;
    }

    public Long getHarga() {
        return harga;
    }

    public void setHarga(Long harga) {
        this.harga = harga;
    }

    public Long getIdKategori() {
        return IdKategori;
    }

    public void setIdKategori(Long idKategori) {
        IdKategori = idKategori;
    }
}
