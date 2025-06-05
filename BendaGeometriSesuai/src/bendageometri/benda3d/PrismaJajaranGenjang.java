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
public class PrismaJajaranGenjang extends JajaranGenjang { // Mewarisi JajaranGenjang untuk alasnya
    private double tinggiPrisma; // Bisa mutable dengan setter

    // Konstruktor utama
    public PrismaJajaranGenjang(double alasJajaranGenjang, double tinggiAlasJajaranGenjang, 
                                double sisiMiringAlas, double tinggiPrisma) {
        super(alasJajaranGenjang, tinggiAlasJajaranGenjang, sisiMiringAlas);
        // Dimensi alas menjadi immutable dan validasinya ditangani oleh JajaranGenjang
        setTinggiPrisma(tinggiPrisma); // Menggunakan setter untuk validasi tinggiPrisma
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

    // Getter untuk properti alas diwarisi dari JajaranGenjang (immutable)

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
