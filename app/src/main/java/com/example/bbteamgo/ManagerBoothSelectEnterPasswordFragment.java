package com.example.bbteamgo;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ManagerBoothSelectEnterPasswordFragment extends DialogFragment {
    private final static String TAG = "BoothSelect";

    String userId = null;
    String userEmail = null;
    String userUniversity = null;
    String userBooth = null;
    String password = null;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        View dialog = inflater.inflate(R.layout.fragment_manager_booth_select_enter_password, null);

        Bundle args = getArguments();
        if (args != null) {
            userId = args.getString("USER_ID");
            userEmail = args.getString("USER_EMAIL");
            password = args.getString("BOOTH_PASSWORD");
            userUniversity = args.getString("UNIVERSITY_NAME");
            userBooth = args.getString("BOOTH_NAME");
            TextView textView = dialog.findViewById(R.id.title);
            textView.setText(userBooth);

            Log.d(TAG, "password is :" + password);
        }

        Button backButton = dialog.findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        Button confirmButton = dialog.findViewById(R.id.confirm_button);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText passwordBox = dialog.findViewById(R.id.password_textbox);
                String inputPassword = passwordBox.getText().toString();

                if (password.equals(inputPassword)) {
                    Intent intent = new Intent(getActivity(), ManagerActivity.class);
                    intent.putExtra("USER_EMAIL", userEmail);
                    intent.putExtra("USER_ID", userId);
                    intent.putExtra("BOOTH_NAME", userBooth);
                    intent.putExtra("UNIVERSITY_NAME", userUniversity);
                    startActivity(intent);
                } else {
                    Toast.makeText(getActivity(), "부스 인증에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        builder.setView(dialog);

        return builder.create();
    }
}