package com.biz.test;

import com.alibaba.fastjson.JSON;
import com.biz.study.entity.Student;
import com.biz.study.utils.JedisUtil;
import com.sun.deploy.util.StringUtils;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.SortingParams;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.rmi.StubNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Test {
    private Jedis jedis = JedisUtil.getJedis();
    private static AtomicInteger ai =new AtomicInteger();
    SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd");
    @org.junit.Test
    public void test() throws ParseException {
        Calendar instance = Calendar.getInstance();
        instance.set(1996,10,22);

        Date parse = sp.parse("1996-11-22");

//        long time = instance.getTimeInMillis();
//        String format = sp.format(time);
        System.out.println(sp.format(parse));
    }
    int i =0;
    @org.junit.Test

    public void insertStu() throws ParseException {
            Map<String,String> map = new HashMap<>();
        //生成student对象
        int i;
        for (i=0;i<5;i++) {
            String uuid = String.valueOf(UUID.randomUUID());
            map.put("uuid", uuid);
            map.put("stu_name", "张三"+i);
            map.put("birthday", "1996-3-6");
            map.put("description", "沙发上发生00"+i);
            map.put("avgscore", String.valueOf(73+i));

            jedis.hmset(uuid, map);

            jedis.zadd("zstu", 73+i, uuid);
            //将id作为key存入redis

        }
    }


    @org.junit.Test
    public void getStu() throws ParseException {
        Student student;
        Set<String> stu1 = jedis.zrevrangeByScore("zstu", 10000, 0, 1, 5);
        for (String str : stu1){
            student = new Student();
            String uuid = jedis.hget(str,"uuid");
            String stu_name = jedis.hget(str,"stu_name");
            String birthday = jedis.hget(str,"birthday");
            String decription = jedis.hget(str,"decription");
            String avgscore = jedis.hget(str,"avgscore");
            System.out.println(uuid+"---"+stu_name+"----"+avgscore);
            student.setStu_id(uuid);
            student.setStu_name(stu_name);
            student.setStu_birthday(sp.parse(birthday));
            student.setDescription(decription);
            student.setAvgscore(Integer.parseInt(avgscore));
            System.out.println(student.getAvgscore()+"------"+student.getStu_birthday());
        }
      /*  SortingParams sp = new SortingParams();
        sp.limit(0,10);
        sp.by("not-exists-key");
        sp.get("stu_*");
        sp.desc();
        List<String> stu = jedis.sort("stu_s",sp);
        for(String ss :stu){
            Student student = JSON.parseObject(ss, Student.class);
            System.out.println(student.getAvgscore());
        }*/

    }
    @org.junit.Test
   public void testSort(){
       /* List<Student> students = new ArrayList<>();
        for(int i = 0;i<5;i++){
            Student stu = new Student();
            stu.setStu_id(String.valueOf(i));
            stu.setStu_name("zhe"+i);
            stu.setDescription("ssdf"+i);
            stu.setStu_birthday(new Date());
            stu.setAvgscore(65+i);
            students.add(stu);
        }
        Collections.sort(students);
        for (Student student : students){
            System.out.println(student.getAvgscore());
        }*/

        List<Student> stulist = new ArrayList<>();
        SortingParams sp = new SortingParams();
        sp.limit(0,6);
        sp.by("not-exists-key");
        sp.get("stu_"+"*");
        sp.desc();
        List<String> stu = jedis.sort("stu_"+"info",sp);

        for(String ss :stu){
            System.out.println(ss);
            Student  student = JSON.parseObject(ss, Student.class);
            stulist.add(student);
        }
        Collections.sort(stulist);
        for (Student students : stulist){
            System.out.println(students.getAvgscore());
        }
        JedisUtil.releaseReourse(jedis);
    }

   @org.junit.Test
   public void delall(){
       jedis.flushDB();
   }



     @org.junit.Test
    public void  delinfo(){

//         Long del = jedis.del("stu_b3f22c67-0709-429d-9b47-620b391679f4");
         int stu_ = "stu_b3f22c67-0709-429d-9b47-620b391679f4".indexOf("_");

         String substring = "stu_b3f22c67-0709-429d-9b47-620b391679f4".substring(stu_+1);
         System.out.println(substring);
         Long stu_info = jedis.lrem("stu_info", 1, substring);
         System.out.println(stu_info);
     }
}
