/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bendageometri.benda2d;

/**
 *
 * @author nbnrc
 */
public class LayangLayang extends Benda2D {
    private double diagonal1; // Biasanya diagonal yang menjadi sumbu simetri
    private double diagonal2; // Diagonal yang dibagi dua oleh diagonal1
    private double sisiA;     // Panjang sepasang sisi pertama yang berdekatan (sama panjang)
    private double sisiB;     // Panjang sepasang sisi kedua yang berdekatan (sama panjang)

    // Konstruktor utama
    public LayangLayang(double diagonal1, double diagonal2, double sisiA, double sisiB) {
        if (diagonal1 <= 0 || diagonal2 <= 0 || sisiA <= 0 || sisiB <= 0) {
            throw new IllegalArgumentException("Semua dimensi layang-layang (diagonal1, diagonal2, sisiA, sisiB) harus bernilai positif.");
        }

        // Validasi geometris:
        // Asumsi: diagonal1 adalah sumbu simetri, diagonal2 tegak lurus dan dibagi dua oleh diagonal1.
        // sisiA dan sisiB adalah sisi-sisi yang membentuk puncak pada diagonal1.
        // Setengah dari diagonal2 (d2/2) adalah salah satu sisi siku-siku dari dua segitiga yang berbeda.
        // Sisi miring dari segitiga-segitiga tersebut adalah sisiA dan sisiB.
        // Bagian-bagian dari diagonal1 (misal p dan q, dimana p+q = diagonal1) adalah sisi siku-siku lainnya.
        // Jadi, p = sqrt(sisiA^2 - (diagonal2/2)^2) dan q = sqrt(sisiB^2 - (diagonal2/2)^2).
        // Dan p + q harus sama dengan diagonal1.

        if (sisiA <= diagonal2 / 2.0 || sisiB <= diagonal2 / 2.0) {
            throw new IllegalArgumentException(
                String.format("Sisi-sisi layang-layang (sisiA=%.2f, sisiB=%.2f) harus lebih panjang dari setengah diagonal2 (d2/2=%.2f).",
                sisiA, sisiB, diagonal2 / 2.0)
            );
        }

        double d2_setengah = diagonal2 / 2.0;
        double p_segment = Math.sqrt(Math.pow(sisiA, 2) - Math.pow(d2_setengah, 2));
        double q_segment = Math.sqrt(Math.pow(sisiB, 2) - Math.pow(d2_setengah, 2));

        // Menggunakan toleransi untuk perbandingan floating point
        double epsilon = 1e-9; 
        if (Math.abs((p_segment + q_segment) - diagonal1) > epsilon) {
            throw new IllegalArgumentException(
                String.format("Dimensi yang diberikan (d1=%.2f, d2=%.2f, sisiA=%.2f, sisiB=%.2f) tidak membentuk layang-layang yang valid. \nDengan d2, sisiA, dan sisiB tersebut, diagonal1 seharusnya sekitar %.2f.",
                diagonal1, diagonal2, sisiA, sisiB, (p_segment + q_segment))
            );
        }
        // Memastikan tidak ada NaN dari Math.sqrt jika validasi di atasnya lolos
        if (Double.isNaN(p_segment) || Double.isNaN(q_segment)){
            throw new IllegalArgumentException("Kombinasi sisi dan diagonal tidak valid (menyebabkan akar negatif).");
        }


        this.diagonal1 = diagonal1;
        this.diagonal2 = diagonal2;
        this.sisiA = sisiA;
        this.sisiB = sisiB;
    }

    // Overloaded constructor: Membuat layang-layang dari dua pasang sisi yang berdekatan
    // dan salah satu diagonalnya (diagonal2, yang menghubungkan titik temu sisi berbeda).
    // Diagonal utama (diagonal1) akan dihitung.
    public LayangLayang(double sisiPendek, double sisiPanjang, double diagonalPenghubungSisiBerbeda) {
        if (sisiPendek <= 0 || sisiPanjang <= 0 || diagonalPenghubungSisiBerbeda <= 0) {
            throw new IllegalArgumentException("Dimensi sisi-sisi dan diagonal penghubung harus bernilai positif.");
        }
        if (sisiPendek == sisiPanjang) {
            // Ini akan menjadi belah ketupat, bisa di-handle atau lempar eksepsi jika ingin layang-layang non-belahketupat
            // Untuk sekarang, kita biarkan (belah ketupat adalah kasus khusus layang-layang)
        }
        
        // diagonalPenghubungSisiBerbeda adalah d2.
        // sisiPendek adalah sisiA, sisiPanjang adalah sisiB
        this.sisiA = sisiPendek;
        this.sisiB = sisiPanjang;
        this.diagonal2 = diagonalPenghubungSisiBerbeda;

        if (this.sisiA <= this.diagonal2 / 2.0 || this.sisiB <= this.diagonal2 / 2.0) {
            throw new IllegalArgumentException(
                String.format("Sisi-sisi layang-layang (%.2f, %.2f) harus lebih panjang dari setengah diagonal penghubung (d2/2=%.2f).",
                this.sisiA, this.sisiB, this.diagonal2 / 2.0)
            );
        }

        double d2_setengah = this.diagonal2 / 2.0;
        double p_segment = Math.sqrt(Math.pow(this.sisiA, 2) - Math.pow(d2_setengah, 2));
        double q_segment = Math.sqrt(Math.pow(this.sisiB, 2) - Math.pow(d2_setengah, 2));
        
        if (Double.isNaN(p_segment) || Double.isNaN(q_segment)){
             throw new IllegalArgumentException("Kombinasi sisi dan diagonal tidak valid untuk menghitung diagonal1 (menyebabkan akar negatif).");
        }
        this.diagonal1 = p_segment + q_segment;
        
        if (this.diagonal1 <= 0) { // Seharusnya tidak terjadi jika validasi sebelumnya lolos
            throw new IllegalStateException("Perhitungan diagonal1 menghasilkan nilai tidak valid.");
        }
    }


    // Getter
    public double getDiagonal1() {
        return diagonal1;
    }

    public double getDiagonal2() {
        return diagonal2;
    }

    public double getSisiA() {
        return sisiA; // Salah satu dari dua sisi yang sama panjang (misal, pasangan sisi atas)
    }

    public double getSisiB() {
        return sisiB; // Salah satu dari dua sisi lain yang sama panjang (misal, pasangan sisi bawah)
    }


    @Override
    public double hitungLuas() {
        // Luas Layang-layang = 0.5 * diagonal1 * diagonal2
        return 0.5 * this.diagonal1 * this.diagonal2;
    }

    @Override
    public double hitungKeliling() {
        // Keliling Layang-layang = 2 * (sisiA + sisiB)
        return 2 * (this.sisiA + this.sisiB);
    }
}

