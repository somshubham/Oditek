package fragment;


import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.som.android.oditek.R;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class OneFragment extends Fragment {


    private ProgressBar spinner;
    String responseServer;
    String data=null;
    String[] jsonResult;
    String[] userData;

    ListView list;
    CustomAdapter adapter;
    public OneFragment CustomListView = null;
    public ArrayList<ListModel> CustomListViewValuesArr = new ArrayList<ListModel>();

    public OneFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View x= inflater.inflate(R.layout.fragment_one, container, false);


        CustomListView = this;

        //setListData();
        Resources res =getResources();
        list=(ListView)x.findViewById(R.id.list);
        adapter=new CustomAdapter(getActivity(), CustomListViewValuesArr,res);
        list.setAdapter(adapter);

        AsyncT asyncT = new AsyncT();
        asyncT.execute("","");


        return  x;
    }


    public void setListData()
    {


        CustomListViewValuesArr.clear();
        for (int i = 0; i < 1; i++) {

            final ListModel sched = new ListModel();

            /******* Firstly take data in model object ******/
            sched.setProjectName(""+userData[0]);

            sched.setProjectNumber(""+userData[1]);

            /******** Take Model Object in ArrayList **********/
            CustomListViewValuesArr.add(sched);
        }

    }

    public void onItemClick(int mPosition)
    {
        ListModel tempValues = (ListModel) CustomListViewValuesArr.get(mPosition);


    }




    /* Inner class to get response */
    class AsyncT extends AsyncTask<String , Void, String> {



        //convert the string into json object.....

        private String[] getUserDatafromJsonString(String JsonStr)
                throws JSONException {


            JSONObject UserJson = new JSONObject(JsonStr);
            String success=UserJson.getString("Title");
            String message=UserJson.getString("Plot");
            Log.i("com",""+success+message);
            jsonResult=new String[3];
            jsonResult[0]=""+success;
            jsonResult[1]=""+message;
            return  jsonResult;
        }



        @Override
        protected void onPreExecute() {
            super.onPreExecute();
         }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {


                userData=new String[3];
                userData=getUserDatafromJsonString(data);

                setListData();









            }
            catch (Exception e)
            {
                Log.i("Error in json",""+e);
            }


        }


        //progress bar ends ...............

        @Override
        protected String doInBackground(String... params) {
            HttpClient httpclient = new DefaultHttpClient();

            HttpGet httpGet=new HttpGet("http://www.omdbapi.com/?i=tt2431286&tomatoes=true");
            try {


                // Execute HTTP Post Request
                HttpResponse response = httpclient.execute(httpGet);
                InputStream inputStream = response.getEntity().getContent();
                InputStreamToStringExample str = new InputStreamToStringExample();
                responseServer = str.getStringFromInputStream(inputStream);
                Log.e("response", "response -----" + responseServer);



                data=responseServer;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return responseServer;
        }


        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(String.valueOf(aVoid));

            data=responseServer;





        }
    }

    public static class InputStreamToStringExample {

        public static void main(String[] args) throws IOException {

            // intilize an InputStream
            InputStream is =
                    new ByteArrayInputStream("file content..blah blah".getBytes());

            String result = getStringFromInputStream(is);

            System.out.println(result);
            System.out.println("Done");

        }

        // convert InputStream to String
        public static String getStringFromInputStream(InputStream is) {

            BufferedReader br = null;
            StringBuilder sb = new StringBuilder();

            String line;
            try {

                br = new BufferedReader(new InputStreamReader(is));
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return sb.toString();
        }

    }




















}
