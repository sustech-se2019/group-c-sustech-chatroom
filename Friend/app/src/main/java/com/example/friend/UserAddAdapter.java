package com.example.friend;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.List;

/**
 * Created by cpj on 2016/3/15.
 */
public class UserAddAdapter extends ArrayAdapter<User>{

    private int resourceId;
    Context context;

    public UserAddAdapter(Context context, int textViewResourceId,
                       List<User> objects){
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
        this.context=context;
    }

    /**
     * 功能描述：这个方法在每个子项滚动到屏幕内的时候被调用
     *         1.首先通过 getItem()获取当前项的ToolBar实例
     *         2.使用LayoutInflater将这个子布局项加载并传入我们的主布局
     *         3.调用View的findViewById()分别获取到左右Layout、左右Msg的实例
     *         4.调用它们的setText()来显示文字
     *         5.最后返回布局
     * */
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
            viewHolder.button=(Button) view.findViewById(R.id.add_request);
            view.setTag(viewHolder);

        } else {
            //convertView参数用于将之前加载好的布局进行缓存，以便之后可以进行复用（提高效率）
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }

        /*接受与发送消息的分类处理*/
        //如果为收到的消息，则显示左边的消息布局，将右边的消息布局隐藏
        viewHolder.layout.setVisibility(View.VISIBLE);
        viewHolder.name.setText(user.getName());
        viewHolder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alertDialog1 = new AlertDialog.Builder(context)
                        .setTitle("Warning")//标题
                        .setMessage("No one want to be your friend")//内容
                        .setIcon(R.mipmap.ic_launcher)//图标
                        .create();
                alertDialog1.show();
            }
        });
        return view;
    }

    //新增内部类ViewHolder，用于对控件的实例进行缓存。
    class ViewHolder{
        LinearLayout layout;
        TextView name;
        Button button;
    }
}