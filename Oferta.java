/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model;

/**
 *
 * @author Dell
 */
public class Oferta {
    protected double precio_ofertado;
    
    public Oferta (double precio_ofertado){
        this.precio_ofertado = precio_ofertado;
    }

    public double getPrecio_ofertado() {
        return precio_ofertado;
    }

    public void setPrecio_ofertado(double precio_ofertado) {
        this.precio_ofertado = precio_ofertado;
    }
    
    @Override
    public String toString(){
       return "oferta: "+this.precio_ofertado; 
    }
}
