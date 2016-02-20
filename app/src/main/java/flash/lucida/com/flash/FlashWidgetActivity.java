package flash.lucida.com.flash;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.hardware.Camera;
import android.util.Log;
import android.widget.RemoteViews;

public class FlashWidgetActivity extends AppWidgetProvider {

    private static final String ACTION_FLASH_ON ="com.flash.widget.ON";
    private static final String ACTION_FLASH_OFF ="com.flash.widget.OFF";
    private ComponentName flashWidget;
    private RemoteViews views = null;

    int flashControl = 0;

    private Intent intent;

    private static Camera mCamera = null;


    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        Log.e("Widget State","onUpdate");

        flashWidget = new ComponentName(context, FlashWidgetActivity.class);
        views = new RemoteViews(context.getPackageName(), R.layout.activity_widget);

        if(flashControl == 0){

            views.setImageViewResource(R.id.flash_btn, R.drawable.off);
            intent = new Intent(ACTION_FLASH_ON);

        }else if(flashControl == 1){

            views.setImageViewResource(R.id.flash_btn, R.drawable.on);
            intent = new Intent(ACTION_FLASH_OFF);
        }

        // Flash Intent
        PendingIntent onPendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        views.setOnClickPendingIntent(R.id.flash_btn, onPendingIntent);

        appWidgetManager.updateAppWidget(flashWidget, views);
    }


    public void TurnOnLight() {

        mCamera = Camera.open();
        Camera.Parameters mCameraParameters = mCamera.getParameters();
        mCameraParameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
        mCameraParameters.setFocusMode(Camera.Parameters.FOCUS_MODE_INFINITY);
        mCamera.setParameters(mCameraParameters);
        mCamera.startPreview();

    }

    public void TurnOffLight() {
        mCamera.stopPreview();
        mCamera.release();
        mCamera = null;
    }



    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
        Log.e("Widget State","onReceive");

        String action = intent.getAction();

        if(action.equals(ACTION_FLASH_ON)){
            Log.e("Flash state", intent.getAction());
            try{
                flashControl = 1;

                AppWidgetManager manager = AppWidgetManager.getInstance(context);
                this.onUpdate(context, manager, manager.getAppWidgetIds(new ComponentName(context, FlashWidgetActivity.class)));

                TurnOnLight();

            }catch (Exception e) {
                // TODO: handle exception
                Log.e("Flash state", "Flash ON Exception :: " + e.toString());
            }
        }else if(action.equals(ACTION_FLASH_OFF)){
            Log.e("Flash state", intent.getAction());
            try {

                flashControl = 0;

                AppWidgetManager manager = AppWidgetManager.getInstance(context);
                this.onUpdate(context, manager, manager.getAppWidgetIds(new ComponentName(context, FlashWidgetActivity.class)));

                TurnOffLight();

            }catch (Exception e) {
                // TODO: handle exception
                Log.e("Flash state", "Flash OFF Exception :: " + e.toString());
            }
        }else{
            super.onReceive(context, intent);
        }
    }
}