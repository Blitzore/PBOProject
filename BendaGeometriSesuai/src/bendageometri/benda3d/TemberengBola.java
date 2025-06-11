package bendageometri.benda3d;

import java.util.concurrent.*;

public class TemberengBola extends Bola {

    // ✅ Custom Exception
    public static class PerhitunganTemberengBolaException extends RuntimeException {
        public PerhitunganTemberengBolaException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    private volatile double tinggiTemberengBola;
    private final ExecutorService executor;

    public TemberengBola(double jariJari, double tinggiTembereng) {
        super(jariJari);
        this.executor = Executors.newFixedThreadPool(2);
        setTinggiTemberengBola(tinggiTembereng);
    }

    public double getTinggiTemberengBola() {
        return tinggiTemberengBola;
    }

    public void setTinggiTemberengBola(double tinggiTembereng) {
        double R = super.jariJari;
        if (tinggiTembereng <= 0 || tinggiTembereng > 2 * R) {
            throw new IllegalArgumentException(
                    String.format(
                            "Tinggi tembereng bola (h=%.2f) harus lebih besar dari 0 dan tidak melebihi diameter bola induk (D=%.2f).",
                            tinggiTembereng, 2 * R));
        }
        this.tinggiTemberengBola = tinggiTembereng;
    }

    @Override
    public double hitungVolume() {
        Future<Double> future = executor.submit(() -> {
            double R = super.jariJari;
            double h = tinggiTemberengBola;
            return (1.0 / 3.0) * Math.PI * h * h * (3 * R - h);
        });

        try {
            volume = future.get();
            return volume;
        } catch (InterruptedException | ExecutionException e) {
            volume = -1;
            System.err.println("❌ Gagal menghitung volume tembereng bola: " + e.getMessage());
            throw new PerhitunganTemberengBolaException("Gagal menghitung volume tembereng bola", e);
        }
    }

    @Override
    public double hitungVolume(double jariPengganti) {
        Future<Double> future = executor.submit(() -> {
            double h = tinggiTemberengBola;
            return (1.0 / 3.0) * Math.PI * h * h * (3 * jariPengganti - h);
        });

        try {
            volume = future.get();
            return volume;
        } catch (InterruptedException | ExecutionException e) {
            volume = -1;
            System.err.println("❌ Gagal menghitung volume tembereng bola (param): " + e.getMessage());
            throw new PerhitunganTemberengBolaException("Gagal menghitung volume tembereng bola (dengan parameter)", e);
        }
    }

    @Override
    public double hitungLuasPermukaan() {
        Future<Double> future = executor.submit(() -> {
            double R = super.jariJari;
            double h = tinggiTemberengBola;

            double topi = super.keliling * h;
            double rAlasKuadrat = Math.max(0, h * (2 * R - h));
            double alas = Math.PI * rAlasKuadrat;

            return topi + alas;
        });

        try {
            luasPermukaan = future.get();
            return luasPermukaan;
        } catch (InterruptedException | ExecutionException e) {
            luasPermukaan = -1;
            System.err.println("❌ Gagal menghitung luas permukaan tembereng bola: " + e.getMessage());
            throw new PerhitunganTemberengBolaException("Gagal menghitung luas permukaan tembereng bola", e);
        }
    }

    @Override
    public double hitungLuasPermukaan(double jariPengganti) {
        Future<Double> future = executor.submit(() -> {
            double h = tinggiTemberengBola;

            double topi = super.hitungKeliling(jariPengganti) * h;
            double rAlasKuadrat = Math.max(0, h * (2 * jariPengganti - h));
            double alas = Math.PI * rAlasKuadrat;

            return topi + alas;
        });

        try {
            luasPermukaan = future.get();
            return luasPermukaan;
        } catch (InterruptedException | ExecutionException e) {
            luasPermukaan = -1;
            System.err.println("❌ Gagal menghitung luas permukaan tembereng bola (param): " + e.getMessage());
            throw new PerhitunganTemberengBolaException(
                    "Gagal menghitung luas permukaan tembereng bola (dengan parameter)", e);
        }
    }

    public void shutdown() {
        executor.shutdown();
    }
}
