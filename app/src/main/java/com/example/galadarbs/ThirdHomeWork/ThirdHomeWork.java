package com.example.galadarbs.ThirdHomeWork;


import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.galadarbs.MainActivity;
import com.example.galadarbs.R;
import com.example.galadarbs.ServiceHandler;
import com.example.galadarbs.models.Person;
import com.example.galadarbs.models.Phone;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ThirdHomeWork extends Fragment implements ThirdHomeWork_MusicListAdapter.ItemClickListener {
    View view;
    private ProgressDialog progressDialog;
    ThirdHomeWork_MusicListAdapter adapter;
    RecyclerView list;
    ArrayList<Person> personList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.third_homework_main, container, false);
        new GetContacts().execute();
        return view;
    }

    private void setAdapter(View view) {
        list = view.findViewById(R.id.rv_list);
        adapter = new ThirdHomeWork_MusicListAdapter(getList(), this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(
                getContext(),
                RecyclerView.VERTICAL,
                false);
        list.setLayoutManager(layoutManager);
        list.setAdapter(adapter);

    }

    //-------------------Savacu salasitos datus un uztaisu pieejamus otram fragmentam-------------------------------------------------
    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    public void onItemClick(int position) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("personData", personList.get(position));
        Fragment personalFragment = new ThirdHomeWork_PersonalFragment();
        personalFragment.setArguments(bundle);
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fl_fragment, personalFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();



    }


    private ArrayList<Person> getList() {
        return personList;
    }


    private class GetContacts extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            ServiceHandler sh = new ServiceHandler();
            String jsonString = sh.makeServiceCall(MainActivity.urlRecycle);
            if(jsonString!=null){
                jsonString = jsonString.replace("<pre>", "");
                jsonString = jsonString.replace("</pre>", "");
                Log.d("NEW DATA", jsonString);
                personList = new ArrayList<>();
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(jsonString);
                    JSONArray contacts = jsonObject.getJSONArray("contacts");
                    for(int i =0; i<contacts.length(); i++){
                        JSONObject c = contacts.getJSONObject(i);
                        String id = c.getString("id");
                        String name= c.getString("name");
                        String address = c.getString("address");
                        String gender = c.getString("gender");
                        Phone phone = new Phone(
                                c.getJSONObject("phone").getString("mobile"),
                                c.getJSONObject("phone").getString("home"),
                                c.getJSONObject("phone").getString("office")
                        );
                        ArrayList<String> songs = new ArrayList<String >();
                        JSONArray songJson = c.getJSONObject("song_play_list").getJSONArray("song");
                        for(int j =0; j<songJson.length(); j++){
                            songs.add(songJson.getString(j));
                        }
                        ArrayList<String> artists = new ArrayList<String >();
                        JSONArray artistJson = c.getJSONObject("song_play_list").getJSONArray("artists");
                        for(int j =0; j<artistJson.length(); j++){
                            artists.add(artistJson.getString(j));
                        }
                        personList.add(new Person(
                                id,
                                name,
                                address,
                                gender,
                                songs,
                                artists,
                                phone
                        ));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(getContext());
            progressDialog.setMessage("Please wait data is beeing downloaded");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            if(progressDialog.isShowing()){
                progressDialog.dismiss();
            }
            setAdapter(view);
        }

    }
}
