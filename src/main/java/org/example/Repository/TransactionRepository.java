package org.example.Repository;

import org.example.Model.Entity.Kategori;
import org.example.Model.Entity.Transaksi;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends CrudRepository<Transaksi, Long> {


}
