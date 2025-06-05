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
    private double tinggiLimas; // Bisa mutable dengan setter

    // Konstruktor utama
    public LimasSegitiga(double alasSegitigaAlas, double tinggiAlasSegitiga, 
                         double sisiBAlas, double sisiCAlas, 
                         double tinggiLimas) {
        super(alasSegitigaAlas, tinggiAlasSegitiga, sisiBAlas, sisiCAlas);
        setTinggiLimas(tinggiLimas);
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

    @Override
    public double hitungVolume() {
        // Volume Limas = (1/3) * Luas Alas (dari Segitiga) * tinggiLimas
        return (1.0 / 3.0) * super.hitungLuas() * this.tinggiLimas;
    }

    /**
     * Menghitung luas permukaan limas segitiga.
     * Untuk limas segitiga umum, perhitungan akurat luas sisi tegak memerlukan
     * tinggi masing-masing sisi tegak. Metode ini, tanpa parameter tambahan,
     * akan mencoba menghitungnya untuk kasus limas beraturan dengan alas sama sisi
     * atau mengembalikan luas alas dengan peringatan.
     * Gunakan hitungLuasPermukaan(tst1, tst2, tst3) untuk perhitungan akurat
     * dengan menyediakan tinggi sisi tegak.
     */
    @Override
    public double hitungLuasPermukaan() {
        double luasAlas = super.hitungLuas();
        
        // Coba hitung untuk kasus limas beraturan dengan alas segitiga sama sisi
        if (getAlas() == getSisiB() && getSisiB() == getSisiC()) { // Cek jika alas sama sisi
            double sisiAlasDasar = getAlas();
            // Jarak dari titik pusat alas ke tengah sisi alas (apotema alas segitiga sama sisi)
            double apotemaAlas = (sisiAlasDasar * Math.sqrt(3)) / 6.0;
            double tinggiSisiTegak = Math.sqrt(Math.pow(this.tinggiLimas, 2) + Math.pow(apotemaAlas, 2));
            
            if (!Double.isNaN(tinggiSisiTegak) && tinggiSisiTegak > 0) {
                double luasSatuSisiTegak = 0.5 * sisiAlasDasar * tinggiSisiTegak;
                return luasAlas + (3 * luasSatuSisiTegak);
            }
        }
        
        // Jika bukan kasus di atas atau perhitungan gagal
        System.err.println("Peringatan: hitungLuasPermukaan() untuk LimasSegitiga ini mungkin tidak akurat karena merupakan kasus umum atau perhitungan tinggi sisi tegak gagal. Hanya luas alas yang dihitung atau estimasi kasar. Gunakan hitungLuasPermukaan(tst1, tst2, tst3) untuk hasil akurat.");
        return luasAlas; // Mengembalikan hanya luas alas sebagai fallback
    }

    /**
     * Menghitung luas permukaan limas segitiga dengan menyediakan tinggi masing-masing sisi tegak.
     * @param tinggiSisiTegakUntukAlas Segitiga tinggi sisi tegak yang alasnya adalah 'alas' dari Segitiga dasar.
     * @param tinggiSisiTegakUntukSisiB Tinggi sisi tegak yang alasnya adalah 'sisiB' dari Segitiga dasar.
     * @param tinggiSisiTegakUntukSisiC Tinggi sisi tegak yang alasnya adalah 'sisiC' dari Segitiga dasar.
     * @return Luas permukaan total limas segitiga.
     */
    public double hitungLuasPermukaan(double tinggiSisiTegakUntukAlas, 
                                     double tinggiSisiTegakUntukSisiB, 
                                     double tinggiSisiTegakUntukSisiC) {
        if (tinggiSisiTegakUntukAlas <= 0 || tinggiSisiTegakUntukSisiB <= 0 || tinggiSisiTegakUntukSisiC <= 0) {
            throw new IllegalArgumentException("Semua tinggi sisi tegak harus bernilai positif.");
        }
        // Tidak ada validasi geometris kompleks antara tinggi sisi tegak ini dengan tinggiLimas di sini,
        // diasumsikan pengguna memberikan nilai yang benar.

        double luasAlas = super.hitungLuas();
        // getAlas(), getSisiB(), getSisiC() adalah sisi-sisi dari alas segitiga
        double luasSisiTegak1 = 0.5 * getAlas() * tinggiSisiTegakUntukAlas;
        double luasSisiTegak2 = 0.5 * getSisiB() * tinggiSisiTegakUntukSisiB;
        double luasSisiTegak3 = 0.5 * getSisiC() * tinggiSisiTegakUntukSisiC;

        return luasAlas + luasSisiTegak1 + luasSisiTegak2 + luasSisiTegak3;
    }
}
