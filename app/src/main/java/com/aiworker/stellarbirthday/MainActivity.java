package com.aiworker.stellarbirthday;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.SearchManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.FileProvider;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;

import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.login.widget.ProfilePictureView;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareDialog;

import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends Activity {
    Uri fileUri;
    private DatePicker birthdayDatePicker;
    private TextView info, tvBirthdayStarName, tvBirthdayStarInfo;
    public static String BirthdayStarName = "Rigel";
    public static long days=0;
    private LoginButton loginButton;
    private CallbackManager callbackManager;
    ProfilePictureView profilePicture;
    Button btn_facebookShare;
    ShareDialog shareDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();

        /** You need this method to be used only once to configure
         your key hash in your App Console at developers.facebook.com/apps */
//        getFbKeyHash("com.aiworker.stellarbirthday");

        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        btn_facebookShare = (Button) findViewById(R.id.btn_facebookShare);
        profilePicture = (ProfilePictureView)findViewById(R.id.profile_picture);

        loginButton = (LoginButton)findViewById(R.id.login_button);
        loginButton.setReadPermissions(Arrays.asList("public_profile, email, user_birthday"));
//        loginButton.setReadPermissions(Arrays.asList("user_birthday"));

//		RelativeLayout layout =(RelativeLayout)findViewById(R.id.background);
//		layout.setBackgroundResource(R.drawable.skymap3);

        info = (TextView) findViewById(R.id.info);
        tvBirthdayStarName = (TextView) findViewById(R.id.tvBirthdayStarName);
        tvBirthdayStarInfo = (TextView) findViewById(R.id.tvBirthdayStarInfo);
        birthdayDatePicker = (DatePicker) findViewById(R.id.date_picker);

        tvBirthdayStarName.setVisibility(View.VISIBLE);
        tvBirthdayStarInfo.setVisibility(View.VISIBLE);

//        Stellar.iniStarsArray();
        shareDialog = new ShareDialog(this);


        /** register the custom callback */
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult)
            {
                System.out.println("onSuccess");
                GraphRequest request = GraphRequest.newMeRequest
                        (loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback()
                        {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response)
                            {
                                // Application code
                                Log.v("LoginActivity", response.toString());
                                //System.out.println("Check: " + response.toString());
                                try
                                {
                                    String id = object.getString("id");
                                    String name = object.getString("name");
                                    String email = object.getString("email");
                                    String gender = object.getString("gender");
                                    String birthday = object.getString("birthday");
                                    System.out.println(id + ", " + name + ", " + email + ", " + gender + ", " + birthday);
//                                    tv_facebookInfo.setText(birthday);
//                                    tv_facebookInfo.setText("ID: " + id + " | " + email + "\n" + name + ", " + " | " + gender + " | " + birthday);
                                    profilePicture.setProfileId(object.getString("id"));
                                }
                                catch (JSONException e)
                                {
                                    e.printStackTrace();
                                }

                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id, name, email, gender, birthday, picture");
                request.setParameters(parameters);
                request.executeAsync();
            }

            @Override
            public void onCancel() {
                info.setText("Login attempt canceled."); System.out.println("IR: onCancel");
            }

            @Override
            public void onError(FacebookException e) {
                info.setText("Login attempt failed."); System.out.println("IR: onError");
            }

        });

        /** check if user already login, get and display profile info if yes */
        if(AccessToken.getCurrentAccessToken()!=null){
            GraphRequest request = GraphRequest.newMeRequest(
                    AccessToken.getCurrentAccessToken(),
                    new GraphRequest.GraphJSONObjectCallback() {
                        @Override
                        public void onCompleted(
                                JSONObject object,
                                GraphResponse response) {
                            // Application code
                            try
                            {
                                String id = object.getString("id");
                                String name = object.getString("name");
                                String email = object.getString("email");
                                String gender = object.getString("gender");
                                String birthday = object.getString("birthday");
                                System.out.println(id + ", " + name + ", " + email + ", " + gender + ", " + birthday);


                                info.setText(birthday);

//                                tv_facebookInfo.setText("ID: " + id + " | " + email + "\n" + name + ", " + " | " + gender + " | " + birthday);
                                profilePicture.setProfileId(object.getString("id"));
                            }
                            catch (JSONException e)
                            {
                                e.printStackTrace();
//                                tv_facebookInfo.setText("exception...");
                            }
                        }
                    });
            Bundle parameters = new Bundle();
            parameters.putString("fields", "id,name,email,gender, birthday, picture");
            request.setParameters(parameters);
            request.executeAsync();

        } else {
//            profilePicture.setProfileId(null);
//            tv_facebookInfo.setText("please login");
        }

        /** display sharing dialog */
        btn_facebookShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharePhotoToFacebook();
