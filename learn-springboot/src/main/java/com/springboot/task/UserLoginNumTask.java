package com.springboot.task;

import com.springboot.entity.AccessLog;
import com.springboot.repository.AccessLogRepository;
import com.springboot.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Component
public class UserLoginNumTask {

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private AccessLogRepository accessLogRepository;

    @Scheduled(cron = "0 0 0 * * ?")
    private void processing(){
        final String userLoginNum = (String) redisUtils.get("userLoginNum");

        AccessLog accessLog = new AccessLog();
        accessLog.setCount(Integer.parseInt(userLoginNum));
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String createDatetime = sdf.format(calendar.getTime());
        accessLog.setCreateDatetime(createDatetime);
        accessLogRepository.save(accessLog);
    }
}
