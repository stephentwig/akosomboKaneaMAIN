/*

THIS CODE IS FOR YOU
-TWIGNATION-GH
18/07/2016



NOTICE:THE CODE IS NOT WELL COMMENTED AND INDENTED





*/


package torch.mebulb;










import com.google.android.gms.ads.AdRequest;


import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

import android.support.v4.app.NotificationCompat;


import android.support.v4.app.TaskStackBuilder;


import android.provider.Settings.SettingNotFoundException;
import android.provider.Settings.System;
import android.os.Bundle;
import android.provider.Settings.SettingNotFoundException;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.WindowManager.LayoutParams;
import android.view.Window;
import android.view.WindowManager;


import android.hardware.Camera;
import android.annotation.SuppressLint;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.AlertDialog.Builder;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.hardware.Sensor;

import android.hardware.SensorEvent;

import android.hardware.SensorEventListener;

import android.hardware.SensorManager;
import android.hardware.Camera.Parameters;
import android.widget.LinearLayout;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

import android.media.MediaPlayer;

import android.view.View;



import android.widget.Toast;
import torch.mebulb.Screenlight;


public class MainActivity extends Activity implements SensorEventListener {
	
	
	private NotificationManager notificationManager;
	public int notificationId = 100;
	 private SeekBar brightbar;
	 SharedPreferences appPref;
	    boolean isFirstTime = true;
	    public Builder alertDialog;
	    Camera torch;
	    Context context = this;
	    int count = 0;
	   
	    PackageManager packageManager;
	    Parameters param;
	    LayoutParams params;


	   private SensorManager mSensorManager;

	   private Sensor mSensor;
	// a variable to store the system brightness
			private int brightness;
			//the content resolver used as a handle to the system's settings
			private ContentResolver cResolver;
			//a window object, that will store a reference to the current window
			private Window window;
			 private AdView adView;

