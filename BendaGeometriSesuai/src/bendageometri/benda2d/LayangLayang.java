package bendageometri.benda2d;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LayangLayang extends Benda2D implements Runnable {
    public double diagonal1;
    public double diagonal2;
    public double sisiA;
    public double sisiB;
    
    public double luas;
    public double keliling;

    public LayangLayang(double diagonal1, double diagonal2, double sisiA, double sisiB) {
        if (diagonal1 <= 0 || diagonal2 <= 0 || sisiA <= 0 || sisiB <= 0) {
            throw new IllegalArgumentException("Semua dimensi layang-layang harus bernilai positif.");
        }

        // Validasi geometris
        if (sisiA <= diagonal2 / 2.0 || sisiB <= diagonal2 / 2.0) {
            throw new IllegalArgumentException(
                "Sisi-sisi layang-layang harus lebih panjang dari setengah diagonal2.");
        }

        double d2_setengah = diagonal2 / 2.0;
        double p_segment = Math.sqrt(Math.pow(sisiA, 2) - Math.pow(d2_setengah, 2));
        double q_segment = Math.sqrt(Math.pow(sisiB, 2) - Math.pow(d2_setengah, 2));

        double epsilon = 1e-9;
        if (Math.abs((p_segment + q_segment) - diagonal1) > epsilon) {
            throw new IllegalArgumentException(
                "Dimensi yang diberikan tidak membentuk layang-layang yang valid.");
        }

        this.diagonal1 = diagonal1;
        this.diagonal2 = diagonal2;
        this.sisiA = sisiA;
        this.sisiB = sisiB;

        this.luas = hitungLuas();
        this.keliling = hitungKeliling();
    }

    @Override
    public double hitungLuas() {
        this.luas = 0.5 * this.diagonal1 * this.diagonal2;
        return this.luas;
    }

    @Override
    public double hitungKeliling() {
        this.keliling = 2 * (this.sisiA + this.sisiB);
        return this.keliling;
    }

    @Override
    public synchronized void run() {
        System.out.println("[LayangLayang] Luas: " + hitungLuas());
        System.out.println("[LayangLayang] Keliling: " + hitungKeliling());
        notifyAll();
    }
}