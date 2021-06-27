package com.cn.calllog.consumer;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Hbase消费者，从kafka提取数据，存储到hbase中。
 */
public class HbaseConsumer {
    public static void main(String[] args) throws Exception {
        HbaseDao dao = new HbaseDao();
//        Properties props = new Properties();
//        props.put("zookeeper.connect", "master100:2181,data101:2181,data102:2181");
//        props.put("group.id", "g4");
//        props.put("zookeeper.session.timeout.ms", "500");
//        props.put("zookeeper.sync.time.ms", "250");
//        props.put("auto.commit.interval.ms", "1000");
//        props.put("auto.offset.reset", "smallest");
//        //创建消费者配置对象
//        ConsumerConfig config = new ConsumerConfig(props);

        //加载外部属性文件
//        InputStream in = ClassLoader.getSystemResourceAsStream("kafka.Properties");
//        Properties props = new Properties();
//        props.load(in);

        //创建消费者配置对象
//        ConsumerConfig config = new ConsumerConfig(props);
        ConsumerConfig config = new ConsumerConfig(PropertiesUtil.props);

        //获得主题
//        String topic = props.getProperty("topic");
        String topic = PropertiesUtil.getProp("topic");
        //
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put(topic, new Integer(1));
//        Map<String, List<KafkaStream<byte[], byte[]>>> msgs = Consumer.createJavaConsumerConnector(new ConsumerConfig(props)).createMessageStreams(map);
        Map<String, List<KafkaStream<byte[], byte[]>>> msgs = Consumer.createJavaConsumerConnector(new ConsumerConfig(PropertiesUtil.props)).createMessageStreams(map);
        List<KafkaStream<byte[], byte[]>> msgList = msgs.get(topic);

        String msg = null;
        for(KafkaStream<byte[],byte[]> stream : msgList){
            ConsumerIterator<byte[],byte[]> it = stream.iterator();
            while(it.hasNext()){
                byte[] message = it.next().message();
                System.out.println(new String(message));

                //取的kafka的消息
                msg = new String(message);
                //写入HBASE中
                dao.put(msg);
            }
        }
    }
}
