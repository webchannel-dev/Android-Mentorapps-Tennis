package msc.photint.MentorAppstennis;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.VideoView;

public class Compareslicemale extends Activity implements OnErrorListener, OnPreparedListener {
	/** Called when the activity is first created. */
	private static final int UPDATE_FREQUENCY = 500;

			private TextView currentTime, duration,currentTime2, duration2;

	private VideoView videoView,videoView2;

	private SeekBar seekbar,seekbar2 = null;
	
	private ImageButton playButton,playbutton2 = null;

	private boolean isMoveingSeekBar,isMovingSeekBar2 = false;

		private final Handler handler = new Handler();
	private final Handler handler2 = new Handler();

	private boolean isStarted = true;

	private String currentFile = "ky_320b";

	private boolean isCustomSeekButtonClick,isCustomSeekButtonClick2 = false;

	private boolean isPauseButtonClick = false;

		private static int percentageBuffer = 0; 

	private int mpCurrentPosition,mpCurrentPosition2;

	int hh = 00, mm = 00, ss = 00, ms = 00;
	int hh2=00,mm2=00,ss2=00,ms2=00;

	int i = 0;

	int previouPosition = 0;

	private final Runnable updatePositionRunnable = new Runnable()
	{
	    public void run() 
	    {
	        updatePosition();
	    }
	};
	private final Runnable updatePositionRunnable2 = new Runnable()
	{
	    public void run() 
	    {
	        updatePosition2();
	    }

			};

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
	    super.onCreate(savedInstanceState);

	    setContentView(R.layout.compare);

	  //  setUpMyDialog();
	  //  showMyDialog();
	    
	    videoView = (VideoView) findViewById(R.id.videoView1);
	    videoView2 = (VideoView) findViewById(R.id.videoView2);
	    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
	    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

	    seekbar = (SeekBar) findViewById(R.id.seekBar1);
	    seekbar2 = (SeekBar) findViewById(R.id.seekBar2);
	 

	    playButton = (ImageButton) findViewById(R.id.imageButton1);
	    playbutton2 = (ImageButton) findViewById(R.id.imageButton2);
	   

	  

	    currentTime = (TextView) findViewById(R.id.textView1);
	    currentTime2 = (TextView) findViewById(R.id.textView2);
	  //  duration = (TextView)findViewById(R.id.textView1);
	  //  duration2 = (TextView)findViewById(R.id.TextView02);
	 	    videoView.setOnErrorListener(this);
	    videoView2.setOnErrorListener(this);
	    videoView.setOnPreparedListener(this);
	    videoView2.setOnPreparedListener(this);
	  
	    videoView.setVideoURI(Uri.parse("/sdcard/photint/slicemale.3gp"));
	      
	       String fileName = "android.resource://" + getPackageName() + "/" + R.raw.slice_male;
	       videoView2.setVideoURI(Uri.parse(fileName));
       
        
	    seekbar.setOnSeekBarChangeListener(seekBarChanged);
	    seekbar2.setOnSeekBarChangeListener(seekBarChanged2);
	    
