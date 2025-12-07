package com.example.filemanager;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class AddNewFolderDialog extends DialogFragment {

    private AddNewFolderCallback addNewFolderCallback;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        addNewFolderCallback = (AddNewFolderCallback) context;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_add_new_folder, null, false);
        builder.setView(view);
        AlertDialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        TextInputLayout textInputLayout = view.findViewById(R.id.etl_addNewFolder);
        TextInputEditText textInputEditText = view.findViewById(R.id.et_addNewFolder);
        view.findViewById(R.id.btn_addNewFolder_create).setOnClickListener(v -> {
            if (textInputEditText.length() > 0) {
                addNewFolderCallback.onCreateFolderButtonClick(textInputEditText.getText().toString());
                dismiss();
            } else {
                textInputLayout.setError("Folder name cannot be empty");
            }
        });

        return dialog;
    }
}
