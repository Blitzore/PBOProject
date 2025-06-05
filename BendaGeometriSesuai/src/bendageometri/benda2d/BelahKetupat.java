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
    private double sisi;
    private double diagonal1;
    private double diagonal2;

    // Konstruktor utama
    public BelahKetupat(double sisi, double diagonal1, double diagonal2) {
        if (sisi <= 0 || diagonal1 <= 0 || diagonal2 <= 0) {
            throw new IllegalArgumentException("Dimensi sisi dan kedua diagonal belah ketupat harus bernilai positif.");
        }

        // Validasi geometris: Dalam belah ketupat, setengah dari masing-masing diagonal dan satu sisi
        // membentuk segitiga siku-siku. Jadi, (d1/2)^2 + (d2/2)^2 = sisi^2.
        // Ini berarti setiap diagonal harus lebih kecil dari 2 * sisi.
        // Dan juga, 4 * sisi^2 harus sama dengan d1^2 + d2^2.
        // Kita perlu memeriksa apakah nilai yang diberikan konsisten.
        // Toleransi kecil mungkin diperlukan untuk perbandingan double.
        double d1_setengah_kuadrat = Math.pow(diagonal1 / 2.0, 2);
        double d2_setengah_kuadrat = Math.pow(diagonal2 / 2.0, 2);
        double sisi_kuadrat_dihitung = d1_setengah_kuadrat + d2_setengah_kuadrat;
        double sisi_kuadrat_diberikan = Math.pow(sisi, 2);

        // Menggunakan toleransi untuk perbandingan floating point
        double epsilon = 1e-9; // Toleransi kecil
        if (Math.abs(sisi_kuadrat_dihitung - sisi_kuadrat_diberikan) > epsilon) {
            throw new IllegalArgumentException(
                String.format("Sisi dan diagonal yang diberikan tidak membentuk belah ketupat yang valid. Untuk d1=%.2f dan d2=%.2f, sisi seharusnya sekitar %.2f.",
                diagonal1, diagonal2, Math.sqrt(sisi_kuadrat_dihitung))
            );
        }

        this.sisi = sisi;
        this.diagonal1 = diagonal1;
        this.diagonal2 = diagonal2;
    }
    
    // Konstruktor hanya dengan diagonal1 dan diagonal2. Sisi dapat dihitung.
    public BelahKetupat(double diagonal1, double diagonal2) {
        if (diagonal1 <= 0 || diagonal2 <= 0) {
            throw new IllegalArgumentException("Kedua diagonal belah ketupat harus bernilai positif.");
        }
        this.diagonal1 = diagonal1;
        this.diagonal2 = diagonal2;
        // Hitung sisi berdasarkan diagonal: sisi = sqrt((d1/2)^2 + (d2/2)^2)
        this.sisi = Math.sqrt(Math.pow(diagonal1 / 2.0, 2) + Math.pow(diagonal2 / 2.0, 2));
        if (this.sisi <=0) { // Seharusnya tidak terjadi jika d1 dan d2 positif
             throw new IllegalStateException("Perhitungan sisi menghasilkan nilai tidak valid.");
        }
    }


    // Getter
    public double getSisi() {
        return sisi;
    }

    public double getDiagonal1() {
        return diagonal1;
    }

    public double getDiagonal2() {
        return diagonal2;
    }

    // Tidak ada setter publik untuk menjaga imutabilitas.

    @Override
    public double hitungLuas() {
        // Luas Belah Ketupat = 0.5 * diagonal1 * diagonal2
        return 0.5 * this.diagonal1 * this.diagonal2;
    }

    @Override
    public double hitungKeliling() {
        // Keliling Belah Ketupat = 4 * sisi
        return 4 * this.sisi;
    }
}
