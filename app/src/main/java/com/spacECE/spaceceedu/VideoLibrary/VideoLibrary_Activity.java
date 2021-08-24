package com.spacECE.spaceceedu.VideoLibrary;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.spacECE.spaceceedu.R;

import java.util.ArrayList;

public class VideoLibrary_Activity extends AppCompatActivity{

    public static ArrayList<Topic> topicList= new ArrayList<>();
    public static ArrayList<Topic> recentTopicList = new ArrayList<>();
    public static ArrayList<Topic> trendingTopicList= new ArrayList<>();
    public static ArrayList<Topic> paidTopicList= new ArrayList<>();
    public static ArrayList<Topic> freeTopicList= new ArrayList<>();
    public final static boolean[] ArrayDownloadCOMPLETED = {false};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_library);

//        GetLists getLists = new GetLists();
//        getLists.execute();

        BottomNavigationView videoLibraryBottomNav = findViewById(R.id.VideoLibrary_Bottom_Navigation);
        videoLibraryBottomNav.setOnItemSelectedListener(VL_navListener);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.VideoLibrary_Fragment_layout,
                    new VideoLibrary_Free()).commit();
        }
    }

        NavigationBarView.OnItemSelectedListener VL_navListener =
                new NavigationBarView.OnItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment selectedFragment = null;
                        switch (item.getItemId()) {
                            case R.id.videolibrary_nav_free:
                                selectedFragment = new VideoLibrary_Free();
                                break;
                            case R.id.videolibrary_nav_paid:
                                selectedFragment = new VideoLibrary_Premium();
                                break;
                            case R.id.videolibrary_nav_trending:
                                selectedFragment = new VideoLibrary_trending_Fragment();
                            case R.id.videolibrary_nav_recentlyViewed:
                                selectedFragment= new VideoLibrary_Recent_Fragment();
                        }
                        getSupportFragmentManager().beginTransaction().replace(R.id.VideoLibrary_Fragment_layout,
                                selectedFragment).commit();
                        return true;
                    }
                };

//
//
//    class GetLists extends AsyncTask<String, Void, JSONObject> {
//        final JSONObject[] apiCall = {null};
//
//        @Override
//        protected JSONObject doInBackground(String... strings) {
//
//            apiCall[0] = ApiFunctions.UsingGetAPI("http://3.109.14.4/SpacTube/api_all?uid=1&type=all");
//                Log.i("Object Obtained: ", apiCall[0].toString());
//
//                JSONArray jsonArray = null;
//                JSONArray recentJsonArray=null;
//                JSONArray trendingJsonArray=null;
//                try {
//                    jsonArray = apiCall[0].getJSONArray("data");
//                    recentJsonArray= apiCall[0].getJSONArray("data_recent");
//                    trendingJsonArray = apiCall[0].getJSONArray("data_trending");
//                    Log.i("API : ",apiCall[0].toString());
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                    Log.i("JSON Error: ","Key not found in JSON");
//                }
//
//                try {
//                    for (int i = 0; i < jsonArray.length(); i++) {
//
//                        JSONObject response_element = new JSONObject(String.valueOf(jsonArray.getJSONObject(i)));
//
//                        Topic newTopic = new Topic( response_element.getString("v_id"),
//                                response_element.getString("title"), response_element.getString("v_id"),
//                                response_element.getString("filter"), response_element.getString("length"),
//                                response_element.getString("v_url"), response_element.getString("v_date"),
//                                response_element.getString("v_uni_no"),response_element.getString("desc"));
//                    topicList.add(newTopic);
//                        if(response_element.getString("status").equalsIgnoreCase("created")){
//                            paidTopicList.add(newTopic);
//                        } else{
//                            freeTopicList.add(newTopic);
//                        }
//                    }
//
//                    for (int i = 0; i < recentJsonArray.length(); i++) {
//
//                        JSONObject response_element = new JSONObject(String.valueOf(jsonArray.getJSONObject(i)));
//
//                        Topic newTopic = new Topic( response_element.getString("v_id"),
//                                response_element.getString("title"), response_element.getString("v_id"),
//                                response_element.getString("filter"), response_element.getString("length"),
//                                response_element.getString("v_url"), response_element.getString("v_date"),
//                                response_element.getString("v_uni_no"),response_element.getString("desc"));
//
//                        topicList.add(newTopic);
//                        recentTopicList.add(newTopic);
//                    }
//                    for (int i = 0; i < trendingJsonArray.length(); i++) {
//
//                        JSONObject response_element = new JSONObject(String.valueOf(jsonArray.getJSONObject(i)));
//
//                        Topic newTopic = new Topic( response_element.getString("v_id"),
//                                response_element.getString("title"), response_element.getString("v_id"),
//                                response_element.getString("filter"), response_element.getString("length"),
//                                response_element.getString("v_url"), response_element.getString("v_date"),
//                                response_element.getString("v_uni_no"),response_element.getString("desc"));
//
//                        topicList.add(newTopic);
//                        trendingTopicList.add(newTopic);
//                    }
//                    } catch (JSONException jsonException) {
//                    jsonException.printStackTrace();
//                    Log.i("JSON Object : ","Key not found");
//                }
//                ArrayDownloadCOMPLETED[0] =true;
//                Log.i("VIDEOS:::::",topicList.toString());
//
//            return null;
//        }
//
//
//    }
}