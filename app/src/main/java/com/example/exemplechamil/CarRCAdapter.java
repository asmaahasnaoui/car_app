package com.example.exemplechamil;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CarRCAdapter extends RecyclerView.Adapter<CarRCAdapter.CarViewHolder> {
   private ArrayList<Car>cars;
   private OnRecyclerViewItemClickListener listener;

    public CarRCAdapter(ArrayList<Car> cars, OnRecyclerViewItemClickListener listener) {
        this.cars = cars;
        this.listener=listener;
    }

    public ArrayList<Car> getCars() {
        return cars;
    }

    public void setCars(ArrayList<Car> cars) {
        this.cars = cars;
    }

    public OnRecyclerViewItemClickListener getListener() {
        return listener;
    }

    public void setListener(OnRecyclerViewItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public CarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_car,null,false);
        CarViewHolder viewHolder=new CarViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CarViewHolder holder, int position) {
        Car c=cars.get(position);
        if(c.getImage() !=null && !c.getImage().isEmpty()){
            holder.imge.setImageURI(Uri.parse(c.getImage().toString()));

        }

        else{
            holder.imge.setImageResource(R.drawable.i4);
        }
        holder.tv_model.setText(c.getModel());
        holder.tv_color.setText(c.getColor());
        holder.tv_dpl.setText(String.valueOf(c.getDbl()));
        holder.imge.setTag(c.getId());

    }

    @Override
    public int getItemCount() {
        return cars.size();
    }

    class CarViewHolder extends RecyclerView.ViewHolder{
        ImageView imge;
        TextView tv_model,tv_color,tv_dpl;

        public CarViewHolder(@NonNull View itemView) {
            super(itemView);
            imge=itemView.findViewById(R.id.imageView);
            tv_model=itemView.findViewById(R.id.tv_model);
            tv_color=itemView.findViewById(R.id.tv_color);
            tv_dpl=itemView.findViewById(R.id.tv_dpl);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int id= (int) imge.getTag();
                    listener.onItemClick(id);

                }
            });

        }
    }
}
