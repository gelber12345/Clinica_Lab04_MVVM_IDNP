package com.idnp.lab04mvvm.model;

public class Visita {
    private String peso;
    private String temperatura;
    private String presion;
    private String saturacion;

    public Visita(){
        this.peso = "";
        this.temperatura = "";
        this.presion = "";
        this.saturacion = "";
    }
    public Visita(String peso, String temperatura, String presion, String saturacion) {
        this.peso = peso;
        this.temperatura = temperatura;
        this.presion = presion;
        this.saturacion = saturacion;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(String temperatura) {
        this.temperatura = temperatura;
    }

    public String getPresion() {
        return presion;
    }

    public void setPresion(String presion) {
        this.presion = presion;
    }

    public String getSaturacion() {
        return saturacion;
    }

    public void setSaturacion(String saturacion) {
        this.saturacion = saturacion;
    }

    @Override
    public String toString() {
        return "{" +
                "peso='" + peso + '\'' +
                ", Temperatura='" + temperatura + '\'' +
                ", presion='" + presion + '\'' +
                ", saturacion='" + saturacion + '\'' +
                '}';
    }
}
