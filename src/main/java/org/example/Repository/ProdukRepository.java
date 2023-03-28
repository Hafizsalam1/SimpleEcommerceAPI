package org.example.Repository;

import org.example.Model.Entity.Kategori;
import org.example.Model.Entity.Produk;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdukRepository extends CrudRepository<Produk, Long> {


}
