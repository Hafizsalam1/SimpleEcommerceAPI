package org.example.Model.Request;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import org.example.Model.Entity.Produk;

import java.util.Date;
import java.util.Set;

public class TransactionRequest {
    private Date date;
    private Set<Produk> produk;
    private Long totalHarga;

    public TransactionRequest() {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Set<Produk> getProduk() {
        return produk;
    }

    public void setProduk(Set<Produk> produk) {
        this.produk = produk;
    }

    public Long getTotalHarga() {
        return totalHarga;
    }

    public void setTotalHarga(Long totalHarga) {
        this.totalHarga = totalHarga;
    }
}
