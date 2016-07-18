package torch.mebulb;




import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;





import android.os.Bundle;

import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;


public class Screenlight extends Activity implements SensorEventListener {
	
	  
	private Sensor mSensor;
	   private SensorManager mSensorManager;
	   LayoutParams params;
	
	  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.screenlight);

        this.setContentView(R.layout.screenlight);
        this.mSensorManager = (SensorManager)this.getSystemService("sensor");
        this.mSensor = this.mSensorManager.getDefaultSensor(8);
        this.params = this.getWindow().getAttributes();
        this.params.screenBrightness = 1.0F;
        this.getWindow().setAttributes(this.params);
     
        
        
        
        
        
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
	
		
	   
	} 
	protected void onPause() {
	      super.onPause();
	      this.mSensorManager.unregisterListener(this);
	   }

	   protected void onResume() {
	      super.onResume();
	      this.mSensorManager.registerListener(this, this.mSensor, 3);
	   }
	   public void onAccuracyChanged(Sensor var1, int var2) {
	   }

	   public void onSensorChanged(SensorEvent var1) {
	      if(var1.values[0] == 0.0F) {
	         //this.params.screenBrightness = 0.5F;
	         this.getWindow().setAttributes(this.params);
	         this.finish();
	      }

	   }
	}
