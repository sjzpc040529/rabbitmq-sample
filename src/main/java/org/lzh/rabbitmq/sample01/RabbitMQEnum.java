package org.lzh.rabbitmq.sample01;

import sun.org.mozilla.javascript.internal.annotations.JSSetter;

/**
 * rabbit mq  配置信息
 */
public enum  RabbitMQEnum {
  RABBITMQ_QUEUE_NAME("hello","rabbitmq 队列名称"),
    RABBITMQ_HOST("127.0.0.1","rabbitmq 域名地址");
  private String key ;
    private String value ;
  private RabbitMQEnum(String key ,String value){
    this.key =  key ;
    this.value = value;

  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }
}
