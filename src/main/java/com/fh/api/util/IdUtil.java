package com.fh.api.util;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class IdUtil {

    public static String createId(){
        SimpleDateFormat sim = new SimpleDateFormat("yyyyMMddHHmm");
        String format = sim.format(new Date());
        return format+IdWorker.getId();
    }

    @Test
    public void test(){
        String id = createId();
        System.out.println(id);
    }
}
