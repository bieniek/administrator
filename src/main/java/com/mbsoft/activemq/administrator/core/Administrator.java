package com.mbsoft.activemq.administrator.core;

import java.util.List;

public interface Administrator {
    List<WorkItemsQueue> list();

    void delete(String queueName);
}
