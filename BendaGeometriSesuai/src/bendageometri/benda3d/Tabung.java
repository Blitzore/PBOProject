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
public class Tabung extends Lingkaran implements Runnable{
    // Atribut spesifik kelas ini dibuat private karena tidak ada turunan lagi
    private double tinggi;
    private double volume;
    private double luasPermukaan;

    // Konstruktor utama
    public Tabung(double jariJari, double tinggi) throws PerhitunganLingkaranException {
        // Memanggil konstruktor kelas induk (Lingkaran) untuk menginisialisasi properti alas
        super(jariJari);
        
        if (tinggi <= 0) {
            // Menggunakan exception dari induknya jika tidak ada yang spesifik
            throw new PerhitunganLingkaranException("Tinggi tabung harus bernilai positif.");
        }
        this.tinggi = tinggi;
        
        // Hitung dan simpan volume serta luas permukaan saat objek dibuat
        this.volume = hitungVolume();
        this.luasPermukaan = hitungLuasPermukaan();
    }
    
    // Getter untuk mengakses atribut private
    public double getTinggi() {
        return this.tinggi;
    }

    public double getVolume() {
        return this.volume;
    }

    public double getLuasPermukaan() {
        return this.luasPermukaan;
    }

    // Metode ini baru untuk kelas Tabung
    public double hitungVolume() {
        // Rumus volume tabung = Luas Alas * tinggi
        // Memanfaatkan super.luas dari Lingkaran untuk efisiensi
        this.volume = super.luas * this.tinggi;
        return this.volume;
    }
    
    // Overloading untuk hitungVolume dengan parameter
    public double hitungVolume(double jariJari, double tinggi) throws PerhitunganLingkaranException {
        if (jariJari <= 0 || tinggi <= 0) {
            throw new PerhitunganLingkaranException("Jari-jari dan tinggi harus positif.");
        }
        // Memanfaatkan super.hitungLuas untuk efisiensi
        double luasAlas = super.hitungLuas(jariJari);
        return luasAlas * tinggi;
    }
    
    // Metode ini baru untuk kelas Tabung
    public double hitungLuasPermukaan() {
        // Luas Permukaan = (2 * Luas Alas) + Luas Selimut
        // Luas Selimut = Keliling Alas * tinggi
        // Memanfaatkan super.luas dan super.keliling
        double luasSelimut = super.keliling * this.tinggi;
        this.luasPermukaan = (2 * super.luas) + luasSelimut;
        return this.luasPermukaan;
    }
    
    // Overloading untuk hitungLuasPermukaan dengan parameter
    public double hitungLuasPermukaan(double jariJari, double tinggi) throws PerhitunganLingkaranException {
        if (jariJari <= 0 || tinggi <= 0) {
            throw new PerhitunganLingkaranException("Jari-jari dan tinggi harus positif.");
        }
        // Memanfaatkan metode dari superclass
        double luasAlas = super.hitungLuas(jariJari);
        double kelilingAlas = super.hitungKeliling(jariJari);
        double luasSelimut = kelilingAlas * tinggi;
        return (2 * luasAlas) + luasSelimut;
    }
    
    @Override
    public void run() {
        try {
            System.out.println("-> [Mulai] Thread untuk Tabung r=" + this.jariJari + ", t=" + getTinggi());
            long jeda = (long) (Math.random() * 2000 + 1000);
            Thread.sleep(jeda);
            System.out.println("<- [Selesai] Tabung (setelah " + jeda + " ms)");
            System.out.printf("   > Volume: %.2f, Luas Permukaan: %.2f\n", getVolume(), getLuasPermukaan());
        } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }
}
