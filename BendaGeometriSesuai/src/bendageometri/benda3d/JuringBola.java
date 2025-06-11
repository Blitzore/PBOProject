package bendageometri.benda3d;

import java.util.concurrent.*;

public class JuringBola extends Bola implements Runnable {
    public static class PerhitunganJuringBolaException extends RuntimeException {
        public PerhitunganJuringBolaException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    private volatile double tinggiTopiJuring;

    public JuringBola(double jariJari, double tinggiTopiJuring) {
        super(jariJari);
        setTinggiTopiJuring(tinggiTopiJuring);
    }

    public void setTinggiTopiJuring(double tinggiTopiJuring) {
        double rInduk = super.jariJari;
        if (tinggiTopiJuring <= 0 || tinggiTopiJuring > 2 * rInduk) {
            throw new IllegalArgumentException(
                String.format("Tinggi topi juring (h=%.2f) harus lebih besar dari 0 dan tidak melebihi diameter bola induk (D=%.2f).",
                    tinggiTopiJuring, 2 * rInduk));
        }
        this.tinggiTopiJuring = tinggiTopiJuring;
    }

    @Override
    public synchronized double hitungVolume() {
        this.volume = (2.0 / 3.0) * super.luas * tinggiTopiJuring;
        return this.volume;
    }

    @Override
    public synchronized double hitungLuasPermukaan() {
        double h = tinggiTopiJuring;
        double r = super.jariJari;
        double a = Math.sqrt(Math.max(0, h * (2 * r - h)));
        double selimut = Math.PI * a * r;
        double topi = 2 * Math.PI * r * h;
        this.luasPermukaan = selimut + topi;
        return this.luasPermukaan;
    }

    @Override
    public synchronized void run() {
        try {
            wait();
            System.out.println("[JuringBola] Volume: " + hitungVolume());
            System.out.println("[JuringBola] Luas Permukaan: " + hitungLuasPermukaan());
            notifyAll();
        } catch (InterruptedException e) {
            System.err.println("❌ Error pada thread JuringBola: " + e.getMessage());
            throw new PerhitunganJuringBolaException("Gagal menjalankan perhitungan juring bola", e);
        }
    }
}
