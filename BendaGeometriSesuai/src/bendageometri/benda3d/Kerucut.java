package bendageometri.benda3d;

import bendageometri.benda2d.Lingkaran;

/**
 * Representasi objek Kerucut yang merupakan bangun ruang 3D berbasis alas
 * lingkaran.
 */
public class Kerucut extends Lingkaran implements Runnable {
    public static class PerhitunganKerucutException extends RuntimeException {
        public PerhitunganKerucutException(String message) {
            super(message);
        }
    }

    public final double tinggiKerucut;
    public final double garisPelukis;
    public volatile double volume;
    public volatile double luasPermukaan;

    public Kerucut(double jariJari, double tinggiKerucut) {
        super(jariJari);
        if (tinggiKerucut <= 0) {
            throw new IllegalArgumentException("Tinggi kerucut harus lebih dari 0.");
        }
        this.tinggiKerucut = tinggiKerucut;
        this.garisPelukis = Math.sqrt(jariJari * jariJari + tinggiKerucut * tinggiKerucut);
        if (Double.isNaN(this.garisPelukis) || this.garisPelukis <= 0) {
            throw new PerhitunganKerucutException("Perhitungan garis pelukis tidak valid.");
        }
    }

    @Override
    public synchronized double hitungVolume() {
        this.volume = (1.0 / 3.0) * super.luas * tinggiKerucut;
        return this.volume;
    }

    @Override
    public synchronized double hitungLuasPermukaan() {
        double luasAlas = super.luas;
        double luasSelimut = Math.PI * super.jariJari * garisPelukis;
        this.luasPermukaan = luasAlas + luasSelimut;
        return this.luasPermukaan;
    }

    @Override
    public synchronized void run() {
        try {
            wait();
            System.out.println("[Kerucut] Volume: " + hitungVolume());
            System.out.println("[Kerucut] Luas Permukaan: " + hitungLuasPermukaan());
            notifyAll();
        } catch (InterruptedException e) {
            System.err.println("❌ Error pada thread Kerucut: " + e.getMessage());
            throw new PerhitunganKerucutException("Gagal menjalankan perhitungan kerucut");
        }
    }
}
