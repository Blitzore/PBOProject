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
public class LimasPersegiPanjang extends PersegiPanjang { // Mewarisi PersegiPanjang untuk alasnya
    private double tinggiLimas; // Bisa mutable dengan setter

    // Konstruktor utama
    public LimasPersegiPanjang(double panjangAlas, double lebarAlas, double tinggiLimas) {
        super(panjangAlas, lebarAlas); // Memanggil konstruktor PersegiPanjang
                                       // Dimensi alas (panjangAlas, lebarAlas) menjadi immutable
                                       // Validasi panjangAlas dan lebarAlas ditangani oleh PersegiPanjang
        setTinggiLimas(tinggiLimas);   // Menggunakan setter untuk validasi tinggiLimas
    }
    
    // Getter untuk tinggiLimas
    public double getTinggiLimas() {
        return tinggiLimas;
    }

    // Setter untuk tinggiLimas dengan validasi
    public void setTinggiLimas(double tinggiLimas) {
        if (tinggiLimas <= 0) {
            throw new IllegalArgumentException("Tinggi limas harus bernilai positif.");
        }
        this.tinggiLimas = tinggiLimas;
    }

    // Getter untuk panjangAlas (yaitu getPanjang()) dan lebarAlas (yaitu getLebar())
    // diwarisi dari PersegiPanjang (immutable)

    @Override
    public double hitungVolume() {
        // Volume Limas = (1/3) * Luas Alas (dari PersegiPanjang) * tinggiLimas
        return (1.0 / 3.0) * super.hitungLuas() * this.tinggiLimas;
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
        double tinggiSelimut1 = Math.sqrt(Math.pow(this.tinggiLimas, 2) + Math.pow(getLebar() / 2.0, 2));
        if (Double.isNaN(tinggiSelimut1) || tinggiSelimut1 < 0) {
             throw new IllegalStateException("Perhitungan tinggi selimut 1 menghasilkan nilai tidak valid.");
        }
        double luasDuaSisiTegak1 = 2 * (0.5 * getPanjang() * tinggiSelimut1); // atau getPanjang() * tinggiSelimut1

        // Tinggi selimut (apotema) untuk segitiga dengan alas = lebarAlas
        // t_selimut2 = sqrt(tinggiLimas^2 + (panjangAlas/2)^2)
        double tinggiSelimut2 = Math.sqrt(Math.pow(this.tinggiLimas, 2) + Math.pow(getPanjang() / 2.0, 2));
        if (Double.isNaN(tinggiSelimut2) || tinggiSelimut2 < 0) {
             throw new IllegalStateException("Perhitungan tinggi selimut 2 menghasilkan nilai tidak valid.");
        }
        double luasDuaSisiTegak2 = 2 * (0.5 * getLebar() * tinggiSelimut2); // atau getLebar() * tinggiSelimut2
        
        return luasAlas + luasDuaSisiTegak1 + luasDuaSisiTegak2;
    }
}
