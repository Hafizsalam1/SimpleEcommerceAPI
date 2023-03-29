package org.example.Controller;

import jakarta.validation.Valid;
import org.example.Model.Entity.Kategori;
import org.example.Model.Entity.Produk;
import org.example.Model.Entity.Transaksi;
import org.example.Model.Request.KategoriRequest;
import org.example.Model.Request.ProductRequest;
import org.example.Model.Request.TransactionRequest;
import org.example.Model.Response.SuccessResponse;
import org.example.Service.DetilTransaksiService;
import org.example.Service.KategoriService;
import org.example.Service.ProdukService;
import org.example.Service.TransactionService;
import org.example.Util.generateDate;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    TransactionService transactionService;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ProdukService produkService;

    @Autowired
    DetilTransaksiService detilTransaksiService;


    @GetMapping()
    public ResponseEntity getAllTransaction() throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<Iterable<Transaksi>>("Get All Succeed", transactionService.findAll()));
    }

    @PostMapping
    public ResponseEntity createTransaction(@Valid TransactionRequest transactionRequest) throws Exception {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Transaksi transaksi  = modelMapper.map(transactionRequest, Transaksi.class);
        Optional<Produk> produk = produkService.findById(transactionRequest.getIdProduk());
        transaksi.setProduk(produk.get());
        transaksi.setDetilTransaksi(detilTransaksiService.findById(transactionRequest.getIdDetilTransaksi()).get());
        transactionService.save(transaksi);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<Transaksi>("Add Transaction Succeed", transaksi));
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Long id) throws Exception {
        Optional<Transaksi> transaksi = transactionService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<Optional<Transaksi>>("Get by id Succeed", transaksi));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) throws Exception {
        Optional<Produk> produk = produkService.findById(id);
        produkService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<Optional<Produk>>("Delete Succeed",produk));
    }

    @PutMapping("/{id}")
    public ResponseEntity updateById(@Valid TransactionRequest transactionRequest, @PathVariable Long id) throws Exception {
        Transaksi transaksi = modelMapper.map(transactionRequest, Transaksi.class);
        transaksi.setId(id);
        transactionService.update(transaksi, id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<Transaksi>("Update Succeed",transaksi));
    }




}
