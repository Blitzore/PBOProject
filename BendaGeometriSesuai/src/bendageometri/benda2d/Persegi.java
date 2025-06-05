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
    private final double sisi;

    // Konstruktor utama
    public Persegi(double sisi) {
        // 2. Lakukan validasi langsung di konstruktor
        if (sisi <= 0) {
            throw new IllegalArgumentException("Sisi harus bernilai positif.");
        }
        this.sisi = sisi; // Tetapkan nilai 'sisi' sekali saat konstruksi
    }

    // Getter untuk sisi
    public double getSisi() {
        return sisi;
    }

    @Override
    public double hitungLuas() {
        return this.sisi * this.sisi;
    }

    @Override
    public double hitungKeliling() {
        return 4 * this.sisi;
    }
}
