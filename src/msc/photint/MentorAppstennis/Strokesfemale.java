package msc.photint.MentorAppstennis;



import msc.photint.MentorAppstennis.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;

import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;


public class Strokesfemale extends Activity {
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.strokesfemale);
       
        Button info=(Button)findViewById(R.id.info);
        Button back=(Button) findViewById(R.id.back);
        Button power=(Button) findViewById(R.id.power);
        
        Button rlt1=(Button) findViewById(R.id.home);
        Button fore=(Button) findViewById(R.id.forehand);
        Button backhnd=(Button) findViewById(R.id.backhand);
        Button slice=(Button) findViewById(R.id.slice);
        Button serve=(Button) findViewById(R.id.serve);
        //Button prem=(Button) findViewById(R.id.home);
        Button volley=(Button) findViewById(R.id.volley);
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
        info.setOnClickListener(new View.OnClickListener() {
	          public void onClick(View view) {
	        	info(); 
	        	  
	        	  	        	  
		      	  }
		        });
        
        rlt1.setOnClickListener(new View.OnClickListener() {
        	
	          public void onClick(View view) {
	        	  Intent intent = new Intent(Strokesfemale.this, MentorApps.class);
	                startActivity(intent);
	        	
	        	  	        	  
		      	  }
		        });
        
        fore.setOnClickListener(new View.OnClickListener() {
	          public void onClick(View view) {
	        	 forehand(); 
	        	  
	        	  	        	  
		      	  }
		        });
        backhnd.setOnClickListener(new View.OnClickListener() {
	          public void onClick(View view) {
	        	 backhand(); 
	        	  
	        	  	        	  
		      	  }
		        });
        serve.setOnClickListener(new View.OnClickListener() {
	          public void onClick(View view) {
	        	 serve(); 
	        	  
	        	  	        	  
		      	  }
		        });
