/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bendageometri.benda2d;

import bendageometri.BendaGeometri;

/**
 *
 * @author nbnrc
 */
abstract class Benda2D implements BendaGeometri {
    @Override
    public abstract double hitungLuas();

    @Override
    public abstract double hitungKeliling();
}
