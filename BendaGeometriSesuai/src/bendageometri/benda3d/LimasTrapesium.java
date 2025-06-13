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
public class LimasTrapesium extends Trapesium implements Runnable{
    private double tinggiLimas;
    private double volume;
    private double luasPermukaan;

    // Konstruktor utama
    public LimasTrapesium(double sisiAtas, double sisiBawah, double tinggiAlas, double sisiMiring1, double sisiMiring2, double tinggiLimas)throws IllegalArgumentException {
        // Memanggil konstruktor Trapesium yang benar
        super(sisiAtas, sisiBawah, tinggiAlas, sisiMiring1, sisiMiring2);
        
        if (tinggiLimas <= 0) {
            throw new IllegalArgumentException("Tinggi limas harus bernilai positif.");
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
    public double hitungVolume(double sisiAtas, double sisiBawah, double tinggiAlas, double tinggiLimas) {
        if (tinggiLimas <= 0) {
            throw new IllegalArgumentException("Tinggi limas harus bernilai positif.");
        }
        // Memanfaatkan hitungLuas dari superclass Trapesium
        double luasAlas = super.hitungLuas(sisiAtas, sisiBawah, tinggiAlas);
        return (luasAlas * tinggiLimas) / 3.0;
    }
    
    public double hitungLuasPermukaan() {
        // Luas Permukaan Limas = Luas Alas + Luas Selimut
        // Luas selimut adalah jumlah luas 4 sisi tegak (segitiga) yang bisa berbeda-beda.
        // Formula ini mengasumsikan limas tegak dan menggunakan penyederhanaan.
        
        // Menghitung tinggi sisi tegak (slant height) untuk setiap sisi alas
        double tsTegakAtas = Math.sqrt(Math.pow(tinggiLimas, 2) + Math.pow(super.getTinggi() / 2, 2));
        double tsTegakBawah = Math.sqrt(Math.pow(tinggiLimas, 2) + Math.pow(super.getTinggi() / 2, 2));
        double tsTegakMiring1 = Math.sqrt(Math.pow(tinggiLimas, 2) + Math.pow((Math.abs(super.getSisiAtas() - super.getSisiBawah())) / 2, 2));
        double tsTegakMiring2 = Math.sqrt(Math.pow(tinggiLimas, 2) + Math.pow((Math.abs(super.getSisiAtas() - super.getSisiBawah())) / 2, 2));
        
        // Menghitung luas setiap sisi tegak
        double luasTegakAtas = 0.5 * super.getSisiAtas() * tsTegakAtas;
        double luasTegakBawah = 0.5 * super.getSisiBawah() * tsTegakBawah;
        double luasTegakMiring1 = 0.5 * super.getSisiMiring1() * tsTegakMiring1;
        double luasTegakMiring2 = 0.5 * super.getSisiMiring2() * tsTegakMiring2;
        
        double luasSelimut = luasTegakAtas + luasTegakBawah + luasTegakMiring1 + luasTegakMiring2;
        
        return super.luas + luasSelimut;
    }
    
    // Overloading untuk hitungLuasPermukaan
    public double hitungLuasPermukaan(double sisiAtas, double sisiBawah, double tinggiAlas, double sisiMiring1, double sisiMiring2, double tinggiLimas) {
         if (tinggiLimas <= 0) {
            throw new IllegalArgumentException("Tinggi limas harus bernilai positif.");
        }
        double luasAlas = super.hitungLuas(sisiAtas, sisiBawah, tinggiAlas);

        // Logika perhitungan luas selimut sama dengan metode override, tapi dengan parameter
        double tsTegakAtas = Math.sqrt(Math.pow(tinggiLimas, 2) + Math.pow(tinggiAlas / 2, 2));
        double tsTegakBawah = Math.sqrt(Math.pow(tinggiLimas, 2) + Math.pow(tinggiAlas / 2, 2));
        double tsTegakMiring1 = Math.sqrt(Math.pow(tinggiLimas, 2) + Math.pow((Math.abs(sisiAtas - sisiBawah)) / 2, 2));
        double tsTegakMiring2 = Math.sqrt(Math.pow(tinggiLimas, 2) + Math.pow((Math.abs(sisiAtas - sisiBawah)) / 2, 2));
        
        double luasTegakAtas = 0.5 * sisiAtas * tsTegakAtas;
        double luasTegakBawah = 0.5 * sisiBawah * tsTegakBawah;
        double luasTegakMiring1 = 0.5 * sisiMiring1 * tsTegakMiring1;
        double luasTegakMiring2 = 0.5 * sisiMiring2 * tsTegakMiring2;
        
        double luasSelimut = luasTegakAtas + luasTegakBawah + luasTegakMiring1 + luasTegakMiring2;
        
        return luasAlas + luasSelimut;
    }
    
    @Override
    public void run() {
        try {
            System.out.println("-> [Mulai] Thread untuk Limas Trapesium (t=" + getTinggiLimas() + ")");
            long jeda = (long) (Math.random() * 2000 + 1000);
            Thread.sleep(jeda);
            System.out.println("<- [Selesai] Limas Trapesium (setelah " + jeda + " ms)");
            System.out.printf("   > Volume: %.2f, Luas Permukaan: %.2f\n", getVolume(), getLuasPermukaan());
        } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }
}
