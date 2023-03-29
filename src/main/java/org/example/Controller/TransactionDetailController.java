package org.example.Controller;

import jakarta.validation.Valid;
import org.example.Model.Entity.DetilTransaksi;
import org.example.Model.Entity.Produk;
import org.example.Model.Entity.Transaksi;
import org.example.Model.Request.LaporanBulananRequest;
import org.example.Model.Request.LaporanHarianRequest;
import org.example.Model.Request.TransactionDetailRequest;
import org.example.Model.Request.TransactionRequest;
import org.example.Model.Response.SuccessResponse;
import org.example.Service.DetilTransaksiService;
import org.example.Util.generateDate;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/detail")
public class TransactionDetailController {
    @Autowired
    DetilTransaksiService detilTransaksiService;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    org.example.Util.generateDate generateDate;

    @GetMapping()
    public ResponseEntity getAllTransactionDetail() throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<Iterable<DetilTransaksi>>("Get All Succeed", detilTransaksiService.findAll()));
    }

    @PostMapping
    public ResponseEntity createTransactionDeatil(@Valid TransactionDetailRequest transactionDetailRequest) throws Exception {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        DetilTransaksi detilTransaksi  = modelMapper.map(transactionDetailRequest, DetilTransaksi.class);
        Date date = org.example.Util.generateDate.generate(transactionDetailRequest.getTanggalPembelian());
        detilTransaksi.setTanggalPembelian(date);
        detilTransaksi.setGrandTotal(0L);
        detilTransaksiService.save(detilTransaksi);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<DetilTransaksi>("Add Transaction detail Succeed", detilTransaksi));
    }

    @GetMapping("/daily")
    public ResponseEntity laporanHarian(@Valid String date) throws Exception{

        Iterable<DetilTransaksi> detilTransaksi = detilTransaksiService.laporanHarian(org.example.Util.generateDate.generate(date));
        Set<DetilTransaksi>detilTransaksis = new HashSet<>();
        Long totalHarian = 0L;
        for (DetilTransaksi det:detilTransaksi)
              {
                  detilTransaksis.add(det);
                  totalHarian = totalHarian+ det.getGrandTotal();
        }
        LaporanHarianRequest laporanHarianRequest = new LaporanHarianRequest();
        laporanHarianRequest.setTotalHarian(totalHarian);
        laporanHarianRequest.setDetilTransaksi(detilTransaksis);

        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<LaporanHarianRequest>("Get Laporan Harian succeed", laporanHarianRequest));
    }

    @GetMapping("/monthly")
    public ResponseEntity laporanBulanan(@Valid Integer month, Integer year) throws Exception{

//        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Set<DetilTransaksi>detilTransaksis = detilTransaksiService.laporanBulanan(month, year);
        Long totalBulanan = 0L;
        for (DetilTransaksi det:detilTransaksis)
        {
            detilTransaksis.add(det);
            totalBulanan = totalBulanan+ det.getGrandTotal();
        }
        LaporanBulananRequest laporanBulananRequest = new LaporanBulananRequest();
        laporanBulananRequest.setTotalBulanan(totalBulanan);
        laporanBulananRequest.setDetilTransaksi(detilTransaksis);

        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<LaporanBulananRequest>("Get Laporan Bulanan succeed", laporanBulananRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Long id) throws Exception {
        Optional<DetilTransaksi> detilTransaksi = detilTransaksiService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<Optional<DetilTransaksi>>("Get by id Succeed", detilTransaksi));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) throws Exception {
        Optional<DetilTransaksi> detilTransaksi = detilTransaksiService.findById(id);
        detilTransaksiService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<Optional<DetilTransaksi>>("Delete Succeed",detilTransaksi));
    }

    @PutMapping("/{id}")
    public ResponseEntity updateById(@Valid TransactionDetailRequest transactionDetailRequest, @PathVariable Long id) throws Exception {
        DetilTransaksi detilTransaksi = modelMapper.map(transactionDetailRequest, DetilTransaksi.class);
        detilTransaksi.setId(id);
        detilTransaksiService.update(detilTransaksi, id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<DetilTransaksi>("Update Succeed",detilTransaksi));
    }



}
