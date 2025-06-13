/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bendageometri.benda3d;

import bendageometri.benda2d.LayangLayang;

/**
 *
 * @author nbnrc
 */
public class LimasLayangLayang extends LayangLayang implements Runnable {
    private double tinggiLimas;
    private double volume;
    private double luasPermukaan;

    // Konstruktor utama
    public LimasLayangLayang(double d1, double d2, double sisiA, double sisiB, double tinggiLimas) throws NegativeDimensionException {
        // Memanggil konstruktor LayangLayang
        super(d1, d2, sisiA, sisiB);
        
        if (tinggiLimas <= 0) {
            throw new NegativeDimensionException("Tinggi limas harus bernilai positif.");
        }
        this.tinggiLimas = tinggiLimas;
        
        // Hitung dan simpan nilai saat objek dibuat
        this.volume = hitungVolume();
        this.luasPermukaan = hitungLuasPermukaan();
    }

    // Getter
    public double getTinggiLimas() { return tinggiLimas; }
    public double getVolume() { return volume; }
    public double getLuasPermukaan() { return luasPermukaan; }

    public double hitungVolume() {
        // Volume Limas = (Luas Alas * tinggiLimas) / 3
        return (super.luas * this.tinggiLimas) / 3.0;
    }
    
    // Overloading untuk hitungVolume
    public double hitungVolume(double d1, double d2, double tinggiLimas) throws NegativeDimensionException {
        if (tinggiLimas <= 0) {
            throw new NegativeDimensionException("Tinggi limas harus bernilai positif.");
        }
        // Memanfaatkan hitungLuas dari superclass
        double luasAlas = super.hitungLuas(d1, d2);
        return (luasAlas * tinggiLimas) / 3.0;
    }
    
    public double hitungLuasPermukaan() {
        // Luas Permukaan = Luas Alas + Luas Selimut
        // Diasumsikan limas tegak. Luas selimut adalah jumlah 2 pasang segitiga yang berbeda.
        
        // Menghitung tinggi sisi tegak (slant height) untuk setiap pasang sisi
        // Ini adalah penyederhanaan; rumus akurat memerlukan properti geometri lebih lanjut.
        double tsTegakA = Math.sqrt(Math.pow(tinggiLimas, 2) + Math.pow(super.d1 / 2, 2));
        double tsTegakB = Math.sqrt(Math.pow(tinggiLimas, 2) + Math.pow(super.d2 / 2, 2));
        
        // Luas 2 sisi tegak yang berbasis pada sisi pendek (sisiA)
        double luasTegak1 = super.sisiA * tsTegakA;
        // Luas 2 sisi tegak yang berbasis pada sisi panjang (sisiB)
        double luasTegak2 = super.sisiB * tsTegakB;
        
        return super.luas + luasTegak1 + luasTegak2;
    }
    
    // Overloading untuk hitungLuasPermukaan
    public double hitungLuasPermukaan(double d1, double d2, double sisiA, double sisiB, double tinggiLimas) throws NegativeDimensionException {
         if (tinggiLimas <= 0) {
            throw new NegativeDimensionException("Tinggi limas harus bernilai positif.");
        }
        double luasAlas = super.hitungLuas(d1, d2);

        // Logika perhitungan luas selimut sama dengan metode override
        double tsTegakA = Math.sqrt(Math.pow(tinggiLimas, 2) + Math.pow(d1 / 2, 2));
        double tsTegakB = Math.sqrt(Math.pow(tinggiLimas, 2) + Math.pow(d2 / 2, 2));
        
        double luasTegak1 = sisiA * tsTegakA;
        double luasTegak2 = sisiB * tsTegakB;
        
        return luasAlas + luasTegak1 + luasTegak2;
    }
    
    @Override
    public void run() {
        try {
            System.out.println("-> [Mulai] Thread untuk Limas Layang-Layang (t=" + getTinggiLimas() + ")");
            long jeda = (long) (Math.random() * 2000 + 1000);
            Thread.sleep(jeda);
            System.out.println("<- [Selesai] Limas Layang-Layang (setelah " + jeda + " ms)");
            System.out.printf("   > Volume: %.2f, Luas Permukaan: %.2f\n", getVolume(), getLuasPermukaan());
        } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }
}
