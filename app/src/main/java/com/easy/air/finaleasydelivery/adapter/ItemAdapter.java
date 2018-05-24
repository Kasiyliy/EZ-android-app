package com.easy.air.finaleasydelivery.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.easy.air.finaleasydelivery.R;
import com.easy.air.finaleasydelivery.activity.SeparatePostActivity;
import com.easy.air.finaleasydelivery.model.Blogzone;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder>{
    List<Blogzone> blogzoneList;
    Activity activity;
    private boolean isAddedModifiable;

    public boolean isAddedModifiable() {
        return isAddedModifiable;
    }

    public void setAddedModifiable(boolean addedModifiable) {
        isAddedModifiable = addedModifiable;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public ItemAdapter(List<Blogzone> blogzoneList , boolean isAddedModifiable) {
        this.blogzoneList = blogzoneList;
        this.isAddedModifiable = isAddedModifiable;
    }
    ViewGroup parent;
    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.parent = parent;
        return new ItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_item, parent , false));
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, int position) {

        final Blogzone blogzone = blogzoneList.get(position);

        holder.itemTitle.setText(blogzone.getTitle());
        holder.itemPrice.setText("Price: "+ blogzone.getPrice());
        holder.itemFrom.setText("From: "+blogzone.getFrom());
        holder.itemWhere.setText("To: " + blogzone.getWhere());
        Glide.with(parent.getContext()).load(blogzone.getImageUrl()).into(holder.itemImage);

        if(isAddedModifiable) {
            holder.itemView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
                @Override
                public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
                    contextMenu.add(holder.getAdapterPosition(), 0, 0, "Удалить");
                    //contextMenu.add(holder.getAdapterPosition(), 1, 0, "Изменить");
                }
            });
        }
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity(), SeparatePostActivity.class);
                myIntent.putExtra("key", blogzone.getKey()); //Optional parameters
                getActivity().startActivity(myIntent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return blogzoneList.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder{
        TextView itemTitle;
        ImageView itemImage;
        TextView itemWhere;
        TextView itemFrom;
        TextView itemPrice;

        public ItemViewHolder(View itemView) {
            super(itemView);
            itemTitle = (TextView) itemView.findViewById(R.id.item_title);
            itemWhere = (TextView) itemView.findViewById(R.id.item_where);
            itemFrom = (TextView) itemView.findViewById(R.id.item_from);
            itemPrice = (TextView) itemView.findViewById(R.id.item_price);

            itemImage = (ImageView) itemView.findViewById(R.id.item_image);

        }


    }
}
