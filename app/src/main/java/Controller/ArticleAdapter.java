package Controller;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myplanner.EditArticle;
import com.example.myplanner.Fragments.ArticleFragment;
import com.example.myplanner.R;

import java.util.List;

import Models.ArticleModel;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {

    private Context context;
    private Activity activity ;
    private List<ArticleModel> listitems;
    private DatabaseHandler databaseHandler ;

    public ArticleAdapter(Context context , List<ArticleModel>listitem) {
        this.context = context;
        this.listitems = listitem;
    }
    public ArticleAdapter( Context context, List<ArticleModel> list, DatabaseHandler databaseHandler) {
        this.context = context;
        listitems = list;
        this.databaseHandler = databaseHandler;
    }


    @NonNull
    @Override
    public ArticleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item, parent , false );
        return new ViewHolder(view) ;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder,  int position) {

        final ArticleModel article = listitems.get(position);

        // Get the byte image from your data source for the corresponding position
            byte[] imageBytes = article.getImage_art();
        // Convert the byte array to a Bitmap
        Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
        // Set the Bitmap to the ImageView in the holder

        holder.image.setImageBitmap(bitmap);
        holder.name.setText(article.getName_art());
        holder.weight.setText(String.valueOf(article.getWeight_art()));
        holder.cadence.setText(String.valueOf(article.getCadence_art()));
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHandler = new DatabaseHandler(context.getApplicationContext());
                int position1 = holder.getAdapterPosition();
                int articleID = listitems.get(position1).getId_art();
                databaseHandler.deleteArticle(articleID);
                deleteArticleFromRecyclerView(position1);
                Toast.makeText(context , String.valueOf(articleID),Toast.LENGTH_SHORT).show();
                //Toast.makeText(context , String.valueOf(articleID),Toast.LENGTH_LONG).show();
            }
        });
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context , String.valueOf(article.getId_art()),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context.getApplicationContext(), EditArticle.class);
                intent.putExtra("position",String.valueOf(article.getId_art()));
//                activity.startActivityForResult(intent , 1);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listitems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView name , weight , cadence ;
        private ImageView image;
        private ImageButton delete , edit ;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.nomart);
            weight = itemView.findViewById(R.id.poidsart);
            cadence = itemView.findViewById(R.id.cadenceart);
            image = itemView.findViewById(R.id.imageart);
            delete = itemView.findViewById(R.id.delete_btn);
            edit = itemView.findViewById(R.id.a_edit_btn);
        }
    }

    public void deleteArticleFromRecyclerView(int position) {
        listitems.remove(position);
        notifyItemRemoved(position);
    }
}
