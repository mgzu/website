package cc.mrbird.febs.common.configure;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.BlockAttackInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author MrBird
 */
@Configuration
public class MybatisPlusConfigure {
    /**
     * 分页插件
     */
    @Bean
    public MybatisPlusInterceptor paginationInterceptor() {
        MybatisPlusInterceptor paginationInterceptor = new MybatisPlusInterceptor();
        // 攻击 SQL 阻断解析器、加入解析链
        paginationInterceptor.addInnerInterceptor(new BlockAttackInnerInterceptor());
        paginationInterceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        return paginationInterceptor;
    }
}
