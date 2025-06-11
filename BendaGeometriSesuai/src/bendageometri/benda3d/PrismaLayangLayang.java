package bendageometri.benda3d;

import bendageometri.benda2d.LayangLayang;

public class PrismaLayangLayang extends LayangLayang implements Runnable {
    public double tinggiPrisma;
    public double volume;
    public double luasPermukaan;

    public PrismaLayangLayang(double d1Alas, double d2Alas, 
            double sisiAAlas, double sisiBAlas, double tinggiPrisma) {
        super(d1Alas, d2Alas, sisiAAlas, sisiBAlas);
        
        if (tinggiPrisma <= 0) {
            throw new IllegalArgumentException("Tinggi prisma harus bernilai positif.");
        }
        
        this.tinggiPrisma = tinggiPrisma;
        this.volume = hitungVolume();
        this.luasPermukaan = hitungLuasPermukaan();
    }

    @Override
    public double hitungVolume() {
        this.volume = super.hitungLuas() * this.tinggiPrisma;
        return this.volume;
    }

    @Override
    public double hitungLuasPermukaan() {
        this.luasPermukaan = (2 * super.hitungLuas()) + (super.hitungKeliling() * this.tinggiPrisma);
        return this.luasPermukaan;
    }

    @Override
    public synchronized void run() {
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("[PrismaLayangLayang] Volume: " + hitungVolume());
        System.out.println("[PrismaLayangLayang] Luas Permukaan: " + hitungLuasPermukaan());
        notifyAll();
    }
}