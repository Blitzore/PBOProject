/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bendageometri.benda3d;
import bendageometri.benda2d.PersegiPanjang;

/**
 *
 * @author nbnrc
 */
public class PrismaPersegiPanjang extends PersegiPanjang implements Runnable{
    private double tinggi;
    private double volume;
    private double luasPermukaan;

    // Konstruktor utama
    public PrismaPersegiPanjang(double panjang, double lebar, double tinggi)throws IllegalArgumentException  {
        super(panjang, lebar); // Memanggil konstruktor PersegiPanjang
        if (tinggi <= 0) {
            throw new IllegalArgumentException("Tinggi harus bernilai positif.");
        }
        this.tinggi = tinggi;
        
        // Hitung dan simpan volume serta luas permukaan saat objek dibuat
        this.volume = hitungVolume();
        this.luasPermukaan = hitungLuasPermukaan();
    }

    // Getter untuk tinggi
    public double getTinggi() {
        return tinggi;
    }

    // Getter untuk volume
    public double getVolume() {
        return volume;
    }

    // Getter untuk luasPermukaan
    public double getLuasPermukaan() {
        return luasPermukaan;
    }

    public double hitungVolume() {
        // Volume Prisma = Luas Alas (dari PersegiPanjang) * tinggiPrisma
        // super.luas mengakses atribut luas dari instance PersegiPanjang yang sudah dihitung di konstruktor super
        return super.luas * this.tinggi;
    }
    
    // Overloading untuk hitungVolume dengan parameter
    public double hitungVolume(double panjang, double lebar, double tinggi) {
        if (tinggi <= 0) {
            throw new IllegalArgumentException("Tinggi untuk perhitungan volume harus bernilai positif.");
        }
        // Memanfaatkan hitungLuas(p, l) dari superclass PersegiPanjang untuk luas alas
        double luasAlas = super.hitungLuas(panjang, lebar);
        return luasAlas * tinggi;
    }

    public double hitungLuasPermukaan() {
        // Luas Permukaan Prisma = (2 * Luas Alas) + Luas Selimut
        // Luas Selimut = Keliling Alas * tinggiPrisma
        // Mengakses super.luas dan super.keliling dari instance PersegiPanjang
        return (2 * super.luas) + (super.keliling * this.tinggi);
    }
    
    // Overloading untuk hitungLuasPermukaan dengan parameter
    public double hitungLuasPermukaan(double panjang, double lebar, double tinggi) {
        if (tinggi <= 0) {
            throw new IllegalArgumentException("Tinggi untuk perhitungan luas permukaan harus bernilai positif.");
        }
        // Memanfaatkan hitungLuas(p,l) dari superclass PersegiPanjang
        double luasAlas = super.hitungLuas(panjang, lebar);
        // Memanfaatkan hitungKeliling(p,l) dari superclass PersegiPanjang
        double kelilingAlas = super.hitungKeliling(panjang, lebar);
        return (2 * luasAlas) + (kelilingAlas * tinggi);
    }
    
    @Override
    public void run() {
        try {
            System.out.println("-> [Mulai] Thread untuk Prisma Persegi Panjang (" + this.panjang + "x" + this.lebar + ", t=" + getTinggi() + ")");
            long jeda = (long) (Math.random() * 2000 + 1000);
            Thread.sleep(jeda);
            System.out.println("<- [Selesai] Prisma Persegi Panjang (setelah " + jeda + " ms)");
            System.out.printf("   > Volume: %.2f, Luas Permukaan: %.2f\n", getVolume(), getLuasPermukaan());
        } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }
}
