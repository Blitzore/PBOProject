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
public class PrismaBelahKetupat extends BelahKetupat { // Mewarisi BelahKetupat untuk alasnya
    private double tinggiPrisma; // Bisa mutable dengan setter

    // Konstruktor utama
    public PrismaBelahKetupat(double sisiAlas, double d1Alas, double d2Alas, double tinggiPrisma) {
        super(sisiAlas, d1Alas, d2Alas);
        // Dimensi alas menjadi immutable dan validasinya (termasuk konsistensi sisi vs diagonal)
        // ditangani oleh BelahKetupat
        setTinggiPrisma(tinggiPrisma); // Menggunakan setter untuk validasi tinggiPrisma
    }
    
    // Overloaded constructor: jika alas BelahKetupat didefinisikan hanya dengan dua diagonalnya
    // (sisi alas akan dihitung oleh konstruktor BelahKetupat yang di-overload)
    public PrismaBelahKetupat(double d1Alas, double d2Alas, double tinggiPrisma) {
        super(d1Alas, d2Alas); // Memanggil konstruktor BelahKetupat(double diagonal1, double diagonal2)
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

    // Getter untuk properti alas diwarisi dari BelahKetupat (immutable)

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
