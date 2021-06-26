package cc.mrbird.febs;

import cc.mrbird.febs.common.entity.BaseEntity;
import cc.mrbird.febs.system.entity.DictDetail;
import cc.mrbird.febs.system.mapper.DictDetailMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ClassUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.util.CastUtils;

@Slf4j
@SpringBootTest(classes = FebsApplication.class)
public class FebsApplicationTest {

    @Autowired
    private DictDetailMapper dictDetailMapper;

    @BeforeEach
    public void before() {

    }

    @Test
    public void runTestCase() {
        BaseEntity entity = new DictDetail();
        dictDetailMapper.insert(CastUtils.cast(entity));
    }

}
