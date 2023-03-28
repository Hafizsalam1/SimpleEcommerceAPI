package org.example.Controller;

import jakarta.validation.Valid;
import org.example.Model.Entity.Kategori;
import org.example.Model.Entity.Produk;
import org.example.Model.Request.KategoriRequest;
import org.example.Model.Request.ProductRequest;
import org.example.Model.Response.SuccessResponse;
import org.example.Service.KategoriService;
import org.example.Service.ProdukService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProdukService produkService;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    KategoriService kategoriService;

    @GetMapping
    public ResponseEntity getAllProduct() throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<Iterable<Produk>>("Get All Succeed", produkService.findAll()));
    }

    @PostMapping
    public ResponseEntity createProduct(@Valid ProductRequest productRequest) throws Exception {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Produk produk  = modelMapper.map(productRequest, Produk.class);
        Optional<Kategori> kategori1= kategoriService.findById(productRequest.getIdKategori());
        produk.setKategori(kategori1.get());
        produkService.save(produk);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<Produk>("Add Product Succeed", produk));



    }


}
