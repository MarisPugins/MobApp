package com.example.galadarbs.SecondHomeWork;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.galadarbs.R;

public class SecondHomeWork_ContactActivity extends Fragment {
    Button btnDo, btnback;
    TextView headerName,textViewId,textViewGender,textViewAddress,textViewEmail,
            textViewMobile,textViewHome,textViewOffice;
    Boolean click = false;
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.second_homework_activity_contact, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnDo = view.findViewById(R.id.btn_do_something);
        btnback = view.findViewById(R.id.btn_back);
        headerName = view.findViewById(R.id.tv_header_title);
        textViewId = view.findViewById(R.id.tv_id);
        textViewGender = view.findViewById(R.id.tv_gender);
        textViewAddress = view.findViewById(R.id.tv_address);
        textViewEmail = view.findViewById(R.id.tv_email);
        textViewMobile = view.findViewById(R.id.tv_phone);
        textViewHome = view.findViewById(R.id.tv_home);
        textViewOffice = view.findViewById(R.id.tv_office);

        Bundle bundle = this.getArguments();
        if(bundle!=null){
            String name = bundle.getString(GV.TAG_NAME);
            String id = bundle.getString(GV.TAG_ID);
            String gender = bundle.getString(GV.TAG_GENDER);
            String address = bundle.getString(GV.TAG_ADDRESS);
            String email = bundle.getString(GV.TAG_EMAIL);
            String mobile = bundle.getString(GV.TAG_MOBILE);
            String home = bundle.getString(GV.TAG_HOME);
            String office = bundle.getString(GV.TAG_OFFICE);

            textViewMobile.setText(mobile);
            textViewId.setText(id);
            textViewGender.setText(gender);
            textViewAddress.setText(address);
            textViewEmail.setText(email);
            textViewHome.setText(home);
            textViewOffice.setText(office);

            StringBuilder stars = new StringBuilder(name);
            for(int i=0; i<name.length(); i++){

                if(Character.isWhitespace(name.charAt(i))==false){
                    stars.replace(i,i+1,"*");
                }

            }
            headerName.setText(stars);

            btnDo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(click==false){
                        headerName.setText(name);
                        click=true;
                    }
                    else{
                        headerName.setText(stars);
                        click=false;
                    }

                }
            });

        }

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment personList = new SecondHomeWork();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fl_fragment, personList);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

    }
}
