package bendageometri; // Atau paket lain yang sesuai

import bendageometri.benda2d.Lingkaran;

// Kelas untuk menyimpan hasil perhitungan
class HasilLingkaran {
    double jariJari;
    double luas;
    double keliling;
    String threadName;

    public HasilLingkaran(double jariJari, double luas, double keliling, String threadName) {
        this.jariJari = jariJari;
        this.luas = luas;
        this.keliling = keliling;
        this.threadName = threadName;
    }

    @Override
    public String toString() {
        return String.format("Lingkaran (r=%.2f) oleh Thread %s: Luas=%.2f, Keliling=%.2f",
                jariJari, threadName, luas, keliling);
    }
}

public class LingkaranCalculator implements Runnable {
    private final Lingkaran lingkaran;
    private HasilLingkaran hasil; // Untuk menyimpan hasil

    public LingkaranCalculator(Lingkaran lingkaran) {
        if (lingkaran == null) {
            throw new IllegalArgumentException("Objek Lingkaran tidak boleh null.");
        }
        this.lingkaran = lingkaran;
    }

    @Override
    public void run() {
        // Pekerjaan yang dilakukan oleh thread
        System.out.println("Thread " + Thread.currentThread().getName() + " mulai menghitung untuk Lingkaran r=" + lingkaran.getJariJari());
        double luas = lingkaran.hitungLuas();
        double keliling = lingkaran.hitungKeliling();
        this.hasil = new HasilLingkaran(lingkaran.getJariJari(), luas, keliling, Thread.currentThread().getName());
        System.out.println("Thread " + Thread.currentThread().getName() + " selesai menghitung untuk Lingkaran r=" + lingkaran.getJariJari());
    }

    // Metode untuk mendapatkan hasil setelah thread selesai
    public HasilLingkaran getHasil() {
        return hasil;
    }
}