//        prem.setOnClickListener(new View.OnClickListener() {
//	          public void onClick(View view) {
//	        	 prem(); 
//	        	  
//	        	  	        	  
//		      	  }
//		        });
        
        slice.setOnClickListener(new View.OnClickListener() {
	          public void onClick(View view) {
	        	 slice(); 
	        	  
	        	  	        	  
		      	  }
		        });
        
        volley.setOnClickListener(new View.OnClickListener() {
	          public void onClick(View view) {
	        	 volley(); 
	        	  
	        	  	        	  
		      	  }
		        });
        next.setOnClickListener(new View.OnClickListener() {
	          public void onClick(View view) {
		        	 next(); 
		        	  
		        	  	        	  
			      	  }
			        });
        
       
    }
    
 
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
        	Intent intent = new Intent(Strokesfemale.this, MentorApps.class);
          	 startActivity(intent);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
        
    private void next() {
    	Context context = getApplicationContext();
    	CharSequence text = "Please choose a type of shot which you want to shoot";
    	int duration = Toast.LENGTH_SHORT;

    	Toast toast = Toast.makeText(context, text, duration);
    	toast.show();
	       
	   }
    private void back() {
    	 Intent intent = new Intent(Strokesfemale.this, MentorApps.class);
         startActivity(intent);
      	
           
   	       
   	   }
    
    private void forehand() {
    	 Intent intent = new Intent(Strokesfemale.this, Previewforehandfemale.class);
        startActivity(intent);
//     	MediaPlayer mp = MediaPlayer.create(Strokesfemale.this, R.raw.sound);   
//        mp.start();
//        mp.setOnCompletionListener(new OnCompletionListener() {
//
//            
//            public void onCompletion(MediaPlayer mp) {
//                // TODO Auto-generated method stub
//                mp.release();
//            }
//
//        });
//      
   	       
   	   }
    private void backhand() {
    	 
    	Intent intent = new Intent(Strokesfemale.this, Previewbackhandfemale.class);
   	 startActivity(intent);
// 	MediaPlayer mp = MediaPlayer.create(Strokesfemale.this, R.raw.sound);   
//    mp.start();
//    mp.setOnCompletionListener(new OnCompletionListener() {
//
//        
//        public void onCompletion(MediaPlayer mp) {
//            // TODO Auto-generated method stub
//            mp.release();
//        }
//
//    });
        
          
  	       
  	   }
    private void slice() {
     Intent intent = new Intent(Strokesfemale.this, Previewslicefemale.class);
     startActivity(intent);
// 	MediaPlayer mp = MediaPlayer.create(Strokesfemale.this, R.raw.sound);   
//    mp.start();
//    mp.setOnCompletionListener(new OnCompletionListener() {
//
//        
//        public void onCompletion(MediaPlayer mp) {
//            // TODO Auto-generated method stub
//            mp.release();
//        }
//
//    });
//          
  	       
  	   }
    private void serve() {
    	 Intent intent = new Intent(Strokesfemale.this, Previewservefemale.class);
        startActivity(intent);
     	
//    	MediaPlayer mp = MediaPlayer.create(Strokesfemale.this, R.raw.sound);   
//        mp.start();
//        mp.setOnCompletionListener(new OnCompletionListener() {
//
//            
//            public void onCompletion(MediaPlayer mp) {
//                // TODO Auto-generated method stub
//                mp.release();
//            }
//
//        });
  	       
  	   }
    private void prem() {
     	 
    	// prepare the alert box
    	final String[] EMAIL = {"contact@photint.com"};
    	final String WEB = "http://www.photint.com";
        AlertDialog.Builder alertbox = new AlertDialog.Builder(this);
        final Intent i = new Intent();
        
		String text = "Photint: If you have any questions or "
						+"comments, please email the developer or visit "
						+"the Photint web page.\n\nThank you\n\n";
        // set the message to display
        alertbox.setMessage(text);

        // set a positive/yes button and create a listener
        alertbox.setPositiveButton("Email", new DialogInterface.OnClickListener() {

            // do something when the button is clicked
            public void onClick(DialogInterface arg0, int arg1) {
            	i.setAction(android.content.Intent.ACTION_SEND);
				i.setType("message/rfc822");
				i.putExtra(Intent.EXTRA_EMAIL, EMAIL);
				try {
					startActivity(Intent.createChooser(i, "Email using..."));
					
				} catch(ActivityNotFoundException e) {
					 Toast.makeText(getApplicationContext(), "Sorry Could not send Email", Toast.LENGTH_SHORT).show();
				}
            }
        });

        // set a negative/no button and create a listener
        alertbox.setNegativeButton("Website", new DialogInterface.OnClickListener() {

            // do something when the button is clicked
            public void onClick(DialogInterface arg0, int arg1) {
            	i.setAction(android.content.Intent.ACTION_VIEW);
				i.setData(Uri.parse(WEB));
				try {
					startActivity(i);
					
				} catch(ActivityNotFoundException e) {
					 Toast.makeText(getApplicationContext(), "'Sorry could not open Website", Toast.LENGTH_SHORT).show();
				}
            }
        });

        // display box
        alertbox.show();
     	
  	       
  	   }
    private void volley() {
    	 Intent intent = new Intent(Strokesfemale.this, Previewvolleyfemale.class);
      startActivity(intent);
//  	MediaPlayer mp = MediaPlayer.create(Strokesfemale.this, R.raw.sound);   
//    mp.start();
//    mp.setOnCompletionListener(new OnCompletionListener() {
//
//        
//        public void onCompletion(MediaPlayer mp) {
//            // TODO Auto-generated method stub
//            mp.release();
//        }
//
//    });
          
  	       
  	   }
   private void power()
    {
	   Intent intent = new Intent(Intent.ACTION_MAIN);
    intent.addCategory(Intent.CATEGORY_HOME);
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    startActivity(intent);
    	
    }
    private void info() {
     	 
    	Context context = getApplicationContext();
    	CharSequence text = "Please choose a type of shot which you want to shoot and compare";
    	int duration = Toast.LENGTH_SHORT;

    	Toast toast = Toast.makeText(context, text, duration);
    	toast.show();
    	//toast.setGravity(Gravity.TOP|Gravity.LEFT, 0, 0);
           
   	       
   	   }	
    
    
   
}