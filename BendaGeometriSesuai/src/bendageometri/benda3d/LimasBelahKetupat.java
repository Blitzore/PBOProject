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
    private double tinggiLimas; // Bisa mutable dengan setter

    // Konstruktor utama
    public LimasBelahKetupat(double sisiAlas, double d1Alas, double d2Alas, double tinggiLimas) {
        super(sisiAlas, d1Alas, d2Alas); // Validasi alas oleh BelahKetupat
        setTinggiLimas(tinggiLimas);
    }
    
    // Overloaded constructor: jika alas BelahKetupat didefinisikan hanya dengan dua diagonalnya
    public LimasBelahKetupat(double d1Alas, double d2Alas, double tinggiLimas) {
        super(d1Alas, d2Alas); // Memanggil konstruktor BelahKetupat(d1, d2)
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

    // Getter untuk properti alas (getSisi(), getDiagonal1(), getDiagonal2())
    // diwarisi dari BelahKetupat (immutable)

    @Override
    public double hitungVolume() {
        // Volume Limas = (1/3) * Luas Alas (dari BelahKetupat) * tinggiLimas
        return (1.0 / 3.0) * super.hitungLuas() * this.tinggiLimas;
    }

    @Override
    public double hitungLuasPermukaan() {
        // Luas Permukaan Limas = Luas Alas + Luas Seluruh Sisi Tegak
        // Asumsi ini adalah limas belah ketupat tegak (puncak di atas pusat alas),
        // sehingga keempat sisi tegaknya adalah segitiga sama kaki yang identik.
        double luasAlas = super.hitungLuas();
        
        // Untuk menghitung tinggi segitiga sisi tegak (apotema limas):
        // Kita perlukan jarak dari pusat alas ke sisi alas (apotema alas/inradius).
        // Apotema alas belah ketupat (r_in) = Luas Alas / (0.5 * Keliling Alas)
        // r_in = (0.5 * d1 * d2) / (2 * sisi) = (d1 * d2) / (4 * sisi)
        // (Catatan: Sisi di sini adalah getSisi() dari BelahKetupat)
        double d1 = getDiagonal1();
        double d2 = getDiagonal2();
        double sisi = getSisi();
        
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
        
        return luasAlas + luasTotalSisiTegak;
    }
}