	    playButton.setOnClickListener(onButtonClick);
	    playbutton2.setOnClickListener(onButtonClick1);


	}
	 public boolean onKeyDown(int keyCode, KeyEvent event) {
	        if (keyCode == KeyEvent.KEYCODE_BACK) {
	        	Intent intent = new Intent(Compareslicemale.this, Previewslicemale.class);
	          	 startActivity(intent);
	            return true;
	        }
	        return super.onKeyDown(keyCode, event);
	    }
	public boolean onError(MediaPlayer mp, int what, int extra) {
	    // TODO Auto-generated method stub
	    return false;
	}

	public void calculateTime(int ms) {

	    ss = ms / 1000;
	    mm = ss / 60;
	    ss %= 60;
	    hh = mm / 60;
	    mm %= 60;
	    hh %= 24;
	}
	
	public void calculateTime2(int ms2) {

	    ss2=ms2/1000;	    
mm2=ss2/60;	    
ss2%=60;	    
hh2=mm2/60;	    
mm2%=60;	    
hh2%=24;
	}

	public void onPrepared(MediaPlayer mp)
	{
	   // dismissMyDialog();
	

	    videoView.start();
videoView2.start();
	 
	    seekbar.setProgress(0);
	    seekbar2.setProgress(0);
	    seekbar.setMax(videoView.getDuration());
	    seekbar2.setMax(videoView2.getDuration());

	    //ms = videoView.getDuration();
	    //ms2 = videoView2.getDuration();
	    //calculateTime(ms);
	    //calculateTime2(ms2);

	  //  duration.setText("" + hh + ":" + mm + ":" + ss);
	  //  duration2.setText("" + hh2 + ":" + mm2 + ":" + ss2);

	    ms = videoView.getCurrentPosition();
	    ms2 = videoView2.getCurrentPosition();

	    calculateTime(ms);
	    calculateTime2(ms2);

	    currentTime.setText("" + hh + ":" + mm + ":" + ss);
	    currentTime2.setText("" + hh2 + ":" + mm2 + ":" + ss2);
	    
	    playButton.setImageResource(android.R.drawable.ic_media_pause);
	    playbutton2.setImageResource(android.R.drawable.ic_media_pause);
	    
	    updatePosition();
	    updatePosition2();
	    
	    

	    isStarted = true;

	  	//    mp.setOnSeekCompleteListener(new OnSeekCompleteListener()
	   // {
	   //     public void onSeekComplete(MediaPlayer mp) 
	   //     {
	   //         if (mp.isPlaying())
//	            {
//
//	            }
//	            else 
//	            {
//	                onStart();
//
//	                onPause();
//
//	                onStart();
//
//	            }
//
//	        }
//	    });
	}
	
	
	private SeekBar.OnSeekBarChangeListener seekBarChanged = new SeekBar.OnSeekBarChangeListener()
	{
	    public void onStopTrackingTouch(SeekBar seekBar)
	    {
	        isMoveingSeekBar = false;
	    }

	    public void onStartTrackingTouch(SeekBar seekBar)
	    {
	        isMoveingSeekBar = true;
	    }

	    public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) 
	    {
	        Log.e("",""+progress+""+percentageBuffer);

	        if (fromUser)
	        {
	            isCustomSeekButtonClick = fromUser;

	            videoView.seekTo(progress);
	            

	            mpCurrentPosition = progress;
	            

	            Log.e("OnSeekBarChangeListener", "onProgressChanged");
	        }
	        if (isMoveingSeekBar) 
	        {
	            videoView.seekTo(progress);
	            

	            Log.i("OnSeekBarChangeListener", "onProgressChanged");
	        }
	    }
	};
	private SeekBar.OnSeekBarChangeListener seekBarChanged2 = new SeekBar.OnSeekBarChangeListener()
	{
	    public void onStopTrackingTouch(SeekBar seekBar)
	    {
	    	isMovingSeekBar2 = false;
	    }

	    public void onStartTrackingTouch(SeekBar seekBar)
	    {
	    	isMovingSeekBar2 = true;
	    }

	    public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) 
	    {
	        Log.e("",""+progress+""+percentageBuffer);

	        if (fromUser)
	        {
	            isCustomSeekButtonClick2 = fromUser;

	            
	            videoView2.seekTo(progress);

	            mpCurrentPosition2 = progress;

	            Log.e("OnSeekBarChangeListener2", "onProgressChanged2");
	        }
	        if (isMovingSeekBar2) 
	        {
	            
	            videoView2.seekTo(progress);

	            Log.i("OnSeekBarChangeListener22", "onProgressChanged22");
	        }
	    }
	};
	private View.OnClickListener onButtonClick = new View.OnClickListener() {

	    public void onClick(View v)
	    {
	        switch (v.getId()) 
	        {
	        case R.id.imageButton1:
	        {
	            if (videoView.isPlaying())
	            {
	                handler.removeCallbacks(updatePositionRunnable);

	                isPauseButtonClick = true;

	                videoView.pause();

	                playButton.setImageResource(android.R.drawable.ic_media_play);

	            } 
	            else 
	            {
	                if (isStarted)
	                {
	                    videoView.start();
	                    isPauseButtonClick = false;
	                    playButton.setImageResource(android.R.drawable.ic_media_pause);

	                    updatePosition();
	                } 
	                else 
	                {
	                    startPlay(currentFile);
	                    isPauseButtonClick = false;
	                    videoView.start();
	                }
	            }

	            break;
	        }
	        
	        
	        }
	    }
	};
	
	private View.OnClickListener onButtonClick1 = new View.OnClickListener() {

	    public void onClick(View v)
	    {
	        switch (v.getId()) 
	        {
	        case R.id.imageButton2:
	        {
	            if (videoView2.isPlaying())
	            {
	                handler2.removeCallbacks(updatePositionRunnable2);

	                isPauseButtonClick = true;

	                videoView2.pause();

	                playbutton2.setImageResource(android.R.drawable.ic_media_play);

	            } 
	            else 
	            {
	                if (isStarted)
	                {
	                    videoView2.start();
	                    isPauseButtonClick = false;
	                    playbutton2.setImageResource(android.R.drawable.ic_media_pause);

	                    updatePosition2();
	                } 
	                else 
	                {
	                    startPlay2(currentFile);
	                    isPauseButtonClick = false;
	                    videoView2.start();
	                }
	            }

	            break;
	        }
	        
	        
	        }
	    }
	};

	private void updatePosition() 
	{
	    handler.removeCallbacks(updatePositionRunnable);

	    seekbar.setProgress(videoView.getCurrentPosition());
	    

	    ms = videoView.getCurrentPosition();
	    

	    calculateTime(ms);
	    
	    currentTime.setText("" + hh + ":" + mm + ":" + ss);
	    

	    handler.postDelayed(updatePositionRunnable, UPDATE_FREQUENCY);
	}
	private void updatePosition2() {

	    handler2.removeCallbacks(updatePositionRunnable2);

	   
	    seekbar2.setProgress(videoView2.getCurrentPosition());

	   
	    ms2 = videoView2.getCurrentPosition();

	   
	    calculateTime2(ms2);
	   
	    currentTime2.setText("" + hh2 + ":" + mm2 + ":" + ss2);

	    handler2.postDelayed(updatePositionRunnable2, UPDATE_FREQUENCY);
	
		
	}

	private void startPlay(String file) 
	{
	    Log.i("Selected: ", file);

	    // selelctedFile.setText(file);
	    seekbar.setProgress(0);

	    videoView.stopPlayback();

	    videoView.start();

	    seekbar.setMax(videoView.getDuration());

	    playButton.setImageResource(android.R.drawable.ic_media_pause);
	    updatePosition();

	    isStarted = true;
	}
	private void startPlay2(String file) 
	{
	    Log.i("Selected: ", file);

	    seekbar2.setProgress(0);

	    videoView2.stopPlayback();

	    videoView2.start();

	    seekbar2.setMax(videoView2.getDuration());

	    playbutton2.setImageResource(android.R.drawable.ic_media_pause);

	    updatePosition2();

	    isStarted = true;
	}
		
}

