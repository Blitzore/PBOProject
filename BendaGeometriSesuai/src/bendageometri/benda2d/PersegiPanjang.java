/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bendageometri.benda2d;

/**
 *
 * @author nbnrc
 */
public class PersegiPanjang extends Benda2D implements Runnable { 
    public double panjang;
    public double lebar;
    public double luas;
    public double keliling;
    
    // Konstruktor tetap sama, sudah baik dalam menginisialisasi objek
    public PersegiPanjang(double panjang, double lebar) throws IllegalArgumentException {
        if (panjang <= 0 || lebar <= 0) {
            throw new IllegalArgumentException("Panjang dan lebar harus bernilai positif.");
        }
        this.panjang = panjang;
        this.lebar = lebar;
        this.luas = hitungLuas();
        this.keliling = hitungKeliling();
    }
    
    @Override
    public double hitungLuas() {
        // Metode ini menghitung DAN menyimpan luas ke atribut instance
        this.luas = this.panjang * this.lebar;
        return this.luas;
    }

    //Metode overloading untuk menghitung luas dengan parameter
    public double hitungLuas(double p, double l) {
        if (p <= 0 || l <= 0) {
            throw new IllegalArgumentException("Panjang dan lebar untuk perhitungan luas harus bernilai positif.");
        }
        // Metode ini hanya menghitung dan mengembalikan hasil tanpa mengubah atribut instance
        return p * l;
    }
    
    @Override
    public double hitungKeliling() {
        // Metode ini menghitung DAN menyimpan keliling ke atribut instance
        this.keliling = 2 * (this.panjang + this.lebar);
        return this.keliling;
    }

    //Metode overloading untuk menghitung keliling dengan parameter
    public double hitungKeliling(double p, double l) {
        if (p <= 0 || l <= 0) {
            throw new IllegalArgumentException("Panjang dan lebar untuk perhitungan keliling harus bernilai positif.");
        }
        // Metode ini hanya menghitung dan mengembalikan hasil tanpa mengubah atribut instance
        return 2 * (p + l);
    }
    
    @Override
    public void run() {
        try {
            System.out.println("-> [Mulai] Thread untuk Persegi Panjang " + this.panjang + "x" + this.lebar);
            long jeda = (long) (Math.random() * 2000 + 1000);
            Thread.sleep(jeda);
            System.out.println("<- [Selesai] Persegi Panjang (setelah " + jeda + " ms)");
            System.out.printf("   > Luas: %.2f, Keliling: %.2f\n", this.luas, this.keliling);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Thread untuk Persegi Panjang diinterupsi.");
        }
    }
}
