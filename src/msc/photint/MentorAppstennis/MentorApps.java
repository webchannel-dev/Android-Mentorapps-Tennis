package msc.photint.MentorAppstennis;



import java.io.File;

import msc.photint.MentorAppstennis.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import android.widget.Toast;


public class MentorApps extends Activity {
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
       
        setContentView(R.layout.main);
       
        Button rlt=(Button) findViewById(R.id.next);
        Button home=(Button) findViewById(R.id.home);
        Button info=(Button) findViewById(R.id.info);
        Button back=(Button) findViewById(R.id.back);
        Button male=(Button) findViewById(R.id.male);
        Button female=(Button) findViewById(R.id.female);
        File wallpaperDirectory = new File("/sdcard/photint/");
        wallpaperDirectory.mkdirs();
        Button power = (Button)findViewById(R.id.power);
        
        rlt.setOnClickListener(new View.OnClickListener() {
	          public void onClick(View view) {
	        	  
	        	  Context context = getApplicationContext();
	          	CharSequence text = "Please select your Gender to move to next screen";
	          	int duration = Toast.LENGTH_SHORT;

	          	Toast toast = Toast.makeText(context, text, duration);
	          	toast.show();      	  
		      	  }
		        });
      info.setOnClickListener(new View.OnClickListener() {
	          public void onClick(View view) {
	        	  info(); 
	        	  	        	  
		      	  }
		        });
      back.setOnClickListener(new View.OnClickListener() {
          public void onClick(View view) {
        	  Intent intent = new Intent(MentorApps.this, SplashScreen.class);
            	 startActivity(intent);
        	
        	  	        	  
	      	  }
	        });
        power.setOnClickListener(new View.OnClickListener() {
	          public void onClick(View view) {
	        	  power(); 
	        	  	        	  
		      	  }
		        });
        home.setOnClickListener(new View.OnClickListener() {
	          public void onClick(View view) {
	        	  Context context = getApplicationContext();
	          	CharSequence text = "You are already in the Home screen!!!";
	          	int duration = Toast.LENGTH_SHORT;

	          	Toast toast = Toast.makeText(context, text, duration);
	          	toast.show();
	          	
	        	  	        	  
		      	  }
		        });
        female.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
          	  female(); 
          	  
          	  	        	  
            	  }
              });
      male.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
          	  male(); 
          	  
          	  	        	  
            	  }
              });
       
 
  
    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
        	   Intent intent = new Intent(Intent.ACTION_MAIN);
        	    intent.addCategory(Intent.CATEGORY_HOME);
        	    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        	    startActivity(intent);
            return true;
        }
      
        return super.onKeyDown(keyCode, event);
    }
    private void power()
    {
	   Intent intent = new Intent(Intent.ACTION_MAIN);
    intent.addCategory(Intent.CATEGORY_HOME);
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    startActivity(intent);
    	
    }
    private void info()
    {
    	
    	AlertDialog.Builder alertbox = new AlertDialog.Builder(this);
    	 
        // set the message to display
        alertbox.setMessage("Start Playing Tennis with Instruction and advice for begineers on StrokesMale,Strategy,rules,terms,and equipment.These free lessons,tips on which racquet to buy and many other resources will help you learn tennis easily.");

        // add a neutral button to the alert box and assign a click listener
        alertbox.setNeutralButton("Ok", new DialogInterface.OnClickListener() {

            // click listener on the alert box
            public void onClick(DialogInterface arg0, int arg1) {
                // the button was clicked
                Toast.makeText(getApplicationContext(), "Please select the Gender", Toast.LENGTH_LONG).show();
            }
        });

        // show it
        alertbox.show();
    	
    }
    private void female()
    {
    	 
    	Intent intent = new Intent(MentorApps.this, Strokesfemale.class);
   	 startActivity(intent);
   	MediaPlayer mp = MediaPlayer.create(MentorApps.this, R.raw.sound);   
     mp.start();
     mp.setOnCompletionListener(new OnCompletionListener() {

         
         public void onCompletion(MediaPlayer mp) {
             // TODO Auto-generated method stub
             mp.release();
         }

     });

    }
    private void male()
    {
   	 
    	Intent intent = new Intent(MentorApps.this, StrokesMale.class);
   	 startActivity(intent);
   	MediaPlayer mp = MediaPlayer.create(MentorApps.this, R.raw.sound);   
    mp.start();
    mp.setOnCompletionListener(new OnCompletionListener() {

        
        public void onCompletion(MediaPlayer mp) {
            // TODO Auto-generated method stub
            mp.release();
        }

    });
    }
 
 
    
   
}