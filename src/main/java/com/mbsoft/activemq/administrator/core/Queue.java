package com.mbsoft.activemq.administrator.core;

public class Queue {
    private String name;
    private Long timestamp;
    private Long numberOfMessages;
    private Long expectedNumberOfMessages;

    public Queue(String name, long timestamp, long numberOfMessages, long expectedNumberOfMessages) {
        this.name = name;
        this.timestamp = timestamp;
        this.numberOfMessages = numberOfMessages;
        this.expectedNumberOfMessages = expectedNumberOfMessages;
    }

    public String getName() {
        return name;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public Long getExpectedNumberOfMessages() {
        return expectedNumberOfMessages;
    }

    public Long getNumberOfMessages() {
        return numberOfMessages;
    }
}
