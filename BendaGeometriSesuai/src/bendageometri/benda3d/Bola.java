package bendageometri.benda3d;

import bendageometri.benda2d.*;
import java.util.concurrent.*;

public class Bola extends Lingkaran implements Runnable {
    public static class PerhitunganBolaException extends RuntimeException {
        public PerhitunganBolaException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    public volatile double volume;
    public volatile double luasPermukaan;

    public Bola(double jariJari) {
        super(jariJari);
    }

    @Override
    public synchronized double hitungVolume() {
        this.volume = (4.0 / 3.0) * super.luas * super.jariJari;
        return this.volume;
    }

    @Override
    public synchronized double hitungLuasPermukaan() {
        this.luasPermukaan = 4 * super.luas;
        return this.luasPermukaan;
    }

    @Override
    public synchronized void run() {
        try {
            wait();
            System.out.println("[Bola] Volume: " + hitungVolume());
            System.out.println("[Bola] Luas Permukaan: " + hitungLuasPermukaan());
            notifyAll();
        } catch (InterruptedException e) {
            System.err.println("❌ Error pada thread Bola: " + e.getMessage());
            throw new PerhitunganBolaException("Gagal menjalankan perhitungan bola", e);
        }
    }
}
