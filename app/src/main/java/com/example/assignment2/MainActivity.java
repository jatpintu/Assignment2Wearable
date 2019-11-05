package com.example.assignment2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.particle.android.sdk.cloud.ParticleCloud;
import io.particle.android.sdk.cloud.ParticleCloudSDK;
import io.particle.android.sdk.cloud.ParticleDevice;
import io.particle.android.sdk.cloud.ParticleEvent;
import io.particle.android.sdk.cloud.ParticleEventHandler;
import io.particle.android.sdk.cloud.exceptions.ParticleCloudException;
import io.particle.android.sdk.utils.Async;
import android.graphics.Matrix;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private final String TAG="Pintu";
    private final String PARTICLE_USERNAME = "pintu.chaudhary9@gmail.com";
    private final String PARTICLE_PASSWORD = "Qwerty@123";
    private final String DEVICE_ID = "2d0033000447363333343435";
    private long subscriptionId;
    private ParticleDevice mDevice;
    TextView answer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        answer = (TextView) findViewById(R.id.answerLabel);
        ParticleCloudSDK.init(this.getApplicationContext());
        getDeviceFromCloud();
    }

    public void getDeviceFromCloud() {
        Async.executeAsync(ParticleCloudSDK.getCloud(), new Async.ApiWork<ParticleCloud, Object>() {

            @Override
            public Object callApi(@NonNull ParticleCloud particleCloud) throws ParticleCloudException, IOException {
                particleCloud.logIn("pintu.chaudhary9@gmail.com", "Qwerty@123");
                mDevice = particleCloud.getDevice(DEVICE_ID);
                return -1;
            }
            @Override
            public void onSuccess(Object o) {
                Log.d(TAG, "Successfully got device from Cloud");
            }
            @Override
            public void onFailure(ParticleCloudException exception) {
                Log.d(TAG, exception.getBestMessage());
            }
        });
    }

    public void turnParticleGreen1() {

        Async.executeAsync(ParticleCloudSDK.getCloud(), new Async.ApiWork<ParticleCloud, Object>() {
            @Override
            public Object callApi(@NonNull ParticleCloud particleCloud) throws ParticleCloudException, IOException {
                // put your logic here to talk to the particle
                // --------------------------------------------
                List<String> functionParameters = new ArrayList<String>();
                functionParameters.add("square1");
                try {
                    mDevice.callFunction("answer1", functionParameters);

                } catch (ParticleDevice.FunctionDoesNotExistException e1) {
                    e1.printStackTrace();
                }


                return -1;
            }

            @Override
            public void onSuccess(Object o) {
                // put your success message here
                Log.d(TAG, "Success: Turned light green!!");
                answer.setText("Answer is InCorrect");

            }

            @Override
            public void onFailure(ParticleCloudException exception) {
                // put your error handling code here
                Log.d(TAG, exception.getBestMessage());
            }
        });
    }

    public void turnParticleRed1() {

        Async.executeAsync(ParticleCloudSDK.getCloud(), new Async.ApiWork<ParticleCloud, Object>() {
            @Override
            public Object callApi(@NonNull ParticleCloud particleCloud) throws ParticleCloudException, IOException {
                // put your logic here to talk to the particle
                // --------------------------------------------
                List<String> functionParameters = new ArrayList<String>();
                functionParameters.add("triangle1");
                try {
                    mDevice.callFunction("answer1", functionParameters);

                } catch (ParticleDevice.FunctionDoesNotExistException e1) {
                    e1.printStackTrace();
                }


                return -1;
            }

            @Override
            public void onSuccess(Object o) {
                // put your success message here
                answer.setText("Answer is Correct");

                Log.d(TAG, "Success: Turned lights red!!");
            }

            @Override
            public void onFailure(ParticleCloudException exception) {
                // put your error handling code here
                Log.d(TAG, exception.getBestMessage());
            }
        });



    }
    public void turnParticleGreen2() {

        Async.executeAsync(ParticleCloudSDK.getCloud(), new Async.ApiWork<ParticleCloud, Object>() {
            @Override
            public Object callApi(@NonNull ParticleCloud particleCloud) throws ParticleCloudException, IOException {
                // put your logic here to talk to the particle
                // --------------------------------------------
                List<String> functionParameters = new ArrayList<String>();
                functionParameters.add("triangle2");
                try {
                    mDevice.callFunction("answer2", functionParameters);

                } catch (ParticleDevice.FunctionDoesNotExistException e1) {
                    e1.printStackTrace();
                }


                return -1;
            }

            @Override
            public void onSuccess(Object o) {
                // put your success message here
                Log.d(TAG, "Success: Triangle 2!!");
                answer.setText("Answer is Correct");

            }

            @Override
            public void onFailure(ParticleCloudException exception) {
                // put your error handling code here
                Log.d(TAG, exception.getBestMessage());
            }
        });
    }

    public void turnParticleRed2() {

        Async.executeAsync(ParticleCloudSDK.getCloud(), new Async.ApiWork<ParticleCloud, Object>() {
            @Override
            public Object callApi(@NonNull ParticleCloud particleCloud) throws ParticleCloudException, IOException {
                // put your logic here to talk to the particle
                // --------------------------------------------
                List<String> functionParameters = new ArrayList<String>();
                functionParameters.add("square2");
                try {
                    mDevice.callFunction("answer2", functionParameters);

                } catch (ParticleDevice.FunctionDoesNotExistException e1) {
                    e1.printStackTrace();
                }


                return -1;
            }

            @Override
            public void onSuccess(Object o) {
                // put your success message here
                Log.d(TAG, "Success: Turned Square red 2");
                answer.setText("Answer is InCorrect");

            }

            @Override
            public void onFailure(ParticleCloudException exception) {
                // put your error handling code here
                Log.d(TAG, exception.getBestMessage());
            }
        });
    }

    public void subscribeToParticleEvents2() {
        Async.executeAsync(ParticleCloudSDK.getCloud(), new Async.ApiWork<ParticleCloud, Object>() {
            @Override
            public Object callApi(@NonNull ParticleCloud particleCloud) throws ParticleCloudException, IOException {
                subscriptionId = ParticleCloudSDK.getCloud().subscribeToAllEvents(
                        "playerChoice",  // the first argument, "eventNamePrefix", is optional
                        new ParticleEventHandler() {
                            public void onEvent(String eventName, ParticleEvent event) {
                                Log.i(TAG, "Received event with payload: " + event.dataPayload);
                                String choice = event.dataPayload;
                                if (choice.contentEquals("A")) {
//                                    turnParticleGreen2();
                                    turnParticleRed2();
                                }
                                else if (choice.contentEquals("B")) {
//                                    turnParticleRed2();
                                    turnParticleGreen2();
                                }

                            }

                            public void onEventError(Exception e) {
                                Log.e(TAG, "Event error: ", e);
                            }
                        });


                return -1;
            }

            @Override
            public void onSuccess(Object o) {
                Log.d(TAG, "Success: Subscribed to event 2");
            }

            @Override
            public void onFailure(ParticleCloudException exception) {
                Log.d(TAG, exception.getBestMessage());
            }
        });
    }
    public void subscribeToParticleEvents1() {
        Async.executeAsync(ParticleCloudSDK.getCloud(), new Async.ApiWork<ParticleCloud, Object>() {
            @Override
            public Object callApi(@NonNull ParticleCloud particleCloud) throws ParticleCloudException, IOException {
                subscriptionId = ParticleCloudSDK.getCloud().subscribeToAllEvents(
                        "playerChoice",  // the first argument, "eventNamePrefix", is optional
                        new ParticleEventHandler() {
                            public void onEvent(String eventName, ParticleEvent event) {
                                Log.i(TAG, "Received event with payload: " + event.dataPayload);
                                String choice = event.dataPayload;
                                if (choice.contentEquals("A")) {
//                                    turnParticleGreen1();
                                    turnParticleRed1();
                                }
                                else if (choice.contentEquals("B")) {
//                                    turnParticleRed1();
                                    turnParticleGreen1();
                                }

                            }

                            public void onEventError(Exception e) {
                                Log.e(TAG, "Event error: ", e);
                            }
                        });


                return -1;
            }

            @Override
            public void onSuccess(Object o) {
                Log.d(TAG, "Success: Subscribed to event 1");
            }

            @Override
            public void onFailure(ParticleCloudException exception) {
                Log.d(TAG, exception.getBestMessage());
            }
        });
    }

    int counter = 1;

    public void showImage(View view) {

        if(this.counter == 0) {
            unsubscribeFromParticleEvent();
            answer.setText("Answer");
            ImageView img1 = (ImageView) findViewById(R.id.getImage);
            img1.setImageResource(R.drawable.square);
//            img1.setScaleType(ImageView.ScaleType.MATRIX);
//            Matrix matrix = new Matrix();
//            matrix.postRotate((float) 0, 0, 0);
//            img1.setImageMatrix(matrix);
            this.counter = this.counter + 1;
            subscribeToParticleEvents1();
        }else{
            unsubscribeFromParticleEvent();
            answer.setText("Answer");
            ImageView img1 = (ImageView) findViewById(R.id.getImage);
            img1.setImageResource(R.drawable.triangle);
//            img1.setScaleType(ImageView.ScaleType.MATRIX);
//            Matrix matrix = new Matrix();
//            matrix.postRotate((float) 60, 0, 0);
//            img1.setImageMatrix(matrix);
            this.counter = 0;
            subscribeToParticleEvents2();
        }


    }

    private void unsubscribeFromParticleEvent() {
        Async.executeAsync(ParticleCloudSDK.getCloud(), new Async.ApiWork<ParticleCloud, Object>() {
            @Override
            public Object callApi(@NonNull ParticleCloud particleCloud) throws ParticleCloudException, IOException {
                ParticleCloudSDK.getCloud().unsubscribeFromEventWithID(subscriptionId);
                return -1;
            }
            @Override
            public void onSuccess(Object o) {
            }

            @Override
            public void onFailure(ParticleCloudException exception) {
                Log.d(TAG, exception.getBestMessage());
            }
        });
    }

    public void startActivity(View view) {
        Intent intent = new Intent(this,Activity2.class);
        startActivity(intent);
    }


}
