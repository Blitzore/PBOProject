/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bendageometri.benda2d;

/**
 *
 * @author nbnrc
 */
public class JajaranGenjang extends Benda2D {
    private double alas;
    private double tinggi;
    private double sisiMiring;

    // Konstruktor utama
    public JajaranGenjang(double alas, double tinggi, double sisiMiring) {
        if (alas <= 0 || tinggi <= 0 || sisiMiring <= 0) {
            throw new IllegalArgumentException("Dimensi alas, tinggi, dan sisi miring jajaran genjang harus bernilai positif.");
        }
        if (sisiMiring < tinggi) {
            throw new IllegalArgumentException("Sisi miring tidak boleh lebih pendek dari tinggi jajaran genjang.");
        }

        this.alas = alas;
        this.tinggi = tinggi;
        this.sisiMiring = sisiMiring;
    }

    // Getter
    public double getAlas() {
        return alas;
    }

    public double getTinggi() {
        return tinggi;
    }

    public double getSisiMiring() {
        return sisiMiring;
    }

    @Override
    public double hitungLuas() {
        // Luas Jajaran Genjang = alas * tinggi
        return this.alas * this.tinggi;
    }

    @Override
    public double hitungKeliling() {
        // Keliling Jajaran Genjang = 2 * (alas + sisi miring)
        return 2 * (this.alas + this.sisiMiring);
    }
}
