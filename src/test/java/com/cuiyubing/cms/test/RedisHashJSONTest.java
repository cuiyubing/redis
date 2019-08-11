package com.cuiyubing.cms.test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cuiyubing.cms.domain.Employee;
import com.cuiyubing.common.utils.RandomUtil;
import com.cuiyubing.common.utils.StreamUtil;
import com.cuiyubing.common.utils.StringUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring-beans2.xml")
public class RedisHashJSONTest {
		@Resource
		private RedisTemplate<String, Serializable> redisTemplate;
		
		@Test
		public void test_insert() {
			Map<String,Employee> employees = new HashMap<String,Employee>();
			//随机生成1万个数据
			for (int i = 0; i < 100000; i++) {
				employees.put("e_"+i,new Employee(i,StringUtil.generateChineseName()
						+StringUtil.randomChineseString(2),"13"+RandomUtil.randomString(9)));
			}
			
			long startTimes = System.currentTimeMillis();
				redisTemplate.opsForHash().putAll("employee_json_keys", employees);
			long endTimes = System.currentTimeMillis();
			
			System.out.println("采用hash,json序列化存储时间:"+(endTimes-startTimes));
		}
		

}
