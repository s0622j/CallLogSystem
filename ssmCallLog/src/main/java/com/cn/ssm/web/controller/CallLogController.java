package com.cn.ssm.web.controller;

import com.cn.ssm.domain.CallLog;
import com.cn.ssm.service.CallLogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class CallLogController {

    @Resource(name="callLogService")
    private CallLogService cs;

    @RequestMapping("/callLog/findAll")
    public String findAll(Model m){
        List<CallLog> list = cs.findAll();
        m.addAttribute("callLogs",list);
        return "callLog/callLogList";
    }
}
