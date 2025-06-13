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
public class PrismaBelahKetupat extends BelahKetupat implements Runnable{
    private double tinggi;
    private double volume;
    private double luasPermukaan;
    
    // Konstruktor utama
    public PrismaBelahKetupat(double d1, double d2, double tinggi)throws IllegalArgumentException {
        super(d1, d2);
        if (tinggi <= 0) {
            throw new IllegalArgumentException("Tinggi prisma harus bernilai positif.");
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
        return super.luas * this.tinggi;
    }
    
    // Overloading untuk hitungVolume
    public double hitungVolume(double d1, double d2, double tinggi) {
        if (tinggi <= 0) {
            throw new IllegalArgumentException("Tinggi prisma harus bernilai positif.");
        }
        double luasAlas = super.hitungLuas(d1, d2);
        return luasAlas * tinggi;
    }

    public double hitungLuasPermukaan() {
        return (2 * super.luas) + (super.keliling * this.tinggi);
    }
    
    // Overloading untuk hitungLuasPermukaan
    public double hitungLuasPermukaan(double d1, double d2, double tinggi) {
        if (tinggi <= 0) {
            throw new IllegalArgumentException("Tinggi prisma harus bernilai positif.");
        }
        double luasAlas = super.hitungLuas(d1, d2);
        
        // DIUBAH: Hitung sisi (s) terlebih dahulu dari diagonal untuk memanggil hitungKeliling(s)
        double sisi = Math.sqrt(Math.pow(d1 / 2, 2) + Math.pow(d2 / 2, 2));
        double kelilingAlas = super.hitungKeliling(sisi); // Memanggil metode yang benar
        
        return (2 * luasAlas) + (kelilingAlas * tinggi);
    }
    
    @Override
    public void run() {
        try {
            System.out.println("-> [Mulai] Thread untuk Prisma Belah Ketupat (t=" + getTinggi() + ")");
            long jeda = (long) (Math.random() * 2000 + 1000);
            Thread.sleep(jeda);
            System.out.println("<- [Selesai] Prisma Belah Ketupat (setelah " + jeda + " ms)");
            System.out.printf("   > Volume: %.2f, Luas Permukaan: %.2f\n", getVolume(), getLuasPermukaan());
        } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }
}
