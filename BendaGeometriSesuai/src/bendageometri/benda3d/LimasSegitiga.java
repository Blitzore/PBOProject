/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bendageometri.benda3d;
import bendageometri.benda2d.Segitiga;

/**
 *
 * @author nbnrc
 */
public class LimasSegitiga extends Segitiga { // Mewarisi Segitiga untuk alasnya
    private double tinggiLimas; //
    private double volume; // Atribut untuk menyimpan nilai volume
    private double luasPermukaan; // Atribut untuk menyimpan nilai luas permukaan

    // Konstruktor utama
    public LimasSegitiga(double alasSegitigaAlas, double tinggiAlasSegitiga, 
                         double sisiBAlas, double sisiCAlas, 
                         double tinggiLimas) throws IllegalArgumentException { // Menambahkan throws
        super(alasSegitigaAlas, tinggiAlasSegitiga, sisiBAlas, sisiCAlas); //
        setTinggiLimas(tinggiLimas); //

        // Hitung dan simpan volume serta luas permukaan saat objek dibuat
        this.volume = hitungVolume();
        this.luasPermukaan = hitungLuasPermukaan();
    }

    // Getter untuk tinggiLimas
    public double getTinggiLimas() { //
        return tinggiLimas; //
    }

    // Setter untuk tinggiLimas dengan validasi
    public void setTinggiLimas(double tinggiLimas) { //
        if (tinggiLimas <= 0) { //
            throw new IllegalArgumentException("Tinggi limas harus bernilai positif."); //
        }
        this.tinggiLimas = tinggiLimas; //
    }

    // Getter untuk volume
    public double getVolume() {
        return volume;
    }

    // Getter untuk luasPermukaan
    public double getLuasPermukaan() {
        return luasPermukaan;
    }

    @Override //
    public double hitungVolume() { //
        // Volume Limas = (1/3) * Luas Alas (dari Segitiga) * tinggiLimas
        volume = (1.0 / 3.0) * this.luas * this.tinggiLimas; // Menggunakan atribut luas dari superclass
        return volume;
    }

    // Overloading untuk hitungVolume dengan parameter alas, tinggi alas, dan tinggi limas
    public double hitungVolume(double alasSegitiga, double tinggiAlasSegitiga, double tinggiLimas) {
        if (alasSegitiga <= 0 || tinggiAlasSegitiga <= 0 || tinggiLimas <= 0) {
            throw new IllegalArgumentException("Alas segitiga, tinggi alas, dan tinggi limas harus bernilai positif.");
        }
        // Memanfaatkan hitungLuas(double a, double t) dari superclass Segitiga
        double luasAlasUntukHitung = super.hitungLuas(alasSegitiga, tinggiAlasSegitiga); //
        return (1.0 / 3.0) * luasAlasUntukHitung * tinggiLimas;
    }

    /**
     * Menghitung luas permukaan limas segitiga.
     * Untuk limas segitiga umum, perhitungan akurat luas sisi tegak memerlukan
     * tinggi masing-masing sisi tegak. Metode ini, tanpa parameter tambahan,
     * akan mencoba menghitungnya untuk kasus limas beraturan dengan alas sama sisi
     * atau mengembalikan luas alas dengan peringatan.
     */
    @Override
    public double hitungLuasPermukaan() {
        double currentLuasAlas = this.luas;
        
        // Periksa jika alas sama sisi (kasus eksak)
        if (this.alas == this.sisiB && this.sisiB == this.sisiC) {
            double sisiAlasDasar = this.alas;
            double apotemaAlas = (sisiAlasDasar * Math.sqrt(3)) / 6.0;
            double tinggiSisiTegak = Math.sqrt(Math.pow(this.tinggiLimas, 2) + Math.pow(apotemaAlas, 2));
            
            if (!Double.isNaN(tinggiSisiTegak) && tinggiSisiTegak > 0) {
                double luasSatuSisiTegak = 0.5 * sisiAlasDasar * tinggiSisiTegak;
                luasPermukaan = currentLuasAlas + (3 * luasSatuSisiTegak);
                return luasPermukaan;
            }
        }
        
        // Jika bukan kasus di atas atau perhitungan eksak gagal, berikan aproksimasi
        // Pendekatan aproksimasi kasar: Luas Selimut = 0.5 * Keliling Alas * Tinggi Limas (ini sangat kasar, asumsi seperti kerucut)
        // Atau sedikit lebih baik, dengan estimasi tinggi sisi tegak rata-rata
        
        // Estimasi tinggi sisi tegak rata-rata untuk aproksimasi (menggunakan rata-rata sisi alas sebagai basis)
        double kelilingAlas = this.keliling;
        double rataRataSisiAlas = kelilingAlas / 3.0; // Rata-rata panjang sisi alas
        
        // Menggunakan rata-rata proyeksi apotema alas untuk estimasi tinggi sisi tegak efektif
        // Jika diasumsikan limas tegak, dan alasnya di pusat.
        // Kita bisa menggunakan rata-rata apotema jika bisa dihitung, atau lebih sederhana,
        // menggunakan rata-rata sisi alas / 2 sebagai proyeksi.
        double estimasiApotemaAlasUntukLP = rataRataSisiAlas / 2.0; // Ini adalah aproksimasi kasar untuk alas sembarang
        
        double estimasiTinggiSisiTegak = Math.sqrt(Math.pow(this.tinggiLimas, 2) + Math.pow(estimasiApotemaAlasUntukLP, 2));
        
        if (Double.isNaN(estimasiTinggiSisiTegak) || estimasiTinggiSisiTegak <= 0) {
            // Fallback jika estimasi tinggi sisi tegak tidak valid
            System.err.println("Peringatan: hitungLuasPermukaan() untuk LimasSegitiga umum (objek) mengembalikan luas alas saja karena estimasi tinggi sisi tegak tidak valid.");
            luasPermukaan = currentLuasAlas;
            return luasPermukaan;
        }
        
        double luasSelimutAproksimasi = 0.5 * kelilingAlas * estimasiTinggiSisiTegak;
        luasPermukaan = currentLuasAlas + luasSelimutAproksimasi;
        System.err.println("Peringatan: hitungLuasPermukaan() untuk LimasSegitiga umum (objek) mengembalikan nilai aproksimasi.");
        return luasPermukaan;
    }

