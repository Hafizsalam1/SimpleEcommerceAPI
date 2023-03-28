package org.example.Model.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "produk")
public class Produk implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "namaProduk")
    private String namaProduk;
    @Column(name = "harga")
    private Long harga;

//    @ManyToMany
//    @JsonBackReference
//    @JoinTable(name = "produk_transaksi", joinColumns = @JoinColumn (name = "produk_id"), inverseJoinColumns = @JoinColumn(name = "transaksi_id"))
//    private Set<Transaksi> transaksi;

    @ManyToOne
//    @JsonManagedReference
    private Kategori kategori;


    public Produk() {
    }

    public Produk(Long id, String namaProduk, Long harga, Kategori kategori) {
        this.id = id;
        this.namaProduk = namaProduk;
        this.harga = harga;
        this.kategori = kategori;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Kategori getKategori() {
        return kategori;
    }

    public void setKategori(Kategori kategori) {
        this.kategori = kategori;
    }
}
