package com.comoyo;

import com.comoyo.pojo.Result;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResultTest {
    private static final Logger log = LoggerFactory.getLogger(Result.class);
    
    @Test
    public void testResult(){
        Result result = new Result();
//        System.out.println(result.success());
        log.info(result.success().toString());
        
    }
}
