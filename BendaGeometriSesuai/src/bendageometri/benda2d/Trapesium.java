/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bendageometri.benda2d;

/**
 *
 * @author nbnrc
 */
public class Trapesium extends Benda2D implements Runnable {
    public double sisiAtas;
    public double sisiBawah;
    public double tinggi;
    public double sisiMiring1;
    public double sisiMiring2;
    public double luas;
    public double keliling;
    
    // Konstruktor utama
    public Trapesium(double sisiAtas, double sisiBawah, double tinggi, double sisiMiring1, double sisiMiring2) throws IllegalArgumentException {
        // Validasi input
        if (sisiAtas <= 0 || sisiBawah <= 0 || tinggi <= 0 || sisiMiring1 <= 0 || sisiMiring2 <= 0) {
            throw new IllegalArgumentException("Semua dimensi trapesium harus bernilai positif.");
        }
        this.sisiAtas = sisiAtas;
        this.sisiBawah = sisiBawah;
        this.tinggi = tinggi;
        this.sisiMiring1 = sisiMiring1;
        this.sisiMiring2 = sisiMiring2;
        
        // Hitung dan simpan luas serta keliling saat objek dibuat
        this.luas = hitungLuas();
        this.keliling = hitungKeliling();
    }
    
    // Getter untuk atribut spesifik Trapesium
    public double getSisiAtas() { return sisiAtas; }
    public double getSisiBawah() { return sisiBawah; }
    public double getTinggi() { return tinggi; }
    public double getSisiMiring1() { return sisiMiring1; }
    public double getSisiMiring2() { return sisiMiring2; }
    
    @Override
    public double hitungLuas() {
        // Rumus luas trapesium
        this.luas = 0.5 * (this.sisiAtas + this.sisiBawah) * this.tinggi;
        return this.luas;
    }
    
    // Overloading untuk hitungLuas dengan parameter
    public double hitungLuas(double sisiAtas, double sisiBawah, double tinggi) {
        if (sisiAtas <= 0 || sisiBawah <= 0 || tinggi <= 0) {
            throw new IllegalArgumentException("Dimensi untuk perhitungan luas harus positif.");
        }
        // Metode ini hanya menghitung tanpa mengubah atribut instance
        return 0.5 * (sisiAtas + sisiBawah) * tinggi;
    }

    @Override
    public double hitungKeliling() {
        // Rumus keliling trapesium adalah jumlah semua sisinya
        this.keliling = this.sisiAtas + this.sisiBawah + this.sisiMiring1 + this.sisiMiring2;
        return this.keliling;
    }

    // Overloading untuk hitungKeliling dengan parameter
    public double hitungKeliling(double sisiAtas, double sisiBawah, double sisiMiring1, double sisiMiring2) {
        if (sisiAtas <= 0 || sisiBawah <= 0 || sisiMiring1 <= 0 || sisiMiring2 <= 0) {
            throw new IllegalArgumentException("Sisi untuk perhitungan keliling harus positif.");
        }
        // Metode ini hanya menghitung tanpa mengubah atribut instance
        return sisiAtas + sisiBawah + sisiMiring1 + sisiMiring2;
    }
    
    @Override
    public void run() {
        try {
            System.out.println("-> [Mulai] Thread untuk Trapesium");
            long jeda = (long) (Math.random() * 2000 + 1000);
            Thread.sleep(jeda);
            System.out.println("<- [Selesai] Trapesium (setelah " + jeda + " ms)");
            System.out.printf("   > Luas: %.2f, Keliling: %.2f\n", this.luas, this.keliling);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Thread untuk Trapesium diinterupsi.");
        }
    }
}
