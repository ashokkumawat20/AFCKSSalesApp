package in.afckstechnologies.afckssalesapp.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

import in.afckstechnologies.afckssalesapp.R;
import in.afckstechnologies.afckssalesapp.utils.AppStatus;
import in.afckstechnologies.afckssalesapp.utils.Config;
import in.afckstechnologies.afckssalesapp.utils.Constant;
import in.afckstechnologies.afckssalesapp.utils.VersionChecker;
import in.afckstechnologies.afckssalesapp.utils.WebClient;

public class SalesDashBoardActivity extends AppCompatActivity {
    SharedPreferences preferences;
    SharedPreferences.Editor prefEditor;
    LinearLayout takeStudentFeedback, takeSendDetalis, takeShowDemand, takeCallList, takeUserFeedbacks, takeSendcall, takeCampaignDetalis, takeEnquiriesDetalis,takeShowDemo;
    String verifyMobileDeviceIdResponse = "";
    boolean statusv;
    String mobileDeviceId = "";
    JSONObject jsonObj, jsonObj1, jsonLeadObj, jsonLeadObj1, jsonObjEn,jsonObjST;
    boolean status;
    private String latestVersion = "", smspassResponse = "", getRoleusersResponse = "";
    String trainerResponse = "", callbackCountResponse = "", feedbackCountResponse = "", enquiriesResponse = "";
    TextView callbackcount, feedbackcount, enquiriescount;
    ProgressDialog mProgressDialog;
    String callBackCount = "";
    String feedbackCount = "0";
    String enquiries_count = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_dash_board);
        preferences = getSharedPreferences("Prefrence", Context.MODE_PRIVATE);
        prefEditor = preferences.edit();
        takeSendDetalis = (LinearLayout) findViewById(R.id.takeSendDetalis);
        takeUserFeedbacks = (LinearLayout) findViewById(R.id.takeUserFeedbacks);
        takeCampaignDetalis = (LinearLayout) findViewById(R.id.takeCampaignDetalis);
        takeCallList = (LinearLayout) findViewById(R.id.takeCallList);
        takeShowDemand = (LinearLayout) findViewById(R.id.takeShowDemand);
        takeSendcall = (LinearLayout) findViewById(R.id.takeSendcall);
        takeStudentFeedback = (LinearLayout) findViewById(R.id.takeStudentFeedback);
        takeEnquiriesDetalis = (LinearLayout) findViewById(R.id.takeEnquiriesDetalis);
        takeShowDemo= (LinearLayout) findViewById(R.id.takeShowDemo);
        callbackcount = (TextView) findViewById(R.id.callbackcount);
        feedbackcount = (TextView) findViewById(R.id.feedbackcount);
        enquiriescount = (TextView) findViewById(R.id.enquiriescount);

        takeSendDetalis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (AppStatus.getInstance(getApplicationContext()).isOnline()) {

                    Intent intent = new Intent(getApplicationContext(), StudentsListActivity.class);
                    startActivity(intent);
                } else {

                    Toast.makeText(getApplicationContext(), Constant.INTERNET_MSG, Toast.LENGTH_SHORT).show();
                }
            }
        });

        takeUserFeedbacks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (AppStatus.getInstance(getApplicationContext()).isOnline()) {
                    Intent intent = new Intent(getApplicationContext(), UserFeedbacksDisplayActivity.class);
                    startActivity(intent);
                } else {

                    Toast.makeText(getApplicationContext(), Constant.INTERNET_MSG, Toast.LENGTH_SHORT).show();
                }
            }
        });

        takeCampaignDetalis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (AppStatus.getInstance(getApplicationContext()).isOnline()) {
                    Intent intent = new Intent(getApplicationContext(), CampaignActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), Constant.INTERNET_MSG, Toast.LENGTH_SHORT).show();
                }
            }
        });
        takeCallList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (AppStatus.getInstance(getApplicationContext()).isOnline()) {
                    Intent intent = new Intent(getApplicationContext(), CallingUserListActivity.class);
                    startActivity(intent);
                } else {

                    Toast.makeText(getApplicationContext(), Constant.INTERNET_MSG, Toast.LENGTH_SHORT).show();
                }
            }
        });

        takeShowDemand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (AppStatus.getInstance(getApplicationContext()).isOnline()) {

                    Intent intent = new Intent(getApplicationContext(), ShowDemandActivity.class);
                    startActivity(intent);
                } else {

                    Toast.makeText(getApplicationContext(), Constant.INTERNET_MSG, Toast.LENGTH_SHORT).show();
                }
            }
        });

        takeSendcall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (AppStatus.getInstance(getApplicationContext()).isOnline()) {

                    Intent intent = new Intent(getApplicationContext(), SendCallActivity.class);
                    startActivity(intent);
                } else {

                    Toast.makeText(getApplicationContext(), Constant.INTERNET_MSG, Toast.LENGTH_SHORT).show();
                }
            }
        });
        takeStudentFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (AppStatus.getInstance(getApplicationContext()).isOnline()) {
                    Intent intent = new Intent(getApplicationContext(), StudentFeedbackActivity.class);
                    startActivity(intent);
                } else {

                    Toast.makeText(getApplicationContext(), Constant.INTERNET_MSG, Toast.LENGTH_SHORT).show();
                }
            }
        });
        takeEnquiriesDetalis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (AppStatus.getInstance(getApplicationContext()).isOnline()) {
                    Intent intent = new Intent(getApplicationContext(), EnquiriesActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), Constant.INTERNET_MSG, Toast.LENGTH_SHORT).show();
                }
            }
        });
        takeShowDemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (AppStatus.getInstance(getApplicationContext()).isOnline()) {

                    Intent intent = new Intent(getApplicationContext(), DemoStudentsListActivity.class);
                    startActivity(intent);
                } else {

                    Toast.makeText(getApplicationContext(), Constant.INTERNET_MSG, Toast.LENGTH_SHORT).show();
                }
            }
        });
        if (AppStatus.getInstance(getApplicationContext()).isOnline()) {

            verifyMobileDeviceId();

            new initCallbackCount().execute();
        } else {

            Toast.makeText(getApplicationContext(), Constant.INTERNET_MSG, Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * Called when the activity is about to become visible.
     */
    @Override
    protected void onStart() {
        super.onStart();

        if (AppStatus.getInstance(getApplicationContext()).isOnline()) {
            getUserRoles();


        }


    }

    public void verifyMobileDeviceId() {


        jsonObjST = new JSONObject() {
            {
                try {
                    put("pDeviceID", preferences.getString("sales_mobile_deviceid", ""));
                    put("role_id", "16");

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        Thread objectThread = new Thread(new Runnable() {
            public void run() {
                // TODO Auto-generated method stub
                WebClient serviceAccess = new WebClient();
                verifyMobileDeviceIdResponse = serviceAccess.SendHttpPost(Config.URL_GETAVAILABLEMOBILEDEVICES, jsonObjST);
                Log.i("loginResponse", "verifyMobileDeviceIdResponse" + verifyMobileDeviceIdResponse);
                final Handler handler = new Handler(Looper.getMainLooper());
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        handler.post(new Runnable() { // This thread runs in the UI
                            @Override
                            public void run() {
                                if (verifyMobileDeviceIdResponse.compareTo("") == 0) {

                                } else {

                                    try {
                                        JSONObject jObject = new JSONObject(verifyMobileDeviceIdResponse);
                                        statusv = jObject.getBoolean("status");

                                        if (statusv) {
                                        }
                                        else {
                                            finish();
                                            prefEditor.putString("sales_user_id", "");
                                            prefEditor.commit();
                                            Intent i = new Intent(SalesDashBoardActivity.this, SplashScreenActivity.class);
                                            startActivity(i);
                                        }


                                    } catch (JSONException e) {
                                        // TODO Auto-generated catch block
                                        e.printStackTrace();
                                    }
                                }
                            }
                        });
                    }
                };

                new Thread(runnable).start();
            }
        });
        objectThread.start();
    }

    public void getUserRoles() {


        jsonObj1 = new JSONObject() {
            {
                try {
                    put("role_id", "16");

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        Thread objectThread = new Thread(new Runnable() {
            public void run() {
                // TODO Auto-generated method stub
                WebClient serviceAccess = new WebClient();
                getRoleusersResponse = serviceAccess.SendHttpPost(Config.URL_GETAVAILABLEROLES, jsonObj1);
                Log.i("getRoleusersResponse", getRoleusersResponse);
                final Handler handler = new Handler(Looper.getMainLooper());
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        handler.post(new Runnable() { // This thread runs in the UI
                            @Override
                            public void run() {
                                if (getRoleusersResponse.compareTo("") == 0) {

                                } else {

                                    try {
                                        JSONObject jObject = new JSONObject(getRoleusersResponse);
                                        status = jObject.getBoolean("status");

                                        if (status) {
                                            forceUpdate();
                                        }
                                    } catch (JSONException e) {
                                        // TODO Auto-generated catch block
                                        e.printStackTrace();
                                    }
                                }
                            }
                        });
                    }
                };

                new Thread(runnable).start();
            }
        });
        objectThread.start();
    }

    public void forceUpdate() {
        //  int playStoreVersionCode = FirebaseRemoteConfig.getInstance().getString("android_latest_version_code");
        VersionChecker versionChecker = new VersionChecker();
        try {
            latestVersion = versionChecker.execute().get();
            /*if (latestVersion.length() > 0) {
                latestVersion = latestVersion.substring(50, 58);
                latestVersion = latestVersion.trim();
            }*/


            Log.d("versoncode", "" + latestVersion);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        PackageManager packageManager = this.getPackageManager();
        PackageInfo packageInfo = null;
        try {
            packageInfo = packageManager.getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        //  String currentVersion = packageInfo.versionName;
        String currentVersion = packageInfo.versionName;

        new ForceUpdateAsync(currentVersion, SalesDashBoardActivity.this).execute();

    }

    public class ForceUpdateAsync extends AsyncTask<String, String, JSONObject> {


        private String currentVersion;
        private Context context;

        public ForceUpdateAsync(String currentVersion, Context context) {
            this.currentVersion = currentVersion;
            this.context = context;
        }

        @Override
        protected JSONObject doInBackground(String... params) {


            return new JSONObject();
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            if (latestVersion != null) {
                if (!latestVersion.equals("")) {
                    if (!currentVersion.equalsIgnoreCase(latestVersion)) {
                        // Toast.makeText(context,"update is available.",Toast.LENGTH_LONG).show();

                        if (!((Activity) context).isFinishing()) {
                            showForceUpdateDialog();
                        }


                    }
                } else {
                    if (AppStatus.getInstance(getApplicationContext()).isOnline()) {

                        // AppUpdater appUpdater = new AppUpdater((Activity) context);
                        //  appUpdater.start();
                    } else {

                        Toast.makeText(getApplicationContext(), Constant.INTERNET_MSG, Toast.LENGTH_SHORT).show();
                    }

                }
            }
            super.onPostExecute(jsonObject);
        }

        public void showForceUpdateDialog() {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(new ContextThemeWrapper(context, R.style.AppTheme));

            alertDialogBuilder.setTitle(context.getString(R.string.youAreNotUpdatedTitle));
            alertDialogBuilder.setMessage(context.getString(R.string.youAreNotUpdatedMessage) + " " + latestVersion + context.getString(R.string.youAreNotUpdatedMessage1));
            alertDialogBuilder.setCancelable(false);
            alertDialogBuilder.setPositiveButton(R.string.update, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + context.getPackageName())));
                    dialog.cancel();
                }
            });
            alertDialogBuilder.show();
        }
    }

    //
//
    private class initCallbackCount extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create a progressdialog
            mProgressDialog = new ProgressDialog(SalesDashBoardActivity.this);
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


                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e("json exception", "json exception" + e);
                    }
                }
            };

            jsonObj = new JSONObject() {
                {
                    try {
                        put("user_id", preferences.getString("sales_user_id", ""));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };

            jsonLeadObj1 = new JSONObject() {
                {
                    try {


                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e("json exception", "json exception" + e);
                    }
                }
            };
            jsonObjEn = new JSONObject() {
                {
                    try {

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            WebClient serviceAccess = new WebClient();

            //  String baseURL = "http://192.168.1.13:8088/lms/api/lead/showlead";
            Log.i("json", "json" + jsonLeadObj);
            callbackCountResponse = serviceAccess.SendHttpPost(Config.URL_GETCOUNTCALLBACK, jsonLeadObj);
            Log.i("resp", "leadListResponse" + callbackCountResponse);

            //  String baseURL = "http://192.168.1.13:8088/lms/api/lead/showlead";
            Log.i("json", "json" + jsonLeadObj1);
            feedbackCountResponse = serviceAccess.SendHttpPost(Config.URL_GETCOUNTFEEDBACK, jsonLeadObj1);
            Log.i("resp", "leadListResponse" + feedbackCountResponse);


            Log.i("json", "json" + jsonObjEn);
            enquiriesResponse = serviceAccess.SendHttpPost(Config.URL_GETCOUNTENQUIRIES, jsonObjEn);
            Log.i("resp", "enquiriesResponse" + enquiriesResponse);

            if (callbackCountResponse.compareTo("") != 0) {
                if (isJSONValid(callbackCountResponse)) {

                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {

                            try {


                                JSONObject jsonObject = new JSONObject(callbackCountResponse);
                                callBackCount = jsonObject.getString("totalcallback");


                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }
                    });

                }
                if (isJSONValid(enquiriesResponse)) {


                    try {

                        jsonObjEn = new JSONObject(enquiriesResponse);
                        status = jsonObjEn.getBoolean("status");
                        enquiries_count = jsonObjEn.getString("totalenquiries");

                    } catch (JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }


                }
                if (isJSONValid(feedbackCountResponse)) {

                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {

                            try {

                                JSONObject jsonObject = new JSONObject(feedbackCountResponse);
                                feedbackCount = jsonObject.getString("totalfeedback");

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }
                    });

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
            mProgressDialog.dismiss();
            if (callbackCountResponse.compareTo("") != 0) {
                callbackcount.setText(callBackCount);
            }
            if (feedbackCountResponse.compareTo("") != 0) {
                feedbackcount.setText(feedbackCount);
            }
            if (enquiriesResponse.compareTo("") != 0) {
                enquiriescount.setText(enquiries_count);
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
        Intent setIntent = new Intent(Intent.ACTION_MAIN);
        setIntent.addCategory(Intent.CATEGORY_HOME);
        setIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(setIntent);
    }
}
