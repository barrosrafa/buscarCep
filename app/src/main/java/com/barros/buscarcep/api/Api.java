package com.barros.buscarcep.api;

import com.barros.buscarcep.model.PostalCode;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Api {

    @GET("{cep}/json/")
    Call<PostalCode> buscarCEP(@Path("cep") String cep);

}
