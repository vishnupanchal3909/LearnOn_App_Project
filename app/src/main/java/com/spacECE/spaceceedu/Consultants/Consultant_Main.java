package com.spacECE.spaceceedu.Consultants;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.spacECE.spaceceedu.ApiFunctions;
import com.spacECE.spaceceedu.FragmentProfile;
import com.spacECE.spaceceedu.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Consultant_Main extends AppCompatActivity {

    public static ArrayList<Consultant_Categories.ConsultantCategory> categoryList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultant_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.ConsultantMain_Frame,
                    new Consultant_Categories()).commit();
        }
        //List Generation:
//        getCategoryList generator = new getCategoryList();
//        generator.execute();
        generateList();

         BottomNavigationView bottomNav = findViewById(R.id.Consultant_Main_BottomNav);
                bottomNav.setOnItemSelectedListener(navListener);
    }

    NavigationBarView.OnItemSelectedListener navListener =
            new NavigationBarView.OnItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.consultant_main_navButton_all:
                            selectedFragment = new Consultant_Categories();
                            break;
                        case R.id.consultant_main_navButton_my:
                            selectedFragment = new FragmentProfile();
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.ConsultantMain_Frame,
                            selectedFragment).commit();

                    return true;
                }
            };


    private void generateList() {
        Log.i("Generate List : "," Generating....");
        categoryList.add(new Consultant_Categories.ConsultantCategory("Psycho","Nice"));
        categoryList.add(new Consultant_Categories.ConsultantCategory("Surgeon","Nice"));
        categoryList.add(new Consultant_Categories.ConsultantCategory("Anesthesia","Nice"));
        categoryList.add(new Consultant_Categories.ConsultantCategory("New","Nice"));
        categoryList.add(new Consultant_Categories.ConsultantCategory("Old","Nice"));
        categoryList.add(new Consultant_Categories.ConsultantCategory("Good","Nice"));
        categoryList.add(new Consultant_Categories.ConsultantCategory("Great","Nice"));
        categoryList.add(new Consultant_Categories.ConsultantCategory("Cool","Nice"));
        categoryList.add(new Consultant_Categories.ConsultantCategory("Kind","Nice"));
        categoryList.add(new Consultant_Categories.ConsultantCategory("Unkind","Nice"));
        categoryList.add(new Consultant_Categories.ConsultantCategory("Right","Nice"));
        categoryList.add(new Consultant_Categories.ConsultantCategory("Down","Nice"));
        categoryList.add(new Consultant_Categories.ConsultantCategory("Up","Nice"));
        Log.i("LIST ::::::", categoryList.toString());
    }

    class getCategoryList extends AsyncTask<String, Void, JSONObject> {
        final private JSONObject[] apiCall = {null};

        @Override
        protected JSONObject doInBackground(String... strings) {

            apiCall[0] = ApiFunctions.UsingGetAPI("http://3.109.14.4/SpacTube/api_all?uid=1&type=all");
            Log.i("Object Obtained: ", apiCall[0].toString());

            JSONArray jsonArray = null;
            try {
                jsonArray = apiCall[0].getJSONArray("data");
                Log.i("API : ",apiCall[0].toString());
            } catch (JSONException e) {
                e.printStackTrace();
                Log.i("JSON Error: ","Key not found in JSON");
            }
            try {
                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject response_element = new JSONObject(String.valueOf(jsonArray.getJSONObject(i)));
                    Consultant_Categories.ConsultantCategory newCategory= new Consultant_Categories.ConsultantCategory(response_element.getString("name")
                            , response_element.getString("icon"));

                    categoryList.add(newCategory);
                }
            } catch (JSONException jsonException) {
                jsonException.printStackTrace();
                Log.i("JSON Object : ","Key not found");
            }

            Log.i("categoryList:::::",categoryList.toString());

            return null;
        }
    }
}