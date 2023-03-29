package com.example.galadarbs.FirstHomeWork;

import android.annotation.SuppressLint;
import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.galadarbs.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.io.IOException;
import java.io.InputStream;

public class FirstHomeWork_WallpaperActivity extends Fragment {

    View view;
    ImageView bigPicture;
    Button setWallpaper;
    LinearLayout linearLayout;
    TextView myTextView;

    TextView pirmais;
    TextView darbiba;
    TextView otrais;
    Context context;

    View.OnClickListener listener;

    public int toPhone = R.drawable.pic1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.first_homework_wallpaper, container, false);
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context=context;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bigPicture = view.findViewById(R.id.iv_big_picture);
        setWallpaper = view.findViewById(R.id.btn_change_wallpaper);
        linearLayout = view.findViewById(R.id.ll_view);
        myTextView = view.findViewById(R.id.tv_changeable_text);
        pirmais = view.findViewById(R.id.tv_pirmaisSk);
        darbiba = view.findViewById(R.id.tv_zime);
        otrais = view.findViewById(R.id.tv_otraisSk);

        setupImageViews();

        listener = new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                BottomSheetDialogFragment bottomSheetDialog = new FirstHomeWork_BottomSheetDialog(new FirstHomeWork_BottomSheetDialog.OnCalculationListener() {
                    @Override
                    public void onCalculationClicked(boolean clicked) {
                        if (clicked == true) {
                            @SuppressLint("ResourceType") InputStream is = getResources().openRawResource(toPhone);
                            Bitmap picture = BitmapFactory.decodeStream(is);
                            WallpaperManager mywallpapers = WallpaperManager.getInstance(getContext());

                            try {
                                mywallpapers.setBitmap(picture);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            Toast toast = Toast.makeText(getActivity(), "Atbilde ir pareiza. Ekrāna bilde ir nomainīta!", Toast.LENGTH_SHORT);
                            toast.show();
                            Log.d("True",toast.toString());

                        } else {
                            Log.d("False","nav");

                            Toast.makeText(getActivity(), "Atbilde nav pareiza. Mēģini vēlreiz!", Toast.LENGTH_LONG).show();

                        }
                    }
                });

                        bottomSheetDialog.show(getActivity().getSupportFragmentManager(), "TAG");
                        bottomSheetDialog.setCancelable(false);


                    }
                };

        setWallpaper.setOnClickListener(listener);

    }



    private void setupImageViews() {
        for(int i=1; i<=13; i++) {
            final ImageView iv = new ImageView(getActivity());
            String a = "spic"+Integer.toString(i);
            int id = getResources().getIdentifier(a, "drawable", getActivity().getPackageName());
            iv.setImageResource(id);
            iv.setId(i);

            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            lp.setMargins(10,20,20,30);
            iv.setLayoutParams(lp);
            linearLayout.addView(iv);

            iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int ide  = iv.getId();
                    String b = "pic"+Integer.toString(ide);
                    int id = getResources().getIdentifier(b, "drawable", getActivity().getPackageName());
                    bigPicture.setImageResource(id);
                    toPhone = id;
                }
            });

        }
    }
}
