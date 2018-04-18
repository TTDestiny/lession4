package com.biz.study.service.Impl;

import com.alibaba.fastjson.JSON;
import com.biz.study.entity.Page;
import com.biz.study.entity.Student;
import com.biz.study.service.StudentService;
import com.biz.study.utils.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.SortingParams;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class StudentServiceImpl implements StudentService {
    private Jedis jedis;
    private static String LNAME = "stu_info";
    private static String STU_SORT = "zstu";
    private SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 查询所有学生信息
     *
     * @return
     * @throws ParseException
     */
    @Override
    public Page getAllStu(String currentPage, String pageSize) {
        Student student;
        List<Student> stulist = new ArrayList<>();
        //获取jedis实例
        jedis = JedisUtil.getJedis();
        // 创建Page对象
        Page indexPage = new Page();
        //初始化第一页
        if (currentPage == null) {
            indexPage.setCurrentPage(1);
        } else {
            indexPage.setCurrentPage(Integer.parseInt(currentPage));
        }
        //每页要显示的数据
        if (pageSize != null) {
            indexPage.setPagSize(Integer.parseInt(pageSize));
        }
        //起始下标，
        indexPage.setStar((indexPage.getCurrentPage() - 1) * indexPage.getPagSize());
        // 获取排序，分页后的id
        Set<String> stuset = jedis.zrevrangeByScore(STU_SORT, 10000, 0, indexPage.getStar(), indexPage.getPagSize());
        // 再遍历id获取hash里的信息
        for (String stu : stuset) {
            try {
                student = new Student();
                student.setStu_id(jedis.hget(stu, "stu_id"));
                student.setStu_name(jedis.hget(stu, "stu_name"));
                student.setStu_birthday(sp.parse(jedis.hget(stu, "stu_birthday")));
                student.setDescription(jedis.hget(stu, "decription"));
                student.setAvgscore(Integer.parseInt(jedis.hget(stu, "avgscore")));
                //将查询出的信息放入student中，再放入stulist中
                stulist.add(student);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        //总条数
        Long totalCount = jedis.zcard(STU_SORT);
        //设置总页数
        indexPage.setTotaalPage((int) (totalCount % indexPage.getPagSize() == 0 ? totalCount / indexPage.getPagSize() : totalCount / indexPage.getPagSize() + 1));
        indexPage.setDataList(stulist);
        //释放redis资源
        JedisUtil.releaseReourse(jedis);
        return indexPage;
    }

    /**
     * 根据id获取学生信息
     *
     * @param id
     * @return
     */
    @Override
    public Student getStuById(String id) {
        Student student = new Student();
        jedis = JedisUtil.getJedis();
        try {
            student.setStu_id(id);
            student.setStu_name(jedis.hget(id, "stu_name"));
            student.setStu_birthday(sp.parse(jedis.hget(id, "stu_birthday")));
            student.setDescription(jedis.hget(id, "decription"));
            student.setAvgscore(Integer.parseInt(jedis.hget(id, "avgscore")));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        JedisUtil.releaseReourse(jedis);
        return student;
    }

    /**
     * 添加学生信息
     *
     * @param stu_name
     * @param stu_birthday
     * @param description
     * @param avgscore
     * @throws ParseException
     */
    @Override
    public void insertStu(String stu_name, String stu_birthday, String description, String avgscore) throws ParseException {
        //获取jedis资源
        jedis = JedisUtil.getJedis();
        //创建一个装属性的map
        Map<String, String> stumap = new HashMap<>();
        //生成uuid
        String uuid = String.valueOf(UUID.randomUUID());
        //将属性放入map中
        stumap.put("stu_id", uuid);
        stumap.put("stu_name", stu_name);
        stumap.put("stu_birthday", stu_birthday);
        stumap.put("decription", description);
        stumap.put("avgscore", avgscore);
        //将信息存入hash
        jedis.hmset(uuid, stumap);
        //将uuid，score存入用来分页排序
        jedis.zadd(STU_SORT, Long.valueOf(avgscore), uuid);
        JedisUtil.releaseReourse(jedis);
    }

    /**
     * 根据id修改学生信息
     *
     * @param stu_id
     * @param stu_name
     * @param stu_birthday
     * @param description
     * @param avgscore
     * @return
     */
    @Override
    public String updateStuInfo(String stu_id, String stu_name, String stu_birthday, String description, String avgscore) {
        //是否修改完成
        String flag = null;
        //获取jedis资源
        jedis = JedisUtil.getJedis();
        //创建一个装属性的map
        Map<String, String> stumap = new HashMap<>();
        //将属性放入map中
        stumap.put("stu_id", stu_id);
        stumap.put("stu_name", stu_name);
        stumap.put("stu_birthday", stu_birthday);
        stumap.put("decription", description);
        stumap.put("avgscore", avgscore);
        //将信息存入hash
        String hmset = jedis.hmset(stu_id, stumap);
        //将uuid，score存入用来分页排序
        Long zadd = jedis.zadd(STU_SORT, Long.valueOf(avgscore), stu_id);
        JedisUtil.releaseReourse(jedis);
        flag = hmset+zadd;
        return flag;
    }

    /**
     * 根据id删除学生信息
     *
     * @param stu_id
     * @return
     */
    public Long delStuInfo(String stu_id) {
        //获取jedis实例
        jedis = JedisUtil.getJedis();
        //删除学生信息
        Long hdel = jedis.hdel(stu_id, "stu_id", "stu_name", "stu_birthday", "decription", "avgscore");
        //删除存放在list的id
        Long lrem = jedis.zrem(STU_SORT,stu_id);
        return hdel + lrem;
    }
}
