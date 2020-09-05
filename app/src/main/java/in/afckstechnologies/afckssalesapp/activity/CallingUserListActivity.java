package in.afckstechnologies.afckssalesapp.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import in.afckstechnologies.afckssalesapp.R;
import in.afckstechnologies.afckssalesapp.adapter.ContactForCallListAdpter;
import in.afckstechnologies.afckssalesapp.jsonparser.JsonHelper;
import in.afckstechnologies.afckssalesapp.models.ContactCallingListDAO;
import in.afckstechnologies.afckssalesapp.utils.AppStatus;
import in.afckstechnologies.afckssalesapp.utils.Config;
import in.afckstechnologies.afckssalesapp.utils.Constant;
import in.afckstechnologies.afckssalesapp.utils.SmsListener;
import in.afckstechnologies.afckssalesapp.utils.WebClient;
import in.afckstechnologies.afckssalesapp.view.AddContactForCallingView;

public class CallingUserListActivity extends AppCompatActivity {
    List<ContactCallingListDAO> contactListDAOArrayCallingList = new ArrayList<ContactCallingListDAO>();
    SharedPreferences preferences;
    SharedPreferences.Editor prefEditor;
    ProgressDialog mProgressDialog;
    ContactCallingListDAO contactCallingListDAO;
    ContactForCallListAdpter contactListAdpter;
    private RecyclerView mcontactList;
    String calllistResponse="";

    private JSONObject jsonLeadObj, jsonLeadObj1;
    JSONArray jsonArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calling_user_list);
        mcontactList = (RecyclerView) findViewById(R.id.contactsList);
        if (AppStatus.getInstance(getApplicationContext()).isOnline()) {

            new getContactList().execute();
        } else {

            Toast.makeText(getApplicationContext(), Constant.INTERNET_MSG, Toast.LENGTH_SHORT).show();
        }

        ContactForCallListAdpter.bindListener(new SmsListener() {
            @Override
            public void messageReceived(String messageText) {
                new getContactList().execute();
            }
        });
        AddContactForCallingView.bindListener(new SmsListener() {
            @Override
            public void messageReceived(String messageText) {
                new getContactList().execute();
            }
        });
    }

    //
    private class getContactList extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create a progressdialog
            mProgressDialog = new ProgressDialog(CallingUserListActivity.this);
            // Set progressdialog title
            mProgressDialog.setTitle("Please Wait...");
            // Set progressdialog message
            mProgressDialog.setMessage("Loading...");
            //mProgressDialog.setIndeterminate(false);
            // Show progressdialog
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {

            jsonLeadObj = new JSONObject() {
                {
                    try {
                        //  put("mobile", "" + preferences.getInt("user_id", 0));
                        // put("branch_id", flag);

                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e("json exception", "json exception" + e);
                    }
                }
            };
            WebClient serviceAccess = new WebClient();

            //  String baseURL = "http://192.168.1.13:8088/lms/api/lead/showlead";
            Log.i("json", "json" + jsonLeadObj);
            calllistResponse = serviceAccess.SendHttpPost(Config.URL_GETCALLINGUSERDETAILS, jsonLeadObj);
            Log.i("resp", "calllistResponse" + calllistResponse);

            if (calllistResponse.compareTo("") != 0) {
                if (isJSONValid(calllistResponse)) {

                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {

                            try {


                                JsonHelper jsonHelper = new JsonHelper();
                                contactListDAOArrayCallingList = jsonHelper.parsecallUserList(calllistResponse);
                                jsonArray = new JSONArray(calllistResponse);

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }
                    });

                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), "Please check your network connection", Toast.LENGTH_LONG).show();
                        }
                    });

                    return null;
                }
            } else {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "Please check your network connection.", Toast.LENGTH_LONG).show();
                    }
                });

                return null;
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void args) {
            if (contactListDAOArrayCallingList.size() > 0) {
                contactListAdpter = new ContactForCallListAdpter(CallingUserListActivity.this, contactListDAOArrayCallingList);
                mcontactList.setAdapter(contactListAdpter);
                mcontactList.setLayoutManager(new LinearLayoutManager(CallingUserListActivity.this));
                contactListAdpter.notifyDataSetChanged();
                mProgressDialog.dismiss();
            } else {
                // Close the progressdialog
                mProgressDialog.dismiss();
            }
        }
    }

    protected boolean isJSONValid(String callReoprtResponse2) {
        // TODO Auto-generated method stub
        try {
            new JSONObject(callReoprtResponse2);
        } catch (JSONException ex) {
            // edited, to include @Arthur's comment
            // e.g. in case JSONArray is valid as well...
            try {
                new JSONArray(callReoprtResponse2);
            } catch (JSONException ex1) {
                return false;
            }
        }
        return true;
    }

    //
    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        super.onBackPressed();
        Intent setIntent = new Intent(CallingUserListActivity.this,SalesDashBoardActivity.class);
        startActivity(setIntent);
    }


}

