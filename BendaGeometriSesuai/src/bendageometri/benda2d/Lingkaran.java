package bendageometri.benda2d;

public class Lingkaran extends Benda2D implements Runnable {
    // Atribut publik agar bisa diakses oleh kelas turunan
    public double jariJari;
    public double luas;
    public double keliling;

    // Exception kustom dari file asli
    public static class PerhitunganLingkaranException extends Exception {
        public PerhitunganLingkaranException(String message) {
            super(message);
        }
    }

    // Konstruktor utama
    public Lingkaran(double jariJari) throws PerhitunganLingkaranException {
        try {
            // 1. Validasi input di dalam blok try
            if (jariJari <= 0) {
                // Melempar exception jika validasi gagal
                throw new PerhitunganLingkaranException("Jari-jari lingkaran harus bernilai positif.");
            }

            this.jariJari = jariJari;
            this.luas = hitungLuas();
            this.keliling = hitungKeliling();

        } catch (PerhitunganLingkaranException e) {
            throw e;
        }
    }
    
    @Override
    public double hitungLuas() {
        // Rumus luas lingkaran = PI * r^2
        this.luas = Math.PI * Math.pow(this.jariJari, 2);
        return this.luas;
    }
    
    // Overloading untuk hitungLuas dengan parameter
    public double hitungLuas(double jariJari) throws PerhitunganLingkaranException {
        if (jariJari <= 0) {
            throw new PerhitunganLingkaranException("Jari-jari untuk perhitungan luas harus positif.");
        }
        // Metode ini hanya menghitung tanpa mengubah atribut instance
        return Math.PI * Math.pow(jariJari, 2);
    }
    
    @Override
    public double hitungKeliling() {
        // Rumus keliling lingkaran = 2 * PI * r
        this.keliling = 2 * Math.PI * this.jariJari;
        return this.keliling;
    }
    
    // Overloading untuk hitungKeliling dengan parameter
    public double hitungKeliling(double jariJari) throws PerhitunganLingkaranException {
        if (jariJari <= 0) {
            throw new PerhitunganLingkaranException("Jari-jari untuk perhitungan keliling harus positif.");
        }
        // Metode ini hanya menghitung tanpa mengubah atribut instance
        return 2 * Math.PI * jariJari;
    }
    
    @Override
    public void run() {
        try {
            System.out.println("-> [Mulai] Thread untuk Lingkaran r=" + this.jariJari);
            long jeda = (long) (Math.random() * 2000 + 1000);
            Thread.sleep(jeda);
            System.out.println("<- [Selesai] Lingkaran (setelah " + jeda + " ms)");
            System.out.printf("   > Luas: %.2f, Keliling: %.2f\n", this.luas, this.keliling);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Thread untuk Lingkaran diinterupsi.");
        }
    }
}
