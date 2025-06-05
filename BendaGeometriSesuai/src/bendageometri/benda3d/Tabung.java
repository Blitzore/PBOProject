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
public class Tabung extends Lingkaran { // Mewarisi Lingkaran untuk alas tabung
    private double tinggiTabung;

    // Konstruktor utama
    public Tabung(double jariJariAlas, double tinggiTabung) {
        super(jariJariAlas); // Memanggil konstruktor Lingkaran untuk jari-jari alas
                             // Validasi jariJariAlas ditangani oleh Lingkaran
        setTinggiTabung(tinggiTabung); // Menggunakan setter untuk validasi tinggiTabung
    }

    // Getter untuk tinggiTabung
    public double getTinggiTabung() {
        return tinggiTabung;
    }

    // Setter
    public void setTinggiTabung(double tinggiTabung) {
        if (tinggiTabung <= 0) {
            throw new IllegalArgumentException("Tinggi tabung harus bernilai positif.");
        }
        this.tinggiTabung = tinggiTabung;
    }

    @Override
    public double hitungVolume() {
        // Volume Tabung = Luas Alas (dari Lingkaran) * tinggiTabung
        // super.hitungLuas() akan memanggil Lingkaran.hitungLuas()
        return super.hitungLuas() * this.tinggiTabung;
    }

    @Override
    public double hitungLuasPermukaan() {
        // Luas Permukaan Tabung = 2 * Luas Alas + Luas Selimut
        // Luas Selimut = Keliling Alas * tinggiTabung
        // super.hitungLuas() akan memanggil Lingkaran.hitungLuas()
        // super.hitungKeliling() akan memanggil Lingkaran.hitungKeliling()
        double luasAlas = super.hitungLuas();
        double kelilingAlas = super.hitungKeliling();
        return (2 * luasAlas) + (kelilingAlas * this.tinggiTabung);
    }
}
