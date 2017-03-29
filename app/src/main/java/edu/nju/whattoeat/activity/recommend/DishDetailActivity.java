package edu.nju.whattoeat.activity.recommend;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import edu.nju.whattoeat.R;
import edu.nju.whattoeat.activity.user.adpater.DishAdapter;


public class DishDetailActivity extends AppCompatActivity {

    private TextView nameView,mainstanceView,substanceView,tasteView,timeView,kindView,nutritionView,placeView,operationView;
    private DishAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish_detail);
        nameView = (TextView)findViewById(R.id.dish_name);
        mainstanceView = (TextView)findViewById(R.id.dish_mainstance);
        substanceView = (TextView)findViewById(R.id.dish_substance);
        tasteView = (TextView)findViewById(R.id.dish_taste);
        timeView = (TextView)findViewById(R.id.dish_time);
        kindView = (TextView)findViewById(R.id.dish_kind);
        nutritionView = (TextView)findViewById(R.id.dish_nutrition);
        placeView = (TextView)findViewById(R.id.dish_place);
        operationView = (TextView)findViewById(R.id.dish_operation);

        Intent intent = getIntent();
        nameView.setText(intent.getStringExtra("name"));
        mainstanceView.setText(intent.getStringExtra("mainstance"));
        substanceView.setText(intent.getStringExtra("substance"));
        tasteView.setText(intent.getStringExtra("taste"));
        timeView.setText(intent.getStringExtra("time"));
        kindView.setText(intent.getStringExtra("kind"));
        nutritionView.setText(intent.getStringExtra("nutrition"));
        placeView.setText(intent.getStringExtra("place"));
        operationView.setText(intent.getStringExtra("operation"));
    }
}
