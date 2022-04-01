package com.dream.server;

public class TestProducer
{
    public static void main(String[] args) throws Exception
    {
        /*System.setProperty(ClientLogger.CLIENT_LOG_USESLF4J, "true");
        DefaultMQProducer producer = new DefaultMQProducer("test");
        producer.setNamesrvAddr("192.168.1.106:9876");

        producer.start();

        Message message = new Message("TestTopic", "TagA", "HAHA".getBytes(StandardCharsets.UTF_8));
        producer.send(message);
        System.out.println("send");

        producer.shutdown();

        System.exit(0);*/


        /*CacheManager manager = CacheManagerBuilder.newCacheManagerBuilder()
                .withCache("test", CacheConfigurationBuilder.newCacheConfigurationBuilder(
                        Integer.class, String.class, ResourcePoolsBuilder.newResourcePoolsBuilder().heap(10, MemoryUnit.MB))
                        .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(5))))
                .build(true);

        Cache<Integer, String> cache = manager.getCache("test", Integer.class, String.class);

        cache.put(1, "hello world");

        Thread.sleep(4000);

        cache.put(2, "hello ehcache");

        Thread.sleep(2000);

        System.out.println(cache.get(1));
        System.out.println(cache.get(2));*/
    }
}
