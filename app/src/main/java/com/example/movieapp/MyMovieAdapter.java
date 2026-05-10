package com.example.movieapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyMovieAdapter extends RecyclerView.Adapter<MyMovieAdapter.ViewHolder>
        implements Filterable {

    private MyMovieData[] originalMovieData;
    private List<MyMovieData> filteredMovieData;
    private Context context;

    public MyMovieAdapter(MyMovieData[] myMovieData, Context context) {
        this.originalMovieData = myMovieData;
        this.filteredMovieData = new ArrayList<>(Arrays.asList(myMovieData));
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.movie_item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final MyMovieData movieData = filteredMovieData.get(position);

        holder.textViewName.setText(movieData.getMoviename());
        holder.textViewDate.setText(movieData.getMoviedate());

        Glide.with(context)
                .load("https://image.tmdb.org/t/p/w500" + movieData.getMovieimage())
                .into(holder.movieImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MovieDetailActivity.class);
                intent.putExtra("movieId", movieData.getMovieid());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return filteredMovieData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView movieImage;
        TextView textViewName;
        TextView textViewDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // IDs exacts de ton XML
            movieImage = itemView.findViewById(R.id.imageView);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewDate = itemView.findViewById(R.id.textViewDate);
        }
    }

    @Override
    public Filter getFilter() {
        return movieFilter;
    }

    private final Filter movieFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<MyMovieData> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(Arrays.asList(originalMovieData));
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (MyMovieData movie : originalMovieData) {
                    if (movie.getMoviename().toLowerCase().contains(filterPattern)) {
                        filteredList.add(movie);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filteredMovieData.clear();
            filteredMovieData.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
}