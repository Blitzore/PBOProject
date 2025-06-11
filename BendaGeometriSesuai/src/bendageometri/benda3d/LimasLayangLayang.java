package bendageometri.benda3d;

import bendageometri.benda2d.LayangLayang;

public class LimasLayangLayang extends LayangLayang implements Runnable {
    public double tinggiLimas;
    public double volume;
    public double luasPermukaan;

    public LimasLayangLayang(double d1Alas, double d2Alas, 
            double sisiAAlas, double sisiBAlas, double tinggiLimas) {
        super(d1Alas, d2Alas, sisiAAlas, sisiBAlas);
        
        if (tinggiLimas <= 0) {
            throw new IllegalArgumentException("Tinggi limas harus bernilai positif.");
        }
        
        this.tinggiLimas = tinggiLimas;
        this.volume = hitungVolume();
        this.luasPermukaan = hitungLuasPermukaan();
    }

    @Override
    public double hitungVolume() {
        this.volume = (1.0 / 3.0) * super.hitungLuas() * this.tinggiLimas;
        return this.volume;
    }

    @Override
    public double hitungLuasPermukaan() {
        System.err.println("Peringatan: hitungLuasPermukaan() mungkin tidak akurat.");
        this.luasPermukaan = super.hitungLuas(); // hanya luas alas
        return this.luasPermukaan;
    }

    public double hitungLuasPermukaan(double tinggiSisiTegakA, double tinggiSisiTegakB) {
        if (tinggiSisiTegakA <= 0 || tinggiSisiTegakB <= 0) {
            throw new IllegalArgumentException("Tinggi sisi tegak harus bernilai positif.");
        }

        double luasAlas = super.hitungLuas();
        double luasSisiTegak = 2 * (0.5 * sisiA * tinggiSisiTegakA) + 
                               2 * (0.5 * sisiB * tinggiSisiTegakB);
        this.luasPermukaan = luasAlas + luasSisiTegak;
        return this.luasPermukaan;
    }

    @Override
    public synchronized void run() {
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("[LimasLayangLayang] Volume: " + hitungVolume());
        System.out.println("[LimasLayangLayang] Luas Permukaan: " + hitungLuasPermukaan());
        notifyAll();
    }
}