package flash.lucida.com.flash;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_CAMERA = 2;
    private Switch swc; // 스위치 객체
    private Switch swc2; // 스위치 객체
    private TextView textView; // 스위치 객체

    private Camera mCamera = null;
    private boolean isCameraOn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView= (TextView)findViewById(R.id.textView);
        swc = (Switch)findViewById(R.id.switch1);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Request missing location permission.
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_CODE_CAMERA);
        } else {
            swc.setOnCheckedChangeListener(new Switch.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton cb, boolean isChecking) {
                    String str = String.valueOf(isChecking); // boolean -> String 변환

                    // 상태가 on, off인 경우에 알맞게 토스트를 띄움
                    if (isChecking) {
                        mCamera = Camera.open();
                        Camera.Parameters mCameraParameters = mCamera.getParameters();
                        mCameraParameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                        mCameraParameters.setFocusMode(Camera.Parameters.FOCUS_MODE_INFINITY);
                        mCamera.setParameters(mCameraParameters);
                        mCamera.startPreview();
                        isCameraOn = true;
                        swc2.setVisibility(View.VISIBLE);
                        textView.setVisibility(View.VISIBLE);
                    } else {
                        mCamera.release();
                        isCameraOn = false;

                    }
                }
            });
            swc2 = (Switch)findViewById(R.id.switch2);
            swc2.setOnCheckedChangeListener(new Switch.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton cb, boolean isChecking2) {
                    String str2 = String.valueOf(isChecking2); // boolean -> String 변환

                    // 상태가 on, off인 경우에 알맞게 토스트를 띄움
                    if(isChecking2){
                        mCamera = Camera.open();
                        Camera.Parameters mCameraParameters = mCamera.getParameters();
                        mCameraParameters.setFlashMode(Camera.Parameters.FLASH_MODE_ON);
                        mCameraParameters.setFocusMode(Camera.Parameters.FOCUS_MODE_INFINITY);
                        mCamera.setParameters(mCameraParameters);
                        mCamera.startPreview();
                        isCameraOn = true;}
                    else{
                        mCamera.release();
                        isCameraOn = false;}
                }
            });
        }

        swc.setOnCheckedChangeListener(new Switch.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton cb, boolean isChecking) {
                String str = String.valueOf(isChecking); // boolean -> String 변환

                // 상태가 on, off인 경우에 알맞게 토스트를 띄움
                if (isChecking) {
                    mCamera = Camera.open();
                    Camera.Parameters mCameraParameters = mCamera.getParameters();
                    mCameraParameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                    mCameraParameters.setFocusMode(Camera.Parameters.FOCUS_MODE_INFINITY);
                    mCamera.setParameters(mCameraParameters);
                    mCamera.startPreview();
                    isCameraOn = true;
                    swc2.setVisibility(View.VISIBLE);
                    textView.setVisibility(View.VISIBLE);
                } else {
                    mCamera.release();
                    isCameraOn = false;
                    swc2.setVisibility(View.INVISIBLE);
                    textView.setVisibility(View.INVISIBLE);
                }
            }
        });
        swc2 = (Switch)findViewById(R.id.switch2);
        swc2.setOnCheckedChangeListener(new Switch.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton cb, boolean isChecking2) {
                String str2 = String.valueOf(isChecking2); // boolean -> String 변환

                // 상태가 on, off인 경우에 알맞게 토스트를 띄움
                if(isChecking2){
                    mCamera = Camera.open();
                    Camera.Parameters mCameraParameters = mCamera.getParameters();
                    mCameraParameters.setFlashMode(Camera.Parameters.FLASH_MODE_ON);
                    mCameraParameters.setFocusMode(Camera.Parameters.FOCUS_MODE_INFINITY);
                    mCamera.setParameters(mCameraParameters);
                    mCamera.startPreview();
                    isCameraOn = true;}
                else{
                    mCamera.release();
                    isCameraOn = false;}
            }
        });
    }




        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.activity_main, menu);
            return true;
        }
    @Override

    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.menu_settings) {
            Intent SettingActivity = new Intent(this, SettingsActvit.class);
            startActivity(SettingActivity);
        }
        return super.onOptionsItemSelected(item);
    }


}







