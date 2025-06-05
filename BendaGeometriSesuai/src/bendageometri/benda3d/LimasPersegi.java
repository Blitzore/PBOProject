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

    // Konstruktor utama
    public LimasPersegi(double sisiAlas, double tinggiLimas) {
        super(sisiAlas); // Memanggil konstruktor Persegi (sisiAlas menjadi immutable)
                         // Validasi sisiAlas ditangani oleh Persegi
        setTinggiLimas(tinggiLimas); // Menggunakan setter untuk validasi tinggiLimas
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

    // Getter untuk sisiAlas (yaitu getSisi()) diwarisi dari Persegi (immutable)

    @Override
    public double hitungVolume() {
        // Volume Limas = (1/3) * Luas Alas (dari Persegi) * tinggiLimas
        return (1.0 / 3.0) * super.hitungLuas() * this.tinggiLimas;
    }

    @Override
    public double hitungLuasPermukaan() {
        // Luas Permukaan Limas = Luas Alas + Luas Seluruh Sisi Tegak
        double luasAlas = super.hitungLuas();
        
        // Tinggi segitiga sisi tegak
        // s_tegak = sqrt(tinggiLimas^2 + (sisiAlas/2)^2)
        // getSisi() akan mengembalikan sisiAlas dari superclass Persegi
        double setengahSisiAlas = getSisi() / 2.0;
        double tinggiSegitigaSisiTegak = Math.sqrt(Math.pow(this.tinggiLimas, 2) + Math.pow(setengahSisiAlas, 2));
        if (Double.isNaN(tinggiSegitigaSisiTegak) || tinggiSegitigaSisiTegak < 0) {
            throw new IllegalStateException("Perhitungan tinggi segitiga sisi tegak menghasilkan nilai tidak valid.");
        }

        // Luas satu sisi tegak (segitiga) = 0.5 * alasSegitiga * tinggiSegitigaSisiTegak
        // alasSegitiga di sini adalah sisiAlas dari limas.
        double luasSatuSisiTegak = 0.5 * getSisi() * tinggiSegitigaSisiTegak;
        
        // Ada 4 sisi tegak yang identik pada limas persegi
        double luasTotalSisiTegak = 4 * luasSatuSisiTegak;
        
        return luasAlas + luasTotalSisiTegak;
    }
}
