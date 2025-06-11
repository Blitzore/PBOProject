package bendageometri.benda3d;

import java.util.concurrent.*;

public class CincinBola extends Bola implements Runnable {
    public static class PerhitunganCincinBolaException extends RuntimeException {
        public PerhitunganCincinBolaException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    private final double jariJariAtas;
    private final double jariJariBawah;
    private final double tinggi;

    public CincinBola(double jariJari, double r1, double r2, double h) {
        super(jariJari);
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

    @Override
    public synchronized double hitungVolume() {
        if (tinggi <= 0) {
            System.err.println("❌ Tinggi tidak valid untuk menghitung volume: " + tinggi);
            return -1.0;
        }
        this.volume = (1.0 / 6.0) * Math.PI * tinggi *
                (3 * Math.pow(jariJariAtas, 2) + 3 * Math.pow(jariJariBawah, 2) + Math.pow(tinggi, 2));
        return this.volume;
    }

    @Override
    public synchronized double hitungLuasPermukaan() {
        double selimut = 2 * Math.PI * super.jariJari * tinggi;
        double alasAtas = Math.PI * Math.pow(jariJariAtas, 2);
        double alasBawah = Math.PI * Math.pow(jariJariBawah, 2);
        this.luasPermukaan = selimut + alasAtas + alasBawah;
        return this.luasPermukaan;
    }

    @Override
    public synchronized void run() {
        try {
            wait();
            System.out.println("[CincinBola] Volume: " + hitungVolume());
            System.out.println("[CincinBola] Luas Permukaan: " + hitungLuasPermukaan());
            notifyAll();
        } catch (InterruptedException e) {
            System.err.println("❌ Error pada thread CincinBola: " + e.getMessage());
            throw new PerhitunganCincinBolaException("Gagal menjalankan perhitungan cincin bola", e);
        }
    }
}
