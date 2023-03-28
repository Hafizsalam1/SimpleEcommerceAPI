package org.example.Controller;

import jakarta.validation.Valid;
import org.example.Model.Entity.Kategori;
import org.example.Model.Entity.Produk;
import org.example.Model.Entity.Transaksi;
import org.example.Model.Request.KategoriRequest;
import org.example.Model.Request.TransactionRequest;
import org.example.Model.Response.SuccessResponse;
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
    generateDate generateDate;


    @GetMapping()
    public ResponseEntity getAllTransaction() throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<Iterable<Transaksi>>("Get All Succeed", transactionService.findAll()));
    }

    @PostMapping
    public ResponseEntity createProduct(@Valid TransactionRequest transactionRequest) throws Exception {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Transaksi transaksi  = modelMapper.map(transactionRequest, Transaksi.class);
        Optional<Produk> produk = produkService.findById(transactionRequest.getIdProduk());
        Date date = org.example.Util.generateDate.generate(transactionRequest.getDate());
        transaksi.setDate(date);
        transaksi.setProduk(produk.get());
        transactionService.save(transaksi);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<Transaksi>("Add Transaction Succeed", transaksi));
    }


}