    // Overloading
    /**
     * Menghitung luas permukaan limas segitiga dengan menyediakan parameter alas segitiga,
     * tinggi alas, sisi B, sisi C, dan tinggi limas.
     * Akan memberikan nilai eksak jika alas sama sisi, atau aproksimasi jika tidak.
     */
    public double hitungLuasPermukaan(double alasSegitiga, double tinggiAlasSegitiga, 
                                     double sisiBAlas, double sisiCAlas, double tinggiLimas) {
        if (alasSegitiga <= 0 || tinggiAlasSegitiga <= 0 || sisiBAlas <= 0 || sisiCAlas <= 0 || tinggiLimas <= 0) {
            throw new IllegalArgumentException("Semua dimensi untuk perhitungan luas permukaan harus bernilai positif.");
        }
        
        // Buat objek Segitiga sementara untuk validasi dan perhitungan alas/keliling
        Segitiga tempSegitiga = new Segitiga(alasSegitiga, tinggiAlasSegitiga, sisiBAlas, sisiCAlas);
        
        double luasAlasUntukHitung = tempSegitiga.luas; // Menggunakan atribut luas dari Segitiga
        double kelilingAlasUntukHitung = tempSegitiga.keliling; // Menggunakan atribut keliling dari Segitiga

        // Kasus eksak: jika alas sama sisi
        if (alasSegitiga == sisiBAlas && sisiBAlas == sisiCAlas) {
            double sisiAlasDasar = alasSegitiga;
            double apotemaAlas = (sisiAlasDasar * Math.sqrt(3)) / 6.0;
            double tinggiSisiTegak = Math.sqrt(Math.pow(tinggiLimas, 2) + Math.pow(apotemaAlas, 2));
            if (Double.isNaN(tinggiSisiTegak) || tinggiSisiTegak < 0) {
                 throw new IllegalStateException("Perhitungan tinggi sisi tegak menghasilkan nilai tidak valid.");
            }
            double luasSatuSisiTegak = 0.5 * sisiAlasDasar * tinggiSisiTegak;
            return luasAlasUntukHitung + (3 * luasSatuSisiTegak);
        } else {
            // Kasus aproksimasi: alas bukan sama sisi
            // Estimasi tinggi sisi tegak rata-rata untuk aproksimasi
            double rataRataSisiAlas = kelilingAlasUntukHitung / 3.0;
            double estimasiApotemaAlasUntukLP = rataRataSisiAlas / 2.0;
            
            double estimasiTinggiSisiTegak = Math.sqrt(Math.pow(tinggiLimas, 2) + Math.pow(estimasiApotemaAlasUntukLP, 2));
            
            if (Double.isNaN(estimasiTinggiSisiTegak) || estimasiTinggiSisiTegak <= 0) {
                System.err.println("Peringatan: hitungLuasPermukaan() overload untuk LimasSegitiga umum mengembalikan luas alas saja karena estimasi tinggi sisi tegak tidak valid.");
                return luasAlasUntukHitung;
            }
            
            double luasSelimutAproksimasi = 0.5 * kelilingAlasUntukHitung * estimasiTinggiSisiTegak;
            System.err.println("Peringatan: hitungLuasPermukaan() overload untuk LimasSegitiga umum mengembalikan nilai aproksimasi.");
            return luasAlasUntukHitung + luasSelimutAproksimasi;
        }
    }
}