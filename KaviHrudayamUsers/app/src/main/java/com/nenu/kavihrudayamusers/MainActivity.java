package com.nenu.kavihrudayamusers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import UI.MovieRecylerAdapter;

//        android:background="#66000000"

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private FirebaseRecyclerOptions<DataSetFile> options;
    private FirebaseRecyclerAdapter<DataSetFile,MovieRecylerAdapter> adapter;
    private DatabaseReference databaseReference;
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private Button refresh;



    boolean isShimmer = true;




    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();

    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        ConnectivityManager connectivityManager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo == null || !networkInfo.isConnected() || !networkInfo.isAvailable()) {
            dialogBuilder = new AlertDialog.Builder(MainActivity.this);
            View view = getLayoutInflater().inflate(R.layout.noconnectivity, null);

            refresh = (Button) view.findViewById(R.id.btn);

            refresh.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    recreate();
                }
            });
            dialogBuilder.setView(view);
            dialog = dialogBuilder.create();
            dialog.show();
        }
       // pb = findViewById(R.id.pbv);
        recyclerView = findViewById(R.id.RecyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Poetry");

        databaseReference.keepSynced(true);

        options = new FirebaseRecyclerOptions.Builder<DataSetFile>().setQuery(databaseReference, DataSetFile.class)
                .build();
        adapter = new FirebaseRecyclerAdapter<DataSetFile, MovieRecylerAdapter>(options) {
            @Override
            protected void onBindViewHolder(@NonNull MovieRecylerAdapter holder, int i, @NonNull final DataSetFile dataSetFile) {

                if (isShimmer) {
                    holder.shimmerFrameLayout.startShimmer();
                } else {
                    holder.shimmerFrameLayout.stopShimmer();
                    holder.shimmerFrameLayout.setShimmer(null);
                holder.movie.setText(dataSetFile.getMovie());
                holder.song.setText(dataSetFile.getSong());
                holder.writer.setText(dataSetFile.getWriter());
                holder.lyrics.setText(dataSetFile.getLyrics());

                holder.share.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String message = dataSetFile.getLyrics();

                        Intent shareintent = new Intent(Intent.ACTION_SEND);
                        shareintent.setType("text/plain");
                        shareintent.putExtra(Intent.EXTRA_SUBJECT, "Here are some lyrics from KaviHrudayam");
                        shareintent.putExtra(Intent.EXTRA_TEXT, message);
                        startActivity(shareintent);
                    }
                });

            }

            }

            @NonNull
            @Override
            public MovieRecylerAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new MovieRecylerAdapter(LayoutInflater.from(MainActivity.this).inflate(R.layout.movie_row, parent, false));
            }
        };

        recyclerView.setAdapter(adapter);
        isShimmer = false;

    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertdialogbuilder =new AlertDialog.Builder(MainActivity.this);
        alertdialogbuilder.setTitle("Do you want to exit");
        alertdialogbuilder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                moveTaskToBack(true);
            }
        });
        alertdialogbuilder.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alertdialog = alertdialogbuilder.create();
        alertdialog.show();


    }
}

