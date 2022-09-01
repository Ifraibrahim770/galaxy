package com.ibrahim.galaxy.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ibrahim.galaxy.R;
import com.ibrahim.galaxy.model.Item;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DescriptionActivity extends AppCompatActivity {
    Item imageItem;

    TextView descTitle,descCenter, descDate, descDescription;

    ImageView descImage;

    FloatingActionButton backButton;

    DateFormat inputFormat = new SimpleDateFormat( "E MMM dd HH:mm:ss 'GMT'z yyyy", Locale.ENGLISH);
    DateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd",Locale.ENGLISH);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);
        descTitle = findViewById(R.id.desc_txt_title);
        descCenter = findViewById(R.id.desc_text_center);
        descDate = findViewById(R.id.desc_txt_created);
        descDescription = findViewById(R.id.desc_txt_description);
        descImage = findViewById(R.id.desc_item_image);
        backButton = findViewById(R.id.desc_button_back);

        imageItem = (Item) getIntent().getSerializableExtra("IMAGE_INFO");

        descTitle.setText(imageItem.getData().get(0).getTitle());
        descCenter.setText(imageItem.getData().get(0).getCenter());

        Date date = null;
        try {
            date = inputFormat.parse(imageItem.getData().get(0).getDate_created().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String formattedDate = outputFormat.format(date);
        descDate.setText(formattedDate);
        descDescription.setText(Html.fromHtml(imageItem.getData().get(0).getDescription()));

        Glide.with(DescriptionActivity.this)
                .load(imageItem.getLinks().get(0).getHref())
                .apply(RequestOptions.centerCropTransform())
                .into(descImage);


        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }
}