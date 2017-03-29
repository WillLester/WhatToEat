package edu.nju.whattoeat.activity.user.adpater;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import edu.nju.whattoeat.vo.Dish;

/**
 * Created by WillLester on 2017/3/25.
 */

public class DishAdapter extends ArrayAdapter<Dish>{

    private int resourceId;
    public DishAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Dish> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }


    public View getView(int position, View convertView, ViewGroup parent){
        Dish dish = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        TextView dishName = (TextView)view.findViewById(R.id.dish_name);
        dishName.setText(dish.getName());
        TextView dishTaste = (TextView)view.findViewById(R.id.dish_taste);
        dishTaste.setText( dish.getTaste());
        TextView dishTime = (TextView)view.findViewById(R.id.dish_time_to_eat);
        dishTime.setText(dish.getTime_to_eat());
        TextView dishKind = (TextView)view.findViewById(R.id.dish_kind);
        dishKind.setText(dish.getKind());
        TextView dishNuitrition = (TextView)view.findViewById(R.id.dish_nuitrition);
        dishNuitrition.setText(dish.getNutrition());
        TextView dishPlace = (TextView)view.findViewById(R.id.dish_place);
        dishPlace.setText(dish.getPlace());
        return view;
    }
}
