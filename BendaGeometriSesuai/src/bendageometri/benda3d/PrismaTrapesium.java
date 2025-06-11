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
public class PrismaTrapesium extends Trapesium { // Mewarisi Trapesium untuk alasnya
    private double tinggiPrisma; // Bisa mutable dengan setter

    // Konstruktor utama
    public PrismaTrapesium(double sisiAtasAlas, double sisiBawahAlas, 
                           double tinggiAlasTrapesium, 
                           double sisiMiring1Alas, double sisiMiring2Alas, 
                           double tinggiPrisma) {
        super(sisiAtasAlas, sisiBawahAlas, tinggiAlasTrapesium, sisiMiring1Alas, sisiMiring2Alas);
        // Dimensi alas menjadi immutable dan validasinya ditangani oleh Trapesium
        setTinggiPrisma(tinggiPrisma); // Menggunakan setter untuk validasi tinggiPrisma
    }

    // Overloaded constructor: untuk Prisma Trapesium Sama Kaki
    public PrismaTrapesium(double sisiAtasAlas, double sisiBawahAlas, 
                           double tinggiAlasTrapesium, 
                           double sisiMiringSamaAlas, 
                           double tinggiPrisma) {
        // Memanggil konstruktor Trapesium(sisiAtas, sisiBawah, tinggi, sisiMiringSama)
        super(sisiAtasAlas, sisiBawahAlas, tinggiAlasTrapesium, sisiMiringSamaAlas, tinggiPrisma); 
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

    // Getter untuk properti alas diwarisi dari Trapesium (immutable)

    @Override
    public double hitungVolume() {
        // Volume Prisma = Luas Alas (dari Trapesium) * tinggiPrisma
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
