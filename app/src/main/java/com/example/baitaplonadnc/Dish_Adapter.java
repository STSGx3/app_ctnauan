package com.example.baitaplonadnc;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class Dish_Adapter extends RecyclerView.Adapter<Dish_Adapter.Dish_AdapterHolder> {
    private List<Dish> mListDish;
    private Context context;
    private IClickListener iClickListener;
    public  interface IClickListener {
        void onClick(Dish dish);
    }

    public  Dish_Adapter( Context context, List<Dish> mListDish,IClickListener iClickListener ){
        this.context = context;
        this.mListDish=mListDish;
        this.iClickListener=iClickListener;
    }

    @NonNull
    @Override
    public Dish_AdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.dish_lish,parent,false);
        return new Dish_AdapterHolder(view);
    }

    //Gọi các kiểu dữ liệu sẽ đưa vào list
    @Override
    public void onBindViewHolder(@NonNull Dish_AdapterHolder holder, int position) {
        Dish dish = mListDish.get(position);
        if(dish==null){
                return;
        }
        if(dish.getLinkAnh()!=null){
            Uri uri = Uri.parse(dish.getLinkAnh());
            Glide.with(context).load(uri).error(R.drawable.image).into(holder.IMGbutton_dish);
        }else {
            holder.IMGbutton_dish.setImageResource(R.drawable.image);}
        holder.button_to_about_dish.setText(dish.getName_ofDish());
        holder.TV_about_calo.setText(dish.getCalories()+" CALO");
        holder.button_to_about_dish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iClickListener.onClick(dish);
            }
        });
        holder.IMGbutton_dish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iClickListener.onClick(dish);
            }
        });

    }

    //Kiem tra list co bao nhiêu phần tử
    @Override
    public int getItemCount() {
        if(mListDish!=null){
            return mListDish.size();
        }
        return 0;
    }

    public class Dish_AdapterHolder extends RecyclerView.ViewHolder {
        private ImageView IMGbutton_dish;
        private TextView button_to_about_dish;
        private TextView TV_about_calo;
        public Dish_AdapterHolder(@NonNull View itemView) {
            super(itemView);
         IMGbutton_dish = itemView.findViewById(R.id.IMGbutton_dish);
         button_to_about_dish = itemView.findViewById(R.id.button_to_about_dish);
         TV_about_calo=itemView.findViewById(R.id.TV_about_calo);
        }
    }
}
