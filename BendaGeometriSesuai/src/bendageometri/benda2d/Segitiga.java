/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bendageometri.benda2d;

/**
 *
 * @author nbnrc
 */
public class Segitiga extends Benda2D implements Runnable {
    public double alas;
    public double tinggi;
    public double sisiB;
    public double sisiC;
    
    public double luas;
    public double keliling;
    
    // Konstruktor utama
    public Segitiga(double alas, double tinggi, double sisiB, double sisiC) throws IllegalArgumentException {
        // Validasi nilai positif untuk semua dimensi
        if (alas <= 0 || tinggi <= 0 || sisiB <= 0 || sisiC <= 0) {
            throw new IllegalArgumentException("Dimensi alas, tinggi, sisiB, dan sisiC segitiga harus bernilai positif.");
        }

        // Memeriksa apakah alas, sisiB, dan sisiC dapat membentuk segitiga
        if (!cekValiditasSisiSegitiga(alas, sisiB, sisiC)) {
            throw new IllegalArgumentException("Sisi-sisi yang diberikan (alas, sisiB, sisiC) tidak membentuk segitiga yang valid.");
        }

        this.alas = alas;
        this.tinggi = tinggi;
        this.sisiB = sisiB;
        this.sisiC = sisiC;

        // Hitung dan simpan luas serta keliling saat objek dibuat
        this.luas = hitungLuas();
        this.keliling = hitungKeliling();
    }

    // Metode privat untuk validasi ketaksamaan sisi segitiga
    // Menggunakan parameter alas, sisiB, sisiC
    private boolean cekValiditasSisiSegitiga(double side1, double side2, double side3) {
        return (side1 + side2 > side3) &&
               (side1 + side3 > side2) &&
               (side2 + side3 > side1);
    }

    @Override
    public double hitungLuas() {
        luas = 0.5 * this.alas * this.tinggi;
        return luas;
    }

    // Overloading untuk hitungLuas dengan parameter alas dan tinggi
    public double hitungLuas(double a, double t) {
        if (a <= 0 || t <= 0) {
            throw new IllegalArgumentException("Alas dan tinggi untuk perhitungan luas harus bernilai positif.");
        }
        return 0.5 * a * t;
    }

    @Override
    public double hitungKeliling() {
        keliling = this.alas + this.sisiB + this.sisiC;
        return keliling;
    }

    // Overloading untuk hitungKeliling dengan parameter ketiga sisi
    public double hitungKeliling(double a, double b, double c) {
        if (a <= 0 || b <= 0 || c <= 0) {
            throw new IllegalArgumentException("Semua sisi untuk perhitungan keliling harus bernilai positif.");
        }
        // Validasi ketaksamaan segitiga untuk sisi-sisi yang diberikan
        if (!cekValiditasSisiSegitiga(a, b, c)) {
            throw new IllegalArgumentException("Sisi-sisi yang diberikan (a, b, c) tidak membentuk segitiga yang valid.");
        }
        return a + b + c;
    }
    
    
    @Override
    public void run() {
        try {
            System.out.println("-> [Mulai] Thread untuk Segitiga alas=" + this.alas);
            long jeda = (long) (Math.random() * 2000 + 1000);
            Thread.sleep(jeda);
            System.out.println("<- [Selesai] Segitiga (setelah " + jeda + " ms)");
            System.out.printf("   > Luas: %.2f, Keliling: %.2f\n", this.luas, this.keliling);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Thread untuk Segitiga diinterupsi.");
        }
    }
}
