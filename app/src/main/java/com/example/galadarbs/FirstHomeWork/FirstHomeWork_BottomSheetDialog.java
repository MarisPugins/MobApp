package com.example.galadarbs.FirstHomeWork;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.galadarbs.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.Random;

public class FirstHomeWork_BottomSheetDialog extends BottomSheetDialogFragment {

    private OnCalculationListener mListener;


    public FirstHomeWork_BottomSheetDialog(OnCalculationListener listener) {
        mListener=listener;
    }

    View view;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.first_homework_bottom_calc_dialog, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView pirmaisSK = view.findViewById(R.id.tv_pirmaisSk);
        TextView darbiba = view.findViewById(R.id.tv_zime);
        TextView otraisSk = view.findViewById(R.id.tv_otraisSk);
        EditText atbilde = view.findViewById(R.id.et_atbilde);
        Button validate = view.findViewById(R.id.btn_validate);
        ImageButton close = view.findViewById(R.id.bt_close);

// Ģenerēju matemātisko izteiksmi

        String[] darbibasZime = {"+", "-", "*", "/"};
        Random randomaPirmaisSk = new Random();
        Random randomaOtraisSk = new Random();
        Random randomaDarbiba = new Random();
        int Sk1 = randomaPirmaisSk.nextInt(21 - 11) + 11;
        int Sk2 = randomaOtraisSk.nextInt(11 - 1) + 1;
        int zime = randomaDarbiba.nextInt(4);
        String rezultats = null;
        pirmaisSK.setText(Integer.toString(Sk1));
        otraisSk.setText(Integer.toString(Sk2));
        darbiba.setText(darbibasZime[zime]);
        if (zime == 0) {
            rezultats = Integer.toString(Sk1 + Sk2);
        } else if (zime == 1) {
            rezultats = Integer.toString(Sk1 - Sk2);
        } else if (zime == 2) {
            rezultats = Integer.toString(Sk1 * Sk2);
        } else if (zime == 3) {

            rezultats = Float.toString(Sk1 / Sk2);
        }


        String finalRezultats = rezultats;
        validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String rez = atbilde.getText().toString();

                if (rez.equals(finalRezultats)) {

                    mListener.onCalculationClicked(true);

                } else {

                    mListener.onCalculationClicked(false);

                }


                dismiss();
            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

    }

    interface OnCalculationListener {
        void onCalculationClicked(boolean clicked);
    }

}
