package in.eldhopj.threadinginandroidsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void startThread(View view) {
        BackgroundThread thread = new BackgroundThread(10);
       // thread.run(); //Make run in MainThread
        new Thread(thread).start();
    }

    public void stopThread(View view) {
    }

    class BackgroundThread implements Runnable{
        private int seconds;

        BackgroundThread(int seconds) {
            this.seconds = seconds;
        }

        @Override
        public void run() {
            for (int i = 0; i < seconds; i++) {
                Log.d(TAG, "startThread: " + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
