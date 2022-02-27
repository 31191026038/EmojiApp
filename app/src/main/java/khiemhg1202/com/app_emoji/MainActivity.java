package khiemhg1202.com.app_emoji;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    GridView myGird;
    int index = 5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myGird = findViewById(R.id.myGird);
        List<Integer> Emoji = Arrays.asList(
                9800, 9801, 9802, 9803, 9804,
                9805, 9806, 9807, 9808, 9809,
                9810, 9811, 128148, 128151, 128147, 128163
        );
        List data=new ArrayList();
        for(int i=0;i<Emoji.size();i++)
        {
            data.add(new String(Character.toChars(Emoji.get(i))));
        }
        Collections.shuffle(data);
        EmojiAdapter adapter=new EmojiAdapter(getApplicationContext(),R.layout.emoji_item,data);
        myGird.setAdapter(adapter);
        List list=new ArrayList(data);
        final String[] emojiRD = {getAndShowRandomEmoji(list)};
        myGird.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView v = (TextView) view;
                String selected = v.getText().toString();
                if(selected.equals(emojiRD[0])){
                    list.remove(emojiRD[0]);
                    v.setText("");
                    if(list.size()==0) finish();
                }
                else
                {
                    index--;
                    if(index==0)
                    {
                        finish();
                    }
                }
                emojiRD[0] = getAndShowRandomEmoji(list);
            }
        });
    }

    private String getAndShowRandomEmoji(List list) {
        Random random=new Random();
        String result=list.get(random.nextInt(list.size())).toString();
        TextView myText=findViewById(R.id.myText);
        myText.setText(result);
        return result;
    }
}