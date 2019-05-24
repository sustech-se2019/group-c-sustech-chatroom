package com.example.se_project;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

/**
 * Created by cpj on 2016/3/15.
 */
public class MomentsAdapter extends ArrayAdapter<Moments> implements Serializable {

    private final int resourceId;
    private MomentsAdapter adapter=this;
    public MomentsAdapter(Context context, int textViewResourceId,
                          List<Moments> objects){
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
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
        final Moments moments = getItem(position);
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
            viewHolder.text = (TextView) view.findViewById(R.id.moments_text);
            viewHolder.button=(Button) view.findViewById(R.id.good);
            viewHolder.image=(ImageView) view.findViewById(R.id.moments_profile_picture) ;
            viewHolder.goodNum=(TextView)view.findViewById(R.id.good_num) ;
            view.setTag(viewHolder);
        } else {
            //convertView参数用于将之前加载好的布局进行缓存，以便之后可以进行复用（提高效率）
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }

        /*接受与发送消息的分类处理*/
        //如果为收到的消息，则显示左边的消息布局，将右边的消息布局隐藏
        viewHolder.button.setTag(moments);
        viewHolder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (moments != null)
                    AppData.getInstance().getMe().thumbUpMoment(moments.getMomentId());
                moments.addGood();
                adapter.notifyDataSetChanged();
            }
        });
        viewHolder.goodNum.setText(String.valueOf(moments.getGoodNum()));
        viewHolder.text.setText(moments.getText());
        viewHolder.image.setImageResource(moments.getUser().getProfilePictureID());
        return view;
    }

    //新增内部类ViewHolder，用于对控件的实例进行缓存。
    class ViewHolder implements Serializable{
        TextView text;
        Button button;
        ImageView image;
        TextView goodNum;
    }
}