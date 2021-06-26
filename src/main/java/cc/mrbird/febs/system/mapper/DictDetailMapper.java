package cc.mrbird.febs.system.mapper;

import cc.mrbird.febs.system.entity.DictDetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 字典详情表 Mapper 接口
 * </p>
 *
 * @author mgzu
 * @since 2021-06-26
 */
public interface DictDetailMapper extends BaseMapper<DictDetail> {

    @Select("SELECT * from t_dict_detail t " +
            "left join t_dict_base t1 on t1.dict_id = t.PARENT_ID " +
            "where t1.`KEY` = #{key} and t.LABEL = #{label}")
    DictDetail selectDictByKeyAndLabel(String key, String label);

    @Select("SELECT * from t_dict_detail t " +
            "left join t_dict_base t1 on t1.dict_id = t.PARENT_ID " +
            "where t1.`KEY` = #{key} and t.VALUE = #{value}")
    DictDetail selectDictByKeyAndValue(String key, String value);
}
