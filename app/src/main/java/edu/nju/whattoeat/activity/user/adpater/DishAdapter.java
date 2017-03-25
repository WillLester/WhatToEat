package edu.nju.whattoeat.activity.user.adpater;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

import java.util.List;

import edu.nju.whattoeat.vo.Dish;

/**
 * Created by WillLester on 2017/3/25.
 */

public class DishAdapter extends ArrayAdapter<Dish>{
    public DishAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Dish> objects) {
        super(context, resource, objects);
    }
}
