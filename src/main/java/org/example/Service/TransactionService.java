package org.example.Service;

import jakarta.transaction.Transactional;
import org.example.Model.Entity.DetilTransaksi;
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
@Transactional
public class TransactionService implements IService<Transaksi> {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    ProdukRepository produkRepository;


    @Override
    public Transaksi save(Transaksi transaksi) throws Exception {
        try {
            Produk produk = transaksi.getProduk();
            Long id = produk.getId();
            Produk produk1 = produkRepository.findById(id).get();
            produk.setNamaProduk(produk1.getNamaProduk());
            produk.setHarga(produk1.getHarga());
            produk.setKategori(produk1.getKategori());
            Long harga = 0L;
            harga = produk.getHarga()* transaksi.getJumlah();
            transaksi.setSubTotal(harga);
            transaksi.setProduk(produk);
            DetilTransaksi detilTransaksi = transaksi.getDetilTransaksi();
            Long grandTotal = detilTransaksi.getGrandTotal();
            detilTransaksi.setGrandTotal(grandTotal+ transaksi.getSubTotal());
            transaksi.setDetilTransaksi(detilTransaksi);






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
        try{Optional<Transaksi> transaksi = transactionRepository.findById(id);
            if (transaksi.isEmpty()) {
                throw new RuntimeException("transaction not found");
            }
            return transaksi;
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }    }

    @Override
    public Transaksi update(Transaksi transaksi, Long id) throws Exception {
        try{
            Optional<Transaksi>transaksi1 = transactionRepository.findById(id);
            if(transaksi1.isEmpty()){
                throw new RuntimeException("transaction not found");
            }
            transaksi.setId(id);
            return transactionRepository.save(transaksi);
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(Long id) throws Exception {
        try{
            Optional<Transaksi>transaksi= transactionRepository.findById(id);
            if(transaksi.isEmpty()){
                throw new RuntimeException("transaction not found");
            }
            transactionRepository.deleteById(id);
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }

    }


}
