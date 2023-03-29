package org.example.Repository;

import org.example.Model.Entity.DetilTransaksi;
import org.example.Model.Entity.Kategori;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface DetilTransaksiRepository extends CrudRepository<DetilTransaksi, Long> {

    public Iterable<DetilTransaksi>findBytanggalPembelian(Date date);


}
