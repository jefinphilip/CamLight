package com.example.clement.camlight;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Camera;
import android.hardware.camera2.CameraDevice;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import java.security.Policy;

public class MainActivity extends AppCompatActivity {
    ImageButton imageButton;



    android.hardware.Camera camera;
    android.hardware.Camera.Parameters parameters;
    boolean isFlash=false;
    boolean isOn =false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        imageButton= (ImageButton)findViewById(R.id.flashBtn);

         //ActivityCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.CAMERA);
       // Permission != PackageManager.PERMISSION_GRANTED;


            if(getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH));

        {
            // runtime camera permission




            camera = android.hardware.Camera.open();
            parameters = camera.getParameters();
            isFlash =true;



        }

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {





                if(isFlash)
                {


                    if(isOn)
                    {
                        imageButton.setImageResource(R.drawable.on);
                        parameters.setFlashMode(android.hardware.Camera.Parameters.FLASH_MODE_TORCH);
                        camera.setParameters(parameters);
                        camera.startPreview();

                        isOn=true;

                    }
                    else
                    {
                        imageButton.setImageResource(R.drawable.off);
                        parameters.setFlashMode(android.hardware.Camera.Parameters.FLASH_MODE_OFF);
                        camera.setParameters(parameters);
                        camera.stopPreview();

                        isOn =false;
                    }

                }

                else
                {
                    AlertDialog.Builder builder =new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Error");
                    builder.setMessage("Flash light is not available");
                    builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            finish();
                        }
                    });

                            AlertDialog alertDialog =builder.create();
                    alertDialog.show();
                }
            }
        });






    }

    @Override
    protected void onStop() {
        super.onStop();

        if(camera!=null)
        {
            camera.release();
            camera =null;
        }
    }
}
