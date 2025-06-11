package bendageometri.benda2d;

public class JuringLingkaran extends Lingkaran implements Runnable {
    public static class PerhitunganJuringException extends RuntimeException {
        public PerhitunganJuringException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    private volatile double sudutPusatDerajat;

    public JuringLingkaran(double jariJari, double sudutPusatDerajat) {
        super(jariJari);
        setSudutPusatDerajat(sudutPusatDerajat);
    }

    public double getSudutPusatDerajat() {
        return sudutPusatDerajat;
    }

    public void setSudutPusatDerajat(double sudutPusatDerajat) {
        if (sudutPusatDerajat <= 0 || sudutPusatDerajat >= 360) {
            System.err.println("❌ Sudut tidak valid untuk juring: " + sudutPusatDerajat);
            this.sudutPusatDerajat = -1;
        } else {
            this.sudutPusatDerajat = sudutPusatDerajat;
        }
    }

    @Override
    public synchronized double hitungLuas() {
        if (sudutPusatDerajat <= 0 || sudutPusatDerajat >= 360) {
            System.err.println("❌ Tidak bisa menghitung luas: sudut tidak valid");
            return -1.0;
        }
        this.luas = (sudutPusatDerajat / 360.0) * super.luas;
        return this.luas;
    }

    @Override
    public synchronized double hitungKeliling() {
        if (sudutPusatDerajat <= 0 || sudutPusatDerajat >= 360) {
            System.err.println("❌ Tidak bisa menghitung keliling: sudut tidak valid");
            return -1.0;
        }
        double panjangBusur = (sudutPusatDerajat / 360.0) * super.keliling;
        this.keliling = 2 * getJariJari() + panjangBusur;
        return this.keliling;
    }

    @Override
    public synchronized void run() {
        try {
            wait();
            System.out.println("[JuringLingkaran] Luas: " + hitungLuas());
            System.out.println("[JuringLingkaran] Keliling: " + hitungKeliling());
            notifyAll();
        } catch (InterruptedException e) {
            System.err.println("❌ Error pada thread JuringLingkaran: " + e.getMessage());
            throw new PerhitunganJuringException("Gagal menjalankan perhitungan juring lingkaran", e);
        }
    }
}
