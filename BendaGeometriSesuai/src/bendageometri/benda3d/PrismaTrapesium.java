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
public class PrismaTrapesium extends Trapesium {
    private double volume;
    private double luasPermukaan;
    private double tinggiPrisma;
    
    // Konstruktor utama
    public PrismaTrapesium(double sisiAtas, double sisiBawah, double tinggiTrapesium, double sisiMiring1, double sisiMiring2, double tinggiPrisma) throws IllegalArgumentException {
        super(sisiAtas, sisiBawah, tinggiTrapesium, sisiMiring1, sisiMiring2); // Memanggil konstruktor Trapesium
        
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
    public double hitungVolume(double sisiAtas, double sisiBawah, double tinggiTrapesium, double tinggiPrisma) {
        if (sisiAtas <= 0 || sisiBawah <= 0 || tinggiTrapesium <= 0 || tinggiPrisma <= 0) {
            throw new IllegalArgumentException("Semua parameter harus bernilai positif.");
        }
        double luasAlas = 0.5 * (sisiAtas + sisiBawah) * tinggiTrapesium;
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
    public double hitungLuasPermukaan(double sisiAtas, double sisiBawah, double tinggiTrapesium, double sisiMiring1, double sisiMiring2, double tinggiPrisma) {
        if (sisiAtas <= 0 || sisiBawah <= 0 || tinggiTrapesium <= 0 || sisiMiring1 <= 0 || sisiMiring2 <= 0 || tinggiPrisma <= 0) {
            throw new IllegalArgumentException("Semua parameter harus bernilai positif.");
        }
        double luasAlas = 0.5 * (sisiAtas + sisiBawah) * tinggiTrapesium;
        double kelilingAlas = sisiAtas + sisiBawah + sisiMiring1 + sisiMiring2;
        return (2 * luasAlas) + (kelilingAlas * tinggiPrisma);
    }
}
