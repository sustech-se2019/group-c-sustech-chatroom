package com.example.se_project;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.List;

import static android.support.v4.content.ContextCompat.startActivity;
import static android.support.v4.content.ContextCompat.startForegroundService;

/**
 * Created by cpj on 2016/3/15.
 */
public class UserAdapter extends ArrayAdapter<User>{

    private int resourceId;

    public UserAdapter(Context context, int userViewResourceId,
                       List<User> objects){

        super(context, userViewResourceId, objects);
        resourceId = userViewResourceId;
    }
    public View getView(int position, View convertView, ViewGroup parent){
        User user = getItem(position);
        /*
         * 新增内部类ViewHolder，用于对控件的实例进行缓存。
         * 1.当convertView为空时，创建一个ViewHolder对象，并将控件的实例对象存放在ViewHolder里。
         * 2.然后调用View的setTag()方法，将ViewHolder对象存储在View中。
         * 3.当convertView不为空时，调用View的getTag()方法把ViewHolder重新取出来。
         * */
        View view;
        ViewHolder viewHolder;

        /*加载自定义布局与控件实例*/
        if(convertView == null){
            view = LayoutInflater.from(getContext()).inflate(resourceId, null);

            //创建控件实例并进行缓存
            viewHolder = new ViewHolder();
            viewHolder.layout = (LinearLayout) view.findViewById(R.id.user_add_layout);
            viewHolder.name = (TextView) view.findViewById(R.id.user_name);
            viewHolder.image = (ImageView) view.findViewById(R.id.user_image);
            view.setTag(viewHolder);

        } else {
            //convertView参数用于将之前加载好的布局进行缓存，以便之后可以进行复用（提高效率）
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }

        /*display the user*/
        viewHolder.layout.setVisibility(View.VISIBLE);
        viewHolder.name.setText(user.getName());
        viewHolder.image.setImageResource(user.getProfilePictureID());
        return view;
    }

    //新增内部类ViewHolder，用于对控件的实例进行缓存。
    class ViewHolder{
        LinearLayout layout;
        TextView name;
        ImageView image;
    }
}