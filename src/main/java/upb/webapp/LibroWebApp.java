package upb.webapp;


import upb.entity.Usuario;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/usuario")
public class LibroWebApp {
    public LibroWebApp(){}
    private static Database b = new Database();
    @POST
    @Path("/post")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public javax.ws.rs.core.Response createTrackInJSON(Usuario usuario) {
        //String result = "Usuario Guardado : " + usuario;

        Usuario usr=b.create(usuario.getNombre(), usuario.getCorreo(), usuario.getContrasena(),usuario.getFactura(), usuario.getCelular(), usuario.getNit());
        return javax.ws.rs.core.Response
                .status(200)
                .entity(usr)
                .build();
    }
    public  static void main(String [] args) {
        new Database().create("pedro" , "JUHUHJUsad1231", "TJJD", "JJDJD", 75421542, 456123);
        new Database().closeDataBase();
    }


}