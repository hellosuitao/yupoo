package org.ruixun.yupoo.controller;

import org.ruixun.yupoo.bean.Mess;
import org.ruixun.yupoo.bean.Messages;
import org.ruixun.yupoo.service.MessagesService;
import org.ruixun.yupoo.utils.Result;
import org.ruixun.yupoo.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/*
 * 作者：罗闽秋
 * 留言控制器
 * */
@Controller
public class MessagesController {
    @Autowired
    private MessagesService messagesService;

    /*
     * 添加留言
     * */
    @ResponseBody
     @RequestMapping(value = "/insmes", consumes = "application/json")
    public Result insertMes(@RequestBody  @Valid Messages messages, BindingResult bindingResult){
         if(bindingResult.hasErrors()){
             List<ObjectError> errors = bindingResult.getAllErrors();
             StringBuilder sb =new StringBuilder();
             errors.forEach(error->sb.append(error+","));
             return ResultUtils.buildFail(sb.toString());
         }
         String s = messagesService.insMes(messages);
         if (s!="success"){
             return ResultUtils.buildFail("fail");
         }
//         List<Mess> mesByPid = messagesService.findMesByPid(messages.getPid());
//         mesByPid.forEach(System.out::println);
         return ResultUtils.buildSuccess("success");
     }
     //留言回显
     @RequestMapping("/mesList")
    public  Result findMes(@RequestBody Map<String,Object> map){
         System.out.println(map.keySet());
         System.out.println(((Integer) map.get("pid")).longValue());
         System.out.println((Integer) map.get("page")+"aaaaaaaaaaaa");
         Page<Messages> mes = messagesService.findMes(((Integer) map.get("pid")).longValue(),(Integer)map.get("page"));
         System.out.println(mes.getContent());
         return ResultUtils.buildSuccess(mes);
     }
     //留言分页
     @RequestMapping("/mesOverPage")
     public String mesPage(Integer pid,Integer page,Map<String,Object> map){
         Page<Messages> mes = messagesService.findMes(pid.longValue(),page);
         map.put("Mess",mes);
         return "add_album";
     }
}
