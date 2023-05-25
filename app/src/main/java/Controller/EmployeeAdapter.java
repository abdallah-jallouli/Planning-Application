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

import com.example.myplanner.EditArticle;
import com.example.myplanner.EditEmployee;
import com.example.myplanner.R;

import java.util.List;

import Models.ArticleModel;
import Models.Employee;
import Models.Team;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.viewHolder> {
    private Context context;
    private Activity activity ;
    private List<Employee> employeeList;
    private DatabaseHandler databaseHandler;

    public EmployeeAdapter(Context context, List<Employee> employeeList, DatabaseHandler databaseHandler) {
        this.context = context;
        this.employeeList = employeeList;
        this.databaseHandler = databaseHandler;
    }

    @NonNull
    @Override
    public EmployeeAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_employee, parent , false );
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeAdapter.viewHolder holder, int position) {
        final Employee employee = employeeList.get(position);

        holder.e_name.setText(employee.getE_name());
        holder.e_fname.setText(employee.getE_first_name());
        holder.e_phone_num.setText(employee.getE_phone_number());
        if (employee.getTeam() != null ){
            holder.e_team_name.setText(employee.getTeam().getT_name());
        }

        holder.e_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHandler = new DatabaseHandler(context.getApplicationContext());
                int position1 = holder.getAdapterPosition();
                int employeeID = employeeList.get(position1).getE_id();
                databaseHandler.deleteEmployee(employeeID);
                deleteArticleFromRecyclerView(position1);
                Toast.makeText(context , String.valueOf(employeeID),Toast.LENGTH_SHORT).show();
            }
        });
        holder.e_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context.getApplicationContext(), EditEmployee.class);
                intent.putExtra("position",String.valueOf(employee.getE_id()));
                //activity.startActivityForResult(intent , 1);
                context.startActivity(intent);
            }
        });
    }



    @Override
    public int getItemCount() {
        return employeeList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        private TextView e_name , e_fname , e_phone_num , e_team_name ;
        private ImageButton e_delete , e_edit ;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            e_name = itemView.findViewById(R.id.name);
            e_fname = itemView.findViewById(R.id.first_name);
            e_phone_num = itemView.findViewById(R.id.phone_num);
            e_team_name = itemView.findViewById(R.id.team_name);
            e_delete = itemView.findViewById(R.id.e_del_btn);
            e_edit = itemView.findViewById(R.id.e_edit_btn);

        }
    }

    private void deleteArticleFromRecyclerView(int position1) {
        employeeList.remove(position1);
        notifyItemRemoved(position1);
    }
}
