package com.example.filemanager;

import java.io.File;

public interface FileItemEventListener {
    void onFileItemClick(File file);
    void onDeleteItemClick(File file);
    void onCopyFileItemClick(File file);
    void onMoveFileItemClick(File file);
}
