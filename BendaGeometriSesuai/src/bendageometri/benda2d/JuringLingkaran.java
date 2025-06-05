/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bendageometri.benda2d;

/**
 *
 * @author nbnrc
 */
public class JuringLingkaran extends Lingkaran {
    private double sudutPusatDerajat; // Sudut pusat dalam derajat

    // Konstruktor utama
    public JuringLingkaran(double jariJari, double sudutPusatDerajat) {
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
            throw new IllegalArgumentException("Sudut pusat juring harus lebih besar dari 0 dan kurang dari 360 derajat.");
        }
        this.sudutPusatDerajat = sudutPusatDerajat;
    }

    @Override
    public double hitungLuas() {
        // Luas juring = (sudutPusatDerajat / 360.0) * LuasLingkaranInduk
        return (this.sudutPusatDerajat / 360.0) * super.hitungLuas();
    }

    @Override
    public double hitungKeliling() {
        // Keliling juring = 2 * jari-jari + panjang busur
        // Panjang busur = (sudutPusatDerajat / 360.0) * KelilingLingkaranInduk
        double panjangBusur = (this.sudutPusatDerajat / 360.0) * super.hitungKeliling();
        return (2 * getJariJari()) + panjangBusur;
    }
}
