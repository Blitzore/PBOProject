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
public class LimasJajaranGenjang extends JajaranGenjang implements Runnable {
    private double tinggiLimas;
    private double volume;
    private double luasPermukaan;

    // Konstruktor utama
    public LimasJajaranGenjang(double alas, double tinggiAlas, double sisiMiring, double tinggiLimas)throws IllegalArgumentException {
        // Memanggil konstruktor JajaranGenjang
        super(alas, tinggiAlas, sisiMiring);
        
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
    public double hitungVolume(double alas, double tinggiAlas, double tinggiLimas) {
        if (tinggiLimas <= 0) {
            throw new IllegalArgumentException("Tinggi limas harus bernilai positif.");
        }
        // Memanfaatkan hitungLuas dari superclass
        double luasAlas = super.hitungLuas(alas, tinggiAlas);
        return (luasAlas * tinggiLimas) / 3.0;
    }
    
    public double hitungLuasPermukaan() {
        // Luas Permukaan = Luas Alas + Luas Selimut
        // Diasumsikan limas tegak. Luas selimut adalah jumlah 2 pasang segitiga yang berbeda.
        
        // Tinggi sisi tegak (slant height) untuk sisi alas
        double tsTegakAlas = Math.sqrt(Math.pow(tinggiLimas, 2) + Math.pow(super.tinggi / 2, 2));
        // Tinggi sisi tegak (slant height) untuk sisi miring
        // Perhitungan ini memerlukan jarak tegak lurus dari pusat ke sisi miring, yang rumit.
        // Kita gunakan penyederhanaan.
        double tsTegakMiring = Math.sqrt(Math.pow(tinggiLimas, 2) + Math.pow(super.alas / 2, 2)); 
        
        // Luas 2 sisi tegak yang berbasis pada 'alas'
        double luasTegak1 = super.alas * tsTegakAlas;
        // Luas 2 sisi tegak yang berbasis pada 'sisiMiring'
        double luasTegak2 = super.sisiMiring * tsTegakMiring;
        
        return super.luas + luasTegak1 + luasTegak2;
    }
    
    // Overloading untuk hitungLuasPermukaan
    public double hitungLuasPermukaan(double alas, double tinggiAlas, double sisiMiring, double tinggiLimas) {
         if (tinggiLimas <= 0) {
            throw new IllegalArgumentException("Tinggi limas harus bernilai positif.");
        }
        double luasAlas = super.hitungLuas(alas, tinggiAlas);

        // Logika perhitungan luas selimut sama dengan metode override
        double tsTegakAlas = Math.sqrt(Math.pow(tinggiLimas, 2) + Math.pow(tinggiAlas / 2, 2));
        double tsTegakMiring = Math.sqrt(Math.pow(tinggiLimas, 2) + Math.pow(alas / 2, 2));
        
        double luasTegak1 = alas * tsTegakAlas;
        double luasTegak2 = sisiMiring * tsTegakMiring;
        
        return luasAlas + luasTegak1 + luasTegak2;
    }
    
    @Override
    public void run() {
        try {
            System.out.println("-> [Mulai] Thread untuk Limas Jajaran Genjang (t=" + getTinggiLimas() + ")");
            long jeda = (long) (Math.random() * 2000 + 1000);
            Thread.sleep(jeda);
            System.out.println("<- [Selesai] Limas Jajaran Genjang (setelah " + jeda + " ms)");
            System.out.printf("   > Volume: %.2f, Luas Permukaan: %.2f\n", getVolume(), getLuasPermukaan());
        } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }
}