package muna.munaleagueandroidapp;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class RosterActivity extends AppCompatActivity {

    private String TAG = RosterActivity.class.getSimpleName();

    private ProgressDialog pDialog;
    private ListView listView;

    // URL to get google sheets in JSON
    private static String url = "https://script.googleusercontent.com/macros/echo?user_content_key=YPmKhmorNZiyq64ocHYVhyRGgq5nf_tcnfoUM7QKShRqPrZYMRyZJeSkEd1uej-ef5XYakWGrErGo0_iczI270tMeMsD2WSNOJmA1Yb3SEsKFZqtv3DaNYcMrmhZHmUMWojr9NvTBuBLhyHCd5hHa1GhPSVukpSQTydEwAEXFXgt_wltjJcH3XHUaaPC1fv5o9XyvOto09QuWI89K6KjOu0SP2F-BdwUwLeJpJH7t1mx4JvWDHF3tPJ8PjUZ71kEbP8aJsUsiBYafQhrM5X1P4oXKkrbXg_u5y7FLqOV0Tk27B8Rh4QJTQ&lib=MnrE7b2I2PjfH799VodkCPiQjIVyBAxva";

    ArrayList<HashMap<String, String>> playersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roster);

        playersList = new ArrayList<>();

        listView = (ListView) findViewById(R.id.listView);

        new GetPlayers().execute();
    }

    /**
     * Async task class to get json by making HTTP call
     */
    private class GetPlayers extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(RosterActivity.this);
            pDialog.setMessage("Loading Statistics...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler httpHandler = new HttpHandler();

            // Making a request to url and getting response
            String jsonStr = httpHandler.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray contacts = jsonObj.getJSONArray("Sheet1");

                    // looping through All Contacts
                    for (int i = 0; i < contacts.length(); i++) {
                        JSONObject c = contacts.getJSONObject(i);

                        String player = c.getString("Players");
                        String points = c.getString("Points");
                        String rebounds = c.getString("Rebounds");
                        String assists = c.getString("Assists");

                        // tmp hash map for single contact
                        HashMap<String, String> contact = new HashMap<>();

                        // adding each child node to HashMap key => value
                        contact.put("Players", player);
                        contact.put("Points", points);
                        contact.put("Rebounds", rebounds);
                        contact.put("Assists", assists);

                        // adding contact to contact list
                        playersList.add(contact);
                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });

                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            /**
             * Updating parsed JSON data into ListView
             * */
            ListAdapter adapter = new SimpleAdapter(
                    RosterActivity.this, playersList,
                    R.layout.roster_list_item, new String[]{"Players", "Points", "Rebounds", "Assists"}, new int[]{R.id.textViewPlayerName, R.id.textViewPPG, R.id.textViewRPG, R.id.textViewAPG});

            listView.setAdapter(adapter);
        }

    }
}
