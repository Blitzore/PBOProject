/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bendageometri.benda3d;
import bendageometri.benda2d.Segitiga;

/**
 *
 * @author nbnrc
 */
public class LimasSegitiga extends Segitiga implements Runnable{
    private double tinggiLimas;
    private double volume;
    private double luasPermukaan;

    // Konstruktor utama
    public LimasSegitiga(double alas, double tinggiAlas, double sisiB, double sisiC, double tinggiLimas)throws IllegalArgumentException {
        super(alas, tinggiAlas, sisiB, sisiC);
        
        if (tinggiLimas <= 0) {
            throw new IllegalArgumentException("Tinggi limas harus bernilai positif.");
        }
        this.tinggiLimas = tinggiLimas;
        
        this.volume = hitungVolume();
        this.luasPermukaan = hitungLuasPermukaan();
    }

    // Getter
    public double getTinggiLimas() { return tinggiLimas; }
    public double getVolume() { return volume; }
    public double getLuasPermukaan() { return luasPermukaan; }

    public double hitungVolume() {
        return (super.luas * this.tinggiLimas) / 3.0;
    }
    
    // Overloading untuk hitungVolume
    public double hitungVolume(double alas, double tinggiAlas, double tinggiLimas) {
        if (tinggiLimas <= 0) {
            throw new IllegalArgumentException("Tinggi limas harus bernilai positif.");
        }
        double luasAlas = super.hitungLuas(alas, tinggiAlas);
        return (luasAlas * tinggiLimas) / 3.0;
    }

    public double hitungLuasPermukaan() {
        // Implementasi luas permukaan berdasarkan asumsi limas tegak.
        double alasSegitiga = super.alas; 
        double sisiB = super.sisiB;
        double sisiC = super.sisiC;
        
        // Menghitung tinggi untuk setiap sisi tegak (slant height)
        // Ini adalah penyederhanaan; rumus akurat memerlukan properti geometri lebih lanjut.
        double tinggiSisiTegakA = Math.sqrt(Math.pow(tinggiLimas, 2) + Math.pow(alasSegitiga / 2, 2));
        double tinggiSisiTegakB = Math.sqrt(Math.pow(tinggiLimas, 2) + Math.pow(sisiB / 2, 2));
        double tinggiSisiTegakC = Math.sqrt(Math.pow(tinggiLimas, 2) + Math.pow(sisiC / 2, 2));
        
        // Menghitung luas setiap sisi tegak
        double luasSisiTegakA = 0.5 * alasSegitiga * tinggiSisiTegakA;
        double luasSisiTegakB = 0.5 * sisiB * tinggiSisiTegakB;
        double luasSisiTegakC = 0.5 * sisiC * tinggiSisiTegakC;
        
        return super.luas + luasSisiTegakA + luasSisiTegakB + luasSisiTegakC;
    }
    
    // Overloading untuk hitungLuasPermukaan
    public double hitungLuasPermukaan(double alas, double tinggiAlas, double sisiB, double sisiC, double tinggiLimas) {
        if (tinggiLimas <= 0) {
            throw new IllegalArgumentException("Tinggi limas harus bernilai positif.");
        }
        // Memanfaatkan metode dari superclass untuk luas dan keliling alas
        double luasAlas = super.hitungLuas(alas, tinggiAlas);
        
        // Logika perhitungan luas selimut sama dengan metode override, tapi dengan parameter
        double tinggiSisiTegakA = Math.sqrt(Math.pow(tinggiLimas, 2) + Math.pow(alas / 2, 2));
        double tinggiSisiTegakB = Math.sqrt(Math.pow(tinggiLimas, 2) + Math.pow(sisiB / 2, 2));
        double tinggiSisiTegakC = Math.sqrt(Math.pow(tinggiLimas, 2) + Math.pow(sisiC / 2, 2));
        
        double luasSisiTegakA = 0.5 * alas * tinggiSisiTegakA;
        double luasSisiTegakB = 0.5 * sisiB * tinggiSisiTegakB;
        double luasSisiTegakC = 0.5 * sisiC * tinggiSisiTegakC;
        
        return luasAlas + luasSisiTegakA + luasSisiTegakB + luasSisiTegakC;
    }
    
    @Override
    public void run() {
        try {
            System.out.println("-> [Mulai] Thread untuk Limas Segitiga (t=" + getTinggiLimas() + ")");
            long jeda = (long) (Math.random() * 2000 + 1000);
            Thread.sleep(jeda);
            System.out.println("<- [Selesai] Limas Segitiga (setelah " + jeda + " ms)");
            System.out.printf("   > Volume: %.2f, Luas Permukaan: %.2f\n", getVolume(), getLuasPermukaan());
        } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }
}