/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bendageometri.benda2d;

/**
 *
 * @author nbnrc
 */
public class Trapesium extends Benda2D {
    private double sisiAtas;
    private double sisiBawah;
    private double tinggi;
    private double sisiMiring1;
    private double sisiMiring2;

    // Konstruktor utama untuk trapesium umum
    public Trapesium(double sisiAtas, double sisiBawah, double tinggi, double sisiMiring1, double sisiMiring2) {
        if (sisiAtas <= 0 || sisiBawah <= 0 || tinggi <= 0 || sisiMiring1 <= 0 || sisiMiring2 <= 0) {
            throw new IllegalArgumentException("Semua dimensi trapesium (sisi atas, sisi bawah, tinggi, sisi miring) harus bernilai positif.");
        }
        // Validasi dasar: sisi miring tidak boleh lebih pendek dari tinggi.
        if (sisiMiring1 < tinggi || sisiMiring2 < tinggi) {
            throw new IllegalArgumentException("Sisi miring tidak boleh lebih pendek dari tinggi trapesium.");
        }
        this.sisiAtas = sisiAtas;
        this.sisiBawah = sisiBawah;
        this.tinggi = tinggi;
        this.sisiMiring1 = sisiMiring1;
        this.sisiMiring2 = sisiMiring2;
    }

    // Overloaded constructor: untuk trapesium sama kaki
    public Trapesium(double sisiAtas, double sisiBawah, double tinggi, double sisiMiringSama) {
        // Memanggil konstruktor utama dengan sisiMiring1 = sisiMiring2 = sisiMiringSama
        this(sisiAtas, sisiBawah, tinggi, sisiMiringSama, sisiMiringSama);
    }


    // Getter
    public double getSisiAtas() {
        return sisiAtas;
    }

    public double getSisiBawah() {
        return sisiBawah;
    }

    public double getTinggi() {
        return tinggi;
    }

    public double getSisiMiring1() {
        return sisiMiring1;
    }

    public double getSisiMiring2() {
        return sisiMiring2;
    }

    // Tidak ada setter publik untuk menjaga imutabilitas setelah objek dibuat.

    @Override
    public double hitungLuas() {
        return 0.5 * (this.sisiAtas + this.sisiBawah) * this.tinggi;
    }

    @Override
    public double hitungKeliling() {
        return this.sisiAtas + this.sisiBawah + this.sisiMiring1 + this.sisiMiring2;
    }
}
