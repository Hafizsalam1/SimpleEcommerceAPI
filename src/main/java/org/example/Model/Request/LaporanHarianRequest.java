package org.example.Model.Request;

import org.example.Model.Entity.DetilTransaksi;
import org.example.Model.Entity.Transaksi;

import java.util.Set;

public class LaporanHarianRequest {
    private Long totalHarian;
    private Set<DetilTransaksi> detilTransaksi;

    public LaporanHarianRequest() {
    }

    public Long getTotalHarian() {
        return totalHarian;
    }

    public void setTotalHarian(Long totalHarian) {
        this.totalHarian = totalHarian;
    }

    public Set<DetilTransaksi> getDetilTransaksi() {
        return detilTransaksi;
    }

    public void setDetilTransaksi(Set<DetilTransaksi> detilTransaksi) {
        this.detilTransaksi = detilTransaksi;
    }
}
