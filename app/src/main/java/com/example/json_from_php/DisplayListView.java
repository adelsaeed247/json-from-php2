package com.example.json_from_php;

import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class DisplayListView extends AppCompatActivity {

    ListView UsersListView;
    ProgressBar progressBarSubject;
    String ServerURL = "http://192.168.100.82:8080/sqli/fetch2json.php";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.display_listview);

        UsersListView = (ListView)findViewById(R.id.listview1);

        progressBarSubject = (ProgressBar)findViewById(R.id.progressBar);

        new GetHttpResponse(DisplayListView.this).execute();
    }

    private class GetHttpResponse extends AsyncTask<Void, Void, Void>
    {
        public Context context;

        String ResultHolder;

        List<users> usersList;

        public GetHttpResponse(Context context)
        {
            this.context = context;
        }

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0)
        {
            HttpServicesClass httpServiceObject = new HttpServicesClass(ServerURL);
            try
            {
                httpServiceObject.ExecutePostRequest();

                if(httpServiceObject.getResponseCode() == 200)
                {
                    ResultHolder = httpServiceObject.getResponse();

                    if(ResultHolder != null)
                    {
                        JSONArray jsonArray = null;

                        try {
                            jsonArray = new JSONArray(ResultHolder);

                            JSONObject jsonObject;

                            users users;

                            usersList = new ArrayList<users>();

                            for(int i=0; i<jsonArray.length(); i++)
                            {
                                users = new users();

                                jsonObject = jsonArray.getJSONObject(i);

                                users.TextViewName = jsonObject.getString("name");
                                users.TextViewPhone = jsonObject.getString("phone");
                                users.TextViewEmail = jsonObject.getString("email");

                                usersList.add(users);


                            }
                        }
                        catch (JSONException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }
                else
                {
                    Toast.makeText(context, httpServiceObject.getErrorMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            catch (Exception e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result)

        {
            progressBarSubject.setVisibility(View.GONE);

            UsersListView.setVisibility(View.VISIBLE);

            if(usersList != null)
            {
                ListAdapter adapter = new ListAdapter(usersList, context);

                UsersListView.setAdapter(adapter);
            }
        }
    }
}
