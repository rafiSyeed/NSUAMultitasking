package self.rafisyeed.nsuamultitasking;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Handler hand = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                int i = msg.arg1;
                ((TextView)findViewById(R.id.text1)).setText("Run 1: "+i);
                ((TextView)findViewById(R.id.text2)).setText("Run 2: "+(i+2));
            }
        };

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<1000000;i++){
                    try {
                        Message m = hand.obtainMessage();
                        m.arg1=i;
                        hand.sendMessage(m);
                        Thread.sleep(200);
                    }
                    catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
    public void first(View v){
        Toast.makeText(this,"Toast",Toast.LENGTH_SHORT).show();
    }
    public void second(View v){
        Toast.makeText(this,"Toast2",Toast.LENGTH_SHORT).show();
    }
}
