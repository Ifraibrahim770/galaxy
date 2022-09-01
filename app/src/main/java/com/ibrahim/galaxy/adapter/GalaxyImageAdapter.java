package com.ibrahim.galaxy.adapter;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.ibrahim.galaxy.R;
import com.ibrahim.galaxy.model.Data;
import com.ibrahim.galaxy.model.Item;
import com.ibrahim.galaxy.model.Link;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@RequiresApi(api = Build.VERSION_CODES.O) //SimpleDateFormat is deprecated
public class GalaxyImageAdapter extends RecyclerView.Adapter<GalaxyImageAdapter.ViewHolder> {
    private final Context context;
    private List<Item> galaxyImageList;
    private ItemClickListener clickListener;

    DateFormat inputFormat = new SimpleDateFormat( "E MMM dd HH:mm:ss 'GMT'z yyyy", Locale.ENGLISH);
    DateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd",Locale.ENGLISH);


    public GalaxyImageAdapter(Context context, List<Item> galaxyImageList,
                              ItemClickListener clickListener) {
        this.context = context;
        this.galaxyImageList = galaxyImageList;
        this.clickListener = clickListener;
    }

    public void setGalaxyImagesList(List<Item> galaxyImagesList) {
        this.galaxyImageList = galaxyImagesList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public GalaxyImageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.galaxy_image_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GalaxyImageAdapter.ViewHolder holder, int position) {
        Data imageData = galaxyImageList.get(position).getData().get(0);
        Link imageSrc = galaxyImageList.get(position).getLinks().get(0);

        Date date = null;
        try {
            date = inputFormat.parse(imageData.getDate_created().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String formattedDate = outputFormat.format(date);

        holder.itemImageTitle.setText(imageData.getTitle());
        holder.itemDateAndID.setText(String.format("%s | %s", imageData.getCenter(), formattedDate));

        Glide.with(context)
                .load(imageSrc.getHref())
                .apply(RequestOptions.centerCropTransform())
                .into(holder.itemImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onImageClick(galaxyImageList.get(holder.getAdapterPosition()));
            }
        });


    }

    @Override
    public int getItemCount() {
        if(this.galaxyImageList != null) {
            return this.galaxyImageList.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView itemImageTitle;
        ImageView itemImage;
        TextView itemDateAndID;

        public ViewHolder(View itemView) {
            super(itemView);
            itemImageTitle = itemView.findViewById(R.id.item_text_title);
            itemDateAndID = itemView.findViewById(R.id.item_txt_date_center);
            itemImage = itemView.findViewById(R.id.desc_item_image);

        }
    }

    public interface ItemClickListener{
         void onImageClick(Item image);
    }
}
