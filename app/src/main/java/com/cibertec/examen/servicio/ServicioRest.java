package com.cibertec.examen.servicio;

import com.cibertec.examen.entidad.Pais;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ServicioRest {

    //Crud de Rol
    @GET("pais")
    public abstract Call<List<Pais>> listaPais();

    @POST("pais")
    public abstract Call<Pais> agregaPais(@Body Pais pais);

    @PUT("pais")
    public abstract Call<Pais> actualizaPais(@Body Pais pais);

    @DELETE("pais/{idpais}")
    public abstract Call<Pais> eliminaPais(@Path("idpais") int id);

}
