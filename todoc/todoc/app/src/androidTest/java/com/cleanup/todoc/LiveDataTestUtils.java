package com.cleanup.todoc;


import androidx.lifecycle.LiveData;

import java.util.concurrent.CountDownLatch;

public class LiveDataTestUtils {

    public static <T> void getValue(final LiveData<T> liveData) throws InterruptedException{

        final Object[] data = new Object[1];
        final CountDownLatch latch=new CountDownLatch(1);





    }
}
