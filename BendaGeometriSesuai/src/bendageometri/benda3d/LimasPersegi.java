/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bendageometri.benda3d;
import bendageometri.benda2d.Persegi;

/**
 *
 * @author nbnrc
 */
public class LimasPersegi extends Persegi implements Runnable {
    private double tinggi;
    private double volume;
    private double luasPermukaan;

    // Konstruktor utama
    public LimasPersegi(double sisi, double tinggi)throws IllegalArgumentException {
        super(sisi);
        if (tinggi <= 0) {
            throw new IllegalArgumentException("Tinggi harus bernilai positif.");
        }
        this.tinggi = tinggi;
        
        // Hitung dan simpan volume serta luas permukaan saat objek dibuat
        this.volume = hitungVolume();
        this.luasPermukaan = hitungLuasPermukaan();
    }
    
    // Getter
    public double getTinggi() { return tinggi; }
    public double getVolume() { return volume; }
    public double getLuasPermukaan() { return luasPermukaan; }

    public double hitungVolume() {
        // Volume Limas = (Luas Alas * tinggiLimas) / 3
        return (super.luas * this.tinggi) / 3;
    }

    // Overloading untuk hitungVolume dengan parameter
    public double hitungVolume(double sisi, double tinggi) {
        if (sisi <= 0 || tinggi <= 0) {
            throw new IllegalArgumentException("Sisi dan tinggi harus bernilai positif.");
        }
        // Memanfaatkan hitungLuas(s) dari superclass Persegi
        double luasAlas = super.hitungLuas(sisi);
        return (luasAlas * tinggi) / 3;
    }

    public double hitungLuasPermukaan() {
        // Luas Permukaan Limas = Luas Alas + Luas Selimut
        double sisiAlas = super.sisi;
        // Menghitung tinggi sisi tegak (segitiga) menggunakan Pythagoras
        double tinggiSisiTegak = Math.sqrt(Math.pow(sisiAlas / 2, 2) + Math.pow(this.tinggi, 2));
        double luasSelimut = 2 * sisiAlas * tinggiSisiTegak; // 4 * (1/2 * sisiAlas * tinggiSisiTegak)
        return super.luas + luasSelimut;
    }

    // Overloading untuk hitungLuasPermukaan dengan parameter
    public double hitungLuasPermukaan(double sisi, double tinggi) {
        if (sisi <= 0 || tinggi <= 0) {
            throw new IllegalArgumentException("Sisi dan tinggi harus bernilai positif.");
        }
        double luasAlas = super.hitungLuas(sisi);
        double tinggiSisiTegak = Math.sqrt(Math.pow(sisi / 2, 2) + Math.pow(tinggi, 2));
        double luasSelimut = 2 * sisi * tinggiSisiTegak;
        return luasAlas + luasSelimut;
    }
    
    @Override
    public void run() {
        try {
            System.out.println("-> [Mulai] Thread untuk Limas Persegi (alas " + this.sisi + "x" + this.sisi + ", t=" + getTinggi() + ")");
            long jeda = (long) (Math.random() * 2000 + 1000);
            Thread.sleep(jeda);
            System.out.println("<- [Selesai] Limas Persegi (setelah " + jeda + " ms)");
            System.out.printf("   > Volume: %.2f, Luas Permukaan: %.2f\n", getVolume(), getLuasPermukaan());
        } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }
}
