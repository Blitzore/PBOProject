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
public class PrismaLayangLayang extends LayangLayang implements Runnable{
    private double tinggiPrisma;
    private double volume;
    private double luasPermukaan;

    // Konstruktor utama
    public PrismaLayangLayang(double d1, double d2, double sisiA, double sisiB, double tinggiPrisma) throws NegativeDimensionException {
        // Memanggil konstruktor LayangLayang
        super(d1, d2, sisiA, sisiB);
        
        if (tinggiPrisma <= 0) {
            throw new NegativeDimensionException("Tinggi prisma harus bernilai positif.");
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
        // Volume Prisma = Luas Alas (dari LayangLayang) * tinggiPrisma
        return super.luas * this.tinggiPrisma;
    }

    public double hitungLuasPermukaan() {
        // Luas Permukaan Prisma = (2 * Luas Alas) + (Keliling Alas * tinggiPrisma)
        return (2 * super.luas) + (super.keliling * this.tinggiPrisma);
    }
    
    // Overloading untuk hitungVolume
    public double hitungVolume(double d1, double d2, double tinggiPrisma) throws NegativeDimensionException {
        if (tinggiPrisma <= 0) {
            throw new NegativeDimensionException("Tinggi prisma harus bernilai positif.");
        }
        // Memanfaatkan hitungLuas dari superclass
        double luasAlas = super.hitungLuas(d1, d2);
        return luasAlas * tinggiPrisma;
    }
    
    // Overloading untuk hitungLuasPermukaan
    public double hitungLuasPermukaan(double d1, double d2, double sisiA, double sisiB, double tinggiPrisma) throws NegativeDimensionException {
        if (tinggiPrisma <= 0) {
            throw new NegativeDimensionException("Tinggi prisma harus bernilai positif.");
        }
        // Memanfaatkan metode dari superclass
        double luasAlas = super.hitungLuas(d1, d2);
        double kelilingAlas = super.hitungKeliling(sisiA, sisiB);
        return (2 * luasAlas) + (kelilingAlas * tinggiPrisma);
    }
    
    @Override
    public void run() {
        try {
            System.out.println("-> [Mulai] Thread untuk Prisma Layang-Layang (t=" + getTinggiPrisma() + ")");
            long jeda = (long) (Math.random() * 2000 + 1000);
            Thread.sleep(jeda);
            System.out.println("<- [Selesai] Prisma Layang-Layang (setelah " + jeda + " ms)");
            System.out.printf("   > Volume: %.2f, Luas Permukaan: %.2f\n", getVolume(), getLuasPermukaan());
        } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }
}
