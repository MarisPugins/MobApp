package com.example.galadarbs.ThirdHomeWork;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.galadarbs.R;
import com.example.galadarbs.models.ArtistList;
import com.example.galadarbs.models.Person;
import com.example.galadarbs.models.SongList;

import java.util.ArrayList;

public class ThirdHomeWork_PersonalFragment extends Fragment {

    View view;
    ImageView icon;
    TextView name, mobile, id, gender, address, home, office;
    Person person;
    Button btnReturn;
    ThirdHomeWork_SongAdapter songAdapter;
    ThirdHomeWork_ArtistAdapter artistAdapter;
    LinearLayoutManager lmsongs, lmartists;
    ArrayList sName,aName = new ArrayList();
    ArrayList<SongList> songLists = new ArrayList<>();
    ArrayList<ArtistList> artistLists = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.third_homework_person_data, container, false);


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView artists = (RecyclerView) view.findViewById(R.id.rv_artist);
        RecyclerView songs = (RecyclerView) view.findViewById(R.id.rv_songs);
        btnReturn = view.findViewById(R.id.btn_return);
        icon = view.findViewById(R.id.iv_topIcon);
        name = view.findViewById(R.id.tv_topName);
        mobile = view.findViewById(R.id.tv_topPhone);
        id = view.findViewById(R.id.tv_id);
        gender = view.findViewById(R.id.tv_gender);
        address = view.findViewById(R.id.tv_address);
        home = view.findViewById(R.id.tv_home);
        office = view.findViewById(R.id.tv_office);

        fillSongList();
        fillArtistList();



//---------------------------------------Panemu personas datus no iepriekseja fragmenta-------------------------------------------------
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            person = bundle.getParcelable("personData");
            name.setText(person.getName());
            mobile.setText(person.getPhoneList().getMobile());
            id.setText(person.getId());
            gender.setText(person.getGender());
            address.setText(person.getAddress());
            home.setText(person.getPhoneList().getHome());
            office.setText(person.getPhoneList().getOffice());
            sName=person.getSongPlaylist();
            aName=person.getArtistPlaylist();


        }

        lmsongs = new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false);
        songs.setLayoutManager(lmsongs);
        songAdapter = new ThirdHomeWork_SongAdapter(getContext(),songLists,sName);
        songs.setAdapter(songAdapter);

        lmartists = new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false);
        artists.setLayoutManager(lmartists);
        artistAdapter = new ThirdHomeWork_ArtistAdapter(getContext(),artistLists,aName);
        artists.setAdapter(artistAdapter);




        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment personList = new ThirdHomeWork();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fl_fragment, personList);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });


    }

    private void fillArtistList() {
        artistLists.add(new ArtistList("5 Seconds Of Summer",R.drawable.seconds_of_summer));
        artistLists.add(new ArtistList("ABBA",R.drawable.abba));
        artistLists.add(new ArtistList("Alicia Keys",R.drawable.alicia_keys));
        artistLists.add(new ArtistList("Alison Krauss and Union Station",R.drawable.alison_krauss_and_union_station));
        artistLists.add(new ArtistList("Amos Lee",R.drawable.amos_lee));
        artistLists.add(new ArtistList("Anne-Marie",R.drawable.anne_marie));
        artistLists.add(new ArtistList("Billie Eilish",R.drawable.billie_eilish));
        artistLists.add(new ArtistList("Blink 182",R.drawable.blink_182));
        artistLists.add(new ArtistList("Bob Marley",R.drawable.bob_marley));
        artistLists.add(new ArtistList("Bruce Springsteen",R.drawable.bruce_springsteen));
        artistLists.add(new ArtistList("BTS",R.drawable.bts));
        artistLists.add(new ArtistList("Cedarmont Kids",R.drawable.cedarmont_kids));
        artistLists.add(new ArtistList("Chris Stapleton",R.drawable.chris_stapleton));
        artistLists.add(new ArtistList("Foo Fighters",R.drawable.foo_fighters));
        artistLists.add(new ArtistList("Foo Fighters & Kendrick Lamar",R.drawable.ic_baseline_person_24));
        artistLists.add(new ArtistList("George Strait",R.drawable.george_strait));
        artistLists.add(new ArtistList("Iron Maiden",R.drawable.iron_maiden));
        artistLists.add(new ArtistList("Jackie Evancho",R.drawable.jackie_evancho));
        artistLists.add(new ArtistList("John Legend",R.drawable.john_legend));
        artistLists.add(new ArtistList("Kid Cudi",R.drawable.kid_cudi));
        artistLists.add(new ArtistList("Lady Gaga",R.drawable.lady_gaga));
        artistLists.add(new ArtistList("Linkin Park",R.drawable.linkin_park));
        artistLists.add(new ArtistList("M. Ward",R.drawable.m_ward));
        artistLists.add(new ArtistList("Mammoth Wvh",R.drawable.mammoth_wvh));
        artistLists.add(new ArtistList("Mark Knopfler",R.drawable.mark_knopfler));
        artistLists.add(new ArtistList("Marvin Gaye",R.drawable.marvin_gaye));
        artistLists.add(new ArtistList("Michael Bublé",R.drawable.michael_buble));
        artistLists.add(new ArtistList("Moby",R.drawable.moby));
        artistLists.add(new ArtistList("Neil Diamond",R.drawable.neil_diamond));
        artistLists.add(new ArtistList("Nelly Furtado",R.drawable.nelly_furtado));
        artistLists.add(new ArtistList("Nickelback",R.drawable.nickelback));
        artistLists.add(new ArtistList("Original Broadway Cast",R.drawable.original_broadway_cast));
        artistLists.add(new ArtistList("Pink Floyd",R.drawable.pink_floyd));
        artistLists.add(new ArtistList("Queen",R.drawable.queen));
        artistLists.add(new ArtistList("Radiohead",R.drawable.radiohead));
        artistLists.add(new ArtistList("Simon & Garfunkel",R.drawable.simon_and_garfunkel));
        artistLists.add(new ArtistList("Slipknot",R.drawable.slipknot));
        artistLists.add(new ArtistList("Taylor Swift",R.drawable.taylor_swift));
        artistLists.add(new ArtistList("The Beatles",R.drawable.the_beatles));
        artistLists.add(new ArtistList("The Black Keys",R.drawable.the_black_keys));
        artistLists.add(new ArtistList("The Piano Guys",R.drawable.the_piano_guys));
        artistLists.add(new ArtistList("Train",R.drawable.ic_baseline_person_24));
        artistLists.add(new ArtistList("Zara Larsson",R.drawable.zara_larsson));
        artistLists.add(new ArtistList("TOOL",R.drawable.ic_baseline_person_24));

    }

    private void fillSongList(){
        songLists.add(new SongList("Aint My Fault",R.drawable.aint_my_fault));
        songLists.add(new SongList("THATS WHAT I WANT",R.drawable.thats_what_i_want));
        songLists.add(new SongList("Alejandro",R.drawable.alejandro));
        songLists.add(new SongList("All My Life",R.drawable.all_my_life));
        songLists.add(new SongList("All of Me",R.drawable.all_of_me));
        songLists.add(new SongList("All the Small Things",R.drawable.all_the_small_things));
        songLists.add(new SongList("Alarm",R.drawable.ic_baseline_music_note_24));

    }
}
