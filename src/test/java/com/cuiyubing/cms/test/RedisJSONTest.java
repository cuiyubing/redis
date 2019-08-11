package com.cuiyubing.cms.test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cuiyubing.cms.domain.Employee;
import com.cuiyubing.common.utils.RandomUtil;
import com.cuiyubing.common.utils.StringUtil;
/**
 * 
 * @ClassName: RedisJSONTest 
 * @Description: TODO
 * @author:cyb 
 * @date: 2019年8月10日 上午9:27:12
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring-beans2.xml")
public class RedisJSONTest {

		@Resource
		private RedisTemplate<String, Serializable> redisTemplate;
		
		@Test
		public void test_insert() {
			List<Employee> employees = new ArrayList<Employee>();
			//随机生成1万个数据
			for (int i = 0; i < 10000; i++) {
				employees.add(new Employee(i,StringUtil.generateChineseName()
						+StringUtil.randomChineseString(2),"13"+RandomUtil.randomString(9)));
			}
			
			long startTimes = System.currentTimeMillis();
			for (Employee employee : employees) {
				redisTemplate.opsForValue().set("u_"+employee.getId(), employee);
			}
			long endTimes = System.currentTimeMillis();
			
			System.out.println("采用json序列化存储时间:"+(endTimes-startTimes));
		}
}
