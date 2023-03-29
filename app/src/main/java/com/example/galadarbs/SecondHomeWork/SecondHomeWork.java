package com.example.galadarbs.SecondHomeWork;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.ListFragment;

import com.example.galadarbs.R;
import com.example.galadarbs.ServiceHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class  SecondHomeWork extends ListFragment {
    View view;
    private ProgressDialog progressDialog;
    private static String url = "https://api.androidhive.info/contacts/";
    JSONArray contacts = null;
    ArrayList<HashMap<String, String>> contactList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.second_homework_main, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        contactList = new ArrayList<>();
        ListView lv = getListView();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String name = ((TextView)view.findViewById(R.id.tv_name)).getText().toString();
                String id = ((TextView)view.findViewById(R.id.tv_Sid)).getText().toString();
                String gender = ((TextView)view.findViewById(R.id.tv_Sgender)).getText().toString();
                String address = ((TextView)view.findViewById(R.id.tv_Saddress)).getText().toString();
                String email = ((TextView)view.findViewById(R.id.tv_Semail)).getText().toString();
                String mobile = ((TextView)view.findViewById(R.id.tv_phone)).getText().toString();
                String home = ((TextView)view.findViewById(R.id.tv_Shome)).getText().toString();
                String office = ((TextView)view.findViewById(R.id.tv_Soffice)).getText().toString();
                Bundle bundle = new Bundle();

                bundle.putString(GV.TAG_NAME, name);
                bundle.putString(GV.TAG_ID, id);
                bundle.putString(GV.TAG_GENDER, gender);
                bundle.putString(GV.TAG_ADDRESS, address);
                bundle.putString(GV.TAG_EMAIL, email);
                bundle.putString(GV.TAG_MOBILE, mobile);
                bundle.putString(GV.TAG_HOME,home);
                bundle.putString(GV.TAG_OFFICE, office);

                Fragment personalFragment = new SecondHomeWork_ContactActivity();
                personalFragment.setArguments(bundle);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fl_fragment, personalFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });
        new GetContacts().execute();
    }

    private class GetContacts extends AsyncTask<Void,Void,Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(getContext());
            progressDialog.setMessage("Downloading data. Please Wait");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            ServiceHandler sh = new ServiceHandler();
            String jsonStr = sh.makeServiceCall(url);
            if (jsonStr!=null){
                try {
                    JSONObject jsonObject = new JSONObject(jsonStr);
                    contacts = jsonObject.getJSONArray(GV.TAG_CONTACTS);
                    for (int i=0; i<contacts.length(); i++) {
                        JSONObject c = contacts.getJSONObject(i);
                        String name = c.getString(GV.TAG_NAME);
                        String id = c.getString(GV.TAG_ID);
                        String gender = c.getString(GV.TAG_GENDER);
                        String address = c.getString(GV.TAG_ADDRESS);
                        String email = c.getString(GV.TAG_EMAIL);
                        JSONObject phone = c.getJSONObject(GV.TAG_PHONE);
                        String mobile= phone.getString(GV.TAG_MOBILE);
                        String home = phone.getString(GV.TAG_HOME);
                        String office = phone.getString(GV.TAG_OFFICE);


                        HashMap<String, String> oneContact = new HashMap<>();
                        oneContact.put(GV.TAG_NAME, name);
                        oneContact.put(GV.TAG_ID, id);
                        oneContact.put(GV.TAG_GENDER, gender);
                        oneContact.put(GV.TAG_ADDRESS, address);
                        oneContact.put(GV.TAG_EMAIL, email);
                        oneContact.put(GV.TAG_MOBILE, mobile);
                        oneContact.put(GV.TAG_HOME,home);
                        oneContact.put(GV.TAG_OFFICE, office);
                        contactList.add(oneContact);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            if(progressDialog.isShowing()){
                progressDialog.dismiss();
            }

            ListAdapter listAdapter = new SimpleAdapter(
                    getContext(),
                    contactList,
                    R.layout.second_homework_single_contact,
                    new String[]{GV.TAG_NAME,GV.TAG_ID, GV.TAG_GENDER,GV.TAG_ADDRESS,GV.TAG_EMAIL, GV.TAG_MOBILE, GV.TAG_HOME, GV.TAG_OFFICE},
                    new int[]{ R.id.tv_name,R.id.tv_Sid, R.id.tv_Sgender,R.id.tv_Saddress, R.id.tv_Semail, R.id.tv_phone, R.id.tv_Shome, R.id.tv_Soffice}
            );
            setListAdapter(listAdapter);
        }
    }
}
