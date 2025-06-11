/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bendageometri.benda3d;
import bendageometri.benda2d.BelahKetupat;

/**
 *
 * @author nbnrc
 */
public class LimasBelahKetupat extends BelahKetupat { // Mewarisi BelahKetupat untuk alasnya
    private double tinggiLimas;
    private double volume; // Atribut private untuk menyimpan nilai volume
    private double luasPermukaan; // Atribut private untuk menyimpan nilai luas permukaan

    // Konstruktor utama
    public LimasBelahKetupat(double sisiAlas, double d1Alas, double d2Alas, double tinggiLimas) throws IllegalArgumentException, IllegalStateException {
        super(sisiAlas, d1Alas, d2Alas); // Validasi alas oleh BelahKetupat
        setTinggiLimas(tinggiLimas);

        // Hitung dan simpan volume serta luas permukaan saat objek dibuat
        this.volume = hitungVolume();
        this.luasPermukaan = hitungLuasPermukaan();
    }
    
    // Overloaded constructor: jika alas BelahKetupat didefinisikan hanya dengan dua diagonalnya
    public LimasBelahKetupat(double d1Alas, double d2Alas, double tinggiLimas) throws IllegalArgumentException, IllegalStateException {
        super(d1Alas, d2Alas); // Memanggil konstruktor BelahKetupat(d1, d2)
        setTinggiLimas(tinggiLimas);

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
    
    // Getter volume
    public double getVolume() {
        return volume;
    }
    
    // Getter luasPermukaan
    public double getLuasPermukaan() {
        return luasPermukaan;
    }

    @Override
    public double hitungVolume() {
        // Volume Limas = (1/3) * Luas Alas (dari BelahKetupat) * tinggiLimas
        double calculatedVolume = (1.0 / 3.0) * this.luas * this.tinggiLimas; // Menggunakan atribut luas dari superclass
        return calculatedVolume;
    }
    
    // Overloading untuk hitungVolume
    public double hitungVolume(double d1Alas, double d2Alas, double tinggiLimas) {
        if (d1Alas <= 0 || d2Alas <= 0 || tinggiLimas <= 0) {
            throw new IllegalArgumentException("Diagonal alas dan tinggi limas harus bernilai positif.");
        }
        // Memanfaatkan hitungLuas(d1, d2) dari superclass BelahKetupat
        double luasAlasUntukHitung = super.hitungLuas(d1Alas, d2Alas); //
        return (1.0 / 3.0) * luasAlasUntukHitung * tinggiLimas;
    }
    
    @Override
    public double hitungLuasPermukaan() {
        // Luas Permukaan Limas = Luas Alas + Luas Seluruh Sisi Tegak
        // Asumsi ini adalah limas belah ketupat tegak (puncak di atas pusat alas),
        // sehingga keempat sisi tegaknya adalah segitiga sama kaki yang identik.
        double luasAlas = this.luas; // Mengambil luas alas dari atribut public di BelahKetupat
        
        // Untuk menghitung tinggi segitiga sisi tegak (apotema limas):
        // Kita perlukan jarak dari pusat alas ke sisi alas (apotema alas/inradius).
        // Apotema alas belah ketupat (r_in) = Luas Alas / (0.5 * Keliling Alas)
        // r_in = (0.5 * d1 * d2) / (2 * sisi) = (d1 * d2) / (4 * sisi)
        double d1 = this.diagonal1;
        double d2 = this.diagonal2;
        double sisi = this.sisi; // Mengambil sisi dari atribut public di BelahKetupat
        
        if (sisi == 0) { // Menghindari pembagian dengan nol jika sisi (dihitung dari d1,d2) adalah 0
            if (luasAlas == 0) return 0; // Jika alasnya titik, luas permukaan = 0
            throw new IllegalStateException("Sisi alas belah ketupat adalah nol, tidak dapat menghitung apotema alas.");
        }
        double apotemaAlas = (d1 * d2) / (4 * sisi);

        // Tinggi segitiga sisi tegak (s_tegak) = sqrt(tinggiLimas^2 + apotemaAlas^2)
        double tinggiSegitigaSisiTegak = Math.sqrt(Math.pow(this.tinggiLimas, 2) + Math.pow(apotemaAlas, 2));

        if (Double.isNaN(tinggiSegitigaSisiTegak) || tinggiSegitigaSisiTegak < 0) {
            throw new IllegalStateException("Perhitungan tinggi segitiga sisi tegak menghasilkan nilai tidak valid.");
        }
        
        // Luas satu sisi tegak = 0.5 * sisiAlas * tinggiSegitigaSisiTegak
        double luasSatuSisiTegak = 0.5 * sisi * tinggiSegitigaSisiTegak;
        
        // Ada 4 sisi tegak yang identik
        double luasTotalSisiTegak = 4 * luasSatuSisiTegak;
        
        double calculatedLuasPermukaan = luasAlas + luasTotalSisiTegak;
        return calculatedLuasPermukaan;
    }

    // Overloading untuk hitungLuasPermukaan
    public double hitungLuasPermukaan(double d1Alas, double d2Alas, double tinggiLimas) throws IllegalStateException {
        if (d1Alas <= 0 || d2Alas <= 0 || tinggiLimas <= 0) {
            throw new IllegalArgumentException("Diagonal alas dan tinggi limas harus bernilai positif.");
        }
        
        // Luas Alas menggunakan metode overload dari BelahKetupat
        double luasAlasUntukHitung = super.hitungLuas(d1Alas, d2Alas); //

        // Untuk menghitung tinggi segitiga sisi tegak, kita perlukan sisi dari diagonal yang diberikan.
        double sisiDihitung = Math.sqrt(Math.pow(d1Alas / 2.0, 2) + Math.pow(d2Alas / 2.0, 2));
        if (Double.isNaN(sisiDihitung) || sisiDihitung <= 0) {
             throw new IllegalStateException("Perhitungan sisi dari diagonal menghasilkan nilai tidak valid.");
        }

        // Apotema alas belah ketupat (r_in) = (d1 * d2) / (4 * sisi)
        double apotemaAlas = (d1Alas * d2Alas) / (4 * sisiDihitung);
        if (Double.isNaN(apotemaAlas) || apotemaAlas < 0) { // Cek juga jika apotema negatif (tidak valid)
             throw new IllegalStateException("Perhitungan apotema alas menghasilkan nilai tidak valid.");
        }

        // Tinggi segitiga sisi tegak (s_tegak) = sqrt(tinggiLimas^2 + apotemaAlas^2)
        double tinggiSegitigaSisiTegak = Math.sqrt(Math.pow(tinggiLimas, 2) + Math.pow(apotemaAlas, 2));

        if (Double.isNaN(tinggiSegitigaSisiTegak) || tinggiSegitigaSisiTegak < 0) {
            throw new IllegalStateException("Perhitungan tinggi segitiga sisi tegak menghasilkan nilai tidak valid.");
        }
        
        // Luas satu sisi tegak = 0.5 * sisiDihitung * tinggiSegitigaSisiTegak
        double luasSatuSisiTegak = 0.5 * sisiDihitung * tinggiSegitigaSisiTegak;
        
        // Ada 4 sisi tegak yang identik
        double luasTotalSisiTegak = 4 * luasSatuSisiTegak;
        
        return luasAlasUntukHitung + luasTotalSisiTegak;
    }
}
