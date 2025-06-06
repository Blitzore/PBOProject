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
public class PrismaPersegi extends Persegi {
    private double volume;
    private double luasPermukaan;


    // Konstruktor utama
    public PrismaPersegi(double sisiKubus) throws IllegalArgumentException { //
        super(sisiKubus); // Memanggil konstruktor Persegi. sisiAlas (this.sisi) menjadi sisiKubus.
                         // Validasi sisiKubus (harus positif) ditangani oleh Persegi.
        
        // Hitung dan simpan volume serta luas permukaan saat objek dibuat
        this.volume = hitungVolume(); // Memanggil metode hitungVolume
        this.luasPermukaan = hitungLuasPermukaan(); // Memanggil metode hitungLuasPermukaan
    }

    // Getter untuk volume
    public double getVolume() { //
        return volume; //
    }

    // Getter untuk luasPermukaan
    public double getLuasPermukaan() { //
        return luasPermukaan; //
    }

    @Override
    public double hitungVolume() {
        // Volume Prisma = Luas Alas (dari Persegi) * tinggiPrisma
        // super.hitungLuas() akan memanggil Persegi.hitungLuas()
        return this.luas * this.sisi;
    }
    
    // Overloading untuk hitungVolume dengan parameter sisi 's'
    public double hitungVolume(double s) {
        if (s <= 0) {
            throw new IllegalArgumentException("Sisi untuk perhitungan volume harus bernilai positif.");
        }
        // Memanfaatkan hitungLuas(s) dari superclass Persegi untuk luas alas
        double luasAlas = super.hitungLuas(s); //
        // Tinggi kubus juga 's'
        return luasAlas * s;
    }

    @Override
    public double hitungLuasPermukaan() {
        // Luas Permukaan Prisma = 2 * Luas Alas + Luas Selimut
        // Luas Selimut = Keliling Alas * tinggiPrisma
        // super.hitungLuas() dan super.hitungKeliling() dari Persegi
        double luasAlas = this.luas;
        double kelilingAlas = this.keliling;
        double tinggiPrisma = this.sisi;
        return (2 * luasAlas) + (kelilingAlas * tinggiPrisma);
    }
    
    // Overloading untuk hitungLuasPermukaan dengan parameter sisi 's'
    public double hitungLuasPermukaan(double s) {
        if (s <= 0) {
            throw new IllegalArgumentException("Sisi untuk perhitungan luas permukaan harus bernilai positif.");
        }
        // Memanfaatkan hitungLuas(s) dari superclass Persegi untuk luas alas
        double luasAlas = super.hitungLuas(s); //
        // Memanfaatkan hitungKeliling(s) dari superclass Persegi untuk keliling alas
        double kelilingAlas = super.hitungKeliling(s); //
        double tinggiPrisma = s; // Tinggi prisma adalah 's'

        return (2 * luasAlas) + (kelilingAlas * tinggiPrisma);
    }
}
