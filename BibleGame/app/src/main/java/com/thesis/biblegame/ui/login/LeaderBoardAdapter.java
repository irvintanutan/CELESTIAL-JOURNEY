package com.thesis.biblegame.ui.login;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.thesis.biblegame.R;

import java.util.List;

/**
 * Created by irvin on 10/26/16.
 */
public class LeaderBoardAdapter extends RecyclerView.Adapter<LeaderBoardAdapter.ViewHolder> {

    private List<Score> form;
    private Context mContext;

    public LeaderBoardAdapter(List<Score> form , Context mContext) {
        this.form = form;
        this.mContext = mContext;
    }

    @Override
    public LeaderBoardAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.leaderboard_card_view, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LeaderBoardAdapter.ViewHolder viewHolder, int i) {

        viewHolder.name.setText(form.get(i).getName());
        viewHolder.score.setText(Integer.toString(form.get(i).getScore()));

        Glide.with(mContext).load(form.get(i).getPicture()).into(viewHolder.picture);
    }

    @Override
    public int getItemCount() {
        return form.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name , score;
        private ImageView picture;

        public ViewHolder(View view) {
            super(view);

            name = view.findViewById(R.id.name);
            score = view.findViewById(R.id.score);
            picture = view.findViewById(R.id.picture);
        }
    }

}