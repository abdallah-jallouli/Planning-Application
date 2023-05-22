package Controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myplanner.R;

import java.util.ArrayList;

import Models.ScheduleModel;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ViewHolder> {

    private Context context ;
    private ArrayList<ScheduleModel>scheduleModels ;


    public ScheduleAdapter(Context context, ArrayList<ScheduleModel> scheduleModels) {
        this.context = context;
        this.scheduleModels = scheduleModels;
    }
    @NonNull
    @Override
    public ScheduleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_scheduling,parent ,false  );
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ScheduleAdapter.ViewHolder holder, int position) {
    ScheduleModel item = scheduleModels.get(position);
        holder.nameArticle.setText(item.getNameArticle());
        holder.poidsArticle.setText(item.getPoidsArticle());
        holder.nbPreparation.setText(item.getNbPreparation());
        holder.equiRes.setText(item.getEquiResp());
    }

    @Override
    public int getItemCount() {
        return scheduleModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nameArticle , poidsArticle , nbPreparation , equiRes ;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameArticle = itemView.findViewById(R.id.nameArticle);
            poidsArticle = itemView.findViewById(R.id.poidsArticle);
            nbPreparation = itemView.findViewById(R.id.nbPreparation);
            equiRes = itemView.findViewById(R.id.equipeResp);

        }
    }
}
