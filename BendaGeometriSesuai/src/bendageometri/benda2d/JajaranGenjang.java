/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bendageometri.benda2d;

/**
 *
 * @author nbnrc
 */
public class JajaranGenjang extends Benda2D implements Runnable {
    public double alas;
    public double tinggi;
    public double sisiMiring;
    public double luas;
    public double keliling;

    // Konstruktor utama
    public JajaranGenjang(double alas, double tinggi, double sisiMiring) throws IllegalArgumentException {
        // Validasi input
        if (alas <= 0 || tinggi <= 0 || sisiMiring <= 0) {
            throw new IllegalArgumentException("Semua dimensi jajaran genjang harus bernilai positif.");
        }
        this.alas = alas;
        this.tinggi = tinggi;
        this.sisiMiring = sisiMiring;
        
        // Hitung dan simpan luas serta keliling saat objek dibuat
        this.luas = hitungLuas();
        this.keliling = hitungKeliling();
    }
    
    @Override
    public double hitungLuas() {
        // Rumus luas jajaran genjang
        this.luas = this.alas * this.tinggi;
        return this.luas;
    }
    
    // Overloading untuk hitungLuas dengan parameter
    public double hitungLuas(double alas, double tinggi) {
        if (alas <= 0 || tinggi <= 0) {
            throw new IllegalArgumentException("Alas dan tinggi untuk perhitungan luas harus positif.");
        }
        // Metode ini hanya menghitung tanpa mengubah atribut instance
        return alas * tinggi;
    }
    
    @Override
    public double hitungKeliling() {
        // Rumus keliling jajaran genjang adalah 2 * (alas + sisi miring)
        this.keliling = 2 * (this.alas + this.sisiMiring);
        return this.keliling;
    }
    
    // Overloading untuk hitungKeliling dengan parameter
    public double hitungKeliling(double alas, double sisiMiring) {
        if (alas <= 0 || sisiMiring <= 0) {
            throw new IllegalArgumentException("Alas dan sisi miring untuk perhitungan keliling harus positif.");
        }
        // Metode ini hanya menghitung tanpa mengubah atribut instance
        return 2 * (alas + sisiMiring);
    }
    
    @Override
    public void run() {
        try {
            System.out.println("-> [Mulai] Thread untuk Jajaran Genjang alas=" + this.alas);
            long jeda = (long) (Math.random() * 2000 + 1000);
            Thread.sleep(jeda);
            System.out.println("<- [Selesai] Jajaran Genjang (setelah " + jeda + " ms)");
            System.out.printf("   > Luas: %.2f, Keliling: %.2f\n", this.luas, this.keliling);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Thread untuk Jajaran Genjang diinterupsi.");
        }
    }
}
