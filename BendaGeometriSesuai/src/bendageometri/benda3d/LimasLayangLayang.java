/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bendageometri.benda3d;

import bendageometri.benda2d.LayangLayang;

/**
 *
 * @author nbnrc
 */
public class LimasLayangLayang extends LayangLayang { // Mewarisi LayangLayang untuk alasnya

    public static class InvalidLimasException extends IllegalArgumentException {

        public InvalidLimasException(String message) {
            super(message);
        }
    }

    private double tinggiLimas; // Bisa mutable dengan setter

    // Konstruktor utama
    public LimasLayangLayang(double d1Alas, double d2Alas,
            double sisiAAlas, double sisiBAlas,
            double tinggiLimas) {
        super(d1Alas, d2Alas, sisiAAlas, sisiBAlas);
        try {
            setTinggiLimas(tinggiLimas);
        } catch (IllegalArgumentException e) {
            throw new InvalidLimasException("Tinggi limas tidak valid: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Gagal membuat limas layang-layang", e);
        }
    }

    // Overloaded constructor: jika alas LayangLayang didefinisikan dengan cara alternatif
    public LimasLayangLayang(double sisiPendekAlas, double sisiPanjangAlas,
            double diagonalPenghubungAlas, double tinggiLimas) {
        super(sisiPendekAlas, sisiPanjangAlas, diagonalPenghubungAlas);
        try {
            setTinggiLimas(tinggiLimas);
        } catch (IllegalArgumentException e) {
            throw new InvalidLimasException("Tinggi limas tidak valid: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Gagal membuat limas layang-layang", e);
        }
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

    // Getter untuk properti alas (getDiagonal1, getDiagonal2, getSisiA, getSisiB)
    // diwarisi dari LayangLayang (immutable)
    @Override
    public double hitungVolume() {
        try {
            return (1.0 / 3.0) * super.hitungLuas() * this.tinggiLimas;
        } catch (Exception e) {
            throw new RuntimeException("Gagal menghitung volume limas layang-layang", e);
        }
    }

    /**
     * Menghitung luas permukaan limas layang-layang. Untuk limas layang-layang
     * umum, perhitungan akurat luas sisi tegak memerlukan tinggi masing-masing
     * sisi tegak. Metode ini, tanpa parameter tambahan, akan mengembalikan luas
     * alas dengan peringatan. Gunakan hitungLuasPermukaan(tinggiSisiTegakA,
     * tinggiSisiTegakB) untuk perhitungan akurat dengan menyediakan tinggi sisi
     * tegak. (Diasumsikan ada dua pasang sisi tegak yang identik jika limasnya
     * tegak).
     */
    @Override
    public double hitungLuasPermukaan() {
        try {
            double luasAlas = super.hitungLuas();
            System.err.println("Peringatan: hitungLuasPermukaan() untuk LimasLayangLayang ini mungkin tidak akurat karena merupakan kasus umum. Hanya luas alas yang dihitung. Gunakan metode hitungLuasPermukaan() dengan parameter tinggi sisi tegak untuk hasil akurat.");
            return luasAlas;
        } catch (Exception e) {
            throw new RuntimeException("Gagal menghitung luas permukaan limas layang-layang", e);
        }
    }

    /**
     * Menghitung luas permukaan limas layang-layang dengan menyediakan tinggi
     * sisi tegak. Layang-layang memiliki dua pasang sisi yang sama panjang
     * (sisiA dan sisiB). Jika limasnya tegak, maka akan ada dua pasang sisi
     * tegak (segitiga) yang identik.
     *
     * @param tinggiSisiTegakUntukSisiA Tinggi sisi tegak yang alasnya adalah
     * 'sisiA' dari LayangLayang dasar.
     * @param tinggiSisiTegakUntukSisiB Tinggi sisi tegak yang alasnya adalah
     * 'sisiB' dari LayangLayang dasar.
     * @return Luas permukaan total limas layang-layang.
     */
    public double hitungLuasPermukaan(double tinggiSisiTegakUntukSisiA,
            double tinggiSisiTegakUntukSisiB) {
        try {
            if (tinggiSisiTegakUntukSisiA <= 0 || tinggiSisiTegakUntukSisiB <= 0) {
                throw new IllegalArgumentException("Semua tinggi sisi tegak harus bernilai positif.");
            }

            double luasAlas = super.hitungLuas();
            double luasTotalSisiTegak = 2 * (0.5 * getSisiA() * tinggiSisiTegakUntukSisiA)
                    + 2 * (0.5 * getSisiB() * tinggiSisiTegakUntukSisiB);

            return luasAlas + luasTotalSisiTegak;
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Gagal menghitung luas permukaan limas layang-layang dengan parameter", e);
        }
    }
}
