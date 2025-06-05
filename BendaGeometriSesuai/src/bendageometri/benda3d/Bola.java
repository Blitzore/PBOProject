/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bendageometri.benda3d;
import bendageometri.benda2d.Lingkaran;

/**
 *
 * @author nbnrc
 */
public class Bola extends Lingkaran { // Mewarisi Lingkaran untuk properti jari-jari
    double volume;
    double luasPermukaan;
    // Konstruktor utama
    public Bola(double jariJari) {
        super(jariJari); // Memanggil konstruktor Lingkaran(double jariJari)
                         // Validasi jari-jari (harus positif) ditangani oleh konstruktor Lingkaran
    }
    
    @Override
    public double hitungVolume() {
        // Volume Bola = (4/3) * PI * r^3
        volume = (4.0 / 3.0) * super.luas * super.jariJari;
        return volume;
    }
    
    public double hitungVolume(double jari) {
        // Volume Bola = (4/3) * PI * r^3
        volume = (4.0 / 3.0) * super.hitungLuas(jari) * jari;
        return volume;
    }

    @Override
    public double hitungLuasPermukaan() {
        // Luas Permukaan Bola = 4 * PI * r^2
        luasPermukaan = 4 * super.luas;
        return luasPermukaan;
    }
    
    public double hitungLuasPermukaan(double jari) {
        // Luas Permukaan Bola = 4 * PI * r^2
        luasPermukaan = 4 * super.hitungLuas(jari);
        return luasPermukaan;
    }
}
