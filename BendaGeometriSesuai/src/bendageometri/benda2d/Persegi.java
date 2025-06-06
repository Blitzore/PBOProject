/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bendageometri.benda2d;

/**
 *
 * @author nbnrc
 */
public class Persegi extends Benda2D {
    public final double sisi;
    public double luas;
    public double keliling;

    // Konstruktor utama
    public Persegi(double sisi) throws IllegalArgumentException {
        if (sisi <= 0) {
            throw new IllegalArgumentException("Sisi harus bernilai positif.");
        }
        this.sisi = sisi; // Sisi diinisialisasi di sini dan tidak dapat diubah lagi
        
        // Hitung dan simpan luas serta keliling saat objek dibuat berdasarkan this.sisi
        this.luas = hitungLuas();
        this.keliling = hitungKeliling();
    }

    public double hitungLuas() {
        // Menggunakan sisi dari objek (this.sisi)
        luas = this.sisi * this.sisi;
        return luas;
    }
    
    // Overloading untuk hitungLuas dengan parameter 's'
    public double hitungLuas(double s) {
        if (s <= 0) {
            throw new IllegalArgumentException("Sisi untuk perhitungan luas harus bernilai positif.");
        }
        return s * s;
    }

    @Override
    public double hitungKeliling() {
        // Menggunakan sisi dari objek (this.sisi)
        keliling = 4 * this.sisi;
        return keliling;
    }

    // Overloading untuk hitungKeliling dengan parameter 's'
    public double hitungKeliling(double s) {
        if (s <= 0) {
            throw new IllegalArgumentException("Sisi untuk perhitungan keliling harus bernilai positif.");
        }
        return 4 * s;
    }
}