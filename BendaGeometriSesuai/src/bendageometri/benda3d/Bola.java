package bendageometri.benda3d;

import bendageometri.benda2d.*;
import java.util.concurrent.*;

public class Bola extends Lingkaran {

    // ✅ Custom Exception
    public static class PerhitunganBolaException extends RuntimeException {
        public PerhitunganBolaException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    public volatile double volume;
    public volatile double luasPermukaan;
    public final ExecutorService executor;

    public Bola(double jariJari) {
        super(jariJari);
        this.executor = Executors.newFixedThreadPool(2);
    }

    @Override
    public double hitungVolume() {
        Future<Double> future = executor.submit(() -> {
            double jari = super.jariJari;
            if (jari <= 0) {
                System.err.println("❌ Jari-jari bola tidak valid: " + jari);
                return -1.0;
            }
            return volume = (4.0 / 3.0) * super.luas * jari;
        });

        try {
            volume = future.get();
            return volume;
        } catch (InterruptedException | ExecutionException e) {
            volume = -1;
            System.err.println("❌ Gagal menghitung volume bola: " + e.getMessage());
            throw new PerhitunganBolaException("Gagal menghitung volume bola", e);
        }
    }

    public double hitungVolume(double jari) {
        Future<Double> future = executor.submit(() -> {
            if (jari <= 0) {
                System.err.println("❌ Jari-jari bola (param) tidak valid: " + jari);
                return -1.0;
            }
            return volume = (4.0 / 3.0) * super.hitungLuas(jari) * jari;
        });

        try {
            volume = future.get();
            return volume;
        } catch (InterruptedException | ExecutionException e) {
            volume = -1;
            System.err.println("❌ Gagal menghitung volume bola (param): " + e.getMessage());
            throw new PerhitunganBolaException("Gagal menghitung volume bola (dengan parameter)", e);
        }
    }

    @Override
    public double hitungLuasPermukaan() {
        Future<Double> future = executor.submit(() -> {
            double jari = super.jariJari;
            if (jari <= 0) {
                System.err.println("❌ Jari-jari bola tidak valid: " + jari);
                return -1.0;
            }
            return luasPermukaan = 4 * super.luas;
        });

        try {
            luasPermukaan = future.get();
            return luasPermukaan;
        } catch (InterruptedException | ExecutionException e) {
            luasPermukaan = -1;
            System.err.println("❌ Gagal menghitung luas permukaan bola: " + e.getMessage());
            throw new PerhitunganBolaException("Gagal menghitung luas permukaan bola", e);
        }
    }

    public double hitungLuasPermukaan(double jari) {
        Future<Double> future = executor.submit(() -> {
            if (jari <= 0) {
                System.err.println("❌ Jari-jari bola (param) tidak valid: " + jari);
                return -1.0;
            }
            return 4 * super.hitungLuas(jari);
        });

        try {
            luasPermukaan = future.get();
            return luasPermukaan;
        } catch (InterruptedException | ExecutionException e) {
            luasPermukaan = -1;
            System.err.println("❌ Gagal menghitung luas permukaan bola (param): " + e.getMessage());
            throw new PerhitunganBolaException("Gagal menghitung luas permukaan bola (dengan parameter)", e);
        }
    }

    public void shutdown() {
        executor.shutdown();
    }
}
