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
public class PrismaBelahKetupat extends BelahKetupat { // Mewarisi BelahKetupat untuk alasnya
    private double tinggiPrisma;
    private double volume; // Atribut private untuk menyimpan nilai volume
    private double luasPermukaan; // Atribut private untuk menyimpan nilai luas permukaan

    // Konstruktor utama
    public PrismaBelahKetupat(double sisiAlas, double d1Alas, double d2Alas, double tinggiPrisma) throws IllegalArgumentException, IllegalStateException {
        super(sisiAlas, d1Alas, d2Alas); // Validasi alas oleh BelahKetupat
        setTinggiPrisma(tinggiPrisma);

        // Hitung dan simpan volume serta luas permukaan saat objek dibuat
        this.volume = hitungVolume();
        this.luasPermukaan = hitungLuasPermukaan();
    }
    
    // Overloaded constructor: jika alas BelahKetupat didefinisikan hanya dengan dua diagonalnya
    // (sisi alas akan dihitung oleh konstruktor BelahKetupat yang di-overload)
    public PrismaBelahKetupat(double d1Alas, double d2Alas, double tinggiPrisma) throws IllegalArgumentException, IllegalStateException {
        super(d1Alas, d2Alas); // Memanggil konstruktor BelahKetupat(d1, d2)
        setTinggiPrisma(tinggiPrisma);

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
        // Volume Prisma = Luas Alas (dari BelahKetupat) * tinggiPrisma
        double calculatedVolume = this.luas * this.tinggiPrisma; // Menggunakan atribut luas dari superclass
        return calculatedVolume;
    }
    
    // Overloading untuk hitungVolume
    public double hitungVolume(double d1Alas, double d2Alas, double tinggiPrisma) {
        if (d1Alas <= 0 || d2Alas <= 0 || tinggiPrisma <= 0) {
            throw new IllegalArgumentException("Diagonal alas dan tinggi prisma harus bernilai positif.");
        }
        // Memanfaatkan hitungLuas(d1, d2) dari superclass BelahKetupat
        double luasAlasUntukHitung = super.hitungLuas(d1Alas, d2Alas); //
        return luasAlasUntukHitung * tinggiPrisma;
    }

    @Override
    public double hitungLuasPermukaan() {
        // Luas Permukaan Prisma = 2 * Luas Alas + Luas Selimut
        // Luas Selimut = Keliling Alas * tinggiPrisma
        double luasAlas = this.luas; // Mengambil luas alas dari atribut public di BelahKetupat
        double kelilingAlas = this.keliling; // Mengambil keliling alas dari atribut public di BelahKetupat
        double calculatedLuasPermukaan = (2 * luasAlas) + (kelilingAlas * this.tinggiPrisma);
        return calculatedLuasPermukaan;
    }
    
    // Overloading untuk hitungLuasPermukaan
    public double hitungLuasPermukaan(double d1Alas, double d2Alas, double tinggiPrisma) throws IllegalStateException {
        if (d1Alas <= 0 || d2Alas <= 0 || tinggiPrisma <= 0) {
            throw new IllegalArgumentException("Diagonal alas dan tinggi prisma harus bernilai positif.");
        }
        
        // Memanfaatkan hitungLuas(d1, d2) dari superclass BelahKetupat untuk luas alas
        double luasAlasUntukHitung = super.hitungLuas(d1Alas, d2Alas); //

        // Untuk keliling, kita perlu sisi. Sisi belah ketupat dari diagonal adalah sqrt((d1/2)^2 + (d2/2)^2)
        double sisiDihitung = Math.sqrt(Math.pow(d1Alas / 2.0, 2) + Math.pow(d2Alas / 2.0, 2));
        if (Double.isNaN(sisiDihitung) || sisiDihitung <= 0) {
             throw new IllegalStateException("Perhitungan sisi dari diagonal menghasilkan nilai tidak valid untuk keliling.");
        }
        // Memanfaatkan hitungKeliling(s) dari superclass BelahKetupat
        double kelilingAlasUntukHitung = super.hitungKeliling(sisiDihitung); //
        
        return (2 * luasAlasUntukHitung) + (kelilingAlasUntukHitung * tinggiPrisma);
    }
}
