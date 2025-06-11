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
public class KerucutTerpancung extends Lingkaran { // Mewarisi Lingkaran untuk alas bawah (R)
    private final double jariJariAtas; // Dibuat final
    private final double tinggiKerucutTerpancung; // Dibuat final
    private final double garisPelukis; // Dibuat final

    // Konstruktor utama
    public KerucutTerpancung(double jariJariAlasBawah, double jariJariAlasAtas, double tinggi) {
        super(jariJariAlasBawah);

        if (jariJariAlasAtas <= 0) {
            throw new IllegalArgumentException("Jari-jari alas atas harus bernilai positif.");
        }
        if (tinggi <= 0) {
            throw new IllegalArgumentException("Tinggi kerucut terpancung harus bernilai positif.");
        }

        this.jariJariAtas = jariJariAlasAtas;
        this.tinggiKerucutTerpancung = tinggi;

        // Hitung garis pelukis (s = sqrt(t^2 + (R-r)^2))
        double selisihJariJari = getJariJari() - this.jariJariAtas; // getJariJari() adalah R (bawah)
        this.garisPelukis = Math.sqrt(Math.pow(this.tinggiKerucutTerpancung, 2) + Math.pow(selisihJariJari, 2));

        if (Double.isNaN(this.garisPelukis) || this.garisPelukis < 0 || (this.garisPelukis == 0
                && !(getJariJari() == this.jariJariAtas && this.tinggiKerucutTerpancung == 0))) {
            // garisPelukis bisa 0 jika R=r dan tinggi=0 (degenerasi menjadi titik/garis),
            // tapi tinggi sudah > 0
            throw new IllegalStateException(
                    "Perhitungan garis pelukis menghasilkan nilai tidak valid dari dimensi yang diberikan.");
        }
    }

    // Getter
    public double getJariJariAlasBawah() {
        return getJariJari(); // Diwarisi dari Lingkaran
    }

    public double getJariJariAtas() {
        return jariJariAtas;
    }

    public double getTinggiKerucutTerpancung() {
        return tinggiKerucutTerpancung;
    }

    public double getGarisPelukis() {
        return garisPelukis;
    }

    // Metode untuk menghitung luas alas atas
    public double hitungLuasAlasAtas() {
        return Math.PI * this.jariJariAtas * this.jariJariAtas;
    }

    @Override
    public synchronized double hitungVolume() {
        double R = getJariJariAlasBawah();
        double r = this.jariJariAtas;
        double t = this.tinggiKerucutTerpancung;
        return (1.0 / 3.0) * Math.PI * t * (Math.pow(R, 2) + (R * r) + Math.pow(r, 2));
    }

    @Override
    public synchronized double hitungLuasPermukaan() {
        double luasAlasBawah = super.hitungLuas(); // Dari Lingkaran (alas bawah)
        double luasAlasAtas = hitungLuasAlasAtas();

        double R = getJariJariAlasBawah();
        double r = this.jariJariAtas;
        double luasSelimut = Math.PI * (R + r) * this.garisPelukis;

        return luasAlasBawah + luasAlasAtas + luasSelimut;
    }
}