package com.example.smartlivingcommunity.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class TestUtils {
    public static <T> T getOrAwaitValue(final LiveData<T> liveData) throws InterruptedException {
        final Object[] data = new Object[1];
        final CountDownLatch latch = new CountDownLatch(1);

        Observer<T> observer = new Observer<T>() {
            @Override
            public void onChanged(T o) {
                data[0] = o;
                latch.countDown();
                liveData.removeObserver(this);
            }
        };

        liveData.observeForever(observer);

        try {
            if (!latch.await(5, TimeUnit.SECONDS)) {
                liveData.removeObserver(observer);
                throw new RuntimeException("LiveData value was not set within 5 seconds");
            }
        } catch (InterruptedException e) {
            liveData.removeObserver(observer);
            throw e;
        }

        return (T) data[0];
    }
}