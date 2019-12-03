package org.ruixun.yupoo.utils;

import org.ruixun.yupoo.bean.Mess;
import org.ruixun.yupoo.bean.Messages;

import java.util.ArrayList;
import java.util.List;

public class MessUtils {
    public List<Mess> messageToMess(List<Messages> messagesList){
        ArrayList<Mess> messes = new ArrayList<>();
        messagesList.forEach(messages -> {Mess mess = new Mess();
                                          mess.setMessage(messages.getMes());
                                          mess.setName(messages.getName());
                                          messes.add(mess);} );
        return messes;
    }
}
