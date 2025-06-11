package bendageometri.benda2d;

import java.util.concurrent.*;

public class Lingkaran extends Benda2D {

    // ✅ Custom Exception
    public static class PerhitunganLingkaranException extends RuntimeException {
        public PerhitunganLingkaranException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    public final double jariJari;
    public volatile double luas = -1;
    public volatile double keliling = -1;
    public final ExecutorService executor;

    public Lingkaran(double jariJari) {
        if (jariJari <= 0) {
            throw new IllegalArgumentException("Jari-jari harus bernilai positif.");
        }
        this.jariJari = jariJari;
        this.executor = Executors.newFixedThreadPool(2);
    }

    public double getJariJari() {
        return jariJari;
    }

    @Override
    public double hitungLuas() {
        Future<Double> future = executor.submit(() -> Math.PI * jariJari * jariJari);
        try {
            luas = future.get();
            return luas;
        } catch (InterruptedException | ExecutionException e) {
            luas = -1; // Tandai error
            System.err.println("❌ Error menghitung luas lingkaran: " + e.getMessage());
            throw new PerhitunganLingkaranException("Gagal menghitung luas lingkaran", e);
        }
    }

    public double hitungLuas(double jari) {
        Future<Double> future = executor.submit(() -> Math.PI * jari * jari);
        try {
            luas = future.get();
            return luas;
        } catch (InterruptedException | ExecutionException e) {
            luas = -1;
            System.err.println("❌ Error menghitung luas lingkaran (dengan parameter): " + e.getMessage());
            throw new PerhitunganLingkaranException("Gagal menghitung luas lingkaran (dengan parameter)", e);
        }
    }

    @Override
    public double hitungKeliling() {
        Future<Double> future = executor.submit(() -> 2 * Math.PI * jariJari);
        try {
            keliling = future.get();
            return keliling;
        } catch (InterruptedException | ExecutionException e) {
            keliling = -1;
            System.err.println("❌ Error menghitung keliling lingkaran: " + e.getMessage());
            throw new PerhitunganLingkaranException("Gagal menghitung keliling lingkaran", e);
        }
    }

    public double hitungKeliling(double jari) {
        Future<Double> future = executor.submit(() -> 2 * Math.PI * jari);
        try {
            keliling = future.get();
            return keliling;
        } catch (InterruptedException | ExecutionException e) {
            keliling = -1;
            System.err.println("❌ Error menghitung keliling lingkaran (dengan parameter): " + e.getMessage());
            throw new PerhitunganLingkaranException("Gagal menghitung keliling lingkaran (dengan parameter)", e);
        }
    }

    public void shutdown() {
        executor.shutdown();
    }
}
