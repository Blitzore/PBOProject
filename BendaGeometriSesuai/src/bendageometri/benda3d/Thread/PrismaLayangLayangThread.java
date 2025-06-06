package bendageometri.benda3d.Thread;

import bendageometri.benda3d.PrismaLayangLayang;

public class PrismaLayangLayangThread implements Runnable {
    private final PrismaLayangLayang prisma;
    private double volume;
    private double luasPermukaan;
    private Exception exception;

    public PrismaLayangLayangThread(PrismaLayangLayang prisma) {
        this.prisma = prisma;
    }

    @Override
    public void run() {
        try {
            // Hitung volume dan luas permukaan secara paralel
            this.volume = prisma.hitungVolume();
            this.luasPermukaan = prisma.hitungLuasPermukaan();
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
            PrismaLayangLayang prisma = new PrismaLayangLayang(10, 6, 5, 5, 8);
            PrismaLayangLayangThread calculator = new PrismaLayangLayangThread(prisma);
            
            Thread thread = new Thread(calculator);
            thread.start();
            
            // Tunggu thread selesai
            thread.join();
            
            System.out.println("Volume Prisma: " + calculator.getVolume());
            System.out.println("Luas Permukaan Prisma: " + calculator.getLuasPermukaan());
            
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}