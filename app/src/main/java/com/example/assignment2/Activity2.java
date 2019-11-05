package com.example.assignment2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.IOException;

import io.particle.android.sdk.cloud.ParticleCloud;
import io.particle.android.sdk.cloud.ParticleCloudSDK;
import io.particle.android.sdk.cloud.ParticleDevice;
import io.particle.android.sdk.cloud.ParticleEvent;
import io.particle.android.sdk.cloud.ParticleEventHandler;
import io.particle.android.sdk.cloud.exceptions.ParticleCloudException;
import io.particle.android.sdk.utils.Async;

public class Activity2 extends AppCompatActivity {

    private final String TAG="Pintu";
    private final String PARTICLE_USERNAME = "pintu.chaudhary9@gmail.com";
    private final String PARTICLE_PASSWORD = "Qwerty@123";
    private final String DEVICE_ID = "2d0033000447363333343435";
    private long subscriptionId;
    private ParticleDevice mDevice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        getDeviceFromCloud();
        subscribeToParticleEvents();
    }

    public void subscribeToParticleEvents() {
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
//                                    turnParticleRed1();
                                }
                                else if (choice.contentEquals("B")) {
//                                    turnParticleRed1();
//                                    turnParticleGreen1();
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

}