			  /* Your ad unit id. Replace with your actual ad unit id. */
			  private static final String AD_UNIT_ID = "ca-app-pub-4354531703345/4989687104545";
	
	
	
	
	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_main);
	        this.setRequestedOrientation(7);
		       this.setContentView(R.layout.activity_main);
		       this.mSensorManager = (SensorManager)this.getSystemService(SENSOR_SERVICE);
		       this.mSensor = this.mSensorManager.getDefaultSensor(8);
		       this.params = this.getWindow().getAttributes();
		       this.packageManager = this.context.getPackageManager();
	        ImageView on = (ImageView) findViewById(R.id.switchi);
	     

	        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
	        
	        // Create an ad.
	        adView = new AdView(this);
	        adView.setAdSize(AdSize.BANNER);
	        adView.setAdUnitId(AD_UNIT_ID);

	        // Add the AdView to the view hierarchy. The view will have no size
	        // until the ad is loaded.
	        LinearLayout layout = (LinearLayout) findViewById(R.id.linearLayout);
	        layout.addView(adView);

	        // Create an ad request. Check logcat output for the hashed device ID to
	        // get test ads on a physical device.
	        AdRequest adRequest = new AdRequest.Builder()
	            .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
	            .addTestDevice("7FC96560E77")
	            .addTestDevice("4B539632E86")
	            .addTestDevice("3E3CF69B007")
	            .build();

	        // Start loading the ad in the background.
	        adView.loadAd(adRequest);
	        on.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View view) {
					// TODO Auto-generated method stub

				
						
					torch.release();

			
					 MediaPlayer IO =MediaPlayer.create(MainActivity.this, R.raw.multimedia_button_click_028);
	                 IO.start();
	                 
	                 notificationManager.cancel(notificationId);
					try {	torch.startPreview();
					  
			     
			        } 
					
					
					catch (Exception e) {
					
			    
			        
			        
			        ImageView img = (ImageView)findViewById(R.id.switchi);
			        img.setVisibility(View.GONE);
			    	ImageView oiip =(ImageView) findViewById(R.id.imageTOP);
					
					oiip.setVisibility(View.VISIBLE);
					 MediaPlayer op =MediaPlayer.create(MainActivity.this, R.raw.multimedia_button_click_028);
	                 op.start();
	                 
	                 
					}
					return;
				}
			}); brightbar = (SeekBar) findViewById(R.id.sb_brightbar);
	        
	        //get the content resolver
	        cResolver = getContentResolver();
	        
	        //get the current window
	        window = getWindow();
	        
	        //seek bar settings//
	        //sets the range between 0 and 255
	        brightbar.setMax(255);
	        //set the seek bar progress to 1
	        brightbar.setKeyProgressIncrement(1);
	      
	        try 
	        {
	        	//get the current system brightness
	        	brightness = System.getInt(cResolver, System.SCREEN_BRIGHTNESS);
			} 
	        catch (SettingNotFoundException e) 
			{
	        	//throw an error case it couldn't be retrieved
				
				e.printStackTrace();
			}
			
			//sets the progress of the seek bar based on the system's brightness
			brightbar.setProgress(brightness);

			//register OnSeekBarChangeListener, so it can actually change values
			brightbar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() 
			{
				@Override
				public void onStopTrackingTouch(SeekBar seekBar) 
				{
					//set the system brightness using the brightness variable value
					System.putInt(cResolver, System.SCREEN_BRIGHTNESS, brightness);
					
					//preview brightness changes at this window
					//get the current window attributes
					LayoutParams layoutpars = window.getAttributes();
					//set the brightness of this window
					layoutpars.screenBrightness = brightness / (float)255;
					//apply attribute changes to this window
					window.setAttributes(layoutpars);
				}
				
				@Override
				public void onStartTrackingTouch(SeekBar seekBar) 
				{
				}
				
				@Override
				public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) 
				{
					//sets the minimal brightness level
					//if seek bar is 20 or any value below
					if(progress<=20)
					{
						//set the brightness to 20
						brightness=20;
					}
					else //brightness is greater than 20
					{
						//sets brightness variable based on the progress bar 
						brightness = progress;
					}
				}
			});	
			Button screen = (Button) findViewById(R.id.helpi);
			
			   screen.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View view) {
						// TODO Auto-generated method stub
						 Intent yu =new Intent(MainActivity.this, Helper.class);
						   	startActivity(yu);
								   
					}
				});
			   
			   // Get preference value to know that is it first time application is
		        // being called.
		        appPref = getSharedPreferences("isFirstTime", 0);
		        isFirstTime = appPref.getBoolean("isFirstTime", true);
		 
		        if (isFirstTime) {
		            // Create explicit intent which will be used to call Our application
		            // when some one clicked on short cut
		            Intent shortcutIntent = new Intent(getApplicationContext(),
		                    MainActivity.class);
		            shortcutIntent.setAction(Intent.ACTION_MAIN);
		            Intent intent = new Intent();
		 
		            // Create Implicit intent and assign Shortcut Application Name, Icon
		            intent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
		            intent.putExtra(Intent.EXTRA_SHORTCUT_NAME, "Akosombo Kanea");
		            intent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE,
		                    Intent.ShortcutIconResource.fromContext(
		                            getApplicationContext(), R.drawable.ic_launcher));
		            intent.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
		            getApplicationContext().sendBroadcast(intent);
		 
		            // Set preference to inform that we have created shortcut on
		            // Homescreen
		            SharedPreferences.Editor editor = appPref.edit();
		            editor.putBoolean("isFirstTime", false);
		            editor.commit();
		 
		        }
