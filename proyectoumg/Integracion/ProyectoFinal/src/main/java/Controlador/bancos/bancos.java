package Controlador.bancos;


//Ruddyard Castro 9959-23-1409
// Nombres de los geter seter y variables igual al nombre en base de datos
// Nombre del punto java como se llama la tabla 
// Se uso encapsulate field para get y set 
public  class  bancos{

    private int id_banco;
    private String nombre;
  //  private  String direccion;
    
    public bancos() {
    }

    public bancos(int id_banco, String nombre) { 
        this.id_banco = id_banco;
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "bancos{" + "id_banco=" + id_banco + ", nombre=" + nombre + '}';
    }
    
//String direccion;

    public int getId_banco() {
        return id_banco;
    }

    public void setId_banco(int id_banco) {
        this.id_banco = id_banco;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


}

