package com.techraj156.mistplay;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothClass;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


public class MainActivity extends AppCompatActivity {

    ListView search_game;
    ArrayAdapter<String> adapter;
    ArrayList<String> loader = new ArrayList<>();
    ArrayList<String> numpy = new ArrayList<>();
    Context context = null;
    //ArrayList<String> numpy1 = new ArrayList<>();
    //private ProgressBar progressBar;


    Button btn;
   // MyAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //get_json();


        search_game=(ListView) findViewById(R.id.search_game);
        ArrayList<String> arrayGame = new ArrayList<>();
        arrayGame.addAll(Arrays.asList(getResources().getStringArray(R.array.my_games)));

        adapter= new ArrayAdapter<String>(
                MainActivity.this,
                android.R.layout.simple_list_item_1,
                arrayGame
        );

        search_game.setAdapter(adapter);
        btn=(Button)findViewById(R.id.btn);

        // Trying to do the load more part
        /*TextView footer = new TextView(this);
        footer.setGravity(Gravity.CENTER);
        footer.setTextSize(30);
        footer.setText("output order");
        if ( footer != null ) {
            search_game.addFooterView(footer);
        } else {
            throw new NullPointerException("footer is null");
        }*/




        /* setListViewFooter(); */

        /*search_game.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if(scrollState == SCROLL_STATE_IDLE && search_game.getLastVisiblePosition() ==
                        5){
                    progressBar.setVisibility(View.VISIBLE);
                    addMoreItems();
                }

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });*/

       // btn=(Button)findViewById(R.id.button);
        //btn.setOnClickListener((View.OnClickListener) this);


    }

    //public void onClick(View view){
        //if(view.getId()==R.id.button){


        //}
    //}

    /*private void addMoreItems(){
        int size = 5;
        for(int i=1;i<=5;i++){
            if((size + i) < numpy.size()){
                numpy1.add(numpy.get(size + i));
            }
        }
        listViewAdapter.notifyDataSetChanged();
        progressBar.setVisibility(View.GONE);
    }*/

    /*private void setListViewFooter(){
        View view = LayoutInflater.from(this).inflate(R.layout.footer_view,null);
        progressBar = view.findViewById(R.id.progressBar);
        search_game.addFooterView(progressBar);
    }*/
    /*public void onClick(View view){

    }*/
    public void get_json(String newText){
        //ArrayList<String> num = new ArrayList<>();
        String json;

        try
        {
            InputStream is = getAssets().open("games.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            json = new String(buffer,"UTF-8");
            JSONArray jsonArray = new JSONArray(json);
            numpy.clear();
            adapter.clear();
            loader.clear();
            for (int i =0; i<jsonArray.length();i++){
                JSONObject obj = jsonArray.getJSONObject(i);
                //the searching method will output all the matching result in all categories
                if(obj.getString("title").contains(newText)){
                    //all the matching title
                    numpy.add("Title: "+obj.getString("title"));
                    //find all the matching subgenre,genere,imgURL, rating , couting and pid
                }if (obj.getString("subgenre").toLowerCase().contains(newText)){
                    numpy.add("Subgenre: "+obj.getString("subgenre"));
                }if (obj.getString("genre").toLowerCase().contains(newText)){
                    numpy.add("Subgenre: "+obj.getString("genre"));
                }if (obj.getString("imgURL").toLowerCase().contains(newText)){
                    numpy.add("Subgenre: "+obj.getString("imgURL"));
                }if (obj.getString("rating").toLowerCase().contains(newText)){
                    numpy.add("Rating: "+obj.getString("rating"));
                }if (obj.getString("rCount").toLowerCase().contains(newText)){
                    numpy.add("RCount: "+obj.getString("rCount"));
                }if (obj.getString("pid").toLowerCase().contains(newText)){
                    numpy.add("Pid: "+obj.getString("pid"));
                }
            }

            //Toast.makeText(getApplicationContext(),number.toString(),Toast.LENGTH_LONG).show();


        }catch (IOException e){
            e.printStackTrace();
        }catch(JSONException e){
            e.printStackTrace();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater =getMenuInflater();
        inflater.inflate(R.menu.search_menu,menu);
        //TextView footer =(TextView) view.findViewById(R.menu.search_menu,menu);

        MenuItem item = menu.findItem(R.id.search_game);
        SearchView searchView =(SearchView)item.getActionView();

        // create the function
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                get_json(newText.toLowerCase());
                adapter.clear();
                Collections.sort(numpy);
                //Only show the first 5 items
                //Otherwise, user needs to click the load more button to see more result
                if (numpy.size()<5){
                    adapter.addAll(numpy);
                    numpy.clear();
                }else{
                    //int mod = numpy.size()/5;
                    //loader.add(numpy.get(0));
                    //numpy.remove(0);
                    for(int i =0; i<5;i++){
                        loader.add(numpy.get(i));
                        numpy.remove(i);
                    }
                    adapter.addAll(loader);

                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //loader.add(numpy.get(0));
                            int min;
                            if(numpy.size()>=5){
                                min = 5;
                            }else{
                                min=numpy.size();
                            }
                            for(int i =0; i<min;i++){
                                loader.add(numpy.get(i));
                                numpy.remove(i);
                            }
                            adapter.clear();
                            adapter.addAll(loader);
                            //numpy.remove(0);

                            //adapter.clear();

                        }
                    });
                    //loader.clear();
                }

                // when the load more button is pressed

                //adapter=new ArrayAdapter<BluetoothClass.Device>(this,android.R.layout.simple_list_item_1,numpy);
                //adapter.getFilter().filter(newText);

                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);

    }
}
