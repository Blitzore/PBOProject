package bendageometri.benda2d;

import java.util.concurrent.*;

public class TemberengLingkaran extends Lingkaran {

    // ✅ Custom Exception
    public static class PerhitunganTemberengException extends RuntimeException {
        public PerhitunganTemberengException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    private volatile double sudutPusatDerajat;
    private final ExecutorService executor;

    public TemberengLingkaran(double jariJari, double sudutPusatDerajat) {
        super(jariJari);
        this.executor = Executors.newFixedThreadPool(2);
        setSudutPusatDerajat(sudutPusatDerajat);
    }

    public double getSudutPusatDerajat() {
        return sudutPusatDerajat;
    }

    public void setSudutPusatDerajat(double sudutPusatDerajat) {
        if (sudutPusatDerajat <= 0 || sudutPusatDerajat >= 360) {
            System.err.println("❌ Sudut tembereng tidak valid: " + sudutPusatDerajat);
            this.sudutPusatDerajat = -1;
        } else {
            this.sudutPusatDerajat = sudutPusatDerajat;
        }
    }

    @Override
    public double hitungLuas() {
        Future<Double> future = executor.submit(() -> {
            if (sudutPusatDerajat <= 0 || sudutPusatDerajat >= 360) {
                System.err.println("❌ Tidak bisa hitung luas: sudut tidak valid");
                return -1.0;
            }
            double sudutRadian = Math.toRadians(sudutPusatDerajat);
            double jariJariKuadrat = Math.pow(super.jariJari, 2);
            double luasJuring = (sudutPusatDerajat / 360.0) * super.luas;
            double luasSegitiga = 0.5 * jariJariKuadrat * Math.sin(sudutRadian);
            return luasJuring - luasSegitiga;
        });

        try {
            luas = future.get();
            return luas;
        } catch (InterruptedException | ExecutionException e) {
            luas = -1;
            System.err.println("❌ Gagal menghitung luas tembereng: " + e.getMessage());
            throw new PerhitunganTemberengException("Gagal menghitung luas tembereng", e);
        }
    }

    public double hitungLuas(double jari) {
        Future<Double> future = executor.submit(() -> {
            if (sudutPusatDerajat <= 0 || sudutPusatDerajat >= 360) {
                System.err.println("❌ Tidak bisa hitung luas (param): sudut tidak valid");
                return -1.0;
            }
            double sudutRadian = Math.toRadians(sudutPusatDerajat);
            double jariJariKuadrat = Math.pow(jari, 2);
            double luasJuring = (sudutPusatDerajat / 360.0) * super.hitungLuas(jari);
            double luasSegitiga = 0.5 * jariJariKuadrat * Math.sin(sudutRadian);
            return luasJuring - luasSegitiga;
        });

        try {
            luas = future.get();
            return luas;
        } catch (InterruptedException | ExecutionException e) {
            luas = -1;
            System.err.println("❌ Gagal menghitung luas tembereng (param): " + e.getMessage());
            throw new PerhitunganTemberengException("Gagal menghitung luas tembereng (dengan parameter)", e);
        }
    }

    @Override
    public double hitungKeliling() {
        Future<Double> future = executor.submit(() -> {
            if (sudutPusatDerajat <= 0 || sudutPusatDerajat >= 360) {
                System.err.println("❌ Tidak bisa hitung keliling: sudut tidak valid");
                return -1.0;
            }
            double sudutRadian = Math.toRadians(sudutPusatDerajat);
            double panjangBusur = super.jariJari * sudutRadian;
            double panjangTaliBusur = 2 * super.getJariJari() * Math.sin(sudutRadian / 2.0);
            return panjangBusur + panjangTaliBusur;
        });

        try {
            keliling = future.get();
            return keliling;
        } catch (InterruptedException | ExecutionException e) {
            keliling = -1;
            System.err.println("❌ Gagal menghitung keliling tembereng: " + e.getMessage());
            throw new PerhitunganTemberengException("Gagal menghitung keliling tembereng", e);
        }
    }

    public double hitungKeliling(double jari) {
        Future<Double> future = executor.submit(() -> {
            if (sudutPusatDerajat <= 0 || sudutPusatDerajat >= 360) {
                System.err.println("❌ Tidak bisa hitung keliling (param): sudut tidak valid");
                return -1.0;
            }
            double sudutRadian = Math.toRadians(sudutPusatDerajat);
            double panjangBusur = jari * sudutRadian;
            double panjangTaliBusur = 2 * jari * Math.sin(sudutRadian / 2.0);
            return panjangBusur + panjangTaliBusur;
        });

        try {
            keliling = future.get();
            return keliling;
        } catch (InterruptedException | ExecutionException e) {
            keliling = -1;
            System.err.println("❌ Gagal menghitung keliling tembereng (param): " + e.getMessage());
            throw new PerhitunganTemberengException("Gagal menghitung keliling tembereng (dengan parameter)", e);
        }
    }

    public void shutdown() {
        executor.shutdown();
    }
}
