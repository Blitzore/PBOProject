/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bendageometri.benda2d;

/**
 *
 * @author nbnrc
 */
public class PersegiPanjang extends Benda2D {
    private final double panjang;
    private final double lebar;

    // Konstruktor utama untuk persegi panjang umum
    public PersegiPanjang(double panjang, double lebar) {
        // 2. Lakukan validasi langsung di konstruktor
        if (panjang <= 0) {
            throw new IllegalArgumentException("Panjang harus bernilai positif.");
        }
        if (lebar <= 0) {
            throw new IllegalArgumentException("Lebar harus bernilai positif.");
        }
        this.panjang = panjang; // Tetapkan nilai 'panjang' sekali saat konstruksi
        this.lebar = lebar;     // Tetapkan nilai 'lebar' sekali saat konstruksi
    }

    // Getter untuk panjang
    public double getPanjang() {
        return panjang;
    }

    // Getter untuk lebar
    public double getLebar() {
        return lebar;
    }

    @Override
    public double hitungLuas() {
        return this.panjang * this.lebar;
    }

    @Override
    public double hitungKeliling() {
        return 2 * (this.panjang + this.lebar);
    }
}
