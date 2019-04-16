package edu.hzuapps.androidlabs.presenter;

import android.content.Context;

import android.util.ArrayMap;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import edu.hzuapps.androidlabs.dao.TaskDao;
import edu.hzuapps.androidlabs.listview.HomeListAdapter;
import edu.hzuapps.androidlabs.model.Task;
import edu.hzuapps.androidlabs.soft1714080902223.R;


public enum  TaskService {

     //使用单例模式实现，保证线程安全
    INSTANCE;
    private TaskService instance;
    private final String LAST_TIME_PATTERN = TaskDao.LAST_TIME_PATTERN;
    public final String FINALSTATUS = "完成";
    public final String UNFINALSTATUS = "进行中";
    private final String CREATE_TIME_PATTERN = TaskDao.CREATE_TIME_PATTERN;

    private TaskDao taskDao;
    //单例保证taskList的使用，而不用每次都去数据库,节约开销
    private List<Task> taskList;
    private HomeListAdapter homeListAdapter;

    private TaskService(){
        taskList = new ArrayList<>();
    }
    /**
     * TaskService的初始化函数，需要上下文时进行修改
     * @param context
     * @return
     */
    public TaskService getTaskService(Context context){
        INSTANCE.taskDao = new TaskDao(context);
        return INSTANCE;
    }

    /**
     * TaskService的初始化函数，需要上下文跟设配器时进行修改
     * @param context
     * @param homeListAdapter
     * @return
     */
    public TaskService getTaskService(Context context, HomeListAdapter homeListAdapter){
        INSTANCE.taskDao = new TaskDao(context);
        INSTANCE.homeListAdapter = homeListAdapter;
        return INSTANCE;
    }

    /**
     *
     * @param title
     * @return 返回值为保存数据的id值
     */
    public TaskService save(String title, String content, String lastTime){
        long id = taskDao.save(title, content, lastTime);
        add(id);
        return INSTANCE;
    }

    /**
     * 将数据库的数据加入列表中
     * @param id 数据id
     */

    private void add(long id){
        if(taskList == null){
            taskList = taskDao.findAll();
        }
        else {
            Task task = taskDao.findById(id);
            taskList.add(0, task);
        }
        homeListAdapter.notifyChange();
    }

    /**
     * 获取对应位置的Task
     * @param position
     * @return
     */

    public Map<String, String> get(int position){
        Task task = taskList.get(position);
        Map<String, String> map = new ArrayMap<>();
        map.put("title", task.getTitle());
        map.put("content", task.getContent());
        map.put("create_time", task.getCreateTime());
        map.put("last_time", task.getLastTime());
        map.put("finish", Long.toString(task.getFinish()));

        return map;
    }

    /**
     * 更新某个Task
     * @param position task的位置
     * @param title
     * @param context
     * @return
     */

    public TaskService update(int position, String title,String context, String lastTime){
        if(position <= taskList.size() && position >= 0){
            Task task = taskList.get(position);
            task.setContent(context);
            task.setTitle(title);
            task.setLastTime(lastTime);
            taskList.set(position, task);
            taskDao.update(title, context, lastTime, task.getId());
        }
        homeListAdapter.notifyChange();
        return INSTANCE;
    }

    /**
     * 删除某个task
     * @param position task的位置
     * @return
     */
    public TaskService delete(int position){
        if(position <= taskList.size() && position >= 0){
            Task task = taskList.remove(position);
            taskDao.delete(task.getId());
        }
        //删除没加nofity也没问题，猜测是ListView有检测
        return INSTANCE;
    }

    /**
     *  把包含所有Task的Adapter返回
     * @return 返回结果的设配器
     */
    public List<Task> getAllList(){
        if(INSTANCE.taskList == null){
        }
        else if(INSTANCE.taskList.isEmpty()){
            INSTANCE.taskList = taskDao.findAll();
        }
        else
            System.out.println(taskList.size());
        return INSTANCE.taskList;
    }

    /**
     * 用于设置完成状态标签的值, 并更新数据库的值
     * @param view 具体某个TextView的
     * @param status status值
     */
    public void setStatus(TextView view, String status, int position){
        view.setText(status);
        switch (status){
            case FINALSTATUS:
                view.setBackgroundResource(R.color.colorFinalStatus);
                INSTANCE.changeTaskStatus(1, position);
                break;
            case UNFINALSTATUS:
                view.setBackgroundResource(R.color.colorUnFinalStatus);
                INSTANCE.changeTaskStatus(0, position);
                break;
        }
    }

    /**
     * 修改Task的状态值，并在通知其他地方更新
     * @param finish
     * @param position
     */
    private void changeTaskStatus(int finish, int position){
        Task task = taskList.get(position);
        if(task.getFinish() != finish) {
            task.setFinish(finish);
            Task task1 = taskList.set(position, task);
            task = taskList.get(position);
            taskDao.update(null, null, null, finish, task.getId());
            homeListAdapter.notifyChange();
        }
    }

    public String timeToString(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(LAST_TIME_PATTERN);
        return simpleDateFormat.format(date);
    }

    /**
     * 将last时间字符串转化为Date
     * @param lastTime 传入Task的lastTime参数
     * @return
     */

    public Date stringToDate(String lastTime) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(LAST_TIME_PATTERN);
        try {
            return (Date) simpleDateFormat.parse(lastTime);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new Date();
    }
    /**
     * 获取可以显示的时间，如刚刚，几分钟前
     * @param time 输入"yyyy-MM-dd HH:mm:ss"格式的字符串
     * @return
     */
    public String getTextTime(String time){
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat(CREATE_TIME_PATTERN);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd:HH:mm");
        Date now = new Date();
        Date old = null;
        try {
            old = simpleDateFormat1.parse(time);
        }catch (Exception e){
            e.printStackTrace();
        }
        String[] olds = simpleDateFormat.format(old).split(":");
        String[] nows = simpleDateFormat.format(now).split(":");

        String result = time;
        if(olds[0].equals(nows[0]))
        {
            result = String.format("%s:%s", olds[1], olds[2]);
            if(olds[1].equals(nows[1])){
                long until =  Long.parseLong(nows[2]) - Long.parseLong(olds[2]);
                if(until < 5){
                    result = "刚刚";
                }
                else {
                    result = until + "分钟前";
                }
            }
        }
        return result;
    }



}
