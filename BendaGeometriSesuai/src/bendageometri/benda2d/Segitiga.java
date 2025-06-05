/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bendageometri.benda2d;

/**
 *
 * @author nbnrc
 */
public class Segitiga extends Benda2D {
    private double alas;    // Salah satu sisi segitiga, digunakan juga untuk keliling
    private double tinggi;  // Tinggi relatif terhadap 'alas' untuk perhitungan luas
    private double sisiB;   // Sisi kedua untuk keliling
    private double sisiC;   // Sisi ketiga untuk keliling

    // Konstruktor utama
    public Segitiga(double alas, double tinggi, double sisiB, double sisiC) {
        // Validasi nilai positif untuk semua dimensi
        if (alas <= 0 || tinggi <= 0 || sisiB <= 0 || sisiC <= 0) {
            throw new IllegalArgumentException("Dimensi alas, tinggi, sisiB, dan sisiC segitiga harus bernilai positif.");
        }

        // Memeriksa apakah alas, sisiB, dan sisiC dapat membentuk segitiga
        if (!cekValiditasSisiSegitiga(alas, sisiB, sisiC)) {
            throw new IllegalArgumentException("Sisi-sisi yang diberikan (alas, sisiB, sisiC) tidak membentuk segitiga yang valid.");
        }

        this.alas = alas;
        this.tinggi = tinggi;
        this.sisiB = sisiB;
        this.sisiC = sisiC;
    }

    // Metode privat untuk validasi ketaksamaan sisi segitiga
    // Menggunakan parameter alas, sisiB, sisiC
    private boolean cekValiditasSisiSegitiga(double side1, double side2, double side3) {
        return (side1 + side2 > side3) &&
               (side1 + side3 > side2) &&
               (side2 + side3 > side1);
    }

    // Getter
    public double getAlas() {
        return alas;
    }

    public double getTinggi() {
        return tinggi;
    }

    // getSisiA() sekarang secara efektif adalah getAlas() jika merujuk pada sisi pertama.
    
    public double getSisiB() {
        return sisiB;
    }

    public double getSisiC() {
        return sisiC;
    }

    @Override
    public double hitungLuas() {
        // Luas dihitung berdasarkan field alas dan tinggi yang disimpan.
        return 0.5 * this.alas * this.tinggi;
    }

    @Override
    public double hitungKeliling() {
        // Keliling dihitung menggunakan alas (sebagai sisi pertama), sisiB, dan sisiC
        return this.alas + this.sisiB + this.sisiC;
    }
}
