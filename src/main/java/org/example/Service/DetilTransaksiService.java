package org.example.Service;

import org.example.Model.Entity.DetilTransaksi;
import org.example.Model.Entity.Kategori;
import org.example.Model.Entity.Produk;
import org.example.Model.Entity.Transaksi;
import org.example.Repository.DetilTransaksiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DetilTransaksiService implements IService<DetilTransaksi>{

    @Autowired
    DetilTransaksiRepository detilTransaksiRepository;



    @Override
    public DetilTransaksi save(DetilTransaksi detilTransaksi) throws Exception {
        try {
            return detilTransaksiRepository.save(detilTransaksi);
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Iterable<DetilTransaksi> findAll() throws Exception {
        try {
            Iterable<DetilTransaksi> detilTransaksis = detilTransaksiRepository.findAll();
            List<DetilTransaksi> detilTransaksis1 = new ArrayList<DetilTransaksi>();
            for (DetilTransaksi det : detilTransaksis) {
                detilTransaksis1.add(det);
            }

            if (detilTransaksis1.isEmpty()) {
                throw new RuntimeException("transaction not found");
            }
            return detilTransaksis;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<DetilTransaksi> findById(Long id) throws Exception {
        try{Optional<DetilTransaksi> detilTransaksi = detilTransaksiRepository.findById(id);
            if (detilTransaksi.isEmpty()) {
                throw new RuntimeException("transaksi not found");
            }
            return detilTransaksi;
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public DetilTransaksi update(DetilTransaksi params, Long id) throws Exception {
        return null;
    }

    @Override
    public void deleteById(Long id) throws Exception {

    }

    public Iterable<DetilTransaksi>laporanHarian(Date date) throws Exception {
        Iterable<DetilTransaksi> detilTransaksis = detilTransaksiRepository.findBytanggalPembelian(date);;

//        Set<DetilTransaksi>detilTransaksis1 = new HashSet<>();
//        for (DetilTransaksi det:detilTransaksis) {
//            if(det.getTanggalPembelian().equals(date)){
//                detilTransaksis1.add(det);
//            }
//
//        }
//        if(detilTransaksis1.isEmpty()){
//            throw new RuntimeException("There are no transaction on that date");
//        }

        return detilTransaksis;


    }

    public Set<DetilTransaksi>laporanBulanan(Integer month, Integer year) throws Exception {
        Iterable<DetilTransaksi>detilTransaksis = findAll();
        Set<DetilTransaksi>detilTransaksis1 = new HashSet<>();


        for (DetilTransaksi det:detilTransaksis) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(det.getTanggalPembelian());
            if(calendar.get(Calendar.MONTH)==month-1 && calendar.get(Calendar.YEAR)==year){
                detilTransaksis1.add(det);
            }
        }
        if(detilTransaksis1.isEmpty()){
            throw new RuntimeException("There are no transaction on that month");
        }
        return detilTransaksis1;

    }

}
