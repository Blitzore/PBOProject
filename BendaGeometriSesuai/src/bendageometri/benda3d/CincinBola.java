package bendageometri.benda3d;

import java.util.concurrent.*;

public class CincinBola extends Bola {

    // ✅ Custom Exception
    public static class PerhitunganCincinBolaException extends RuntimeException {
        public PerhitunganCincinBolaException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    private final double jariJariAtas;
    private final double jariJariBawah;
    private final double tinggi;
    private final ExecutorService executor;

    public CincinBola(double jariJari, double r1, double r2, double h) {
        super(jariJari);
        this.executor = Executors.newFixedThreadPool(2);

        if (r1 < 0 || r2 < 0)
            throw new IllegalArgumentException("Jari-jari alas tidak boleh negatif.");
        if (h <= 0)
            throw new IllegalArgumentException("Tinggi cincin harus lebih dari nol.");
        if (r1 > jariJari || r2 > jariJari)
            throw new IllegalArgumentException("Jari-jari alas tidak boleh melebihi jari-jari bola induk.");
        if (h > 2 * jariJari)
            throw new IllegalArgumentException("Tinggi cincin tidak boleh melebihi diameter bola.");

        this.jariJariAtas = r1;
        this.jariJariBawah = r2;
        this.tinggi = h;
    }

    public double getJariJariAtas() {
        return jariJariAtas;
    }

    public double getJariJariBawah() {
        return jariJariBawah;
    }

    public double getTinggi() {
        return tinggi;
    }

    @Override
    public double hitungVolume() {
        Future<Double> future = executor.submit(() -> {
            if (tinggi <= 0) {
                System.err.println("❌ Tinggi tidak valid untuk menghitung volume: " + tinggi);
                return -1.0;
            }
            return (1.0 / 6.0) * Math.PI * tinggi *
                    (3 * Math.pow(jariJariAtas, 2) + 3 * Math.pow(jariJariBawah, 2) + Math.pow(tinggi, 2));
        });

        try {
            volume = future.get();
            return volume;
        } catch (InterruptedException | ExecutionException e) {
            volume = -1;
            System.err.println("❌ Gagal menghitung volume cincin bola: " + e.getMessage());
            throw new PerhitunganCincinBolaException("Gagal menghitung volume cincin bola", e);
        }
    }

    public double hitungVolume(double jariPengganti) {
        Future<Double> future = executor.submit(() -> {
            if (tinggi <= 0) {
                System.err.println("❌ Tinggi tidak valid untuk menghitung volume: " + tinggi);
                return -1.0;
            }
            return (1.0 / 6.0) * Math.PI * tinggi *
                    (3 * Math.pow(jariPengganti, 2) + 3 * Math.pow(jariJariBawah, 2) + Math.pow(tinggi, 2));
        });

        try {
            volume = future.get();
            return volume;
        } catch (InterruptedException | ExecutionException e) {
            volume = -1;
            System.err.println("❌ Gagal menghitung volume cincin bola (param): " + e.getMessage());
            throw new PerhitunganCincinBolaException("Gagal menghitung volume cincin bola (dengan parameter)", e);
        }
    }

    @Override
    public double hitungLuasPermukaan() {
        Future<Double> future = executor.submit(() -> {
            double selimut = 2 * Math.PI * super.jariJari * tinggi;
            double alasAtas = Math.PI * Math.pow(jariJariAtas, 2);
            double alasBawah = Math.PI * Math.pow(jariJariBawah, 2);
            return selimut + alasAtas + alasBawah;
        });

        try {
            luasPermukaan = future.get();
            return luasPermukaan;
        } catch (InterruptedException | ExecutionException e) {
            luasPermukaan = -1;
            System.err.println("❌ Gagal menghitung luas permukaan cincin bola: " + e.getMessage());
            throw new PerhitunganCincinBolaException("Gagal menghitung luas permukaan cincin bola", e);
        }
    }

    public double hitungLuasPermukaan(double jariPengganti) {
        Future<Double> future = executor.submit(() -> {
            double selimut = 2 * Math.PI * jariPengganti * tinggi;
            double alasAtas = super.hitungLuas(jariJariAtas);
            double alasBawah = super.hitungLuas(jariJariBawah);
            return selimut + alasAtas + alasBawah;
        });

        try {
            luasPermukaan = future.get();
            return luasPermukaan;
        } catch (InterruptedException | ExecutionException e) {
            luasPermukaan = -1;
            System.err.println("❌ Gagal menghitung luas permukaan cincin bola (param): " + e.getMessage());
            throw new PerhitunganCincinBolaException("Gagal menghitung luas permukaan cincin bola (dengan parameter)",
                    e);
        }
    }

    public void shutdown() {
        executor.shutdown();
    }
}
