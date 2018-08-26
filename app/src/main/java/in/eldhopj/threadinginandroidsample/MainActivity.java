package in.eldhopj.threadinginandroidsample;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**Commit 1 : Using Runnable interface
 *
 * Commit 2 : Handler
 *          <href "https://www.youtube.com/watch?v=0Z5MZ0jL2BM/>
 *          Runnable can't update anything from background thread into mainThread only after executing, But Handler can do it
 *
 *Commit 3 : post() and runOnUiThread()
 *          Passing message from background thread into MainThread using post() and runOnUiThread()
 *
 *Commit 4 : Anonymous Runnable inner class and how to stop tread
 *          */

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private Handler mainHandler = new Handler(); // Android class to handle background tasks
    private Button buttonStart;

    private volatile boolean stopThread = false; // volatile makes sure all our threads exists always update version of this variable

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonStart = findViewById(R.id.button_start_thread);
    }

    public void startThread(View view) {
        stopThread = false;
        BackgroundThread thread = new BackgroundThread(10);
       // thread.run(); //Make run in MainThread
        new Thread(thread).start();

        /**Anonymous inner class*/
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                // Work in here
//            }
//        });
    }

    public void stopThread(View view) {
        stopThread = true;
    }

    /**Runnable class*/
    class BackgroundThread implements Runnable{
        private int seconds;
        BackgroundThread(int seconds) {
            this.seconds = seconds;
        }

        @Override
        public void run() { // Background task
            for (int i = 0; i < seconds; i++) {
                Log.d(TAG, "startThread: " + i);

                if (stopThread){
                    return;
                }

                if (i==5){
                    /**Put the message into mainThread using Handler*/
//                    mainHandler.post(new Runnable() { // Handler posts it into the UI thread
//                        @Override
//                        public void run() {
//                            buttonStart.setText("50%");
//                        }
//                    });
                    /**Put the message into mainThread using post()*/
//                    buttonStart.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            buttonStart.setText("50%");
//                        }
//                    });
                    /**Put the message into mainThread using runOnUiThread()*/
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            buttonStart.setText("50%");
                        }
                    });
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
