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
public class PrismaLayangLayang extends LayangLayang { // Mewarisi LayangLayang untuk alasnya
    private double tinggiPrisma; // Bisa mutable dengan setter

    // Konstruktor utama
    public PrismaLayangLayang(double d1Alas, double d2Alas, 
                              double sisiAAlas, double sisiBAlas, 
                              double tinggiPrisma) {
        super(d1Alas, d2Alas, sisiAAlas, sisiBAlas);
        // Dimensi alas menjadi immutable dan validasinya (termasuk konsistensi diagonal vs sisi)
        // ditangani oleh LayangLayang
        setTinggiPrisma(tinggiPrisma); // Menggunakan setter untuk validasi tinggiPrisma
    }

    // Overloaded constructor: jika alas LayangLayang didefinisikan dengan dua pasang sisi
    // dan diagonal penghubung sisi berbeda (d2). Diagonal utama (d1) akan dihitung oleh LayangLayang.
    public PrismaLayangLayang(double sisiPendekAlas, double sisiPanjangAlas, 
                              double diagonalPenghubungAlas, double tinggiPrisma) {
        super(sisiPendekAlas, sisiPanjangAlas, diagonalPenghubungAlas);
        setTinggiPrisma(tinggiPrisma);
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

    // Getter untuk properti alas diwarisi dari LayangLayang (immutable)

    @Override
    public double hitungVolume() {
        return super.hitungLuas() * this.tinggiPrisma;
    }

    @Override
    public double hitungLuasPermukaan() {
        double luasAlas = super.hitungLuas();
        double kelilingAlas = super.hitungKeliling();
        return (2 * luasAlas) + (kelilingAlas * this.tinggiPrisma);
    }
}
