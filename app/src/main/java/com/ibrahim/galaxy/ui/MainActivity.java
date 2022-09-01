package com.ibrahim.galaxy.ui;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.idling.CountingIdlingResource;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ibrahim.galaxy.R;
import com.ibrahim.galaxy.adapter.GalaxyImageAdapter;
import com.ibrahim.galaxy.model.GalaxyImage;
import com.ibrahim.galaxy.model.Item;
import com.ibrahim.galaxy.viewmodel.GalaxyImageViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity implements GalaxyImageAdapter.ItemClickListener {

    private List<Item> galaxyImageList;
    private GalaxyImageAdapter adapter;
    private static  CountingIdlingResource idlingResource = new CountingIdlingResource("list");
    private TextView txtStatusIndicator;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView galaxyImageRecycler = findViewById(R.id.recycler_images);
        txtStatusIndicator = findViewById(R.id.txt_status);
        idlingResource.increment();

        galaxyImageRecycler.setVisibility(View.GONE);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        galaxyImageRecycler.setLayoutManager(layoutManager);
        adapter =  new GalaxyImageAdapter(this, galaxyImageList, this);
        galaxyImageRecycler.setAdapter(adapter);
        GalaxyImageViewModel viewModel = new ViewModelProvider(this).get(GalaxyImageViewModel.class);
        viewModel.getGalaxyImagesList().observe(this, new Observer<List<Item>>() {
            @Override
            public void onChanged(List<Item> galaxyImages) {
                //For UI tests
                if(galaxyImages != null) {
                    galaxyImageList = galaxyImages;
                    adapter.setGalaxyImagesList(galaxyImageList);
                    txtStatusIndicator.setVisibility(View.GONE);
                    galaxyImageRecycler.setVisibility(View.VISIBLE);

                } else {
                    Toast.makeText(MainActivity.this, "Error Loading Data Try Again ", Toast.LENGTH_SHORT).show();
                    txtStatusIndicator.setText(R.string.error_main);

                }
                idlingResource.decrement();
            }
        });
        viewModel.fetchGalaxyImages();

    }
    @Override
    public void onImageClick(Item imageInfo) {
        Intent descIntent = new Intent(MainActivity.this,DescriptionActivity.class);
        descIntent.putExtra("IMAGE_INFO",imageInfo);
        startActivity(descIntent);
    }

    public static CountingIdlingResource getIdlingResourceInTest() {
        return idlingResource;
    }
}
