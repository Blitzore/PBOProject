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
public class PrismaSegitiga extends Segitiga { // Mewarisi Segitiga untuk alasnya
    private double tinggiPrisma;
    private double volume; // Atribut untuk menyimpan nilai volume
    private double luasPermukaan; // Atribut untuk menyimpan nilai luas permukaan

    // Konstruktor utama
    public PrismaSegitiga(double alasSegitiga, double tinggiAlasSegitiga, 
                          double sisiBAlas, double sisiCAlas, 
                          double tinggiPrisma) throws IllegalArgumentException { // Menambahkan throws
        // Memanggil konstruktor Segitiga: public Segitiga(double alas, double tinggi, double sisiB, double sisiC)
        super(alasSegitiga, tinggiAlasSegitiga, sisiBAlas, sisiCAlas); 
        // Validasi alasSegitiga, tinggiAlasSegitiga, sisiBAlas, sisiCAlas ditangani oleh Segitiga
        setTinggiPrisma(tinggiPrisma); // Menggunakan setter untuk validasi tinggiPrisma

        // Hitung dan simpan volume serta luas permukaan saat objek dibuat
        this.volume = hitungVolume();
        this.luasPermukaan = hitungLuasPermukaan();
    }
    

    // Getter untuk tinggiPrisma
    public double getTinggiPrisma() {
        return tinggiPrisma;
    }

    // Setter untuk tinggiPrisma dengan validasi
    public void setTinggiPrisma(double tinggiPrisma) {
        if (tinggiPrisma <= 0) {
            throw new IllegalArgumentException("Tinggi prisma harus bernilai positif.");
        }
        this.tinggiPrisma = tinggiPrisma;
    }

    // Getter untuk volume
    public double getVolume() {
        return volume;
    }

    // Getter untuk luasPermukaan
    public double getLuasPermukaan() {
        return luasPermukaan;
    }

    public double hitungVolume() {
        // Volume Prisma = Luas Alas (dari Segitiga) * tinggiPrisma
        volume = this.luas * this.tinggiPrisma; // Menggunakan atribut luas dari superclass Segitiga
        return volume;
    }

    // Overloading untuk hitungVolume dengan parameter alas, tinggi alas, dan tinggi prisma
    public double hitungVolume(double alasSegitiga, double tinggiAlasSegitiga, double tinggiPrisma) {
        if (alasSegitiga <= 0 || tinggiAlasSegitiga <= 0 || tinggiPrisma <= 0) {
            throw new IllegalArgumentException("Alas segitiga, tinggi alas, dan tinggi prisma harus bernilai positif.");
        }
        // Memanfaatkan hitungLuas(double a, double t) dari superclass Segitiga
        double luasAlasUntukHitung = super.hitungLuas(alasSegitiga, tinggiAlasSegitiga); //
        return luasAlasUntukHitung * tinggiPrisma;
    }

    @Override
    public double hitungLuasPermukaan() {
        // Luas Permukaan Prisma = 2 * Luas Alas + Luas Selimut
        // Luas Selimut = Keliling Alas * tinggiPrisma
        double luasAlas = this.luas; // Mengambil luas alas dari atribut public di Segitiga
        double kelilingAlas = this.keliling; // Mengambil keliling alas dari atribut public di Segitiga
        return (2 * luasAlas) + (kelilingAlas * this.tinggiPrisma);
    }

    // Overloading untuk hitungLuasPermukaan dengan parameter lengkap
    public double hitungLuasPermukaan(double alasSegitiga, double tinggiAlasSegitiga, 
                                     double sisiBAlas, double sisiCAlas, double tinggiPrisma) {
        if (alasSegitiga <= 0 || tinggiAlasSegitiga <= 0 || sisiBAlas <= 0 || sisiCAlas <= 0 || tinggiPrisma <= 0) {
            throw new IllegalArgumentException("Semua dimensi untuk perhitungan luas permukaan harus bernilai positif.");
        }
        // Memanfaatkan hitungLuas(double a, double t) dari superclass Segitiga
        double luasAlasUntukHitung = super.hitungLuas(alasSegitiga, tinggiAlasSegitiga); //
        // Memanfaatkan hitungKeliling(double a, double b, double c) dari superclass Segitiga
        double kelilingAlasUntukHitung = super.hitungKeliling(alasSegitiga, sisiBAlas, sisiCAlas); //
        
        return (2 * luasAlasUntukHitung) + (kelilingAlasUntukHitung * tinggiPrisma);
    }
}
