/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bendageometri.benda3d;
import bendageometri.benda2d.BelahKetupat;

/**
 *
 * @author nbnrc
 */
public class LimasBelahKetupat extends BelahKetupat implements Runnable {
    private double tinggi;
    private double volume;
    private double luasPermukaan;

    // Konstruktor utama
    public LimasBelahKetupat(double d1, double d2, double tinggi)throws IllegalArgumentException {
        super(d1, d2);
        if (tinggi <= 0) {
            throw new IllegalArgumentException("Tinggi limas harus bernilai positif.");
        }
        this.tinggi = tinggi;

        this.volume = this.hitungVolume();
        this.luasPermukaan = this.hitungLuasPermukaan();
    }
    
    // Getter
    public double getTinggi() { return tinggi; }
    public double getVolume() { return volume; }
    public double getLuasPermukaan() { return luasPermukaan; }

    public double hitungVolume() {
        return (super.luas * this.tinggi) / 3;
    }
    
    // Overloading untuk hitungVolume
    public double hitungVolume(double d1, double d2, double tinggi) {
        if (tinggi <= 0) {
            throw new IllegalArgumentException("Tinggi limas harus bernilai positif.");
        }
        double luasAlas = super.hitungLuas(d1, d2);
        return (luasAlas * tinggi) / 3;
    }
    
    public double hitungLuasPermukaan() {
        // Luas Permukaan = Luas Alas + Luas Selimut
        double inradius = (2 * super.luas) / super.keliling;
        double tinggiSisiTegak = Math.sqrt(Math.pow(this.tinggi, 2) + Math.pow(inradius, 2));
        double luasSelimut = (super.keliling * tinggiSisiTegak) / 2;
        return super.luas + luasSelimut;
    }
    
    // Overloading untuk hitungLuasPermukaan
    public double hitungLuasPermukaan(double d1, double d2, double tinggi) {
        if (d1 <= 0 || d2 <= 0 || tinggi <= 0) {
             throw new IllegalArgumentException("Dimensi harus bernilai positif.");
        }
        double luasAlas = super.hitungLuas(d1, d2);
        
        // Hitung sisi (s) terlebih dahulu dari diagonal untuk memanggil hitungKeliling(s)
        double sisi = Math.sqrt(Math.pow(d1 / 2, 2) + Math.pow(d2 / 2, 2));
        double kelilingAlas = super.hitungKeliling(sisi); // Memanggil metode yang benar
        
        // Menghitung luas selimut dengan parameter yang diberikan
        double inradius = (2 * luasAlas) / kelilingAlas;
        double tinggiSisiTegak = Math.sqrt(Math.pow(tinggi, 2) + Math.pow(inradius, 2));
        double luasSelimut = (kelilingAlas * tinggiSisiTegak) / 2;
        
        return luasAlas + luasSelimut;
    }
    
    @Override
    public void run() {
        try {
            System.out.println("-> [Mulai] Thread untuk Limas Belah Ketupat (t=" + getTinggi() + ")");
            long jeda = (long) (Math.random() * 2000 + 1000);
            Thread.sleep(jeda);
            System.out.println("<- [Selesai] Limas Belah Ketupat (setelah " + jeda + " ms)");
            System.out.printf("   > Volume: %.2f, Luas Permukaan: %.2f\n", getVolume(), getLuasPermukaan());
        } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }
}
