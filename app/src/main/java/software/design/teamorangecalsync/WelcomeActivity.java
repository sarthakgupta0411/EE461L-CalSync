package software.design.teamorangecalsync;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;import android.accounts.Account;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.api.client.auth.oauth2.Credential;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.client.googleapis.extensions.android.gms.auth.UserRecoverableAuthIOException;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.Scope;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener{
    private GoogleSignInClient mGoogleSignInClient;
    private static final int RC_SIGN_IN = 9001;
    private static final int RC_RECOVERABLE = 9002;
    private static final String CONTACTS_SCOPE = "https://www.googleapis.com/auth/calendar.readonly";
    private static final String KEY_ACCOUNT = "key_account";
    private Account mAccount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        findViewById(R.id.sign_in_button).setOnClickListener(this);
        findViewById(R.id.sign_out_button).setOnClickListener(this);
        findViewById(R.id.button3).setOnClickListener(this);

        if (savedInstanceState != null) {
            mAccount = savedInstanceState.getParcelable(KEY_ACCOUNT);
        }
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestScopes(new Scope(CONTACTS_SCOPE ))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        SignInButton signInButton = findViewById(R.id.sign_in_button);


        signInButton.setSize(SignInButton.SIZE_STANDARD);
        signInButton.setColorScheme(SignInButton.COLOR_LIGHT);

    }

    @Override
    public void onStart() {
        super.onStart();

        // [START on_start_sign_in]
        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if (GoogleSignIn.hasPermissions(account, new Scope(CONTACTS_SCOPE ))) {
            updateUI(account);
        } else {
            updateUI(null);
        }
        // [END on_start_sign_in]
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(KEY_ACCOUNT, mAccount);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }

        if (requestCode == RC_RECOVERABLE) {
            if (resultCode == RESULT_OK) {
                getContacts();
            } else {
                Toast.makeText(this, R.string.msg_contacts_failed, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            updateUI(account);
            mAccount = account.getAccount();


            // Asynchronously access the People API for the account
            getContacts();
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            // Log.w(TAG, "handleSignInResult:error", e);

            // Clear the local account
            mAccount = null;
            updateUI(null);
        }
    }

    private void getContacts() {
        if (mAccount == null) {
            //Log.w(TAG, "getContacts: null account");
            return;
        }


        new GetContactsTask(this).execute(mAccount);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_in_button:
                signIn();
                break;
            case R.id.sign_out_button:
                signOut();

                break;
            case R.id.button3:
                gotoActivity(MainActivity.class);

                break;
            // ...
        }
    }

    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // [START_EXCLUDE]
                        updateUI(null);
                        // [END_EXCLUDE]
                    }
                });
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    private void updateUI(@Nullable GoogleSignInAccount account) {
        if (account != null) {


            findViewById(R.id.sign_in_button).setVisibility(View.GONE);
            findViewById(R.id.sign_out_button).setVisibility(View.VISIBLE);
            findViewById(R.id.button3).setVisibility(View.VISIBLE);

        } else {

            findViewById(R.id.button3).setVisibility(View.GONE);
            findViewById(R.id.sign_in_button).setVisibility(View.VISIBLE);
            findViewById(R.id.sign_out_button).setVisibility(View.GONE);

        }
    }

    private static class GetContactsTask extends AsyncTask<Account, Void, List<String>> {

        private WeakReference<WelcomeActivity> mActivityRef;

        public GetContactsTask(WelcomeActivity activity) {
            mActivityRef = new WeakReference<>(activity);
        }

        @Override
        protected List<String> doInBackground(Account... accounts) {
            if (mActivityRef.get() == null) {
                return null;
            }

            List<Event> gevents = new ArrayList<>();

            Context context = mActivityRef.get().getApplicationContext();
            try {
                GoogleAccountCredential credential = GoogleAccountCredential.usingOAuth2(
                        context,
                        Collections.singleton(CONTACTS_SCOPE));
                credential.setSelectedAccount(accounts[0]);

                HttpTransport transport = AndroidHttp.newCompatibleTransport();
                JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();

                Calendar serv = new Calendar.Builder(transport, jsonFactory, credential)
                        .setApplicationName("applicationName").build();

                DateTime now = new DateTime(System.currentTimeMillis());
                Events events = serv.events().list("primary")
                        .setMaxResults(10)
                        .setTimeMin(now)
                        .setOrderBy("startTime")
                        .setSingleEvents(true)
                        .execute();
                List<Event> items = events.getItems();
                if (items.isEmpty()) {
                    System.out.println("No upcoming events found.");
                } else {
                    System.out.println("Upcoming events");
                    for (Event event : items) {
                        DateTime start = event.getStart().getDateTime();
                        if (start == null) {
                            start = event.getStart().getDate();
                        }
                        System.out.println( event.getSummary() + "------" + event.getStart().getDateTime());
                        Date[] dates = gEventTimeParser(event.getStart().getDateTime().toString());
                        //gevents.add(new Event(event.getSummary().toString(),dates[0], null, null, "GoogleCalendar"));
                    }
                }

// Retrieve an event





                return null;//connectionsResponse.getConnections();

            } catch (UserRecoverableAuthIOException recoverableException) {
                if (mActivityRef.get() != null) {
                    //mActivityRef.get().onRecoverableAuthException(recoverableException);
                }
            } catch (IOException e) {
                // Log.w(TAG, "getContacts:exception", e);
            }

            return null;
        }

        public Date[] gEventTimeParser(String s){
            //this will take off the CST offest
         //   for(String time : s){
                String time = s.substring(0,s.length()-6);

            GregorianCalendar startDateCal = null;
       //     GregorianCalendar endDateCal = null;

            //these will set the year for both times
            int startTimeYear = Integer.parseInt(s.substring(0,4));
         //   int endTimeYear = Integer.parseInt(s[1].substring(0,4));

            //these will get the month for both times
            int startTimeMonth = Integer.parseInt(s.substring(5,7)) - 1;
           // int endTimeMonth = Integer.parseInt(s[1].substring(5,7)) - 1;

            //these will get the day for both times
            int startTimeDay = Integer.parseInt(s.substring(8,10));
           // int endTimeDay = Integer.parseInt(s[1].substring(8,10));

            //these will get the hour of day for both times
            int startTimeHour = Integer.parseInt(s.substring(11,13));
           // int endTimeHour = Integer.parseInt(s[1].substring(11,13));

            //these will get the minute of day for both times
            int startTimeMinute = Integer.parseInt(s.substring(14,16));
            //int endTimeMinute = Integer.parseInt(s[1].substring(14,16));

            startDateCal = new GregorianCalendar(startTimeYear, startTimeMonth, startTimeDay, startTimeHour, startTimeMinute);
//            endDateCal = new GregorianCalendar(endTimeYear, endTimeMonth, endTimeDay, endTimeHour, endTimeMinute);

            Date[] Duration = {null,null};
            Duration[0] = startDateCal.getTime();
            //Duration[1] = endDateCal.getTime();

            return Duration;

        }

        @Override
        protected void onPostExecute(List<String> people) {
            super.onPostExecute(people);
//            if (mActivityRef.get() != null) {
//                mActivityRef.get().onConnectionsLoadFinished(people);
//            }
        }

    }
    private void gotoActivity(Class activity) {
        Intent activityIntent = new Intent(this, activity);
        startActivity(activityIntent);
    }
}
