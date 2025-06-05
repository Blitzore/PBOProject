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
public class PrismaPersegiPanjang extends PersegiPanjang { // Mewarisi PersegiPanjang untuk alasnya
    private double tinggiPrisma; // Bisa mutable dengan setter

    // Konstruktor utama
    public PrismaPersegiPanjang(double panjangAlas, double lebarAlas, double tinggiPrisma) {
        super(panjangAlas, lebarAlas); // Memanggil konstruktor PersegiPanjang
                                       // Dimensi alas (panjangAlas, lebarAlas) menjadi immutable
                                       // Validasi panjangAlas dan lebarAlas ditangani oleh PersegiPanjang
        setTinggiPrisma(tinggiPrisma);   // Menggunakan setter untuk validasi tinggiPrisma
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

    // Getter untuk panjangAlas (yaitu getPanjang()) dan lebarAlas (yaitu getLebar())
    // diwarisi dari PersegiPanjang (immutable)

    @Override
    public double hitungVolume() {
        // Volume Prisma = Luas Alas (dari PersegiPanjang) * tinggiPrisma
        return super.hitungLuas() * this.tinggiPrisma;
    }

    @Override
    public double hitungLuasPermukaan() {
        // Luas Permukaan Prisma = 2 * Luas Alas + Luas Selimut
        // Luas Selimut = Keliling Alas * tinggiPrisma
        double luasAlas = super.hitungLuas();
        double kelilingAlas = super.hitungKeliling();
        return (2 * luasAlas) + (kelilingAlas * this.tinggiPrisma);
    }
}
