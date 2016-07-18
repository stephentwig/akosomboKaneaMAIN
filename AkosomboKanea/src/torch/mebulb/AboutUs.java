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

import android.app.Activity;



import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;


public class AboutUs extends Activity{
	 private AdView adView;

	  /* Your ad unit id. Replace with your actual ad unit id. */
	  private static final String AD_UNIT_ID = "ca-app-pub-8756566537/3245689988";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about);
		
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
	            .addTestDevice("433446570785")
	            .addTestDevice("5E7A8B494949")
	            .addTestDevice("445548765DCF")
	            .build();
	        
	        adView.loadAd(adRequest);
	}
	
	
	
		
	public void GooglePlay(View view){
		
		Intent send=new Intent(Intent.ACTION_VIEW);
		
		send.setData(Uri.parse("https://play.google.com/store/apps/details?id=torch.mebulb"));
		startActivity(send);
		
		
		
		
	}
public void MyPage(View view){
		
		Intent send=new Intent(Intent.ACTION_VIEW);
		
		send.setData(Uri.parse("https://www.facebook.com/pages/TwigNation-GH/1454022941523580"));
		startActivity(send);
		
		
		
		
	}
	
@Override
public void onResume() {
  super.onResume();
  if (adView != null) {
    adView.resume();
  }
}

@Override
public void onPause() {
  if (adView != null) {
    adView.pause();
  }
  super.onPause();
}

/** Called before the activity is destroyed. */
@Override
public void onDestroy() {
  // Destroy the AdView.
  if (adView != null) {
    adView.destroy();
  }
  super.onDestroy();
}
	
	
	    
	//https://play.google.com/store/apps/details?id=torch.mebulb
}