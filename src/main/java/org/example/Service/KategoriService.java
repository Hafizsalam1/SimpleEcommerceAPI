package org.example.Service;

import org.example.Model.Entity.Kategori;
import org.example.Model.Entity.Produk;
import org.example.Repository.KategoriRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class KategoriService implements IService<Kategori>{

    @Autowired
    KategoriRepository kategoriRepository;

    @Override
    public Kategori save(Kategori kategori) throws Exception {
        try {
            return kategoriRepository.save(kategori);
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Iterable<Kategori> findAll() throws Exception {
        try {
            Iterable<Kategori> kategoris = kategoriRepository.findAll();
            List<Kategori> kategoris1 = new ArrayList<Kategori>();
            for (Kategori kat : kategoris) {
                kategoris1.add(kat);
            }

            if (kategoris1.isEmpty()) {
                throw new RuntimeException("category not found");
            }
            return kategoris;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Kategori> findById(Long id) throws Exception {
        try{Optional<Kategori> kategori = kategoriRepository.findById(id);
            if (kategori.isEmpty()) {
                throw new RuntimeException("category not found");
            }
            return kategori;
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Kategori update(Kategori kategori, Long id) throws Exception {
        try{
            Optional<Kategori>kategori1 = kategoriRepository.findById(id);
            if(kategori1.isEmpty()){
                throw new RuntimeException("category not found");
            }
            kategori.setId(id);
            return kategoriRepository.save(kategori);
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    @Override
    public void deleteById(Long id) throws Exception {
        try{
            Optional<Kategori>kategori= kategoriRepository.findById(id);
            if(kategori.isEmpty()){
                throw new RuntimeException("category not found");
            }
            kategoriRepository.deleteById(id);
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }

    }
}
