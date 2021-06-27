package com.cn.callloggen;

import java.io.FileWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * windows上运行jar注意字符集
 */
//java -Dfile.encoding=utf-8 -cp CallLogGenModule-1.0-SNAPSHOT.jar com.cn.callloggen.App d:/calllog/calllog.log

public class App {
    static Random r = new Random();
    public static List<String> phoneNumbers = new ArrayList<String>();
    public static Map<String,String> callers = new HashMap<String, String>();
    static {
        callers.put("15810092493", "史玉龙");
        callers.put("18000696806", "赵贺彪");
        callers.put("15151889601", "张倩 ");
        callers.put("13269361119", "王世昌");
        callers.put("15032293356", "张涛");
        callers.put("17731088562", "张阳");
        callers.put("15338595369", "李进全");
        callers.put("15733218050", "杜泽文");
        callers.put("15614201525", "任宗阳");
        callers.put("15778423030", "梁鹏");
        callers.put("18641241020", "郭美彤");
        callers.put("15732648446", "刘飞飞");
        callers.put("13341109505", "段光星");
        callers.put("13560190665", "唐会华");
        callers.put("18301589432", "杨力谋");
        callers.put("13520404983", "温海英");
        callers.put("18332562075", "朱尚宽");
        callers.put("18620192711", "刘能宗");
        phoneNumbers.addAll(callers.keySet());
    }

    public static void main(String[] args) throws Exception {
//        while (true){
//            genCallLog();
//        }
        if(args == null || args.length == 0){
            System.out.println("no args");
            System.exit(-1);
        }
        genCallLog(args[0]);
    }

    private static void genCallLog(String logFile) throws Exception {
//        FileWriter fw = new FileWriter("/home/centos/calllog/calllog.log",true);
//        FileWriter fw = new FileWriter("d:/calllog/calllog.log",true);
        FileWriter fw = new FileWriter(logFile,true);
        while(true) {
            //取主叫
            String caller = phoneNumbers.get(r.nextInt(callers.size()));
            String callerName = callers.get(caller);

            //取被叫
            String callee = null;
            String calleeName = null;
            while (true) {
                callee = phoneNumbers.get(r.nextInt(callers.size()));
                if (!caller.equals(callee)) {
                    break;
                }
            }
            calleeName = callers.get(callee);

            //通话时长
            int duration = r.nextInt(60 * 10) + 1;
            DecimalFormat df = new DecimalFormat();
            df.applyPattern("000");
            String durStr = df.format(duration);

            //通话时间
            int year = 2021;
            int month = r.nextInt(2);
            int day = r.nextInt(29) + 1;
            int hour = r.nextInt(24);
            int min = r.nextInt(60);
            int sec = r.nextInt(60);

            Calendar c = Calendar.getInstance();
            c.set(Calendar.YEAR, year);
            c.set(Calendar.MONTH, month);
            c.set(Calendar.DAY_OF_MONTH, day);
            c.set(Calendar.HOUR_OF_DAY, hour);
            c.set(Calendar.MINUTE, min);
            c.set(Calendar.SECOND, sec);
            Date date = c.getTime();

            //如果时间超过今天重新取时间
            Date now = new Date();
            if (date.compareTo(now) > 0) {
                continue;
            }
            SimpleDateFormat dfs = new SimpleDateFormat();
            dfs.applyPattern("yyyy/MM/dd HH:mm:ss");
            //通话时间
            String dateStr = dfs.format(date);

//        System.out.println(phoneNumbers);System.out.println(phoneNumbers.get(0));System.out.println(callers.keySet());System.out.println(r.nextInt(54));
//            String log = caller + "," + callerName + "," + callee + "," + calleeName + "," + dateStr + "," + duration;
            String log = caller + "," + callee + "," + dateStr + "," + duration;
            System.out.println(log);
            fw.write(log + "\r\n");
            fw.flush();
            Thread.sleep(200);
        }
    }
}
