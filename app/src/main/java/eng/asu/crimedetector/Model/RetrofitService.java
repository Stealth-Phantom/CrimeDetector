package eng.asu.crimedetector.Model;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {
    //Base URL must end in /
    private static final String BASE_URL = "http://192.168.1.7:3000/";
    private static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();;


    public static JsonPlaceholderAPI getInterface()
    {
        return retrofit.create(JsonPlaceholderAPI.class);
    }

}
