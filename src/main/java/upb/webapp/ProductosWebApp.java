package upb.webapp;

import upb.entity.Productos;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/productos")
public class ProductosWebApp {

    @POST
    @Path("/post")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public javax.ws.rs.core.Response createTrackInJSON(Productos productos) {
        String result = "Producto Guardado : " + productos;
        Database b = new Database();
        Productos p = b.createP(productos.getIDProductos(), productos.getNombre_pro(), productos.getDescripcion(), productos.getPrecio());
        return javax.ws.rs.core.Response
                .status(200)
                .entity(p)
                .build();

    }

    @DELETE
    @Path("/borrar/{IDProductos}")
    public javax.ws.rs.core.Response borrarProductos(@PathParam("IDProductos") String IDProductos) {
        Database b = new Database();
        b.delete(IDProductos);
        return  javax.ws.rs.core.Response
                .status(200)
                .build();

    }
    @PUT
    @Path("/PUT/{IDProductos}")
    public javax.ws.rs.core.Response modificarProductos(@PathParam("IDProductos") String IDProductos, Productos productos) {
        Database b = new Database();
        b.modificarP(IDProductos, productos);
        return javax.ws.rs.core.Response
                .status(200)
                .build();

    }





    public  static void main(String [] args) {
        new Database().deleteP("YS123YasdaSY");

        new Database().closeDataBase();
    }
}


