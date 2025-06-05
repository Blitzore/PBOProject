/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bendageometri.benda3d;
import bendageometri.benda2d.Persegi;

/**
 *
 * @author nbnrc
 */
public class PrismaPersegi extends Persegi {
    private double tinggiPrisma;

    // Konstruktor utama
    public PrismaPersegi(double sisiAlas, double tinggiPrisma) {
        super(sisiAlas); // Memanggil konstruktor Persegi (sisiAlas menjadi immutable)
                         // Validasi sisiAlas ditangani oleh Persegi
        setTinggiPrisma(tinggiPrisma); // Menggunakan setter untuk validasi tinggiPrisma
    }

    // Overloaded constructor untuk membuat Kubus (PrismaPersegi dengan semua sisi sama)
    public PrismaPersegi(double sisiKubus) {
        this(sisiKubus, sisiKubus); // Memanggil konstruktor utama: sisiAlas = tinggiPrisma = sisiKubus
    }

    // Getter untuk tinggiPrisma
    public double getTinggiPrisma() {
        return tinggiPrisma;
    }

    // Setter untuk tinggiPrisma dengan validasi
    public void setTinggiPrisma(double tinggiPrisma) {
        if (tinggiPrisma <= 0) {
            throw new IllegalArgumentException("Tinggi prisma harus bernilai positif.");
        }
        this.tinggiPrisma = tinggiPrisma;
    }

    // Getter untuk sisiAlas (yaitu getSisi()) diwarisi dari Persegi (immutable)

    @Override
    public double hitungVolume() {
        // Volume Prisma = Luas Alas (dari Persegi) * tinggiPrisma
        // super.hitungLuas() akan memanggil Persegi.hitungLuas()
        return super.hitungLuas() * this.tinggiPrisma;
    }

    @Override
    public double hitungLuasPermukaan() {
        // Luas Permukaan Prisma = 2 * Luas Alas + Luas Selimut
        // Luas Selimut = Keliling Alas * tinggiPrisma
        // super.hitungLuas() dan super.hitungKeliling() dari Persegi
        double luasAlas = super.hitungLuas();
        double kelilingAlas = super.hitungKeliling();
        return (2 * luasAlas) + (kelilingAlas * this.tinggiPrisma);
    }
}
