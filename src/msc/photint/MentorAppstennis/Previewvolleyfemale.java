package msc.photint.MentorAppstennis;



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
import android.widget.Button;

import android.widget.Toast;


public class Previewvolleyfemale extends Activity {
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.preview);
        Button pro=(Button)findViewById(R.id.pro);
        Button myvideo=(Button)findViewById(R.id.my);
        Button rec=(Button)findViewById(R.id.record);
        Button Compare=(Button)findViewById(R.id.seecompare);
        Button info=(Button)findViewById(R.id.info);
        Button back=(Button) findViewById(R.id.back);
        Button power=(Button) findViewById(R.id.power);
        Button home=(Button) findViewById(R.id.home);
        Button next=(Button) findViewById(R.id.next);
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
          	CharSequence text = "Please press a Tennis ball to proceed further- :)";
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
        
        pro.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
          
            	 
            	Intent intent = new Intent(Previewvolleyfemale.this, Provolleyfemale.class);
           	 startActivity(intent);
//           	MediaPlayer mp = MediaPlayer.create(Previewvolleyfemale.this, R.raw.sound);   
//            mp.start();
//            mp.setOnCompletionListener(new OnCompletionListener() {
//
//                
//                public void onCompletion(MediaPlayer mp) {
//                    // TODO Auto-generated method stub
//                    mp.release();
//                }
//
//            }); 
          	  	        	  
            	  }
              });
        
        myvideo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
          
            	Intent intent = new Intent(Previewvolleyfemale.this, Browser.class);
              	 startActivity(intent);
           	 

//             	MediaPlayer mp = MediaPlayer.create(Previewvolleyfemale.this, R.raw.sound);   
//                mp.start();
//                mp.setOnCompletionListener(new OnCompletionListener() {
//
//                    
//                    public void onCompletion(MediaPlayer mp) {
//                        // TODO Auto-generated method stub
//                        mp.release();
//                    }
//
//                });      	  
            	  }
              });
        Compare.setOnClickListener(new View.OnClickListener() {
	          public void onClick(View view) {
	        	
	        	  Intent intent = new Intent(Previewvolleyfemale.this, Comparevolleyfemale.class);
	            	 startActivity(intent);
	            	 finish();
//	            		MediaPlayer mp = MediaPlayer.create(Previewvolleyfemale.this, R.raw.sound);   
//	                    mp.start();
//	                    mp.setOnCompletionListener(new OnCompletionListener() {
//
//	                        
//	                        public void onCompletion(MediaPlayer mp) {
//	                            // TODO Auto-generated method stub
//	                            mp.release();
//	                        }
//
//	                    });  	        	  
		      	  }
		        });
        rec.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
          
            	Context context = getApplicationContext();
            	CharSequence text = "Please rotate the screen to Landscape mode";
            	int duration = Toast.LENGTH_SHORT;
            	Toast toast = Toast.makeText(context, text, duration);
            	toast.show();
            	
            	Intent intent = new Intent(Previewvolleyfemale.this, Recorvolleyfemale.class);
           	 startActivity(intent);
           	 finish();
//         	MediaPlayer mp = MediaPlayer.create(Previewvolleyfemale.this, R.raw.sound);   
//            mp.start();
//            mp.setOnCompletionListener(new OnCompletionListener() {
//
//                
//                public void onCompletion(MediaPlayer mp) {
//                    // TODO Auto-generated method stub
//                    mp.release();
//                }
//
//            }); 
          	  	        	  
            	  }
              });
   	   }	
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
        	Intent intent = new Intent(Previewvolleyfemale.this, Strokesfemale.class);
          	 startActivity(intent);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    private void back() {
   	 Intent intent = new Intent(Previewvolleyfemale.this, Strokesfemale.class);
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
    
    	alertbox.setMessage("Please press a Tennis ball" + "\n \n"+"1.Mentor - Watch a Professional playing the particular shot" +  "\n \n"+ "2.Record Me - Record and Compare a new video of yourself" +"\n \n"+"Compare Me - A comparison of the recorded video with a professional technique" +"\n \n"+"4.My Gallery - Displays the list of recorded videos") ;
        

        // add a neutral button to the alert box and assign a click listener
        alertbox.setNeutralButton("Ok", new DialogInterface.OnClickListener() {

            // click listener on the alert box
            public void onClick(DialogInterface arg0, int arg1) {
                // the button was clicked
                Toast.makeText(getApplicationContext(), "Please select a press Tennis Ball", Toast.LENGTH_LONG).show();
            }
        });

        // show it
        alertbox.show();
    	
    }	
    private void home() {
   	 Intent intent = new Intent(Previewvolleyfemale.this, MentorApps.class);
   	 startActivity(intent);


   	   
   	}
    
    
    
   
}