//                shareLinkToFacebook();

            }
        });


        Stellar.iniStarsArray();

        // --DatePicker listener
        Calendar today = Calendar.getInstance();
        birthdayDatePicker.init(today.get(Calendar.YEAR),today.get(Calendar.MONTH),today.get(Calendar.DAY_OF_MONTH),
                new OnDateChangedListener(){
                    @Override
                    public void onDateChanged(DatePicker view,
                                              int year, int monthOfYear,int dayOfMonth) {
                        // -- get difference in days
                        Calendar thatDay = Calendar.getInstance();

                        thatDay.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                        thatDay.set(Calendar.MONTH,monthOfYear); // 0-11 so 1 less
                        thatDay.set(Calendar.YEAR, year);

                        Calendar today = Calendar.getInstance();
                        long diff = today.getTimeInMillis() - thatDay.getTimeInMillis(); //result in millis

                        days = diff / (24 * 60 * 60 * 1000);
//		                  info.setText(String.valueOf(days)+"  ld");

                        BirthdayStarName = Stellar.getStellarBirthdayStarName(days);


                        if(Stellar.DaysToStellarBirthday == 0){
                            tvBirthdayStarName.setText("Stellar Birthday '" + BirthdayStarName+"' is " + "today" );
                            tvBirthdayStarInfo.setText(Stellar.StarInfo[Stellar.index]);
                        }
                        if(Stellar.DaysToStellarBirthday == 1){
                            tvBirthdayStarName.setText("Stellar Birthday '" + BirthdayStarName+"' in " +
                                    String.valueOf(Math.round(Stellar.DaysToStellarBirthday)) + " day");
                            tvBirthdayStarInfo.setText(Stellar.StarInfo[Stellar.index]);
                        }
                        if(Stellar.DaysToStellarBirthday > 1){
                            tvBirthdayStarName.setText("Stellar Birthday '" + BirthdayStarName+"' in " +
                                    String.valueOf(Math.round(Stellar.DaysToStellarBirthday)) + " days");
                            tvBirthdayStarInfo.setText(Stellar.StarInfo[Stellar.index]);
                        }

                        if(Stellar.DaysToStellarBirthday < 0){
                            tvBirthdayStarName.setText("please select earliest birthday date");
                        }
                        if(days<=0){
                            tvBirthdayStarName.setText("you have not born yet");
                        }
                        if(days==0){
                            tvBirthdayStarName.setText(BirthdayStarName);
                            tvBirthdayStarInfo.setText(Stellar.StarInfo[Stellar.index]);
                        }


//        	      info.setText(
//        	        "Year: " + year + "\n" +
//        	        "Month of Year: " + monthOfYear+1 + "\n" +
//        	        "Day of Month: " + dayOfMonth);

                    }

                }

        );


    }

    private void sharePhotoToFacebook(){
        Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.logo260);
