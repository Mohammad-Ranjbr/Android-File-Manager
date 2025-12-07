package com.example.filemanager;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimary));

        //The path to our project file is in the path storage/self/primary/Android/data
        File externalFilesDirection = getExternalFilesDir(null);
        FileListFragment fileListFragment = new FileListFragment();
        Bundle bundle = new Bundle();
        bundle.putString("path", externalFilesDirection.getPath());
        fileListFragment.setArguments(bundle);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_main_fragmentContainer, fileListFragment);
        transaction.commit();



    }
}