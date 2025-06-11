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
public class LimasJajaranGenjang extends JajaranGenjang {
    private double volume;
    private double luasPermukaan;
    private double tinggiLimas;
    
    // Konstruktor utama
    public LimasJajaranGenjang(double alas, double sisiMiring, double tinggiJajarGenjang, double tinggiLimas) throws IllegalArgumentException {
        super(alas, sisiMiring, tinggiJajarGenjang); // Memanggil konstruktor JajarGenjang
        
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
    public double hitungVolume(double alas, double tinggiJajarGenjang, double tinggiLimas) {
        if (alas <= 0 || tinggiJajarGenjang <= 0 || tinggiLimas <= 0) {
            throw new IllegalArgumentException("Semua parameter harus bernilai positif.");
        }
        double luasAlas = alas * tinggiJajarGenjang;
        return (1.0/3.0) * luasAlas * tinggiLimas;
    }
    
    @Override
    public double hitungLuasPermukaan() {
        // Luas Permukaan = Luas Alas + Luas Sisi Tegak
        // Untuk limas, perhitungan luas sisi tegak disederhanakan
        double luasAlas = this.luas;
        double luasSisiTegak = this.keliling * this.tinggiLimas * 0.5;
        return luasAlas + luasSisiTegak;
    }
    
    // Overloading untuk hitungLuasPermukaan dengan parameter
    public double hitungLuasPermukaan(double alas, double sisiMiring, double tinggiJajarGenjang, double tinggiLimas) {
        if (alas <= 0 || sisiMiring <= 0 || tinggiJajarGenjang <= 0 || tinggiLimas <= 0) {
            throw new IllegalArgumentException("Semua parameter harus bernilai positif.");
        }
        double luasAlas = alas * tinggiJajarGenjang;
        double kelilingAlas = 2 * (alas + sisiMiring);
        double luasSisiTegak = kelilingAlas * tinggiLimas * 0.5;
        return luasAlas + luasSisiTegak;
    }
}