package org.example.Model.Entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "kategori")
public class Kategori implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "namaKategori")
    private String namaKategori;
    @Column(name = "deskripsi")
    private String deskripsi;

    public Kategori() {
    }

    public Kategori(Long id, String namaKategori, String deskripsi) {
        this.id = id;
        this.namaKategori = namaKategori;
        this.deskripsi = deskripsi;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNamaKategori() {
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
