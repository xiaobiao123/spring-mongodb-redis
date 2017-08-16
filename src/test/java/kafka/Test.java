//package kafka;
//
//public class Test {
//
//    @Autowired
//    @Qualifier("inputToKafka")
//    MessageChannel messageChannel;
//
//    @Autowired
//    @Qualifier("inputFromKafka")
//    PollableChannel pollableChannel;
//
//
//    @Test
//    public void sendMsg() throws Exception {
//
//        for (int i = 0; i < 15; i++) {
//            Message<String> message = new GenericMessage<String>("test-------------" + (i + 100));
//            boolean flag = messageChannel.send(message);
//
//            System.out.println(flag + "=============" + (i + 100));
//        }
//
//        Message<?> received = pollableChannel.receive(10000);
//        while (received != null) {
//            System.out.println("|||" + received);
//            received = pollableChannel.receive(10000);
//        }
//
//    }
