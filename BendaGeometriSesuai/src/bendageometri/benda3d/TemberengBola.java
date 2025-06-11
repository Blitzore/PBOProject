package bendageometri.benda3d;

import java.util.concurrent.*;

public class TemberengBola extends Bola implements Runnable {
    public static class PerhitunganTemberengBolaException extends RuntimeException {
        public PerhitunganTemberengBolaException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    private volatile double tinggiTemberengBola;

    public TemberengBola(double jariJari, double tinggiTembereng) {
        super(jariJari);
        setTinggiTemberengBola(tinggiTembereng);
    }

    public void setTinggiTemberengBola(double tinggiTembereng) {
        double R = super.jariJari;
        if (tinggiTembereng <= 0 || tinggiTembereng > 2 * R) {
            throw new IllegalArgumentException(
                String.format("Tinggi tembereng bola (h=%.2f) harus lebih besar dari 0 dan tidak melebihi diameter bola induk (D=%.2f).",
                    tinggiTembereng, 2 * R));
        }
        this.tinggiTemberengBola = tinggiTembereng;
    }

    @Override
    public synchronized double hitungVolume() {
        double R = super.jariJari;
        double h = tinggiTemberengBola;
        this.volume = (1.0 / 3.0) * Math.PI * h * h * (3 * R - h);
        return this.volume;
    }

    @Override
    public synchronized double hitungLuasPermukaan() {
        double R = super.jariJari;
        double h = tinggiTemberengBola;
        double topi = super.keliling * h;
        double rAlasKuadrat = Math.max(0, h * (2 * R - h));
        double alas = Math.PI * rAlasKuadrat;
        this.luasPermukaan = topi + alas;
        return this.luasPermukaan;
    }

    @Override
    public synchronized void run() {
        try {
            wait();
            System.out.println("[TemberengBola] Volume: " + hitungVolume());
            System.out.println("[TemberengBola] Luas Permukaan: " + hitungLuasPermukaan());
            notifyAll();
        } catch (InterruptedException e) {
            System.err.println("❌ Error pada thread TemberengBola: " + e.getMessage());
            throw new PerhitunganTemberengBolaException("Gagal menjalankan perhitungan tembereng bola", e);
        }
    }
}
