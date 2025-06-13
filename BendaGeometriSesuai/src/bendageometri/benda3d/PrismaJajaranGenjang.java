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
public class PrismaJajaranGenjang extends JajaranGenjang implements Runnable{
    private double tinggiPrisma;
    private double volume;
    private double luasPermukaan;

    // Konstruktor utama
    public PrismaJajaranGenjang(double alas, double tinggiAlas, double sisiMiring, double tinggiPrisma)throws IllegalArgumentException {
        // Memanggil konstruktor JajaranGenjang
        super(alas, tinggiAlas, sisiMiring);
        
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
        // Volume Prisma = Luas Alas (dari JajaranGenjang) * tinggiPrisma
        return super.luas * this.tinggiPrisma;
    }

    public double hitungLuasPermukaan() {
        // Luas Permukaan Prisma = (2 * Luas Alas) + (Keliling Alas * tinggiPrisma)
        return (2 * super.luas) + (super.keliling * this.tinggiPrisma);
    }
    
    // Overloading untuk hitungVolume
    public double hitungVolume(double alas, double tinggiAlas, double tinggiPrisma) {
        if (tinggiPrisma <= 0) {
            throw new IllegalArgumentException("Tinggi prisma harus bernilai positif.");
        }
        // Memanfaatkan hitungLuas dari superclass
        double luasAlas = super.hitungLuas(alas, tinggiAlas);
        return luasAlas * tinggiPrisma;
    }
    
    // Overloading untuk hitungLuasPermukaan
    public double hitungLuasPermukaan(double alas, double tinggiAlas, double sisiMiring, double tinggiPrisma) {
        if (tinggiPrisma <= 0) {
            throw new IllegalArgumentException("Tinggi prisma harus bernilai positif.");
        }
        // Memanfaatkan metode dari superclass
        double luasAlas = super.hitungLuas(alas, tinggiAlas);
        double kelilingAlas = super.hitungKeliling(alas, sisiMiring);
        return (2 * luasAlas) + (kelilingAlas * tinggiPrisma);
    }
    
    @Override
    public void run() {
        try {
            System.out.println("-> [Mulai] Thread untuk Prisma Jajaran Genjang (t=" + getTinggiPrisma() + ")");
            long jeda = (long) (Math.random() * 2000 + 1000);
            Thread.sleep(jeda);
            System.out.println("<- [Selesai] Prisma Jajaran Genjang (setelah " + jeda + " ms)");
            System.out.printf("   > Volume: %.2f, Luas Permukaan: %.2f\n", getVolume(), getLuasPermukaan());
        } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }
}
