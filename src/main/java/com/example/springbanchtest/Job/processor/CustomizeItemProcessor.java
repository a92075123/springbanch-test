package com.example.springbanchtest.Job.processor;

import com.example.springbanchtest.bean.User;
import org.springframework.batch.item.ItemProcessor;

//自訂義解析器
public class CustomizeItemProcessor implements ItemProcessor<User,User> {

    @Override
    public User process(User user) throws Exception {


        return user.getAge()> 16 ? user:null;
    }
}