//        BitmapFactory.decodeResource("/sdcard/logo.jpg");

        SharePhoto photo = new SharePhoto.Builder()
                .setBitmap(image)
                .build();
        SharePhotoContent content = new SharePhotoContent.Builder()
                .addPhoto(photo)
                .build();
        shareDialog.show(content);
    }

    private void shareLinkToFacebook(){
        ShareLinkContent content = new ShareLinkContent.Builder()
                .setContentUrl(Uri.parse("https://youtu.be/8XWXJDgbeP0"))
                .setContentTitle("The Singularity Is Near Movie Trailer")
                .setContentDescription("The Singularity is Near, A True Story about the Future, based on Ray Kurzweil’s New York Times bestseller")
                .setImageUrl(Uri.parse("https://qph.is.quoracdn.net/main-thumb-t-1310-200-ykjopcfaccptqyydeyfbovmprpavvzoa.jpeg"))
                .build();

        shareDialog.show(content);
    }



    /** -- start google skymap */
    public void onClick_start_googleskymap (View v)
    {
        Intent i = new Intent(Intent.ACTION_SEARCH);
//		Intent i = new Intent(Intent.ACTION_DEFAULT);
        i.setPackage("com.google.android.stardroid");
//		i.setPackage("com.escapistgames.starchart");
        i.putExtra(SearchManager.QUERY, BirthdayStarName);


        // Verify it resolves
        PackageManager packageManager = getPackageManager();
        List<ResolveInfo> activities = packageManager.queryIntentActivities(i, 0);
        boolean isIntentSafe = activities.size() > 0;

//		startActivity(i);
        // Start an activity if it's safe
        if (isIntentSafe) {
            startActivity(i);
        }
        else {
//			Toast.makeText(this, "warning", Toast.LENGTH_LONG).show();

            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("market://search?q=sky map&c=apps"));
            startActivity(intent);
        }
    }

    /** -- start stellar info page */
    public void onClick_start_stellarinfo (View v)
    {
        Intent intent = new Intent(this, StellarInfo.class);
        startActivity(intent);

    }

    /** -- start stellar info page */
    public void onClick_start_aboutstars (View v)
    {
        tvBirthdayStarName.setVisibility(View.INVISIBLE);
        tvBirthdayStarInfo.setVisibility(View.INVISIBLE);
        Intent intent = new Intent(this, AboutStars.class);
        startActivity(intent);

    }
    /**  -- start stellar info page*/
    public void onClick_share_stellarinfo (View v)
    {

		/* Get a File for the selected file name. Assume that the file names are in the mImageFilename array */
//        File requestFile = new File(mImageFilename[position]);
        File requestFile = new File("logo.png");
//        File filePath = getFileStreamPath("vega1.jpg");
        /* Most file-related method calls need to be in try-catch blocks */
        // Use the FileProvider to get a content URI
        Uri uri = Uri.parse("android.resource://com.aiworker.stellarbirthday/drawable/vega1.jpg");
        try {
            fileUri = FileProvider.getUriForFile(MainActivity.this,
                    "com.aiworker.stellarbirthday.fileprovider", requestFile);
        } catch (IllegalArgumentException e) {
            Log.e("File Selector", "The selected file can't be shared: ");
        }
//        Toast.makeText(this, fileUri, Toast.LENGTH_LONG).show();

        //		// Verify the original intent will resolve to at least one activity
//		if (sendIntent.resolveActivity(getPackageManager()) != null) {
//		    startActivity(chooser);
//		}

//		Intent sendIntent = new Intent();
//		sendIntent.setAction(Intent.ACTION_SEND);
//		File filePath = getFileStreamPath("vega1.jpg");
//		Uri uriToImage = Uri.fromFile(filePath);

        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_TEXT, "My next #Stellar #Birthdays is VEGA");
//		shareIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(filePath));
        shareIntent.putExtra(Intent.EXTRA_STREAM, fileUri);
//		shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
//		shareIntent.setType("image/jpeg");
        // Grant temporary read permission to the content URI
//		if (fileUri != null) {
        // Grant temporary read permission to the content URI
        shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//        }

        shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        shareIntent.setType("image/*");
        startActivity(Intent.createChooser(shareIntent, getResources().getText(R.string.chooser_title)));

//		sendIntent.putExtra(Intent.EXTRA_TEXT, "My next #Stellar #Birthdays is VEGA");
//		sendIntent.setType("text/plain");
//		startActivity(Intent.createChooser(sendIntent, getResources().getText(R.string.chooser_title)));
    }

    public void getFbKeyHash(String packageName) {

        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    packageName,
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("YourKeyHash :", Base64.encodeToString(md.digest(), Base64.DEFAULT));
//                System.out.println("YourKeyHash: "+ Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onResume() {
        super.onResume();
//        Log.v(TAG, "inside onResume");
        tvBirthdayStarName.setVisibility(View.VISIBLE);
        tvBirthdayStarInfo.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}

