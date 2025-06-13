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
public class PrismaTrapesium extends Trapesium implements Runnable{
    private double tinggiPrisma;
    private double volume;
    private double luasPermukaan;

    // Konstruktor utama
    public PrismaTrapesium(double sisiAtas, double sisiBawah, double tinggiAlas, double sisiMiring1, double sisiMiring2, double tinggiPrisma)throws IllegalArgumentException {
        // Memanggil konstruktor Trapesium yang benar
        super(sisiAtas, sisiBawah, tinggiAlas, sisiMiring1, sisiMiring2);
        
        if (tinggiPrisma <= 0) {
            throw new IllegalArgumentException("Tinggi prisma harus bernilai positif.");
        }
        this.tinggiPrisma = tinggiPrisma;
        
        // Hitung dan simpan nilai saat objek dibuat
        this.volume = hitungVolume();
        this.luasPermukaan = hitungLuasPermukaan();
    }

    // Getter
    public double getTinggiPrisma() { return tinggiPrisma; }
    public double getVolume() { return volume; }
    public double getLuasPermukaan() { return luasPermukaan; }
    
    public double hitungVolume() {
        // Volume Prisma = Luas Alas (dari Trapesium) * tinggiPrisma
        return super.luas * this.tinggiPrisma;
    }

    public double hitungLuasPermukaan() {
        // Luas Permukaan Prisma = (2 * Luas Alas) + (Keliling Alas * tinggiPrisma)
        return (2 * super.luas) + (super.keliling * this.tinggiPrisma);
    }
    
    // Overloading untuk hitungVolume dengan parameter
    public double hitungVolume(double sisiAtas, double sisiBawah, double tinggiAlas, double tinggiPrisma) {
        if (tinggiPrisma <= 0) {
            throw new IllegalArgumentException("Tinggi prisma harus bernilai positif.");
        }
        // Memanfaatkan hitungLuas dari superclass Trapesium
        double luasAlas = super.hitungLuas(sisiAtas, sisiBawah, tinggiAlas);
        return luasAlas * tinggiPrisma;
    }
    
    // Overloading untuk hitungLuasPermukaan dengan parameter
    public double hitungLuasPermukaan(double sisiAtas, double sisiBawah, double tinggiAlas, double sisiMiring1, double sisiMiring2, double tinggiPrisma) {
        if (tinggiPrisma <= 0) {
            throw new IllegalArgumentException("Tinggi prisma harus bernilai positif.");
        }
        // Memanfaatkan metode dari superclass Trapesium
        double luasAlas = super.hitungLuas(sisiAtas, sisiBawah, tinggiAlas);
        double kelilingAlas = super.hitungKeliling(sisiAtas, sisiBawah, sisiMiring1, sisiMiring2);
        return (2 * luasAlas) + (kelilingAlas * tinggiPrisma);
    }
    
    @Override
    public void run() {
        try {
            System.out.println("-> [Mulai] Thread untuk Prisma Trapesium (t=" + getTinggiPrisma() + ")");
            long jeda = (long) (Math.random() * 2000 + 1000);
            Thread.sleep(jeda);
            System.out.println("<- [Selesai] Prisma Trapesium (setelah " + jeda + " ms)");
            System.out.printf("   > Volume: %.2f, Luas Permukaan: %.2f\n", getVolume(), getLuasPermukaan());
        } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }
}