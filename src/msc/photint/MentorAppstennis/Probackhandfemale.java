package msc.photint.MentorAppstennis;

import msc.photint.MentorAppstennis.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;


public class Probackhandfemale extends Activity {
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.pro);
        Button info=(Button)findViewById(R.id.info);
        Button back=(Button) findViewById(R.id.back);
        Button power=(Button) findViewById(R.id.power);
        Button home=(Button) findViewById(R.id.home);
        Button next=(Button) findViewById(R.id.next);
      // pro=(VideoView)findViewById(R.id.videoView1);
     
        MediaController mc = new MediaController(this);
        
        VideoView vv = (VideoView)this.findViewById(R.id.video);
        vv.setMediaController(mc);
        String fileName = "android.resource://" + getPackageName() + "/" + R.raw.backhand_fem;
        vv.getDuration();
        vv.setVideoURI(Uri.parse(fileName));
        
        vv.start();
        vv.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            
            public void onCompletion(MediaPlayer mp) {
                // not playVideo
                            // playVideo();

            	Intent intent = new Intent(Probackhandfemale.this, Probackhandfemale.class);
            	 startActivity(intent);
            	 finish();
            }
        });
        
        power.setOnClickListener(new View.OnClickListener() {
	          public void onClick(View view) {
	        	 power(); 
	        	  
	        	  	        	  
		      	  }
		        });
  
    back.setOnClickListener(new View.OnClickListener() {
	          public void onClick(View view) {
	        	 back(); 
	        	  
	        	  	        	  
		      	  }
		        });
    next.setOnClickListener(new View.OnClickListener() {
        public void onClick(View view) {
      	 
      	  Context context = getApplicationContext();
        	CharSequence text = "Please press back then proceed to record a video.";
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
    home.setOnClickListener(new View.OnClickListener() {
        public void onClick(View view) {
      	  home(); 
      	  
      	  	        	  
        	  }
          });
        
            	 }
        
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
        	Intent intent = new Intent(Probackhandfemale.this, Previewbackhandfemale.class);
          	 startActivity(intent);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    
    private void back() {
      	 Intent intent = new Intent(Probackhandfemale.this, Previewbackhandfemale.class);
           startActivity(intent);
        	
             
     	       
     	   }
       private void power()
       {
   	   Intent intent = new Intent(Intent.ACTION_MAIN);
       intent.addCategory(Intent.CATEGORY_HOME);
       intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
       startActivity(intent);
       	
       }
       private void info() {
       	
       	AlertDialog.Builder alertbox = new AlertDialog.Builder(this);
       	 
           // set the message to display
       
       	 alertbox.setMessage("Please use the media controller to pause play and seek the video") ;
           

           // add a neutral button to the alert box and assign a click listener
           alertbox.setNeutralButton("Ok", new DialogInterface.OnClickListener() {

               // click listener on the alert box
               public void onClick(DialogInterface arg0, int arg1) {
                   // the button was clicked
                   Toast.makeText(getApplicationContext(), "Professional backhand-Female Video", Toast.LENGTH_LONG).show();
               }
           });

           // show it
           alertbox.show();
       	
       }	
       private void home() {
      	 Intent intent = new Intent(Probackhandfemale.this, MentorApps.class);
      	 startActivity(intent);


      	   
      	}
    
   
}