package com.example.filemanager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.Arrays;

public class FileListFragment extends Fragment implements FileItemEventListener {

    private String path;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        path = getArguments().getString("path");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_files, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.rv_files);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        TextView pathTextView = view.findViewById(R.id.tv_files_path);
        pathTextView.setText(path);

        File currentFolder = new File(path);
        File[] files = currentFolder.listFiles();
        recyclerView.setAdapter(new FileAdapter(Arrays.asList(files), this));

        view.findViewById(R.id.iv_files_back).setOnClickListener(v -> {
            getActivity().onBackPressed();
        });

        return view;
    }

    @Override
    public void onFileItemClick(File file) {
        if (file.isDirectory()) {
            // path here still points to the previous path, the new path name must be added to it
            ((MainActivity)getActivity()).listFiles(path + File.separator + file.getName());
        }
    }

}
