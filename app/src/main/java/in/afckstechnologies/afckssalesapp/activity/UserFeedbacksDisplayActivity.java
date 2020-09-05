package in.afckstechnologies.afckssalesapp.activity;

import android.app.ProgressDialog;
import android.content.Intent;
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
import in.afckstechnologies.afckssalesapp.adapter.UserFeedbackApprovalListAdpter;
import in.afckstechnologies.afckssalesapp.jsonparser.JsonHelper;
import in.afckstechnologies.afckssalesapp.models.UserFeedbacksDAO;
import in.afckstechnologies.afckssalesapp.utils.Config;
import in.afckstechnologies.afckssalesapp.utils.SmsListener;
import in.afckstechnologies.afckssalesapp.utils.WebClient;

public class UserFeedbacksDisplayActivity extends AppCompatActivity {
    private JSONObject jsonLeadObj;
    JSONArray jsonArray;
    List<UserFeedbacksDAO> data;
    String userFeedbacksResponse="";
    UserFeedbackApprovalListAdpter userFeedbackApprovalListAdpter;
    private RecyclerView mstudentList;
    ProgressDialog mProgressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_feedbacks_display);
        new getUserFeedbackList().execute();
        data = new ArrayList<>();
        UserFeedbackApprovalListAdpter.bindListener(new SmsListener() {
            @Override
            public void messageReceived(String messageText) {
                new getUserFeedbackList().execute();
            }
        });
    }

    //
    private class getUserFeedbackList extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create a progressdialog
            mProgressDialog = new ProgressDialog(UserFeedbacksDisplayActivity.this);
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
                        // put("course_id", course_id);
                        //put("branch_id", branch_id);

                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e("json exception", "json exception" + e);
                    }
                }
            };
            WebClient serviceAccess = new WebClient();

            //  String baseURL = "http://192.168.1.13:8088/srujanlms_new/api/Leadraw/showleadraw";
            Log.i("json", "json" + jsonLeadObj);
            userFeedbacksResponse = serviceAccess.SendHttpPost(Config.URL_GETUSERSFEEDBACKDETAILS, jsonLeadObj);
            Log.i("resp", "batchesListResponse" + userFeedbacksResponse);
            if (userFeedbacksResponse.compareTo("") != 0) {
                if (isJSONValid(userFeedbacksResponse)) {

                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {

                            try {


                                JsonHelper jsonHelper = new JsonHelper();
                                data = jsonHelper.parseUserFeedbacksList(userFeedbacksResponse);
                                jsonArray = new JSONArray(userFeedbacksResponse);

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
                            Toast.makeText(getApplicationContext(), "Please check your webservice", Toast.LENGTH_LONG).show();
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
            if (data.size()>0) {
                userFeedbackApprovalListAdpter = new UserFeedbackApprovalListAdpter(UserFeedbacksDisplayActivity.this, data);
                mstudentList.setAdapter(userFeedbackApprovalListAdpter);
                mstudentList.setLayoutManager(new LinearLayoutManager(UserFeedbacksDisplayActivity.this));
                userFeedbackApprovalListAdpter.notifyDataSetChanged();
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
        Intent setIntent = new Intent(UserFeedbacksDisplayActivity.this,SalesDashBoardActivity.class);
        startActivity(setIntent);
    }
}

