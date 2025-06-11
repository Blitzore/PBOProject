package bendageometri.benda2d.Thread;

import bendageometri.benda2d.LayangLayang;

public class LayangLayangThread implements Runnable {
    private final LayangLayang layangLayang;
    private double luas;
    private double keliling;
    private Exception exception;

    public LayangLayangThread(LayangLayang layangLayang) {
        this.layangLayang = layangLayang;
    }

    @Override
    public void run() {
        try {
            // Hitung luas dan keliling secara paralel
            this.luas = layangLayang.hitungLuas();
            this.keliling = layangLayang.hitungKeliling();
        } catch (Exception e) {
            this.exception = e;
        }
    }

    public double getLuas() throws Exception {
        if (exception != null) {
            throw exception;
        }
        return luas;
    }

    public double getKeliling() throws Exception {
        if (exception != null) {
            throw exception;
        }
        return keliling;
    }

    public static void main(String[] args) {
        try {
            LayangLayang layang = new LayangLayang(10, 6, 5, 5);
            LayangLayangThread calculator = new LayangLayangThread(layang);
            
            Thread thread = new Thread(calculator);
            thread.start();
            
            // Tunggu thread selesai
            thread.join();
            
            System.out.println("Luas Layang-layang: " + calculator.getLuas());
            System.out.println("Keliling Layang-layang: " + calculator.getKeliling());
            
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}