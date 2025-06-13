/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bendageometri.benda2d;

/**
 *
 * @author nbnrc
 */
public class LayangLayang extends Benda2D implements Runnable {
    // Atribut publik agar bisa diakses oleh kelas turunan (Prisma, Limas)
    public double d1;
    public double d2;
    public double sisiA; // Sepasang sisi pendek
    public double sisiB; // Sepasang sisi panjang
    public double luas;
    public double keliling;

    // Exception kustom yang sudah ada di file asli
    public static class NegativeDimensionException extends IllegalArgumentException {
        public NegativeDimensionException(String message) {
            super(message);
        }
    }

    // Konstruktor utama
    public LayangLayang(double d1, double d2, double sisiA, double sisiB) throws NegativeDimensionException {
        // Validasi input
        if (d1 <= 0 || d2 <= 0 || sisiA <= 0 || sisiB <= 0) {
            throw new NegativeDimensionException("Dimensi layang-layang harus bernilai positif.");
        }
        this.d1 = d1;
        this.d2 = d2;
        this.sisiA = sisiA;
        this.sisiB = sisiB;
        
        // Hitung dan simpan luas serta keliling saat objek dibuat
        this.luas = hitungLuas();
        this.keliling = hitungKeliling();
    }
    
    @Override
    public double hitungLuas() {
        // Rumus luas layang-layang
        this.luas = 0.5 * this.d1 * this.d2;
        return this.luas;
    }
    
    // Overloading untuk hitungLuas dengan parameter
    public double hitungLuas(double d1, double d2) throws NegativeDimensionException {
        if (d1 <= 0 || d2 <= 0) {
            throw new NegativeDimensionException("Diagonal untuk perhitungan luas harus positif.");
        }
        // Metode ini hanya menghitung tanpa mengubah atribut instance
        return 0.5 * d1 * d2;
    }
    
    @Override
    public double hitungKeliling() {
        // Rumus keliling layang-layang adalah 2 * (sisiA + sisiB)
        this.keliling = 2 * (this.sisiA + this.sisiB);
        return this.keliling;
    }
    
    // Overloading untuk hitungKeliling dengan parameter
    public double hitungKeliling(double sisiA, double sisiB) throws NegativeDimensionException {
        if (sisiA <= 0 || sisiB <= 0) {
            throw new NegativeDimensionException("Sisi untuk perhitungan keliling harus positif.");
        }
        // Metode ini hanya menghitung tanpa mengubah atribut instance
        return 2 * (sisiA + sisiB);
    }
    
    @Override
    public void run() {
        try {
            System.out.println("-> [Mulai] Thread untuk Layang-Layang d1=" + this.d1 + ", d2=" + this.d2);
            long jeda = (long) (Math.random() * 2000 + 1000);
            Thread.sleep(jeda);
            System.out.println("<- [Selesai] Layang-Layang (setelah " + jeda + " ms)");
            System.out.printf("   > Luas: %.2f, Keliling: %.2f\n", this.luas, this.keliling);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Thread untuk Layang-Layang diinterupsi.");
        }
    }
}
