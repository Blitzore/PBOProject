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
public class PrismaPersegiPanjang extends PersegiPanjang {
    private double volume;
    private double luasPermukaan;
    private double tinggiPrisma;
    
    // Konstruktor utama
    public PrismaPersegiPanjang(double panjang, double lebar, double tinggiPrisma) throws IllegalArgumentException {
        super(panjang, lebar); // Memanggil konstruktor PersegiPanjang
        
        if (tinggiPrisma <= 0) {
            throw new IllegalArgumentException("Tinggi prisma harus bernilai positif.");
        }
        
        this.tinggiPrisma = tinggiPrisma;
        this.volume = hitungVolume();
        this.luasPermukaan = hitungLuasPermukaan();
    }
    
    // Getter untuk volume
    public double getVolume() {
        return volume;
    }
    
    // Getter untuk luasPermukaan
    public double getLuasPermukaan() {
        return luasPermukaan;
    }
    
    // Getter untuk tinggiPrisma
    public double getTinggiPrisma() {
        return tinggiPrisma;
    }
    
    @Override
    public double hitungVolume() {
        // Volume Prisma = Luas Alas * tinggiPrisma
        return this.luas * this.tinggiPrisma;
    }
    
    // Overloading untuk hitungVolume dengan parameter
    public double hitungVolume(double panjang, double lebar, double tinggiPrisma) {
        if (panjang <= 0 || lebar <= 0 || tinggiPrisma <= 0) {
            throw new IllegalArgumentException("Semua parameter harus bernilai positif.");
        }
        double luasAlas = panjang * lebar;
        return luasAlas * tinggiPrisma;
    }
    
    @Override
    public double hitungLuasPermukaan() {
        // Luas Permukaan = 2 * Luas Alas + Luas Selimut
        double luasAlas = this.luas;
        double kelilingAlas = this.keliling;
        return (2 * luasAlas) + (kelilingAlas * this.tinggiPrisma);
    }
    
    // Overloading untuk hitungLuasPermukaan dengan parameter
    public double hitungLuasPermukaan(double panjang, double lebar, double tinggiPrisma) {
        if (panjang <= 0 || lebar <= 0 || tinggiPrisma <= 0) {
            throw new IllegalArgumentException("Semua parameter harus bernilai positif.");
        }
        double luasAlas = panjang * lebar;
        double kelilingAlas = 2 * (panjang + lebar);
        return (2 * luasAlas) + (kelilingAlas * tinggiPrisma);
    }
}
