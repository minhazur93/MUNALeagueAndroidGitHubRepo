package muna.munaleagueandroidapp;


import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import static com.facebook.FacebookSdk.getApplicationContext;


public class TeamChallengesFragment extends Fragment {

    private ProgressDialog pDialog;
    private ListView listViewTeamChallenges;
    private String TAG = TeamChallengesFragment.class.getSimpleName();
    String teamPicked;

    private static String rabbiURL = "https://script.google.com/macros/s/AKfycbygukdW3tt8sCPcFDlkMnMuNu9bH5fpt7bKV50p2bM/exec?id=19EwTKPJNESa4UZ8q3SoFy2_qudZoABG3hVif__aCy_0&sheet=Rabbi/Minhaz";
    private static String shojibURL = "https://script.googleusercontent.com/macros/echo?user_content_key=wo_lMP5QQl1JFWjId3yl9wWfXjwzdbmB025POktQyo2hsFyN0lAt4J86TM2Sy8bCJNwP3tXNr4WamdA9xk_rwNF6pn9wofe_OJmA1Yb3SEsKFZqtv3DaNYcMrmhZHmUMWojr9NvTBuBLhyHCd5hHa1ZsYSbt7G4nMhEEDL32U4DxjO7V7yvmJPXJTBuCiTGh3rUPjpYM_V0PJJG7TIaKpxAap0IVTgyT2-UiMZVbvWN3og5o5ckzmy2CXeyE1uXahs2TNGc8dWk5ZELcMZnvXsKiW3k6MDkf6vOQLzDzS58GhZd1xxiuiA&lib=MbpKbbfePtAVndrs259dhPT7ROjQYJ8yx";

    ArrayList<HashMap<String, String>> playersList;


    public TeamChallengesFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_team_challenges, container, false);

        playersList = new ArrayList<>();
        listViewTeamChallenges = (ListView) view.findViewById(R.id.listViewTeamChallenges);

        teamPicked = getArguments().getString("Team that was clicked on");
        if(teamPicked.equals("team1")){
            new GetPlayers(rabbiURL, "Rabbi/Minhaz").execute();
        } else if (teamPicked.equals("team2")) {
            new GetPlayers(shojibURL, "Shojib/Sahid").execute();
        }

        return view;
    }


    class GetPlayers extends AsyncTask<Void, Void, Void> {

        String url;
        String captainName;

        public GetPlayers(String url, String captainName){
            this.url= url;
            this.captainName= captainName;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(getContext());
            pDialog.setMessage("Loading Statistics...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler httpHandler = new HttpHandler();

            // Making a request to rabbiURL and getting response
            String jsonStr = httpHandler.makeServiceCall(url);

            Log.e(TAG, "Response from rabbiURL: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray contacts = jsonObj.getJSONArray(captainName);

                    // looping through All Contacts
                    for (int i = 0; i < contacts.length(); i++) {
                        JSONObject c = contacts.getJSONObject(i);

                        String activity = c.getString("What_activity_did_you_complete?_(please_provide_details)");
                        String date = c.getString("Date");


                        String numberOfPlayers = c.getString("#_of_players_attended");
                        String nameOfPlayersAttended = c.getString("Players_who_attended");

                        // tmp hash map for single contact
                        HashMap<String, String> contact = new HashMap<>();

                        // adding each child node to HashMap key => value
                        contact.put("What_activity_did_you_complete?_(please_provide_details)", activity);
                        contact.put("Date", date);
                        contact.put("#_of_players_attended", numberOfPlayers);
                        contact.put("Players_who_attended", nameOfPlayersAttended);


                        // adding contact to contact list
                        playersList.add(contact);
                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    getActivity().runOnUiThread(new Runnable() {
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
                getActivity().runOnUiThread(new Runnable() {
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
                    getContext(), playersList,
                    R.layout.team_challenge_list, new String[]
                    {"What_activity_did_you_complete?_(please_provide_details)", "Date", "#_of_players_attended", "Players_who_attended"}, new int[]{R.id.activity, R.id.dateCompleted, R.id.numberOfPlayers, R.id.nameOfPlayers});

            listViewTeamChallenges.setAdapter(adapter);
        }


    }

}