package Controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myplanner.R;

import java.util.List;

import Models.ArticleModel;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {

    private Context context;
    private List<ArticleModel> listitems;

    public ArticleAdapter(Context context , List listitem) {
        this.context = context;
        this.listitems = listitem;
    }


    @NonNull
    @Override
    public ArticleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item, parent , false );
        return new ViewHolder(view) ;
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleAdapter.ViewHolder holder, int position) {

        ArticleModel item = listitems.get(position);
        holder.nomart.setText(item.getNameart());
        holder.poidsart.setText(item.getPoids());
        holder.cadence.setText(item.getCadence());

    }

    @Override
    public int getItemCount() {
        return listitems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView nomart , poidsart , cadence ;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nomart = itemView.findViewById(R.id.nomart);
            poidsart = itemView.findViewById(R.id.poidsart);
            cadence = itemView.findViewById(R.id.cadenceart);

        }
    }
}
