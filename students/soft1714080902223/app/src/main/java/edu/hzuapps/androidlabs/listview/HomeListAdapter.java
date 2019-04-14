package edu.hzuapps.androidlabs.listview;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import edu.hzuapps.androidlabs.model.Task;
import edu.hzuapps.androidlabs.presenter.TaskService;
import edu.hzuapps.androidlabs.soft1714080902223.R;

public class HomeListAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    //由于引用传递，又由于TaskService是单例，tasks赋值一次后便作为TaskService中list的的观察者
    private List<Task> tasks;
    private TaskService taskService;

    public HomeListAdapter(Context context){
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        taskService = TaskService.INSTANCE.getTaskService(mContext, this);
        tasks = taskService.getAllList();
    }

    public void notifyChange(){
        // 通知ListView修改了
        notifyDataSetChanged();
    }

    /**
     *
     * @return 返回ListView的个数
     */
    @Override
    public int getCount() {
        return tasks.size();
    }

    /**
     *
     * @param position 参数为第几个对象
     * @return 返回子项对应的对象
     */

    @Override
    public Object getItem(int position) {
        //获取列表中的对象
        return tasks.get(position);
    }

    /**
     *
     * @param position
     * @return 返回对应位置的item的id
     */
    @Override
    public long getItemId(int position) {
        return 0;
    }

    static class ViewHolder {
        TextView lvTitle, lvTime, lvContent, lvStatus;
    }

    /**
     *
     * @param position 列表中的第几个对象
     * @param convertView
     * @param parent
     * @return 返回子项视图
     */
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Task task = (Task) getItem(position);
        View view;
        final ViewHolder holder;
        //如果不存在，就去xml中获取，存在就直接用
        if(convertView == null){
            //关联list_item及其参数
            view = mLayoutInflater.inflate(R.layout.list_item, null);
            holder = new ViewHolder();
            holder.lvTitle = view.findViewById(R.id.lv_title);
            holder.lvContent = view.findViewById(R.id.lv_content);
            holder.lvTime = view.findViewById(R.id.lv_time);
            holder.lvStatus = view.findViewById(R.id.lv_status);
            view.setTag(holder);
        }
        else{
            view = convertView;
            holder = (ViewHolder) convertView.getTag();
        }
        //使用task中的数据填上
        holder.lvTitle.setText(task.getTitle());
        holder.lvContent.setText(task.getContent());
        holder.lvTime.setText(taskService.getTextTime(task.getDate()));

        //根据Finish的值的不同更改样式
        if(task.getFinish() == 0){
            taskService.setStatus(holder.lvStatus, taskService.UNFINALSTATUS, position);
        }else{
            taskService.setStatus(holder.lvStatus, taskService.FINALSTATUS, position);
        }

        //设置点击更换标签样式
        holder.lvStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.lvStatus.getText() == taskService.FINALSTATUS){
                    taskService.setStatus(holder.lvStatus, taskService.UNFINALSTATUS, position);
                }else{
                    taskService.setStatus(holder.lvStatus, taskService.FINALSTATUS, position);
                }

            }
        });
        return view;
    }




}
