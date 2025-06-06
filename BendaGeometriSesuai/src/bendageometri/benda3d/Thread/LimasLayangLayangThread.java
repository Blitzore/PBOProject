package bendageometri.benda3d.Thread;

import bendageometri.benda3d.LimasLayangLayang;

public class LimasLayangLayangThread implements Runnable {
    private final LimasLayangLayang limas;
    private double volume;
    private double luasPermukaan;
    private Exception exception;

    public LimasLayangLayangThread(LimasLayangLayang limas) {
        this.limas = limas;
    }

    @Override
    public void run() {
        try {
            // Hitung volume dan luas permukaan secara paralel
            this.volume = limas.hitungVolume();
            this.luasPermukaan = limas.hitungLuasPermukaan();
        } catch (Exception e) {
            this.exception = e;
        }
    }

    public double getVolume() throws Exception {
        if (exception != null) {
            throw exception;
        }
        return volume;
    }

    public double getLuasPermukaan() throws Exception {
        if (exception != null) {
            throw exception;
        }
        return luasPermukaan;
    }

    public static void main(String[] args) {
        try {
            LimasLayangLayang limas = new LimasLayangLayang(10, 6, 5, 5, 8);
            LimasLayangLayangThread calculator = new LimasLayangLayangThread(limas);
            
            Thread thread = new Thread(calculator);
            thread.start();
            
            // Tunggu thread selesai
            thread.join();
            
            System.out.println("Volume Limas: " + calculator.getVolume());
            System.out.println("Luas Permukaan Limas: " + calculator.getLuasPermukaan());
            
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}