package upb.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "usuario" )
public class Usuario implements Serializable {

	@Column(name = "Nombre")
	private String nombre;
	@Id
	@Column(name = "Correo", unique= true)
	private String correo;

	@Column(name = "Contrasena")
	private String contrasena;
	@Column(name = "factura")
	private String factura;
	@Column(name = "Celular")
	private int celular;
	@Column(name = "Nit")
	private int nit;

	public Usuario(){}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getContrasena() {

		return contrasena;
	}

	public void setContrasena(String contrasena)
	{

		this.contrasena = contrasena;
	}
	public String getFactura() {

		return factura;
	}

	public void setFactura(String factura)
	{
		this.factura = factura;
	}
	public int getCelular() {

		return celular;
	}

	public void setCelular(int celular)
	{

		this.celular = celular;
	}
	public int getNit() {

		return nit;
	}

	public void setNit(int nit)
	{
		this.nit = nit;
	}

	@Override
	public String toString() {
		return nombre + "\t" + correo + "\t" + contrasena + "\t" + factura + "\t" + celular + "\t" + nit;
	}
}