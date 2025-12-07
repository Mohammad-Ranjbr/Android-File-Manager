package com.example.filemanager;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.io.File;

public class MainActivity extends AppCompatActivity implements AddNewFolderCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimary));

        ImageView addNewFolderButton = findViewById(R.id.iv_main_addNewFolder);
        addNewFolderButton.setOnClickListener(v -> {
            AddNewFolderDialog addNewFolderDialog = new AddNewFolderDialog();
            addNewFolderDialog.show(getSupportFragmentManager(), null);
        });

        //The path to our project file is in the path storage/self/primary/Android/data
        if (StorageHelper.isExternalStorageReadable()) {
            File externalFilesDirection = getExternalFilesDir(null);
            listFiles(externalFilesDirection.getPath(), false);
        }

        EditText searchEditText = findViewById(R.id.et_main_search);
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.frame_main_fragmentContainer);
                if (fragment instanceof FileListFragment) {
                    ((FileListFragment) fragment).search(s.toString());
                }
            }
        });

    }

    public void listFiles(String path, boolean addToBackStack) {
        FileListFragment fileListFragment = new FileListFragment();
        Bundle bundle = new Bundle();
        bundle.putString("path", path);
        fileListFragment.setArguments(bundle);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_main_fragmentContainer, fileListFragment);
        if (addToBackStack) transaction.addToBackStack(null);
        transaction.commit();
    }

    public void listFiles(String path) {
        this.listFiles(path, true);
    }

    @Override
    public void onCreateFolderButtonClick(String folderName) {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.frame_main_fragmentContainer);
        if (fragment instanceof FileListFragment) {
            ((FileListFragment) fragment).createNewFolder(folderName);
        }
    }

}