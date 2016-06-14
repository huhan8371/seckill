package org.seckill.dao.cache;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dao.SeckillDao;
import org.seckill.entity.Seckill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class RedisDaoTest {

    private long id = 1001;

    private Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private RedisDao redisDao;

    @Autowired
    private SeckillDao seckillDao;

    @Test
    public void testSeckill() throws Exception {
        //get and put
        Seckill seckill = redisDao.getSeckill(id);
        if (seckill == null) {
            seckill = seckillDao.queryById(id);
            assertNotNull(seckill);
            if (seckill != null) {
                String result = redisDao.putSeckill(seckill);
                assertEquals("OK",result);
                logger.info(result);
                seckill = redisDao.getSeckill(id);
                assertNotNull(seckill);
                logger.info(seckill);
            }
        }
    }


}