/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bendageometri.benda2d;

/**
 *
 * @author nbnrc
 */
public class BelahKetupat extends Benda2D {
    public double sisi;
    public double diagonal1;
    public double diagonal2;

    public double luas;
    public double keliling;

    // Konstruktor utama
    public BelahKetupat(double sisi, double diagonal1, double diagonal2) throws IllegalArgumentException, IllegalStateException {
        if (sisi <= 0 || diagonal1 <= 0 || diagonal2 <= 0) {
            throw new IllegalArgumentException("Dimensi sisi dan kedua diagonal belah ketupat harus bernilai positif.");
        }

        // Validasi geometris: (d1/2)^2 + (d2/2)^2 = sisi^2.
        double d1_setengah_kuadrat = Math.pow(diagonal1 / 2.0, 2);
        double d2_setengah_kuadrat = Math.pow(diagonal2 / 2.0, 2);
        double sisi_kuadrat_dihitung = d1_setengah_kuadrat + d2_setengah_kuadrat;
        double sisi_kuadrat_diberikan = Math.pow(sisi, 2);

        // Menggunakan toleransi untuk perbandingan floating point
        double epsilon = 1e-9;
        if (Math.abs(sisi_kuadrat_dihitung - sisi_kuadrat_diberikan) > epsilon) {
            throw new IllegalArgumentException(
                String.format("Sisi dan diagonal yang diberikan tidak membentuk belah ketupat yang valid. Untuk d1=%.2f dan d2=%.2f, sisi seharusnya sekitar %.2f.",
                diagonal1, diagonal2, Math.sqrt(sisi_kuadrat_dihitung))
            );
        }

        this.sisi = sisi;
        this.diagonal1 = diagonal1;
        this.diagonal2 = diagonal2;

        // Hitung dan simpan luas serta keliling saat objek dibuat
        this.luas = hitungLuas();
        this.keliling = hitungKeliling();
    }
    
    /**
     * Overloaded constructor untuk membuat objek BelahKetupat hanya dengan diagonal.
     * @param diagonal1 Panjang diagonal pertama.
     * @param diagonal2 Panjang diagonal kedua.
     * @throws IllegalArgumentException Jika diagonal bernilai nol atau negatif.
     * @throws IllegalStateException Jika perhitungan internal menghasilkan nilai tidak valid.
     */
    public BelahKetupat(double diagonal1, double diagonal2) throws IllegalArgumentException, IllegalStateException {
        if (diagonal1 <= 0 || diagonal2 <= 0) {
            throw new IllegalArgumentException("Kedua diagonal belah ketupat harus bernilai positif.");
        }
        this.diagonal1 = diagonal1;
        this.diagonal2 = diagonal2;
        // Hitung sisi berdasarkan diagonal: sisi = sqrt((d1/2)^2 + (d2/2)^2)
        this.sisi = Math.sqrt(Math.pow(diagonal1 / 2.0, 2) + Math.pow(diagonal2 / 2.0, 2));
        if (Double.isNaN(this.sisi) || this.sisi <=0) {
             throw new IllegalStateException("Perhitungan sisi menghasilkan nilai tidak valid.");
        }

        // Hitung dan simpan luas serta keliling saat objek dibuat
        this.luas = hitungLuas();
        this.keliling = hitungKeliling();
    }


    @Override
    public double hitungLuas() {
        // Luas Belah Ketupat = 0.5 * diagonal1 * diagonal2
        this.luas = 0.5 * this.diagonal1 * this.diagonal2;
        return this.luas;
    }

    /**
     * Menghitung luas belah ketupat berdasarkan nilai diagonal yang diberikan sebagai parameter.
     * @param d1 Panjang diagonal pertama untuk perhitungan.
     * @param d2 Panjang diagonal kedua untuk perhitungan.
     * @return Luas belah ketupat.
     * @throws IllegalArgumentException Jika diagonal bernilai nol atau negatif.
     */
    public double hitungLuas(double d1, double d2) {
        if (d1 <= 0 || d2 <= 0) {
            throw new IllegalArgumentException("Kedua diagonal untuk perhitungan luas harus bernilai positif.");
        }
        return 0.5 * d1 * d2;
    }

    @Override
    public double hitungKeliling() {
        // Keliling Belah Ketupat = 4 * sisi
        this.keliling = 4 * this.sisi;
        return this.keliling;
    }

    /**
     * Menghitung keliling belah ketupat berdasarkan nilai sisi yang diberikan sebagai parameter.
     * @param s Panjang sisi untuk perhitungan.
     * @return Keliling belah ketupat.
     * @throws IllegalArgumentException Jika sisi bernilai nol atau negatif.
     */
    public double hitungKeliling(double s) {
        if (s <= 0) {
            throw new IllegalArgumentException("Sisi untuk perhitungan keliling harus bernilai positif.");
        }
        return 4 * s;
    }
}
