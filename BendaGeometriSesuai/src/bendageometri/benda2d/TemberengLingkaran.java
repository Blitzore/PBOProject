package bendageometri.benda2d;

import java.util.concurrent.*;

public class TemberengLingkaran extends Lingkaran implements Runnable {
    public static class PerhitunganTemberengException extends RuntimeException {
        public PerhitunganTemberengException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    private volatile double sudutPusatDerajat;

    public TemberengLingkaran(double jariJari, double sudutPusatDerajat) {
        super(jariJari);
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
    public synchronized double hitungLuas() {
        if (sudutPusatDerajat <= 0 || sudutPusatDerajat >= 360) {
            System.err.println("❌ Tidak bisa hitung luas: sudut tidak valid");
            return -1.0;
        }
        double sudutRadian = Math.toRadians(sudutPusatDerajat);
        double jariJariKuadrat = Math.pow(super.jariJari, 2);
        double luasJuring = (sudutPusatDerajat / 360.0) * super.luas;
        double luasSegitiga = 0.5 * jariJariKuadrat * Math.sin(sudutRadian);
        this.luas = luasJuring - luasSegitiga;
        return this.luas;
    }

    @Override
    public synchronized double hitungKeliling() {
        if (sudutPusatDerajat <= 0 || sudutPusatDerajat >= 360) {
            System.err.println("❌ Tidak bisa hitung keliling: sudut tidak valid");
            return -1.0;
        }
        double sudutRadian = Math.toRadians(sudutPusatDerajat);
        double panjangBusur = super.jariJari * sudutRadian;
        double panjangTaliBusur = 2 * super.getJariJari() * Math.sin(sudutRadian / 2.0);
        this.keliling = panjangBusur + panjangTaliBusur;
        return this.keliling;
    }

    @Override
    public synchronized void run() {
        try {
            wait();
            System.out.println("[TemberengLingkaran] Luas: " + hitungLuas());
            System.out.println("[TemberengLingkaran] Keliling: " + hitungKeliling());
            notifyAll();
        } catch (InterruptedException e) {
            System.err.println("❌ Error pada thread TemberengLingkaran: " + e.getMessage());
            throw new PerhitunganTemberengException("Gagal menjalankan perhitungan tembereng lingkaran", e);
        }
    }
}
