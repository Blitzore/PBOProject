/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bendageometri.benda2d;

/**
 *
 * @author nbnrc
 */
public class TemberengLingkaran extends Lingkaran {
    private double sudutPusatDerajat; // Sudut pusat dalam derajat yang membentuk tembereng

    // Konstruktor utama
    public TemberengLingkaran(double jariJari, double sudutPusatDerajat) {
        super(jariJari); // Memanggil konstruktor Lingkaran
        setSudutPusatDerajat(sudutPusatDerajat);
    }


    // Getter untuk sudutPusatDerajat
    public double getSudutPusatDerajat() {
        return sudutPusatDerajat;
    }

    // Setter untuk sudutPusatDerajat dengan validasi
    public void setSudutPusatDerajat(double sudutPusatDerajat) {
        if (sudutPusatDerajat <= 0 || sudutPusatDerajat >= 360) {
            throw new IllegalArgumentException("Sudut pusat untuk tembereng harus lebih besar dari 0 dan kurang dari 360 derajat.");
        }
        this.sudutPusatDerajat = sudutPusatDerajat;
    }

    @Override
    public double hitungLuas() {
        // Luas Tembereng = Luas Juring - Luas Segitiga yang dibentuk oleh dua jari-jari dan tali busur
        // Luas Juring = (sudut / 360) * PI * r^2
        // Luas Segitiga = 0.5 * r^2 * sin(sudut dalam radian)
        double sudutRadian = Math.toRadians(this.sudutPusatDerajat);
        double jariJariKuadrat = Math.pow(getJariJari(), 2);
        
        // Ini adalah rumus langsung untuk luas tembereng:
        // Luas = r^2 / 2 * (sudut_radian - sin(sudut_radian))
        return 0.5 * jariJariKuadrat * (sudutRadian - Math.sin(sudutRadian));
    }

    @Override
    public double hitungKeliling() {
        // Keliling Tembereng = Panjang Busur + Panjang Tali Busur
        double sudutRadian = Math.toRadians(this.sudutPusatDerajat);
        double r = getJariJari();

        // Panjang Busur = r * sudut_radian
        double panjangBusur = r * sudutRadian;

        // Panjang Tali Busur = 2 * r * sin(sudut_radian / 2)
        double panjangTaliBusur = 2 * r * Math.sin(sudutRadian / 2.0);
        
        return panjangBusur + panjangTaliBusur;
    }
}
