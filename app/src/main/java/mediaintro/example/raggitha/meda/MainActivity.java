package mediaintro.example.raggitha.meda;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private MediaPlayer mediaPlayer;
    private Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        button = (Button) findViewById(R.id.playPause);
        button2 = (Button) findViewById(R.id.button2);
        mediaPlayer = new MediaPlayer();
        mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.alanwalker_faded);

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                int duration = mp.getDuration()/1000;
                Toast.makeText(getApplicationContext(),"Duration: "+ duration +" s", Toast.LENGTH_SHORT).show();
                button.setText("Play");
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying())
                    pauseMusic();
                else
                    playMusic();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    void pauseMusic(){
        if(mediaPlayer!=null){
            mediaPlayer.pause();
            button.setText("Play");
            double dur = mediaPlayer.getCurrentPosition()/1000.0;
            Toast.makeText(getApplicationContext(),"Duration: "+ dur +" s", Toast.LENGTH_SHORT).show();
        }
    }

    void playMusic(){
        if(mediaPlayer!=null){
            mediaPlayer.start();
            button.setText("Pause");
        }
    }

    @Override
    protected void onDestroy() {
        if(mediaPlayer!=null){
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
            Toast.makeText(getApplicationContext(),"Killed it Macha!", Toast.LENGTH_SHORT).show();
        }
        super.onDestroy();

    }
}
