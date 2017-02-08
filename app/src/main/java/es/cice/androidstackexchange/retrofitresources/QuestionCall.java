package es.cice.androidstackexchange.retrofitresources;

import java.util.List;

import es.cice.androidstackexchange.model.Item;
import es.cice.androidstackexchange.model.QuestionGroup;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by cice on 7/2/17.
 */

public interface QuestionCall {
    @GET("/2.1/questions?order=desc&sort=creation&site=stackoverflow")
    public Call<QuestionGroup> getQuesionsCall();
}
