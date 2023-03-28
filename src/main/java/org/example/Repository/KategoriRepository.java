package org.example.Repository;

import org.example.Model.Entity.Kategori;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KategoriRepository extends CrudRepository<Kategori, Long> {

}
