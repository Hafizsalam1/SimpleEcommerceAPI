package org.example.Model.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "transaksi")
public class Transaksi implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    @Column
//    private Date date;

    @ManyToOne
//    @JsonManagedReference
    private Produk produk;

    @Column
    private Long jumlah;

    @Column
    private Long subTotal;

    @ManyToOne
    @JsonBackReference
    private DetilTransaksi detilTransaksi;

    public Transaksi() {
    }

    public Transaksi(Long id, Produk produk, Long subTotal, Long jumlah, DetilTransaksi detilTransaksi) {
        this.id = id;
        this.produk = produk;
        this.subTotal = subTotal;
        this.jumlah = jumlah;
        this.detilTransaksi = detilTransaksi;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Produk getProduk() {
        return produk;
    }

    public void setProduk(Produk produk) {
        this.produk = produk;
    }

    public Long getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Long subTotal) {
        this.subTotal = subTotal;
    }

    public Long getJumlah() {
        return jumlah;
    }

    public void setJumlah(Long jumlah) {
        this.jumlah = jumlah;
    }

    public DetilTransaksi getDetilTransaksi() {
        return detilTransaksi;
    }

    public void setDetilTransaksi(DetilTransaksi detilTransaksi) {
        this.detilTransaksi = detilTransaksi;
    }
}
