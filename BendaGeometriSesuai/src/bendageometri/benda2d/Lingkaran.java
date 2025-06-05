/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bendageometri.benda2d;

/**
 *
 * @author nbnrc
 */
public class Lingkaran extends Benda2D {
    public final double jariJari; // Dibuat final
    public double luas;
    public double keliling;

    // Konstruktor utama
    public Lingkaran(double jariJari) {
        if (jariJari <= 0) { // Validasi langsung di konstruktor
            throw new IllegalArgumentException("Jari-jari harus bernilai positif.");
        }
        this.jariJari = jariJari; // Ditetapkan sekali
    }

    // Getter publik untuk jariJari
    public double getJariJari() {
        return jariJari;
    }

    @Override
    public double hitungLuas() {
        luas = Math.PI * this.jariJari * this.jariJari;
        return luas;
    }
    
    public double hitungLuas(double jari) {
        luas = Math.PI * jari * jari;
        return luas;
    }

    @Override
    public double hitungKeliling() {
        keliling = 2 * Math.PI * this.jariJari;
        return keliling;
    }
    
    public double hitungKeliling(double jari) {
        keliling = 2 * Math.PI * jari;
        return keliling;
    }
}
