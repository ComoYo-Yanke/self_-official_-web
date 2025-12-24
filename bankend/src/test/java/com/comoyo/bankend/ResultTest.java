package com.comoyo.bankend;

import com.comoyo.bankend.pojo.Result;
import org.junit.jupiter.api.Test;

public class ResultTest {
    
    @Test
    public void testResult(){
        Result result = new Result();
        System.out.println(result.success());
        
    }
}
