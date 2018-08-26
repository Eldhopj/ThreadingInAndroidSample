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
 *          */

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private Handler mainHandler = new Handler(); // Android class to handle background tasks
    private Button buttonStart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonStart = findViewById(R.id.button_start_thread);
    }

    public void startThread(View view) {
        BackgroundThread thread = new BackgroundThread(10);
       // thread.run(); //Make run in MainThread
        new Thread(thread).start();
    }

    public void stopThread(View view) {
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

                /**Put the message into mainThread using Handler*/
                if (i==5){
                    mainHandler.post(new Runnable() { // Handler posts it into the UI thread
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
