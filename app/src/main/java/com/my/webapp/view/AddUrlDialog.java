package com.my.webapp.view;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.my.webapp.AppPrefsManager;
import com.my.webapp.R;
import com.my.webapp.databinding.AddUrlDialogBinding;


public class AddUrlDialog extends DialogFragment {

    private static final String TAG = "AddPatient";

    private AddUrlDialogBinding binding;

    private Context mContext;


    AppPrefsManager appPrefsManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NORMAL,
                R.style.AppThemeLight_NoActionBar
        );
    }

    @Override
    public void onStart() {
        super.onStart();

        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width, height);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        binding = AddUrlDialogBinding.inflate(getLayoutInflater(), container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mContext = getContext();
        appPrefsManager = new AppPrefsManager(mContext);

        if (appPrefsManager.isExistUrl()) {
            binding.ivClose.setVisibility(View.VISIBLE);
        }

        binding.ivClose.setOnClickListener(v -> {
            dismiss();
            SplashScreen.start(mContext);
        });


        binding.btnAdd.setOnClickListener(v -> {
            if (!binding.etUrlContent.getText().toString().isEmpty()) {
                appPrefsManager.setKeyUrlStatus(true);
                appPrefsManager.setKeyUserUrl(binding.etUrlContent.getText().toString());

                dismiss();

                SplashScreen.start(mContext);
            }
        });
    }


    public static AddUrlDialog display(FragmentManager fragmentManager, String tag) {

        AddUrlDialog dialogFragment = new AddUrlDialog();
        dialogFragment.show(fragmentManager, tag);

        return dialogFragment;
    }


}