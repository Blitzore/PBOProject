/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bendageometri.benda3d;
import bendageometri.benda2d.Persegi;

/**
 *
 * @author nbnrc
 */
public class LimasPersegi extends Persegi {
    private double tinggiLimas;
    private double volume; // Atribut baru untuk menyimpan nilai volume
    private double luasPermukaan; // Atribut baru untuk menyimpan nilai luas permukaan

    // Konstruktor utama
    public LimasPersegi(double sisiAlas, double tinggiLimas) throws IllegalArgumentException { // Menambahkan throws
        super(sisiAlas); // Memanggil konstruktor Persegi (sisiAlas menjadi immutable)
                         // Validasi sisiAlas ditangani oleh Persegi
        setTinggiLimas(tinggiLimas); // Menggunakan setter untuk validasi tinggiLimas

        // Hitung dan simpan volume serta luas permukaan saat objek dibuat
        this.volume = hitungVolume();
        this.luasPermukaan = hitungLuasPermukaan();
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

    // Getter untuk volume
    public double getVolume() {
        return volume;
    }

    // Getter untuk luasPermukaan
    public double getLuasPermukaan() {
        return luasPermukaan;
    }

    @Override
    public double hitungVolume() {
        // Volume Limas = (1/3) * Luas Alas (dari Persegi) * tinggiLimas
        volume = (1.0 / 3.0) * this.luas * this.tinggiLimas; // Menggunakan atribut luas dari superclass
        return volume;
    }

    // Overloading untuk hitungVolume dengan parameter sisi 's' dan tinggi 't'
    public double hitungVolume(double s, double t) {
        if (s <= 0 || t <= 0) {
            throw new IllegalArgumentException("Sisi alas dan tinggi limas harus bernilai positif.");
        }
        // Memanfaatkan hitungLuas(s) dari superclass Persegi untuk luas alas
        double luasAlasUntukS = super.hitungLuas(s); //
        return (1.0 / 3.0) * luasAlasUntukS * t;
    }

    @Override
    public double hitungLuasPermukaan() {
        // Luas Permukaan Limas = Luas Alas + Luas Seluruh Sisi Tegak
        double luasAlas = this.luas; // Mengambil luas alas dari atribut public di Persegi
        
        // Tinggi segitiga sisi tegak (apotema limas)
        // s_tegak = sqrt(tinggiLimas^2 + (sisiAlas/2)^2)
        double setengahSisiAlas = this.sisi / 2.0; // Menggunakan sisi dari atribut Persegi
        double tinggiSegitigaSisiTegak = Math.sqrt(Math.pow(this.tinggiLimas, 2) + Math.pow(setengahSisiAlas, 2));
        if (Double.isNaN(tinggiSegitigaSisiTegak) || tinggiSegitigaSisiTegak < 0) {
            throw new IllegalStateException("Perhitungan tinggi segitiga sisi tegak menghasilkan nilai tidak valid.");
        }

        // Luas satu sisi tegak (segitiga) = 0.5 * alasSegitiga * tinggiSegitigaSisiTegak
        double luasSatuSisiTegak = 0.5 * this.sisi * tinggiSegitigaSisiTegak; // Menggunakan sisi dari atribut Persegi
        
        // Ada 4 sisi tegak yang identik pada limas persegi
        double luasTotalSisiTegak = 4 * luasSatuSisiTegak;
        
        luasPermukaan = luasAlas + luasTotalSisiTegak; // Simpan juga ke atribut luasPermukaan
        return luasPermukaan;
    }

    // Overloading untuk hitungLuasPermukaan dengan parameter sisi 's' dan tinggi 't'
    public double hitungLuasPermukaan(double s, double t) {
        if (s <= 0 || t <= 0) {
            throw new IllegalArgumentException("Sisi alas dan tinggi limas harus bernilai positif.");
        }
        
        // Luas Alas menggunakan metode overload dari Persegi
        double luasAlasUntukS = super.hitungLuas(s); //

        // Tinggi segitiga sisi tegak (apotema limas) untuk sisi 's' dan tinggi 't'
        double setengahS = s / 2.0;
        double tinggiSegitigaSisiTegak = Math.sqrt(Math.pow(t, 2) + Math.pow(setengahS, 2));
        if (Double.isNaN(tinggiSegitigaSisiTegak) || tinggiSegitigaSisiTegak < 0) {
            throw new IllegalStateException("Perhitungan tinggi segitiga sisi tegak menghasilkan nilai tidak valid untuk parameter yang diberikan.");
        }

        // Luas satu sisi tegak
        double luasSatuSisiTegak = 0.5 * s * tinggiSegitigaSisiTegak;
        
        // Ada 4 sisi tegak yang identik pada limas persegi
        double luasTotalSisiTegak = 4 * luasSatuSisiTegak;
        
        return luasAlasUntukS + luasTotalSisiTegak;
    }
}
