/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package progin5;

import java.util.ArrayList;

/**
 *
 * @author Krisna Dibyo Atmojo <krisnadibyo@gmail.com>
 */
public class Client {
    private String ClientID;
    private ArrayList<Task> listTask;
    private String LOG;

    public Client(String id) {
        ClientID = id;
        listTask = new ArrayList<Task>();
    }
    public String getClientID() {
        return ClientID;
    }

    public void AddTask(Task e) {
        listTask.add(e);
    }
    public void DisposeTaskList() {
        listTask.clear();
    }

    public void UpdateTask(String idtask,boolean b,String time) {
        for (Task a : listTask) {
            if (a.GetTaskID().equals(idtask)) {
                a.SetStatus(b);
                a.SetTimeStamp(time);
            }
        }
    }

    public ArrayList<Task> getTaskList() {
        return listTask;
    }


}
