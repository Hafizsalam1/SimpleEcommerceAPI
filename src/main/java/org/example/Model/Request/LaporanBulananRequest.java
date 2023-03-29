package org.example.Model.Request;

import org.example.Model.Entity.DetilTransaksi;

import java.util.Set;

public class LaporanBulananRequest {
    private Long totalBulanan;
    private Set<DetilTransaksi> detilTransaksi;

    public LaporanBulananRequest() {
    }

    public Long getTotalBulanan() {
        return totalBulanan;
    }

    public void setTotalBulanan(Long totalBulanan) {
        this.totalBulanan = totalBulanan;
    }

    public Set<DetilTransaksi> getDetilTransaksi() {
        return detilTransaksi;
    }

    public void setDetilTransaksi(Set<DetilTransaksi> detilTransaksi) {
        this.detilTransaksi = detilTransaksi;
    }
}
