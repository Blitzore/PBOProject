/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bendageometri.benda3d;
import bendageometri.benda2d.Trapesium;

/**
 *
 * @author nbnrc
 */
public class LimasTrapesium extends Trapesium { // Mewarisi Trapesium untuk alasnya
    private double tinggiLimas; // Bisa mutable dengan setter

    // Konstruktor utama
    public LimasTrapesium(double sisiAtasAlas, double sisiBawahAlas, 
                          double tinggiAlasTrapesium, 
                          double sisiMiring1Alas, double sisiMiring2Alas, 
                          double tinggiLimas) {
        super(sisiAtasAlas, sisiBawahAlas, tinggiAlasTrapesium, sisiMiring1Alas, sisiMiring2Alas);
        setTinggiLimas(tinggiLimas);
    }

    // Overloaded constructor: jika alasnya Trapesium Sama Kaki
    public LimasTrapesium(double sisiAtasAlas, double sisiBawahAlas, 
                          double tinggiAlasTrapesium, 
                          double sisiMiringSamaAlas, 
                          double tinggiLimas) {
        super(sisiAtasAlas, sisiBawahAlas, tinggiAlasTrapesium, sisiMiringSamaAlas, tinggiLimas);
        setTinggiLimas(tinggiLimas);
    }

    // Getter untuk tinggiLimas
    public double getTinggiLimas() {
        return tinggiLimas;
    }

    // Setter untuk tinggiLimas dengan validasi
    public void setTinggiLimas(double tinggiLimas) {
        if (tinggiLimas <= 0) {
            throw new IllegalArgumentException("Tinggi limas harus bernilai positif.");
        }
        this.tinggiLimas = tinggiLimas;
    }

    @Override
    public double hitungVolume() {
        return (1.0 / 3.0) * super.hitungLuas() * this.tinggiLimas;
    }

    /**
     * Menghitung luas permukaan limas trapesium.
     * Untuk limas trapesium umum, perhitungan akurat luas sisi tegak memerlukan
     * tinggi masing-masing dari keempat sisi tegak. Metode ini, tanpa parameter tambahan,
     * akan mengembalikan luas alas dengan peringatan.
     * Gunakan metode hitungLuasPermukaan() dengan parameter tinggi sisi tegak
     * untuk perhitungan akurat.
     */
    @Override
    public double hitungLuasPermukaan() {
        double luasAlas = super.hitungLuas();
        // double rataRataApotemaAlas = getTinggi() / 2.0; // getTinggi() dari alas Trapesium
        // double rataRataTinggiSelimut = Math.sqrt(Math.pow(tinggiLimas, 2) + Math.pow(rataRataApotemaAlas, 2));
        // double luasSisiTegak = 0.5 * super.hitungKeliling() * rataRataTinggiSelimut;
        
        System.err.println("Peringatan: hitungLuasPermukaan() untuk LimasTrapesium ini mungkin tidak akurat karena merupakan kasus umum. Hanya luas alas yang dihitung. Gunakan metode hitungLuasPermukaan() dengan parameter tinggi sisi tegak untuk hasil akurat.");
        return luasAlas; // Mengembalikan hanya luas alas sebagai fallback
    }

    /**
     * Menghitung luas permukaan limas trapesium dengan menyediakan tinggi masing-masing sisi tegak.
     * Sisi-sisi alas trapesium adalah sisiAtas, sisiBawah, sisiMiring1, dan sisiMiring2.
     * @param tstSisiAtas Tinggi sisi tegak yang alasnya adalah 'sisiAtas' dari Trapesium.
     * @param tstSisiBawah Tinggi sisi tegak yang alasnya adalah 'sisiBawah' dari Trapesium.
     * @param tstSisiMiring1 Tinggi sisi tegak yang alasnya adalah 'sisiMiring1' dari Trapesium.
     * @param tstSisiMiring2 Tinggi sisi tegak yang alasnya adalah 'sisiMiring2' dari Trapesium.
     * @return Luas permukaan total limas trapesium.
     */
    public double hitungLuasPermukaan(double tstSisiAtas, double tstSisiBawah,
                                     double tstSisiMiring1, double tstSisiMiring2) {
        if (tstSisiAtas <= 0 || tstSisiBawah <= 0 || tstSisiMiring1 <= 0 || tstSisiMiring2 <= 0) {
            throw new IllegalArgumentException("Semua tinggi sisi tegak harus bernilai positif.");
        }

        double luasAlas = super.hitungLuas();
        double luasSisiTegakAtas = 0.5 * super.sisiAtas * tstSisiAtas;
        double luasSisiTegakBawah = 0.5 * super.sisiBawah * tstSisiBawah;
        double luasSisiTegakMiring1 = 0.5 * super.sisiMiring1 * tstSisiMiring1;
        double luasSisiTegakMiring2 = 0.5 * super.sisiMiring2 * tstSisiMiring2;

        return luasAlas + luasSisiTegakAtas + luasSisiTegakBawah + luasSisiTegakMiring1 + luasSisiTegakMiring2;
    }
}
