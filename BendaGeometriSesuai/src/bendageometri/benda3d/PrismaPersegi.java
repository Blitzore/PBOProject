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
public class PrismaPersegi extends Persegi implements Runnable{
    private double volume;
    private double luasPermukaan;

    // Konstruktor utama untuk Kubus, hanya memerlukan satu sisi
    public PrismaPersegi(double sisi)throws IllegalArgumentException {
        super(sisi); // Memanggil konstruktor Persegi.
        
        // Hitung dan simpan volume serta luas permukaan saat objek dibuat
        this.volume = hitungVolume();
        this.luasPermukaan = hitungLuasPermukaan();
    }

    // Getter untuk volume
    public double getVolume() {
        return volume;
    }

    // Getter untuk luasPermukaan
    public double getLuasPermukaan() {
        return luasPermukaan;
    }

    public double hitungVolume() {
        // Volume Kubus = Luas Alas * tinggi, dimana tinggi = sisi
        // Menggunakan super.sisi untuk tinggi
        return super.luas * super.sisi;
    }
    
    // Overloading untuk hitungVolume dengan parameter sisi 's'
    public double hitungVolume(double s) {
        if (s <= 0) {
            throw new IllegalArgumentException("Sisi untuk perhitungan volume harus bernilai positif.");
        }
        // Memanfaatkan hitungLuas(s) dari superclass Persegi
        double luasAlas = super.hitungLuas(s);
        // Tinggi kubus juga 's'
        return luasAlas * s;
    }

    public double hitungLuasPermukaan() {
        // Luas Permukaan Kubus = 6 * luas sisi
        // Atau (2 * Luas Alas) + (Keliling Alas * tinggi), dimana tinggi = sisi
        return (2 * super.luas) + (super.keliling * super.sisi);
    }
    
    // Overloading untuk hitungLuasPermukaan dengan parameter sisi 's'
    public double hitungLuasPermukaan(double s) {
        if (s <= 0) {
            throw new IllegalArgumentException("Sisi untuk perhitungan luas permukaan harus bernilai positif.");
        }
        // Luas permukaan kubus adalah 6 * (sisi * sisi)
        return 6 * super.hitungLuas(s);
    }
    
    @Override
    public void run() {
        try {
            System.out.println("-> [Mulai] Thread untuk Kubus (Prisma Persegi) sisi " + this.sisi);
            long jeda = (long) (Math.random() * 2000 + 1000);
            Thread.sleep(jeda);
            System.out.println("<- [Selesai] Kubus (setelah " + jeda + " ms)");
            System.out.printf("   > Volume: %.2f, Luas Permukaan: %.2f\n", getVolume(), getLuasPermukaan());
        } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }
}
