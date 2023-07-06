package Controller;


import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DynamicRVAdapter extends RecyclerView.Adapter<DynamicRVAdapter.DynamicRVHolder>{


    public class DynamicRVHolder extends RecyclerView.ViewHolder {
        public DynamicRVHolder(@NonNull View itemView) {
            super(itemView);
            
        }
    }
    @NonNull
    @Override
    public DynamicRVAdapter.DynamicRVHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull DynamicRVAdapter.DynamicRVHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }


}
