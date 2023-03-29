package org.example.Controller;

import jakarta.validation.Valid;
import org.example.Model.Entity.Kategori;
import org.example.Model.Request.KategoriRequest;
import org.example.Model.Response.SuccessResponse;
import org.example.Service.KategoriService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    KategoriService kategoriService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping()
    public ResponseEntity getAllCategory() throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<Iterable<Kategori>>("Get All Succeed", kategoriService.findAll()));
    }

    @PostMapping
    public ResponseEntity createProduct(@Valid KategoriRequest kategoriRequest) throws Exception {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        Kategori kategori  = modelMapper.map(kategoriRequest, Kategori.class);

         kategoriService.save(kategori);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<Kategori>("Add Category Succeed", kategori));

    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Long id) throws Exception {
        Optional<Kategori> kategori = kategoriService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<Optional<Kategori>>("Get by id Succeed", kategori));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) throws Exception {
        Optional<Kategori> kategori = kategoriService.findById(id);
        kategoriService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<Optional<Kategori>>("Delete Succeed",kategori));
    }

    @PutMapping("/{id}")
    public ResponseEntity updateById(@Valid KategoriRequest kategoriRequest, @PathVariable Long id) throws Exception {
        Kategori kategori = modelMapper.map(kategoriRequest, Kategori.class);
        kategori.setId(id);
        kategoriService.update(kategori, id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<Kategori>("Update Succeed",kategori));
    }



}
