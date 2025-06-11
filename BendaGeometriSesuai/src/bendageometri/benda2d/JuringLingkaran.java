package bendageometri.benda2d;

import java.util.concurrent.*;

public class JuringLingkaran extends Lingkaran {

    // ✅ Custom Exception
    public static class PerhitunganJuringException extends RuntimeException {
        public PerhitunganJuringException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    private volatile double sudutPusatDerajat;
    private final ExecutorService executor;

    public JuringLingkaran(double jariJari, double sudutPusatDerajat) {
        super(jariJari);
        this.executor = Executors.newFixedThreadPool(2);
        setSudutPusatDerajat(sudutPusatDerajat);
    }

    public double getSudutPusatDerajat() {
        return sudutPusatDerajat;
    }

    public void setSudutPusatDerajat(double sudutPusatDerajat) {
        if (sudutPusatDerajat <= 0 || sudutPusatDerajat >= 360) {
            System.err.println("❌ Sudut tidak valid untuk juring: " + sudutPusatDerajat);
            this.sudutPusatDerajat = -1;
        } else {
            this.sudutPusatDerajat = sudutPusatDerajat;
        }
    }

    @Override
    public synchronized double hitungLuas() {
        Future<Double> future = executor.submit(() -> {
            if (sudutPusatDerajat <= 0 || sudutPusatDerajat >= 360) {
                System.err.println("❌ Tidak bisa menghitung luas: sudut tidak valid");
                return -1.0;
            }
            return (sudutPusatDerajat / 360.0) * super.luas;
        });
        try {
            luas = future.get();
            return luas;
        } catch (InterruptedException | ExecutionException e) {
            luas = -1;
            System.err.println("❌ Gagal menghitung luas juring: " + e.getMessage());
            throw new PerhitunganJuringException("Gagal menghitung luas juring", e);
        }
    }

    public synchronized double hitungLuas(double jari) {
        Future<Double> future = executor.submit(() -> {
            if (sudutPusatDerajat <= 0 || sudutPusatDerajat >= 360) {
                System.err.println("❌ Tidak bisa menghitung luas (param): sudut tidak valid");
                return -1.0;
            }
            return (sudutPusatDerajat / 360.0) * super.hitungLuas(jari);
        });
        try {
            luas = future.get();
            return luas;
        } catch (InterruptedException | ExecutionException e) {
            luas = -1;
            System.err.println("❌ Gagal menghitung luas juring (param): " + e.getMessage());
            throw new PerhitunganJuringException("Gagal menghitung luas juring (dengan parameter)", e);
        }
    }

    @Override
    public synchronized double hitungKeliling() {
        Future<Double> future = executor.submit(() -> {
            if (sudutPusatDerajat <= 0 || sudutPusatDerajat >= 360) {
                System.err.println("❌ Tidak bisa menghitung keliling: sudut tidak valid");
                return -1.0;
            }
            double panjangBusur = (sudutPusatDerajat / 360.0) * super.keliling;
            return 2 * getJariJari() + panjangBusur;
        });
        try {
            keliling = future.get();
            return keliling;
        } catch (InterruptedException | ExecutionException e) {
            keliling = -1;
            System.err.println("❌ Gagal menghitung keliling juring: " + e.getMessage());
            throw new PerhitunganJuringException("Gagal menghitung keliling juring", e);
        }
    }

    public double hitungKeliling(double jari) {
        Future<Double> future = executor.submit(() -> {
            if (sudutPusatDerajat <= 0 || sudutPusatDerajat >= 360) {
                System.err.println("❌ Tidak bisa menghitung keliling (param): sudut tidak valid");
                return -1.0;
            }
            double panjangBusur = (sudutPusatDerajat / 360.0) * super.hitungKeliling(jari);
            return 2 * jari + panjangBusur;
        });
        try {
            keliling = future.get();
            return keliling;
        } catch (InterruptedException | ExecutionException e) {
            keliling = -1;
            System.err.println("❌ Gagal menghitung keliling juring (param): " + e.getMessage());
            throw new PerhitunganJuringException("Gagal menghitung keliling juring (dengan parameter)", e);
        }
    }

    public void shutdown() {
        executor.shutdown();
    }
}
