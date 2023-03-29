package org.example.Model.Entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "detilTransaksi")
public class DetilTransaksi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "namaPembeli")
    private String namaPembeli;

    @Column(name = "tanggalPembelian")
    private Date tanggalPembelian;

    @Column(name = "grandTotal")
    private Long grandTotal;

    @OneToMany(mappedBy = "detilTransaksi")
    private Set<Transaksi> transaksi;

    public DetilTransaksi() {
    }

    public DetilTransaksi(Long id) {
        this.id = id;
    }

    public String getNamaPembeli() {
        return namaPembeli;
    }

    public void setNamaPembeli(String namaPembeli) {
        this.namaPembeli = namaPembeli;
    }

    public Date getTanggalPembelian() {
        return tanggalPembelian;
    }

    public void setTanggalPembelian(Date tanggalPembelian) {
        this.tanggalPembelian = tanggalPembelian;
    }

    public Long getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(Long grandTotal) {
        this.grandTotal = grandTotal;
    }

    public Set<Transaksi> getTransaksi() {
        return transaksi;
    }

    public void setTransaksi(Set<Transaksi> transaksi) {
        this.transaksi = transaksi;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
