
package msc.photint.MentorAppstennis;

import java.io.File;

import android.app.Dialog;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.StatFs;
import android.os.Environment;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ImageView;
import android.widget.ImageButton;
import android.widget.Button;
import android.widget.Toast;
import android.util.Log;

public final class Browser extends ListActivity {
	public static final String ACTION_WIDGET = "com.browser.check.Browser.ACTION_WIDGET";
	
	private static final String PREFS_NAME = "ManagerPrefsFile";	//user preference file name
	private static final String PREFS_HIDDEN = "hidden";
	private static final String PREFS_COLOR = "color";
	private static final String PREFS_THUMBNAIL = "thumbnail";
	private static final String PREFS_SORT = "sort";
	private static final String PREFS_STORAGE = "sdcard space";
	
	private FileManager mFileMag;
	private EventHandler mHandler;
	private EventHandler.TableRow mTable;
	
	private SharedPreferences mSettings;
	private boolean mReturnIntent = false;
	private boolean mHoldingFile = false;
	private boolean mHoldingZip = false;
	private boolean mUseBackKey = true;
	private String mCopiedTarget;
	private String mZippedTarget;
	private String mSelectedListItem;				//item from context menu
	private TextView  mPathLabel, mDetailLabel, mStorageLabel;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.mainx);
        
        /*read settings*/
        mSettings = getSharedPreferences(PREFS_NAME, 0);
        boolean hide = mSettings.getBoolean(PREFS_HIDDEN, false);
        boolean thumb = mSettings.getBoolean(PREFS_THUMBNAIL, true);
        int space = mSettings.getInt(PREFS_STORAGE, View.VISIBLE);
        int color = mSettings.getInt(PREFS_COLOR, -1);
        int sort = mSettings.getInt(PREFS_SORT, 3);
        
        mFileMag = new FileManager();
        mFileMag.setShowHiddenFiles(hide);
        mFileMag.setSortType(sort);
        
        if (savedInstanceState != null)
        	mHandler = new EventHandler(Browser.this, mFileMag, savedInstanceState.getString("location"));
        else
        	mHandler = new EventHandler(Browser.this, mFileMag);
        
        mHandler.setTextColor(color);
        mHandler.setShowThumbnails(thumb);
        mTable = mHandler.new TableRow();
        
        /*sets the ListAdapter for our ListActivity and
         *gives our EventHandler class the same adapter
         */
        mHandler.setListAdapter(mTable);
        setListAdapter(mTable);
        
        /* register context menu for our list view */
        registerForContextMenu(getListView());
        
        mStorageLabel = (TextView)findViewById(R.id.storage_label);
        mDetailLabel = (TextView)findViewById(R.id.detail_label);
        mPathLabel = (TextView)findViewById(R.id.path_label);
        mPathLabel.setText("path: /sdcard/photint");
        
        updateStorageLabel();
        mStorageLabel.setVisibility(space);
        
        mHandler.setUpdateLabels(mPathLabel, mDetailLabel);
        
        /* setup buttons */
        int[] img_button_id = {R.id.help_button, R.id.home_button, 
        					   R.id.back_button, R.id.info_button, 
        					   R.id.manage_button, R.id.multiselect_button};
        
        int[] button_id = {R.id.hidden_copy, R.id.hidden_attach,
        				   R.id.hidden_delete, R.id.hidden_move};
        
        ImageButton[] bimg = new ImageButton[img_button_id.length];
        Button[] bt = new Button[button_id.length];
        
        for(int i = 0; i < img_button_id.length; i++) {
        	bimg[i] = (ImageButton)findViewById(img_button_id[i]);
        	bimg[i].setOnClickListener(mHandler);

        	if(i < 4) {
        		bt[i] = (Button)findViewById(button_id[i]);
        		bt[i].setOnClickListener(mHandler);
        	}
        }
    
      //  Intent intent = getIntent();
        
       /* if(intent.getAction().equals(Intent.ACTION_GET_CONTENT)) {
        	bimg[5].setVisibility(View.GONE);
        	mReturnIntent = true;
        
        } else if (intent.getAction().equals(ACTION_WIDGET)) {
        	Log.e("Browser", "Widget action, string = " + intent.getExtras().getString("folder"));
        	mHandler.updateDirectory(mFileMag.getNextDir(intent.getExtras().getString("folder"), true));
        	
        }*/
    }

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		
		outState.putString("location", mFileMag.getCurrentDir());
	}
	
	/*(non Java-Doc)
	 * Returns the file that was selected to the intent that
	 * called this activity. usually from the caller is another application.
	 */
	private void returnIntentResults(File data) {
		mReturnIntent = false;
		
		Intent ret = new Intent();
		ret.setData(Uri.fromFile(data));
		setResult(RESULT_OK, ret);
		
		finish();
	}
	
	private void updateStorageLabel() {
		long total, aval;
		int kb = 1024;
		
		StatFs fs = new StatFs(Environment.
								getExternalStorageDirectory().getPath());
		
		total = fs.getBlockCount() * (fs.getBlockSize() / kb);
		aval = fs.getAvailableBlocks() * (fs.getBlockSize() / kb);
		
		mStorageLabel.setText(String.format("sdcard: Total %.2f GB " +
							  "\t\tAvailable %.2f GB", 
							  (double)total / (kb * kb), (double)aval / (kb * kb)));
	}
	
	/**
	 *  To add more functionality and let the user interact with more
	 *  file types, this is the function to add the ability. 
	 *  
	 *  (note): this method can be done more efficiently 
	 */
    @Override
    public void onListItemClick(ListView parent, View view, int position, long id) {
    	final String item = mHandler.getData(position);
    	boolean multiSelect = mHandler.isMultiSelected();
    	File file = new File(mFileMag.getCurrentDir() + "/" + item);
    	String item_ext = null;
    	
    	try {
    		item_ext = item.substring(item.lastIndexOf("."), item.length());
    		
    	} catch(IndexOutOfBoundsException e) {	
    		item_ext = ""; 
    	}
    	
    	/*
    	 * If the user has multi-select on, we just need to record the file
    	 * not make an intent for it.
    	 */
    	if(multiSelect) {
    		mTable.addMultiPosition(position, file.getPath());
    		
    	} else {
	    	if (file.isDirectory()) {
				if(file.canRead()) {
					mHandler.stopThumbnailThread();
		    		mHandler.updateDirectory(mFileMag.getNextDir(item, false));
		    		mPathLabel.setText(mFileMag.getCurrentDir());
		    		
		    		/*set back button switch to true 
		    		 * (this will be better implemented later)
		    		 */
		    		if(!mUseBackKey)
		    			mUseBackKey = true;
		    		
	    		} else {
	    			Toast.makeText(this, "Can't read folder due to permissions", 
	    							Toast.LENGTH_SHORT).show();
	    		}
	    	}
	    	
	    	/*music file selected--add more audio formats*/
	    	else if (item_ext.equalsIgnoreCase(".mp3") || 
	    			 item_ext.equalsIgnoreCase(".m4a")||
	    			 item_ext.equalsIgnoreCase(".mp4")) {
	    		
	    		if(mReturnIntent) {
	    			returnIntentResults(file);
	    		} else {
	    			Intent i = new Intent();
    				i.setAction(android.content.Intent.ACTION_VIEW);
    				i.setDataAndType(Uri.fromFile(file), "audio/*");
    				startActivity(i);
	    		}
	    	}
	    	
	    	/*photo file selected*/
	    	else if(item_ext.equalsIgnoreCase(".jpeg") || 
	    			item_ext.equalsIgnoreCase(".jpg")  ||
	    			item_ext.equalsIgnoreCase(".png")  ||
	    			item_ext.equalsIgnoreCase(".gif")  || 
	    			item_ext.equalsIgnoreCase(".tiff")) {
	 			    		
	    		if (file.exists()) {
	    			if(mReturnIntent) {
	    				returnIntentResults(file);
	    				
	    			} else {
			    		Intent picIntent = new Intent();
			    		picIntent.setAction(android.content.Intent.ACTION_VIEW);
			    		picIntent.setDataAndType(Uri.fromFile(file), "image/*");
			    		startActivity(picIntent);
	    			}
	    		}
	    	}
	    	
	    	/*video file selected--add more video formats*/
	    	else if(item_ext.equalsIgnoreCase(".m4v") || 
	    			item_ext.equalsIgnoreCase(".3gp") ||
	    			item_ext.equalsIgnoreCase(".wmv") || 
	    			item_ext.equalsIgnoreCase(".mp4") || 
	    			item_ext.equalsIgnoreCase(".ogg") ||
	    			item_ext.equalsIgnoreCase(".wav")) {
	    		
	    		if (file.exists()) {
	    			if(mReturnIntent) {
	    				returnIntentResults(file);
	    				
	    			} else {
	    				Intent movieIntent = new Intent();
			    		movieIntent.setAction(android.content.Intent.ACTION_VIEW);
			    		movieIntent.setDataAndType(Uri.fromFile(file), "video/*");
			    		startActivity(movieIntent);
	    			}
	    		}
	    	}
	    	
	    	/*zip file */
	    	else if(item_ext.equalsIgnoreCase(".zip")) {
	    		
	    		if(mReturnIntent) {
	    			returnIntentResults(file);
	    			
	    		} else {
		    		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		    		AlertDialog alert;
		    		mZippedTarget = mFileMag.getCurrentDir() + "/" + item;
		    		CharSequence[] option = {"Extract here", "Extract to..."};
		    		
		    		builder.setTitle("Extract");
		    		builder.setItems(option, new DialogInterface.OnClickListener() {
		
						public void onClick(DialogInterface dialog, int which) {
							switch(which) {
								case 0:
									String dir = mFileMag.getCurrentDir();
									mHandler.unZipFile(item, dir + "/");
									break;
									
								case 1:
									mDetailLabel.setText("Holding " + item + 
														 " to extract");
									mHoldingZip = true;
									break;
							}
						}
		    		});
		    		
		    		alert = builder.create();
		    		alert.show();
	    		}
	    	}
	    	
	    	/* gzip files, this will be implemented later */
	    	else if(item_ext.equalsIgnoreCase(".gzip") ||
	    			item_ext.equalsIgnoreCase(".gz")) {
	    		
	    		if(mReturnIntent) {
	    			returnIntentResults(file);
	    			
	    		} else {
	    			//TODO:
	    		}
	    	}
	    	
	    	/*pdf file selected*/
	    	else if(item_ext.equalsIgnoreCase(".pdf")) {
	    		
	    		if(file.exists()) {
	    			if(mReturnIntent) {
	    				returnIntentResults(file);
	    				
	    			} else {
			    		Intent pdfIntent = new Intent();
			    		pdfIntent.setAction(android.content.Intent.ACTION_VIEW);
			    		pdfIntent.setDataAndType(Uri.fromFile(file), 
			    								 "application/pdf");
			    		
			    		try {
			    			startActivity(pdfIntent);
			    		} catch (ActivityNotFoundException e) {
			    			Toast.makeText(this, "Sorry, couldn't find a pdf viewer", 
									Toast.LENGTH_SHORT).show();
			    		}
		    		}
	    		}
	    	}
	    	
	    	/*Android application file*/
	    	else if(item_ext.equalsIgnoreCase(".apk")){
	    		
	    		if(file.exists()) {
	    			if(mReturnIntent) {
	    				returnIntentResults(file);
	    				
	    			} else {
		    			Intent apkIntent = new Intent();
		    			apkIntent.setAction(android.content.Intent.ACTION_VIEW);
		    			apkIntent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
		    			startActivity(apkIntent);
	    			}
	    		}
	    	}
	    	
	    	/* HTML file */
	    	else if(item_ext.equalsIgnoreCase(".html")) {
	    		
	    		if(file.exists()) {
	    			if(mReturnIntent) {
	    				returnIntentResults(file);
	    				
	    			} else {
		    			Intent htmlIntent = new Intent();
		    			htmlIntent.setAction(android.content.Intent.ACTION_VIEW);
		    			htmlIntent.setDataAndType(Uri.fromFile(file), "text/html");
		    			
		    			try {
		    				startActivity(htmlIntent);
		    			} catch(ActivityNotFoundException e) {
		    				Toast.makeText(this, "Sorry, couldn't find a HTML viewer", 
		    									Toast.LENGTH_SHORT).show();
		    			}
	    			}
	    		}
	    	}
	    	
	    	/* text file*/
	    	else if(item_ext.equalsIgnoreCase(".txt")) {
	    		
	    		if(file.exists()) {
	    			if(mReturnIntent) {
	    				returnIntentResults(file);
	    				
	    			} else {
		    			Intent txtIntent = new Intent();
		    			txtIntent.setAction(android.content.Intent.ACTION_VIEW);
		    			txtIntent.setDataAndType(Uri.fromFile(file), "text/plain");
		    			
		    			try {
		    				startActivity(txtIntent);
		    			} catch(ActivityNotFoundException e) {
		    				txtIntent.setType("text/*");
		    				startActivity(txtIntent);
		    			}
	    			}
	    		}
	    	}
	    	
	    	/* generic intent */
	    	else {
	    		if(file.exists()) {
	    			if(mReturnIntent) {
	    				returnIntentResults(file);
	    				
	    			} else {
			    		Intent generic = new Intent();
			    		generic.setAction(android.content.Intent.ACTION_VIEW);
			    		generic.setDataAndType(Uri.fromFile(file), "text/plain");
			    		
			    		try {
			    			startActivity(generic);
			    		} catch(ActivityNotFoundException e) {
			    			Toast.makeText(this, "Sorry, couldn't find anything " +
			    						   "to open " + file.getName(), 
			    						   Toast.LENGTH_SHORT).show();
			    		}
	    			}
	    		}
	    	}
    	}
	}
    
      
 /*   @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo info) {
    	super.onCreateContextMenu(menu, v, info);
    	
    	
    	AdapterContextMenuInfo _info = (AdapterContextMenuInfo)info;
    	mSelectedListItem = mHandler.getData(_info.position);

    	 if(!mFileMag.isDirectory(mSelectedListItem) && !mHandler.isMultiSelected()) {
        	menu.setHeaderTitle("File Operations");
    		menu.add(0, F_MENU_DELETE, 0, "Delete File");
    		menu.add(0, F_MENU_RENAME, 0, "Rename File");
    		menu.add(0, F_MENU_COPY, 0, "Copy File");
    		menu.add(0, F_MENU_MOVE, 0, "Move(Cut) File");
    		menu.add(0, F_MENU_ATTACH, 0, "Email File");
    	}	
    }
    */
   


}
