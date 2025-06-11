package bendageometri.benda2d;

import java.util.concurrent.*;

public class Lingkaran extends Benda2D implements Runnable {
    // ✅ Custom Exception
    public static class PerhitunganLingkaranException extends RuntimeException {
        public PerhitunganLingkaranException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    public final double jariJari;
    public volatile double luas = -1;
    public volatile double keliling = -1;

    public Lingkaran(double jariJari) {
        if (jariJari <= 0) {
            throw new IllegalArgumentException("Jari-jari harus bernilai positif.");
        }
        this.jariJari = jariJari;
    }

    public double getJariJari() {
        return jariJari;
    }

    @Override
    public synchronized double hitungLuas() {
        this.luas = Math.PI * jariJari * jariJari;
        return this.luas;
    }

    public synchronized double hitungLuas(double jari) {
        this.luas = Math.PI * jari * jari;
        return this.luas;
    }

    @Override
    public synchronized double hitungKeliling() {
        this.keliling = 2 * Math.PI * jariJari;
        return this.keliling;
    }

    public synchronized double hitungKeliling(double jari) {
        this.keliling = 2 * Math.PI * jari;
        return this.keliling;
    }

    @Override
    public synchronized void run() {
        try {
            // Tunggu notifikasi dari thread sebelumnya
            wait();

            System.out.println("[Lingkaran] Luas: " + hitungLuas());
            System.out.println("[Lingkaran] Keliling: " + hitungKeliling());

            // Beri notifikasi ke thread berikutnya
            notifyAll();
        } catch (InterruptedException e) {
            System.err.println("❌ Error pada thread Lingkaran: " + e.getMessage());
            throw new PerhitunganLingkaranException("Gagal menjalankan perhitungan lingkaran", e);
        }
    }
}