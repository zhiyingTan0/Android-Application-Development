package com.techraj156.mistplay;

import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.os.Bundle;
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



public class MainActivity extends AppCompatActivity {

    ListView search_game;
    ArrayAdapter<String> adapter;
    ArrayList<String> number = new ArrayList<>();
    //Button btn;
   // MyAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        get_json();


        search_game=(ListView) findViewById(R.id.search_game);
        ArrayList<String> arrayGame = new ArrayList<>();
        arrayGame.addAll(Arrays.asList(getResources().getStringArray(R.array.my_games)));

        adapter= new ArrayAdapter<String>(
                MainActivity.this,
                android.R.layout.simple_list_item_1,
                arrayGame
        );

        search_game.setAdapter(adapter);

       // btn=(Button)findViewById(R.id.button);
        //btn.setOnClickListener((View.OnClickListener) this);


    }

    //public void onClick(View view){
        //if(view.getId()==R.id.button){


        //}
    //}

    public void get_json(){
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

            for (int i =0; i<jsonArray.length();i++){
                JSONObject obj = jsonArray.getJSONObject(i);
                if(obj.getString("rating").equals("3.6")){
                    number.add(obj.getString("imgURL"));

                }
            }
            Toast.makeText(getApplicationContext(),number.toString(),Toast.LENGTH_LONG).show();

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
                adapter.getFilter().filter(newText);

                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);

    }
}
