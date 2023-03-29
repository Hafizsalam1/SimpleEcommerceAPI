package org.example.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.Model.Entity.Kategori;
import org.example.Repository.KategoriRepository;
import org.example.Service.KategoriService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@DataJpaTest
@WebMvcTest
public class KategoriControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    KategoriService kategoriService;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    public void testFindById() throws Exception {

        Long id = 1L;

        Kategori kategori = Kategori.builder()
                .namaKategori("Pakaian")
                .deskripsi("Baju, celana, dll")
                .build();
        given(kategoriService.findById(id)).willReturn(Optional.of(kategori));

        ResultActions response = mockMvc.perform(get("/category/{id}", id));
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect((ResultMatcher) jsonPath("$.namaKategori", is(kategori.getNamaKategori())))
                .andExpect((ResultMatcher) jsonPath("$.deskripsi", is(kategori.getDeskripsi())));





//        when(kategoriRepository.findById(1L)).thenReturn(Optional.of(new Kategori(1L, "Pakaian", "Baju, celana, dll")));
//
//        Optional<Kategori> result = kategoriService.findById(1L);
//        assertEquals(result.get().getId(), Long.valueOf(1L));
//        assertEquals(result.get().getNamaKategori(), "Pakaian");
//        assertEquals(result.get().getDeskripsi(), "Baju, celana, dll");

    }




    }



