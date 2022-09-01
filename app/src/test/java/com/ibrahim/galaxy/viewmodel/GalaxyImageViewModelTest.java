package com.ibrahim.galaxy.viewmodel;

import com.ibrahim.galaxy.http.APIClient;
import com.ibrahim.galaxy.http.APIService;
import com.ibrahim.galaxy.model.GalaxyImage;

import junit.framework.TestCase;

import org.junit.Test;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class GalaxyImageViewModelTest extends TestCase {
    @Test
    public void testFetch() {
        APIService apiService = APIClient.getAPIClient().create(APIService.class);
        Call<GalaxyImage> call = apiService.getGalaxyImageInfo("milky way","image","2017","2017");
        try {
            Response<GalaxyImage> response = call.execute();
            GalaxyImage image = response.body();
            assertTrue(response.isSuccessful());
            assertNotNull(image);
            assertNotNull(image.getCollection());
            assertNotNull(image.getCollection().getItems());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}