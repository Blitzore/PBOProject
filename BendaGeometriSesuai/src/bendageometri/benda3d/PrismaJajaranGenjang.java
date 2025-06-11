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
public class PrismaJajaranGenjang extends JajaranGenjang {
    private double volume;
    private double luasPermukaan;
    private double tinggiPrisma;
    
    // Konstruktor utama
    public PrismaJajaranGenjang(double alas, double sisiMiring, double tinggiJajarGenjang, double tinggiPrisma) throws IllegalArgumentException {
        super(alas, sisiMiring, tinggiJajarGenjang); // Memanggil konstruktor JajarGenjang
        
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
    public double hitungVolume(double alas, double tinggiJajarGenjang, double tinggiPrisma) {
        if (alas <= 0 || tinggiJajarGenjang <= 0 || tinggiPrisma <= 0) {
            throw new IllegalArgumentException("Semua parameter harus bernilai positif.");
        }
        double luasAlas = alas * tinggiJajarGenjang;
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
    public double hitungLuasPermukaan(double alas, double sisiMiring, double tinggiJajarGenjang, double tinggiPrisma) {
        if (alas <= 0 || sisiMiring <= 0 || tinggiJajarGenjang <= 0 || tinggiPrisma <= 0) {
            throw new IllegalArgumentException("Semua parameter harus bernilai positif.");
        }
        double luasAlas = alas * tinggiJajarGenjang;
        double kelilingAlas = 2 * (alas + sisiMiring);
        return (2 * luasAlas) + (kelilingAlas * tinggiPrisma);
    }
}
