package org.example.Service;

import jakarta.transaction.Transactional;
import org.example.Model.Entity.Produk;
import org.example.Repository.ProdukRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProdukService implements IService<Produk>{

    @Autowired
    ProdukRepository produkRepository;


    @Override
    public Produk save(Produk produk) throws Exception {
        try {
            return produkRepository.save(produk);
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Iterable<Produk> findAll() throws Exception {
        try {
            Iterable<Produk> produks = produkRepository.findAll();
            List<Produk> produks1 = new ArrayList<Produk>();
            for (Produk pro : produks) {
                produks1.add(pro);
            }

            if (produks1.isEmpty()) {
                throw new RuntimeException("product not found");
            }
            return produks;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Produk> findById(Long id) throws Exception {
        try{Optional<Produk> produk = produkRepository.findById(id);
            if (produk.isEmpty()) {
                throw new RuntimeException("product not found");
            }
            return produk;
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Produk update(Produk produk, Long id) throws Exception {
        try{
            Optional<Produk>produk1 = produkRepository.findById(id);
            if(produk1.isEmpty()){
                throw new RuntimeException("product not found");
            }
            produk.setId(id);
            return produkRepository.save(produk);
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }


    }

    @Override
    public void deleteById(Long id) throws Exception {
        try{
            Optional<Produk>produk= produkRepository.findById(id);
            if(produk.isEmpty()){
                throw new RuntimeException("product not found");
            }
            produkRepository.deleteById(id);
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }

    }
}
