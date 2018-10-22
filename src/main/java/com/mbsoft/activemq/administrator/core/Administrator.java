package com.mbsoft.activemq.administrator.core;

import java.util.List;

public interface Administrator {
    List<Queue> list();

    void delete(String queueName);
}
