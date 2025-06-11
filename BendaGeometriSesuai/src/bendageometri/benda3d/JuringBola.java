package bendageometri.benda3d;

import java.util.concurrent.*;

public class JuringBola extends Bola {

    // ✅ Custom Exception
    public static class PerhitunganJuringBolaException extends RuntimeException {
        public PerhitunganJuringBolaException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    private volatile double tinggiTopiJuring;
    private final ExecutorService executor;

    public JuringBola(double jariJari, double tinggiTopiJuring) {
        super(jariJari);
        this.executor = Executors.newFixedThreadPool(2);
        setTinggiTopiJuring(tinggiTopiJuring);
    }

    public double getTinggiTopiJuring() {
        return tinggiTopiJuring;
    }

    public void setTinggiTopiJuring(double tinggiTopiJuring) {
        double rInduk = super.jariJari;
        if (tinggiTopiJuring <= 0 || tinggiTopiJuring > 2 * rInduk) {
            throw new IllegalArgumentException(
                    String.format(
                            "Tinggi topi juring (h=%.2f) harus lebih besar dari 0 dan tidak melebihi diameter bola induk (D=%.2f).",
                            tinggiTopiJuring, 2 * rInduk));
        }
        this.tinggiTopiJuring = tinggiTopiJuring;
    }

    @Override
    public double hitungVolume() {
        Future<Double> future = executor.submit(() -> {
            double h = tinggiTopiJuring;
            return (2.0 / 3.0) * super.luas * h;
        });

        try {
            volume = future.get();
            return volume;
        } catch (InterruptedException | ExecutionException e) {
            volume = -1;
            System.err.println("❌ Gagal menghitung volume juring bola: " + e.getMessage());
            throw new PerhitunganJuringBolaException("Gagal menghitung volume juring bola", e);
        }
    }

    public double hitungVolume(double jariPengganti) {
        Future<Double> future = executor.submit(() -> {
            double h = tinggiTopiJuring;
            return (2.0 / 3.0) * super.hitungLuas(jariPengganti) * h;
        });

        try {
            volume = future.get();
            return volume;
        } catch (InterruptedException | ExecutionException e) {
            volume = -1;
            System.err.println("❌ Gagal menghitung volume juring bola (param): " + e.getMessage());
            throw new PerhitunganJuringBolaException("Gagal menghitung volume juring bola (dengan parameter)", e);
        }
    }

    @Override
    public double hitungLuasPermukaan() {
        Future<Double> future = executor.submit(() -> {
            double h = tinggiTopiJuring;
            double r = super.jariJari;
            double a = Math.sqrt(Math.max(0, h * (2 * r - h)));
            double selimut = Math.PI * a * r;
            double topi = 2 * Math.PI * r * h;
            return selimut + topi;
        });

        try {
            luasPermukaan = future.get();
            return luasPermukaan;
        } catch (InterruptedException | ExecutionException e) {
            luasPermukaan = -1;
            System.err.println("❌ Gagal menghitung luas permukaan juring bola: " + e.getMessage());
            throw new PerhitunganJuringBolaException("Gagal menghitung luas permukaan juring bola", e);
        }
    }

    public double hitungLuasPermukaan(double jariPengganti) {
        Future<Double> future = executor.submit(() -> {
            double h = tinggiTopiJuring;
            double a = Math.sqrt(Math.max(0, h * (2 * jariPengganti - h)));
            double selimut = Math.PI * a * jariPengganti;
            double topi = 2 * Math.PI * jariPengganti * h;
            return selimut + topi;
        });

        try {
            luasPermukaan = future.get();
            return luasPermukaan;
        } catch (InterruptedException | ExecutionException e) {
            luasPermukaan = -1;
            System.err.println("❌ Gagal menghitung luas permukaan juring bola (param): " + e.getMessage());
            throw new PerhitunganJuringBolaException("Gagal menghitung luas permukaan juring bola (dengan parameter)",
                    e);
        }
    }

    public void shutdown() {
        executor.shutdown();
    }
}
