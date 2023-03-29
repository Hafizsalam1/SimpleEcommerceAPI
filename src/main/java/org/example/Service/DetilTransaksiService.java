package org.example.Service;

import org.example.Model.Entity.DetilTransaksi;
import org.example.Model.Entity.Kategori;
import org.example.Model.Entity.Produk;
import org.example.Model.Entity.Transaksi;
import org.example.Model.Request.LaporanBulananRequest;
import org.example.Model.Request.LaporanHarianRequest;
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
    public DetilTransaksi update(DetilTransaksi detilTransaksi, Long id) throws Exception {
        try{
            Optional<DetilTransaksi>detilTransaksi1 = detilTransaksiRepository.findById(id);
            if(detilTransaksi1.isEmpty()){
                throw new RuntimeException("Transaction detail not found");
            }
            detilTransaksi.setId(id);
            return detilTransaksiRepository.save(detilTransaksi);
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(Long id) throws Exception {
        try{
            Optional<DetilTransaksi>detilTransaksi= detilTransaksiRepository.findById(id);
            if(detilTransaksi.isEmpty()){
                throw new RuntimeException("deatil transaction not found");
            }
            detilTransaksiRepository.deleteById(id);
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    public LaporanHarianRequest laporanHarian(Date date) throws Exception {
        Iterable<DetilTransaksi> detilTransaksis1 = detilTransaksiRepository.findBytanggalPembelian(date);;

        Set<DetilTransaksi>detilTransaksis = new HashSet<>();
        Long totalHarian = 0L;
        for (DetilTransaksi det:detilTransaksis1)
        {
            detilTransaksis.add(det);
            totalHarian = totalHarian+ det.getGrandTotal();
        }
        LaporanHarianRequest laporanHarianRequest = new LaporanHarianRequest();
        laporanHarianRequest.setTotalHarian(totalHarian);
        laporanHarianRequest.setDetilTransaksi(detilTransaksis);

        return laporanHarianRequest;


    }

    public LaporanBulananRequest laporanBulanan(Integer month, Integer year) throws Exception {
        Iterable<DetilTransaksi>detilTransaksis = findAll();
        Set<DetilTransaksi>detilTransaksis1 = new HashSet<>();

        Long totalBulanan = 0L;
        for (DetilTransaksi det:detilTransaksis) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(det.getTanggalPembelian());
            if(calendar.get(Calendar.MONTH)==month-1 && calendar.get(Calendar.YEAR)==year){
                detilTransaksis1.add(det);
                totalBulanan = totalBulanan+ det.getGrandTotal();

            }
        }

//        Long totalBulanan = 0L;
//        for (DetilTransaksi det:detilTransaksis1)
//        {
//            detilTransaksis1.add(det);
//            totalBulanan = totalBulanan+ det.getGrandTotal();
//        }
        if(detilTransaksis1.isEmpty()){
            throw new RuntimeException("There are no transaction on that month");
        }
        LaporanBulananRequest laporanBulananRequest = new LaporanBulananRequest();
        laporanBulananRequest.setTotalBulanan(totalBulanan);
        laporanBulananRequest.setDetilTransaksi(detilTransaksis1);


        return laporanBulananRequest;

    }

}
