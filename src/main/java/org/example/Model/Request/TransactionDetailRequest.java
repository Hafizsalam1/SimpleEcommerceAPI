package org.example.Model.Request;

import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import org.example.Model.Entity.Transaksi;

import java.util.Date;
import java.util.Set;

public class TransactionDetailRequest {

    private String namaPembeli;

    private String tanggalPembelian;

    private Long grandTotal;

//    private Set<Transaksi> transaksi;

    public TransactionDetailRequest() {
    }

    public String getNamaPembeli() {
        return namaPembeli;
    }

    public void setNamaPembeli(String namaPembeli) {
        this.namaPembeli = namaPembeli;
    }

    public String getTanggalPembelian() {
        return tanggalPembelian;
    }

    public void setTanggalPembelian(String tanggalPembelian) {
        this.tanggalPembelian = tanggalPembelian;
    }

    public Long getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(Long grandTotal) {
        this.grandTotal = grandTotal;
    }

//    public Set<Transaksi> getTransaksi() {
//        return transaksi;
//    }
//
//    public void setTransaksi(Set<Transaksi> transaksi) {
//        this.transaksi = transaksi;
//    }
}