//		       RelativeLayout  mLinearLayout1 = (RelativeLayout)findViewById(R.id.newui);
//		        mLinearLayout1.setBackgroundColor(Color.TRANSPARENT);
//	
	        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
	    
	    }
	    
	        public void CameraOn(View view){
	        	
	        	
	        	
	        	
	        
		    	
	        	
	    					//torch.startPreview();
	    					
	    					
	    					try {
	    						
	    						
	    						
	    						
//	    			   	        
	    				         if(!this.packageManager.hasSystemFeature("android.hardware.camera.flash")) {
	    				             // Log.i("No_camera", "This  device does not have flash!");
	    				              if(this.count == 0) {
	    				                
	    				                
	    				                
	    				                 Intent yu =new Intent(this, Screenlight.class);
	    				                	startActivity(yu);
	    				                    }}
	    						
	    						
	    						torch = Camera.open();
	    						Camera.Parameters torchi = torch.getParameters();
	        					torchi.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
	        					torch.setParameters(torchi);
	        					 MediaPlayer IO =MediaPlayer.create(MainActivity.this, R.raw.adriantnt_release_click);
		                           IO.start();
	    						torch.startPreview();
	    						
	    						  


	    						ImageView oip =(ImageView) findViewById(R.id.imageTOP);
	    						
	    						oip.setVisibility(View.INVISIBLE);
	ImageView oiip =(ImageView) findViewById(R.id.imageView3);
	    						
	    						oiip.setVisibility(View.INVISIBLE);
	    						
	    						ImageView op =(ImageView) findViewById(R.id.switchi);
	    						
	    						op.setVisibility(View.VISIBLE);
	                          
	    						
	    						
	    						//////////////////notri
	    						NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
	    					    
	    					    
	    					    
	    					    mBuilder.setContentTitle("Your Flashlight Is On");
	    						
	    						mBuilder.setTicker("Flashlight Activated");
	    						mBuilder.setSmallIcon(R.drawable.ic_launcher);
	    						
	    						
	    						
	    						//create explicit intent for an Activity in the App.
	    						Intent resultIntent = new Intent(this, MainActivity.class);
	    						
	    						TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(this);
	    						taskStackBuilder.addParentStack(MainActivity.class);
	    						//add the intent to the stack
	    						taskStackBuilder.addNextIntent(resultIntent);
	    						
	    						PendingIntent resultPendingIntent = taskStackBuilder.getPendingIntent(0, 
	    								PendingIntent.FLAG_UPDATE_CURRENT);    	
	    						mBuilder.setContentIntent(resultPendingIntent);
	    						
	    						notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
	    						
	    						// pass notificationID to update the notification
	    						notificationManager.notify(notificationId, mBuilder.build());

	    						

	    						
	    						
	    					
								
							} catch (Exception e) {
								Intent yu =new Intent(this, Screenlight.class);
			                	startActivity(yu);
			                	if(this.packageManager.hasSystemFeature("android.hardware.camera.flash")) {
	    				             // Log.i("No_camera", "This  device does not have flash!");
	    				              if(this.count == 0) {
	    				                
	    				            	  Toast.makeText(getApplicationContext(), "LED Already In Use", Toast.LENGTH_SHORT).show();
	    				            	  Toast.makeText(getApplicationContext(), "Try Turning Off Using Sensor", Toast.LENGTH_SHORT).show();
	    				            	  
	    				                    }}
			                	
			                	
			                	if(!this.packageManager.hasSystemFeature("android.hardware.camera.flash")) {
	    				             // Log.i("No_camera", "This  device does not have flash!");
	    				              if(this.count == 0) {
	    				                
	    				            	 Toast.makeText(getApplicationContext(), "No Flashlight Detected", Toast.LENGTH_SHORT).show();
	    				            	 
	    				                    }}
								Toast.makeText(getApplicationContext(), "LED Already In Use/No Flashlight Detected", Toast.LENGTH_SHORT).show();
								 Toast.makeText(getApplicationContext(), "Try Turning Off Using Sensor", Toast.LENGTH_SHORT).show();
								 }
	    			
	    			}
	        
	       
	        
	      @Override
	    protected void onPause() {
	    	// TODO Auto-generated method stub
	    	  NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
			    
			    
			    
			    mBuilder.setContentTitle("AKOSOMBO KANEA is still running");
				mBuilder.setContentText("Click To Open Home Screen");
				mBuilder.setTicker("Still Running... ");
				mBuilder.setSmallIcon(R.drawable.ic_launcher);
				
				
				
				//create explicit intent for an Activity in the App.
				Intent resultIntent = new Intent(this, MainActivity.class);
				
				TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(this);
				taskStackBuilder.addParentStack(MainActivity.class);
				//add the intent to the stack
				taskStackBuilder.addNextIntent(resultIntent);
				
				PendingIntent resultPendingIntent = taskStackBuilder.getPendingIntent(0, 
						PendingIntent.FLAG_UPDATE_CURRENT);    	
				mBuilder.setContentIntent(resultPendingIntent);
				
				notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
				
				// pass notificationID to update the notification
				notificationManager.notify(notificationId, mBuilder.build());
				 if (adView != null) {
				      adView.pause();
				    }
	    	super.onPause();
	    	 mSensorManager.unregisterListener(this);
	    }

	    
	    
	    
	    
	   public void About(View view){
		   
		   Intent oi =new Intent(MainActivity.this, AboutUs.class);
		   startActivity(oi);
		   
	   } 
	   
	   
	 
	   protected void onResume() { if (adView != null) {
		      adView.resume();
	    }

		   super.onResume();

		   this.mSensorManager.registerListener(this, this.mSensor, 3);
			

		  }

	
	   
	   
	   
	   @Override
	protected void onDestroy() {
		   notificationManager.cancel(notificationId);
		// TODO Auto-generated method stub
		   if (adView != null) {
			      adView.destroy();
			    }
		   super.onDestroy();
			try { torch.stopPreview();
			torch.release();
			
				
			} catch (Exception e) {
				finish();
				// TODO: handle exception
			} 
		
	}
	 
		//METHODS of somthng shd match n tally with its activity java n xml
	    @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	 
	        getMenuInflater().inflate(R.menu.main, menu);
	    
	        return true;
	    }

	    @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	  
	        switch(item.getItemId()){
	        case R.id.action_settings:         	
	        	finish();
	        	break;
	
	        	
	        }
	        return false;
	    }





	    public void onAccuracyChanged(Sensor var1, int var2) {

	    }


	    

	    public void onSensorChanged(SensorEvent var1) {
	    	if(var1.values[0] == 0.0F) {
	    		
	         if(!this.packageManager.hasSystemFeature("android.hardware.camera.flash")) {
	             // Log.i("No_camera", "This  device does not have flash!");
	              if(this.count == 0) {
	                
	                
	                
	                 Intent yu =new Intent(this, Screenlight.class);
	                	startActivity(yu);
	                    }
	              this.getWindow().setAttributes(this.params);
	              return;
	           }            
	           if(this.count == 0) {
		              try {torch = Camera.open();
						Camera.Parameters torchi = torch.getParameters();
						torchi.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
						torch.setParameters(torchi);
						torch.startPreview();
			              ++this.count;
			              Toast.makeText(getApplicationContext(), "Flashlight ON", 10).show();
						
					} catch (Exception e) {
						// TODO: handle exception
						 Toast.makeText(getApplicationContext(), "Please Press on the Button to Turn OFF", 10).show();
					}
	        		
		             
		              
		            
						
						
		           } else {
		              
		              this.count = 0;
		              torch.release();
		             
						 MediaPlayer op =MediaPlayer.create(MainActivity.this, R.raw.multimedia_button_click_028);
		                 op.start();
		              
		                 
		            	
		            	
		           }

		           this.getWindow().setAttributes(this.params);
	              }

	             
	    	
	    	
	    	
	    	

	
	        }



	     }
	     
	     
	  