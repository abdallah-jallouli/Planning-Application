package Controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myplanner.EditEmployee;
import com.example.myplanner.R;

import java.util.List;

import Models.Employee;
import Models.ScheduledArticle;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.viewHolder> {
    private Context context;
    private List<ScheduledArticle> scheduledArticleList;
    private DatabaseHandler databaseHandler;

    public ScheduleAdapter(Context context, List<ScheduledArticle> scheduledArticleList, DatabaseHandler databaseHandler) {
        this.context = context;
        this.scheduledArticleList = scheduledArticleList;
        this.databaseHandler = databaseHandler;
    }

    @NonNull
    @Override
    public ScheduleAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_scheduling, parent , false );
        return new ScheduleAdapter.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScheduleAdapter.viewHolder holder, int position) {
        final ScheduledArticle scheduledArticle = scheduledArticleList.get(position);

        holder.s_nb_prep.setText(String.valueOf(scheduledArticle.getNum_prep() ));

        if (scheduledArticle.getArticle() != null ){
            holder.s_name_art.setText(scheduledArticle.getArticle().getName_art());
            holder.s_weight_art.setText(String.valueOf(scheduledArticle.getArticle().getWeight_art()));
        }
        if (scheduledArticle.getTeam() != null ){
            holder.s_team.setText(scheduledArticle.getTeam().getT_name());
        }

        holder.s_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHandler = new DatabaseHandler(context.getApplicationContext());
                int position1 = holder.getAdapterPosition();
                int scheduledArticleID = scheduledArticleList.get(position1).getS_id();
                databaseHandler.deleteScheduledArticle(scheduledArticleID);
                deleteArticleFromRecyclerView(position1);
                //Toast.makeText(context , String.valueOf(employeeID),Toast.LENGTH_SHORT).show();
            }
        });

    }



    @Override
    public int getItemCount() {
        return scheduledArticleList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{

        private TextView s_name_art , s_weight_art , s_nb_prep , s_team;
        private ImageButton s_delete ;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            s_name_art = itemView.findViewById(R.id.s_name);
            s_weight_art = itemView.findViewById(R.id.s_weight);
            s_nb_prep = itemView.findViewById(R.id.s_nb_prep);
            s_team = itemView.findViewById(R.id.s_num_team);
            s_delete = itemView.findViewById(R.id.s_delete_btn);

        }
    }

    private void deleteArticleFromRecyclerView(int position1) {
        scheduledArticleList.remove(position1);
        notifyItemRemoved(position1);
    }
}

