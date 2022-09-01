package com.ibrahim.galaxy.viewmodel;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.JsonObject;
import com.ibrahim.galaxy.http.APIClient;
import com.ibrahim.galaxy.http.APIService;
import com.ibrahim.galaxy.model.GalaxyImage;
import com.ibrahim.galaxy.model.Item;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GalaxyImageViewModel extends ViewModel {
    private final MutableLiveData<List<Item>> galaxyImagesList;

    public GalaxyImageViewModel(){
        galaxyImagesList = new MutableLiveData<>();
    }

    public MutableLiveData<List<Item>> getGalaxyImagesList() {
        return galaxyImagesList;

    }
    public void fetchGalaxyImages() {
        APIService apiService = APIClient.getAPIClient().create(APIService.class);
        Call<GalaxyImage> call = apiService.getGalaxyImageInfo("milky way","image","2017","2017");
        call.enqueue(new Callback<GalaxyImage>() {
            @Override
            public void onResponse(@NonNull Call<GalaxyImage> call, @NonNull Response<GalaxyImage>  response) {
                GalaxyImage result = response.body();
                assert result != null;
                galaxyImagesList.postValue(result.getCollection().getItems());
            }

            @Override
            public void onFailure(Call<GalaxyImage> call, Throwable t) {
                galaxyImagesList.postValue(null);
            }
        });
    }
}
