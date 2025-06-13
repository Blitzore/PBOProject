/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bendageometri.benda2d;

/**
 *
 * @author nbnrc
 */
public class BelahKetupat extends Benda2D implements Runnable {
    public double d1;
    public double d2;
    public double sisi;
    public double luas;
    public double keliling;

    public BelahKetupat(double d1, double d2) throws IllegalArgumentException {
        if (d1 <= 0 || d2 <= 0) {
            throw new IllegalArgumentException("Diagonal harus bernilai positif.");
        }
        this.d1 = d1;
        this.d2 = d2;
        this.sisi = hitungSisi();
        this.luas = hitungLuas();
        this.keliling = hitungKeliling();
    }
    
    private double hitungSisi() {
        return Math.sqrt(Math.pow(d1 / 2, 2) + Math.pow(d2 / 2, 2));
    }

    @Override
    public double hitungLuas() {
        this.luas = 0.5 * this.d1 * this.d2;
        return this.luas;
    }

    public double hitungLuas(double d1, double d2) throws IllegalArgumentException {
        if (d1 <= 0 || d2 <= 0) {
            throw new IllegalArgumentException("Diagonal harus positif.");
        }
        return 0.5 * d1 * d2;
    }

    @Override
    public double hitungKeliling() {
        this.keliling = 4 * this.sisi;
        return this.keliling;
    }
    
    public double hitungKeliling(double sisi) throws IllegalArgumentException {
        if (sisi <= 0) {
            throw new IllegalArgumentException("Sisi harus positif.");
        }
        return 4 * sisi;
    }

    @Override
    public void run() {
        try {
            System.out.println("-> [Mulai] Thread untuk Belah Ketupat d1=" + this.d1 + ", d2=" + this.d2);
            long jeda = (long) (Math.random() * 2000 + 1000);
            Thread.sleep(jeda);
            System.out.println("<- [Selesai] Belah Ketupat (setelah " + jeda + " ms)");
            System.out.printf("   > Luas: %.2f, Keliling: %.2f\n", this.luas, this.keliling);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Thread untuk Belah Ketupat diinterupsi.");
        }
    }
}