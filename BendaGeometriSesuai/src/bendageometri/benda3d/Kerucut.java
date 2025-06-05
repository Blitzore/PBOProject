/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bendageometri.benda3d;
import bendageometri.benda2d.Lingkaran;
/**
 *
 * @author nbnrc
 */
public class Kerucut extends Lingkaran { // Mewarisi Lingkaran untuk alas kerucut
    private double tinggiKerucut;
    private final double garisPelukis; // Dibuat final karena dihitung sekali saat konstruksi

    // Konstruktor utama
    public Kerucut(double jariJariAlas, double tinggiKerucut) {
        super(jariJariAlas); // Memanggil konstruktor Lingkaran untuk jari-jari alas
                             // Validasi jariJariAlas ditangani oleh Lingkaran

        if (tinggiKerucut <= 0) {
            throw new IllegalArgumentException("Tinggi kerucut harus bernilai positif.");
        }
        this.tinggiKerucut = tinggiKerucut;

        // Hitung garis pelukis (s = sqrt(r^2 + t^2))
        // getJariJari() akan mengembalikan jari-jari alas dari superclass Lingkaran
        this.garisPelukis = Math.sqrt(Math.pow(getJariJari(), 2) + Math.pow(this.tinggiKerucut, 2));
        if (Double.isNaN(this.garisPelukis) || this.garisPelukis <=0) { // Validasi hasil perhitungan
             throw new IllegalStateException("Perhitungan garis pelukis menghasilkan nilai tidak valid.");
        }
    }

    // Getter untuk tinggiKerucut
    public double getTinggiKerucut() {
        return tinggiKerucut;
    }

    // Getter untuk garisPelukis
    public double getGarisPelukis() {
        return garisPelukis;
    }
    

    // Getter untuk jariJariAlas diwarisi dari Lingkaran sebagai getJariJari()

    @Override
    public double hitungVolume() {
        // Volume Kerucut = (1/3) * Luas Alas (dari Lingkaran) * tinggiKerucut
        return (1.0 / 3.0) * super.hitungLuas() * this.tinggiKerucut;
    }

    @Override
    public double hitungLuasPermukaan() {
        // Luas Permukaan Kerucut = Luas Alas + Luas Selimut
        // Luas Selimut = PI * r * s (s adalah garis pelukis)
        double luasAlas = super.hitungLuas();
        double luasSelimut = Math.PI * getJariJari() * this.garisPelukis;
        return luasAlas + luasSelimut;
    }
}
