package com.hym.errands.adpter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hym.errands.R;
import com.xuexiang.xui.widget.imageview.RadiusImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class HomeBannerAdapter extends RecyclerView.Adapter<HomeBannerAdapter.ViewHolder> {
    private List<Integer> list;

    public HomeBannerAdapter(List<Integer> list){
        this.list = list;
    }

    @NonNull
    @Override
    public HomeBannerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_banner_home, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mIvBanHome.setImageResource(list.get(position%list.size()));
    }

    @Override
    public int getItemCount() {
        return list == null?0:Integer.MAX_VALUE;
    }



    class ViewHolder extends RecyclerView.ViewHolder {
        private RadiusImageView mIvBanHome;
        public ViewHolder(View view) {
            super(view);
            mIvBanHome = view.findViewById(R.id.iv_ban_home);
        }
    }
}
