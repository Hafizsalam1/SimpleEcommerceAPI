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
    @Column
    private Date date;

    @ManyToMany(mappedBy = "transaksi")
    private Set<Produk> produk;

    @Column
    private Long totalHarga;

//    @Column
//    private Long jumlah;

    public Transaksi() {
    }

    public Transaksi(Long id, Date date, Set<Produk> produk, Long totalHarga) {
        this.id = id;
        this.date = date;
        this.produk = produk;
        this.totalHarga = totalHarga;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
