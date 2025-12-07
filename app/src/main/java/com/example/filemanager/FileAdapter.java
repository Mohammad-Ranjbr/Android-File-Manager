package com.example.filemanager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.List;

public class FileAdapter extends RecyclerView.Adapter<FileAdapter.FileViewHolder> {

    private List<File> files;

    public FileAdapter(List<File> files) {
        this.files = files;
    }

    @NonNull
    @Override
    public FileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_file, parent, false);
        return new FileViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FileViewHolder holder, int position) {
        holder.bindFile(files.get(position));
    }

    @Override
    public int getItemCount() {
        return files.size();
    }

    public static class FileViewHolder extends RecyclerView.ViewHolder {
        private TextView fileNameTextView;
        private ImageView fileIconImageView;
        public FileViewHolder(@NonNull View itemView) {
            super(itemView);
            this.fileNameTextView = itemView.findViewById(R.id.tv_file_name);
            this.fileIconImageView = itemView.findViewById(R.id.iv_file);
        }
        public void bindFile(File file) {
            if (file.isDirectory()) {
                fileIconImageView.setImageResource(R.drawable.ic_folder_black_32dp);
            } else {
                fileIconImageView.setImageResource(R.drawable.ic_file_black_32dp);
            }
            fileNameTextView.setText(file.getName());
        }
    }

}
