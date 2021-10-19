package com.idnp.lab04mvvm.model;
import java.util.ArrayList;
public class Cliente {
    private String nombre="";
    private String dni="";
    private String direccion="";
    private String correo="";
    private ArrayList<Visita> visitas=new ArrayList<>();

    public Cliente() {
        this.nombre = "";
        this.dni = "";
        this.direccion = "";
        this.correo = "";
    }
    public Cliente(String nombre, String dni, String direccion, String correo) {
        this.nombre = nombre;
        this.dni = dni;
        this.direccion = direccion;
        this.correo = correo;
    }

    public ArrayList<Visita> getVisitas() {
        return visitas;
    }

    public void setVisitas(Visita visita) {
        this.visitas.add(visita);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public String toString(){
        String vis="";
        for (int i=0;i<visitas.size();i++){
            vis+= "Visita N: "+(i+1)+visitas.get(i).toString() +"\n";
        }
        String res= "Nombre del Paciente :" + nombre +
                "\nDireccion: " + direccion +
                "\nDNI: " + dni+
                "\nCorreo: "+correo+
                "\n"+vis;
        return res;
    }

}
