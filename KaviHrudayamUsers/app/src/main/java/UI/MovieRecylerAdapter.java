package UI;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.nenu.kavihrudayamusers.DataSetFile;
import com.nenu.kavihrudayamusers.MainActivity;
import com.nenu.kavihrudayamusers.R;

public class MovieRecylerAdapter extends RecyclerView.ViewHolder {

    public TextView movie,song,writer,lyrics;
    public ImageView share;
    public ShimmerFrameLayout shimmerFrameLayout;


    public MovieRecylerAdapter(@NonNull View itemView) {
        super(itemView);
        movie = itemView.findViewById(R.id.movie_txt);
        song=itemView.findViewById(R.id.song_txt);
        writer= itemView.findViewById(R.id.writer_txt);
        lyrics=itemView.findViewById(R.id.lyrics_txt);
        share=itemView.findViewById(R.id.shareitem);
        shimmerFrameLayout=itemView.findViewById(R.id.shimmerlayout);

    }
}
