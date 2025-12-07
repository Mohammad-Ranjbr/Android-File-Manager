package com.example.filemanager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileAdapter extends RecyclerView.Adapter<FileAdapter.FileViewHolder> {

    private List<File> files;
    private FileItemEventListener fileItemEventListener;

    public FileAdapter(List<File> files, FileItemEventListener fileItemEventListener) {
        this.files = new ArrayList<>(files);
        this.fileItemEventListener = fileItemEventListener;
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

    public void addFile(File file) {
        files.add(0, file);
        notifyItemInserted(0);
    }

    public void deleteFile(File file) {
        int index = files.indexOf(file);
        if (index > -1) {
            files.remove(index);
            notifyItemRemoved(index);
        }
    }

    public class FileViewHolder extends RecyclerView.ViewHolder {
        private TextView fileNameTextView;
        private ImageView fileIconImageView;
        private ImageView moreImageView;
        public FileViewHolder(@NonNull View itemView) {
            super(itemView);
            this.fileNameTextView = itemView.findViewById(R.id.tv_file_name);
            this.fileIconImageView = itemView.findViewById(R.id.iv_file);
            this.moreImageView = itemView.findViewById(R.id.iv_file_more);
        }
        public void bindFile(File file) {
            if (file.isDirectory()) {
                fileIconImageView.setImageResource(R.drawable.ic_folder_black_32dp);
            } else {
                fileIconImageView.setImageResource(R.drawable.ic_file_black_32dp);
            }
            fileNameTextView.setText(file.getName());

            itemView.setOnClickListener(v -> {
                fileItemEventListener.onFileItemClick(file);
            });

            moreImageView.setOnClickListener(v -> {
                //The second parameter specifies which view to attach the popup menu to.
                PopupMenu popupMenu = new PopupMenu(v.getContext(), v);
                popupMenu.getMenuInflater().inflate(R.menu.menu_file_item, popupMenu.getMenu());
                popupMenu.show();
                popupMenu.setOnMenuItemClickListener(item -> {
                    if (item.getItemId() == R.id.menuItem_delete) {
                        fileItemEventListener.onDeleteItemClick(file);
                    }
                    return true;
                });
            });

        }
    }

}
