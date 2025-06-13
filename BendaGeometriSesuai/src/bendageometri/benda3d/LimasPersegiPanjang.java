/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bendageometri.benda3d;
import bendageometri.benda2d.PersegiPanjang;

/**
 *
 * @author nbnrc
 */
public class LimasPersegiPanjang extends PersegiPanjang implements Runnable {
    private double tinggi;
    private double volume;
    private double luasPermukaan;

    // Konstruktor utama
    public LimasPersegiPanjang(double panjang, double lebar, double tinggi)throws IllegalArgumentException {
        super(panjang, lebar); // Memanggil konstruktor PersegiPanjang
        if (tinggi <= 0) {
            throw new IllegalArgumentException("Tinggi harus bernilai positif.");
        }
        this.tinggi = tinggi;

        // Hitung dan simpan volume serta luas permukaan saat objek dibuat
        this.volume = hitungVolume();
        this.luasPermukaan = hitungLuasPermukaan();
    }
    
    // Getter untuk tinggi
    public double getTinggi() {
        return tinggi;
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
        // Volume Limas = (Luas Alas * tinggiLimas) / 3
        // super.luas mengakses atribut dari instance PersegiPanjang
        return (super.luas * this.tinggi) / 3.0;
    }
    
    // Overloading untuk hitungVolume dengan parameter
    public double hitungVolume(double panjang, double lebar, double tinggi) {
        if (tinggi <= 0) {
            throw new IllegalArgumentException("Tinggi untuk perhitungan volume harus bernilai positif.");
        }
        // Memanfaatkan hitungLuas(p, l) dari superclass PersegiPanjang
        double luasAlas = super.hitungLuas(panjang, lebar);
        return (luasAlas * tinggi) / 3.0;
    }

    public double hitungLuasPermukaan() {
        // Luas Permukaan Limas = Luas Alas + Jumlah Luas Sisi Tegak
        // Menghitung tinggi dari masing-masing pasang sisi tegak (segitiga)
        double tinggiSisiMiringA = Math.sqrt(Math.pow(super.lebar / 2, 2) + Math.pow(this.tinggi, 2));
        double luasSisiTegakA = super.panjang * tinggiSisiMiringA;
        
        double tinggiSisiMiringB = Math.sqrt(Math.pow(super.panjang / 2, 2) + Math.pow(this.tinggi, 2));
        double luasSisiTegakB = super.lebar * tinggiSisiMiringB;

        return super.luas + luasSisiTegakA + luasSisiTegakB;
    }

    // Overloading untuk hitungLuasPermukaan dengan parameter
    public double hitungLuasPermukaan(double panjang, double lebar, double tinggi) {
        if (tinggi <= 0) {
            throw new IllegalArgumentException("Tinggi untuk perhitungan luas permukaan harus bernilai positif.");
        }
        // Memanfaatkan hitungLuas(p, l) dari superclass PersegiPanjang
        double luasAlas = super.hitungLuas(panjang, lebar);
        
        // Menghitung luas selimut dengan parameter yang diberikan
        double tinggiSisiMiringA = Math.sqrt(Math.pow(lebar / 2, 2) + Math.pow(tinggi, 2));
        double luasSisiTegakA = panjang * tinggiSisiMiringA;
        
        double tinggiSisiMiringB = Math.sqrt(Math.pow(panjang / 2, 2) + Math.pow(tinggi, 2));
        double luasSisiTegakB = lebar * tinggiSisiMiringB;

        return luasAlas + luasSisiTegakA + luasSisiTegakB;
    }
    
    @Override
    public void run() {
        try {
            System.out.println("-> [Mulai] Thread untuk Limas Persegi Panjang (" + this.panjang + "x" + this.lebar + ", t=" + getTinggi() + ")");
            long jeda = (long) (Math.random() * 2000 + 1000);
            Thread.sleep(jeda);
            System.out.println("<- [Selesai] Limas Persegi Panjang (setelah " + jeda + " ms)");
            System.out.printf("   > Volume: %.2f, Luas Permukaan: %.2f\n", getVolume(), getLuasPermukaan());
        } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }
}
