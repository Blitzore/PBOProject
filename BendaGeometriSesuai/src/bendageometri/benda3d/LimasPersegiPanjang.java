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
public class LimasPersegiPanjang extends PersegiPanjang {
    private double volume;
    private double luasPermukaan;
    private double tinggiLimas;
    
    // Konstruktor utama
    public LimasPersegiPanjang(double panjang, double lebar, double tinggiLimas) throws IllegalArgumentException {
        super(panjang, lebar); // Memanggil konstruktor PersegiPanjang
        
        if (tinggiLimas <= 0) {
            throw new IllegalArgumentException("Tinggi limas harus bernilai positif.");
        }
        
        this.tinggiLimas = tinggiLimas;
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
    
    // Getter untuk tinggiLimas
    public double getTinggiLimas() {
        return tinggiLimas;
    }
    
    @Override
    public double hitungVolume() {
        // Volume Limas = (1/3) * Luas Alas * tinggiLimas
        return (1.0/3.0) * this.luas * this.tinggiLimas;
    }
    
    // Overloading untuk hitungVolume dengan parameter
    public double hitungVolume(double panjang, double lebar, double tinggiLimas) {
        if (panjang <= 0 || lebar <= 0 || tinggiLimas <= 0) {
            throw new IllegalArgumentException("Semua parameter harus bernilai positif.");
        }
        double luasAlas = panjang * lebar;
        return (1.0/3.0) * luasAlas * tinggiLimas;
    }
    
    @Override
    public double hitungLuasPermukaan() {
        // Luas Permukaan Limas = Luas Alas + Luas Seluruh Sisi Tegak
        double luasAlas = super.hitungLuas();
        
        // Ada dua pasang sisi tegak (segitiga) yang berbeda.
        // Pasangan pertama (2 segitiga) memiliki alas = panjangAlas (dari super.getPanjang())
        // Pasangan kedua (2 segitiga) memiliki alas = lebarAlas (dari super.getLebar())

        // Tinggi selimut (apotema) untuk segitiga dengan alas = panjangAlas
        // t_selimut1 = sqrt(tinggiLimas^2 + (lebarAlas/2)^2)
        double tinggiSelimut1 = Math.sqrt(Math.pow(this.tinggiLimas, 2) + Math.pow(super.lebar / 2.0, 2));
        if (Double.isNaN(tinggiSelimut1) || tinggiSelimut1 < 0) {
             throw new IllegalStateException("Perhitungan tinggi selimut 1 menghasilkan nilai tidak valid.");
        }
        double luasDuaSisiTegak1 = 2 * (0.5 * super.panjang * tinggiSelimut1); // atau getPanjang() * tinggiSelimut1

        // Tinggi selimut (apotema) untuk segitiga dengan alas = lebarAlas
        // t_selimut2 = sqrt(tinggiLimas^2 + (panjangAlas/2)^2)
        double tinggiSelimut2 = Math.sqrt(Math.pow(this.tinggiLimas, 2) + Math.pow(super.panjang / 2.0, 2));
        if (Double.isNaN(tinggiSelimut2) || tinggiSelimut2 < 0) {
             throw new IllegalStateException("Perhitungan tinggi selimut 2 menghasilkan nilai tidak valid.");
        }
        double luasDuaSisiTegak2 = 2 * (0.5 * super.lebar * tinggiSelimut2); // atau getLebar() * tinggiSelimut2
        
        return luasAlas + luasDuaSisiTegak1 + luasDuaSisiTegak2;
    }
}
