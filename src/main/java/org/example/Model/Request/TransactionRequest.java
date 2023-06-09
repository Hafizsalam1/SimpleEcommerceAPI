package org.example.Model.Request;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import org.example.Model.Entity.DetilTransaksi;
import org.example.Model.Entity.Produk;

import java.util.Date;
import java.util.Set;

public class TransactionRequest {
    private Long idProduk;

    private Long jumlah;

    private Long subTotal;

    private Long IdDetilTransaksi;

    public TransactionRequest() {
    }

    public Long getIdProduk() {
        return idProduk;
    }

    public void setIdProduk(Long idProduk) {
        this.idProduk = idProduk;
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

    public Long getIdDetilTransaksi() {
        return IdDetilTransaksi;
    }

    public void setIdDetilTransaksi(Long idDetilTransaksi) {
        IdDetilTransaksi = idDetilTransaksi;
    }
}
