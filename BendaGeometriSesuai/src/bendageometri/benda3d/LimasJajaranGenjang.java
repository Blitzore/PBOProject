/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bendageometri.benda3d;
import bendageometri.benda2d.JajaranGenjang;


/**
 *
 * @author nbnrc
 */
public class LimasJajaranGenjang extends JajaranGenjang { // Mewarisi JajaranGenjang untuk alasnya
    private double tinggiLimas; // Bisa mutable dengan setter

    // Konstruktor utama
    public LimasJajaranGenjang(double alasJGAlas, double tinggiAlasJG, 
                               double sisiMiringAlasJG, double tinggiLimas) {
        super(alasJGAlas, tinggiAlasJG, sisiMiringAlasJG);
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
     * Menghitung luas permukaan limas jajaran genjang.
     * Untuk kasus umum, perhitungan akurat luas sisi tegak memerlukan
     * tinggi masing-masing sisi tegak. Metode ini akan mengembalikan
     * luas alas dengan peringatan. Gunakan metode overload dengan parameter
     * tinggi sisi tegak untuk hasil akurat.
     */
    @Override
    public double hitungLuasPermukaan() {
        double luasAlas = super.hitungLuas();
        
        System.err.println("Peringatan: hitungLuasPermukaan() untuk LimasJajaranGenjang ini mungkin tidak akurat karena merupakan kasus umum. Hanya luas alas yang dihitung. Gunakan metode hitungLuasPermukaan() dengan parameter tinggi sisi tegak untuk hasil akurat.");
        return luasAlas; // Fallback
    }

    /**
     * Menghitung luas permukaan limas jajaran genjang dengan menyediakan tinggi sisi tegak.
     * Jajaran genjang memiliki dua pasang sisi yang sama panjang: 'alas' dan 'sisiMiring'.
     * Jika limasnya tegak, maka akan ada dua pasang sisi tegak (segitiga) yang identik.
     * @param tinggiSisiTegakUntukAlasJG Tinggi sisi tegak yang alasnya adalah 'alas' dari JajaranGenjang.
     * @param tinggiSisiTegakUntukSisiMiringJG Tinggi sisi tegak yang alasnya adalah 'sisiMiring' dari JajaranGenjang.
     * @return Luas permukaan total limas jajaran genjang.
     */
    public double hitungLuasPermukaan(double tinggiSisiTegakUntukAlasJG, 
                                     double tinggiSisiTegakUntukSisiMiringJG) {
        if (tinggiSisiTegakUntukAlasJG <= 0 || tinggiSisiTegakUntukSisiMiringJG <= 0) {
            throw new IllegalArgumentException("Semua tinggi sisi tegak harus bernilai positif.");
        }

        double luasAlas = super.hitungLuas();
        // getAlas() dan getSisiMiring() dari JajaranGenjang
        double luasTotalSisiTegak = 2 * (0.5 * getAlas() * tinggiSisiTegakUntukAlasJG) +
                                   2 * (0.5 * getSisiMiring() * tinggiSisiTegakUntukSisiMiringJG);
        // Bisa disederhanakan: getAlas() * tinggiSisiTegakUntukAlasJG + getSisiMiring() * tinggiSisiTegakUntukSisiMiringJG
        
        return luasAlas + luasTotalSisiTegak;
    }
}
