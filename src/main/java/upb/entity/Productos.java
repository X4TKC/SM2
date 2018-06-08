package upb.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "productos")
public class Productos implements Serializable {

    @Id
    @Column(name = "IDProductos")
    private String IDProductos;

    @Column(name = "nombre_pro")
    private String nombre_pro;

    @Column(name = "Descripcion")
    private String Descripcion;

    @Column(name = "precio")
    private String precio;



    public String getIDProductos() {
        return IDProductos;
    }

    public void setIDProductos(String IDProductos) {
        this.IDProductos = IDProductos;
    }

    public String getNombre_pro() {
        return nombre_pro;
    }

    public void setNombre_pro(String nombre_pro) {
        this.nombre_pro = nombre_pro;
    }

    public String getDescripcion() {

        return Descripcion;
    }

    public void setDescripcion(String descripcion)
    {

        this.Descripcion = descripcion;
    }
    public String getPrecio() {

        return precio;
    }

    public void setPrecio(String precio)
    {
        this.precio = precio;
    }


    @Override
    public String toString() {
        return IDProductos + "\t" + nombre_pro + "\t" + Descripcion + "\t" + precio;
    }
}