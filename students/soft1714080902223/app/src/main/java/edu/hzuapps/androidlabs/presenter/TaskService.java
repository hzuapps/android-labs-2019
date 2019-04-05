package edu.hzuapps.androidlabs.presenter;

import android.content.Context;
import android.util.ArrayMap;

import java.util.List;
import java.util.Map;

import edu.hzuapps.androidlabs.dao.TaskDao;
import edu.hzuapps.androidlabs.listview.HomeListAdapter;
import edu.hzuapps.androidlabs.model.Task;

public class TaskService {
    private TaskDao taskDao;
    private Context context;
    private List<Task> taskList = null;
    private HomeListAdapter homeListAdapter;

    public TaskService(Context context){
        //为每一个activity创建一个dao
        taskDao = new TaskDao(context);
        this.context = context;
        homeListAdapter = new HomeListAdapter(context);
    }

    /**
     *
     * @param title
     * @return 返回值为保存数据的id值
     */
    public long saveTask(String title, String content){
        long id = taskDao.save(title, content);
        add(id);
        return id;
    }

    /**
     *
     * @param id
     */

    private void add(long id){
        if(taskList == null){
            taskList = taskDao.findAll();
        }
        else {
            Task task = taskDao.findById(id);
            taskList.add(0, task);
        }
        homeListAdapter.setTasks(taskList);
    }

    public Map<String, String> get(long id){
        Task task = taskDao.findById(id);
        Map<String, String> map = new ArrayMap<>();
        map.put("title", task.getTitle());
        map.put("content", task.getContent());
        map.put("Date", task.getDate());
        map.put("finish", Long.toString(task.getFinish()));

        return map;
    }


    /**
     *  把包含所有Task的Adapter返回
     * @return 返回结果的设配器
     */
    public HomeListAdapter allListAdapter(){
        if(taskList == null){
            taskList = taskDao.findAll();
        }
        homeListAdapter.setTasks(taskList);
        return homeListAdapter;
    }

}
