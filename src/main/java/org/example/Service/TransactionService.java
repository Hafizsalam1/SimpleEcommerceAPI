package org.example.Service;

import org.example.Model.Entity.Produk;
import org.example.Model.Entity.Transaksi;
import org.example.Repository.ProdukRepository;
import org.example.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TransactionService implements IService<Transaksi> {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    ProdukRepository produkRepository;


    @Override
    public Transaksi save(Transaksi transaksi) throws Exception {
        try {
            Set<Produk> produks = transaksi.getProduk();
            Long harga = 0L;
            for (Produk pro:produks) {
                Long id = pro.getId();
                Optional<Produk> produk = produkRepository.findById(id);
                harga = harga + produk.get().getHarga();
                pro.setNamaProduk(produk.get().getNamaProduk());
                pro.setKategori(produk.get().getKategori());
                pro.setHarga(produk.get().getHarga());
            }
            transaksi.setTotalHarga(harga);

            return transactionRepository.save(transaksi);
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Iterable<Transaksi> findAll() throws Exception {
        try {
            Iterable<Transaksi> transaksis = transactionRepository.findAll();
            List<Transaksi> transaksis1 = new ArrayList<Transaksi>();
            for (Transaksi tra : transaksis) {
                transaksis1.add(tra);
            }

            if (transaksis1.isEmpty()) {
                throw new RuntimeException("transaction not found");
            }
            return transaksis;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Transaksi> findById(Long id) throws Exception {
        return Optional.empty();
    }

    @Override
    public Transaksi update(Transaksi params, Long id) throws Exception {
        return null;
    }

    @Override
    public void deleteById(Long id) throws Exception {

    }


}
