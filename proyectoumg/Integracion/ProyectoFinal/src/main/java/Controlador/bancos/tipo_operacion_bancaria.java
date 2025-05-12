package Controlador.bancos;


//Ruddyard Castro 9959-23-1409
// Nombres de los geter seter y variables igual al nombre en base de datos
// Nombre del punto java como se llama la tabla 
// Se uso encapsulate field para get y set 
public  class  tipo_operacion_bancaria{

      private int id_tipo_operacion;
    private String tipo_operacion;
    private  String descripcion ;

    
    
    public tipo_operacion_bancaria() {
    }

    public tipo_operacion_bancaria(int id_tipo_operacion, String tipo_operacion, String descripcion) {
        this.id_tipo_operacion = id_tipo_operacion;
        this.tipo_operacion = tipo_operacion;
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "tipo_operacion_bancaria{" + "id_tipo_operacion=" + id_tipo_operacion + ", tipo_operacion=" + tipo_operacion + ", descripcion=" + descripcion + '}';
    }

  
    public int getId_tipo_operacion() {
        return id_tipo_operacion;
    }

    public void setId_tipo_operacion(int id_tipo_operacion) {
        this.id_tipo_operacion = id_tipo_operacion;
    }

    public String getTipo_operacion() {
        return tipo_operacion;
    }

    public void setTipo_operacion(String tipo_operacion) {
        this.tipo_operacion = tipo_operacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }



}

