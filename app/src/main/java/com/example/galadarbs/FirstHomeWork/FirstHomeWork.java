package com.example.galadarbs.FirstHomeWork;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.galadarbs.R;

public class FirstHomeWork extends Fragment {
    View view;
    Button bMoveForward;
    EditText name;
    TextView testinput;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.first_homework_main, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bMoveForward = view.findViewById(R.id.b_jumpNext);
        name = view.findViewById(R.id.et_name);
        testinput = view.findViewById(R.id.tv_question);

        bMoveForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ourName = name.getText().toString();
                if(ourName.equals("Maris")){
                    Fragment wallpaperFragment = new FirstHomeWork_WallpaperActivity();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    transaction.replace(R.id.fl_fragment, wallpaperFragment);
                    transaction.commit();


                } else {
                    Toast toast = Toast.makeText(getContext(), "Neparaizais vards", Toast.LENGTH_SHORT);
                    toast.show();
                }

        };




});
    }
}