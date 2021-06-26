/*
 *    Copyright 2021 [website of copyright mrbird & mgzu]
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package cc.mrbird.febs.common.converter;

import cc.mrbird.febs.common.annotation.Dict;
import cc.mrbird.febs.system.entity.DictDetail;
import cc.mrbird.febs.system.service.IDictService;
import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import lombok.RequiredArgsConstructor;

import java.lang.reflect.Field;

/**
 * @author mgzu
 * @date 2021-04-24
 */
@RequiredArgsConstructor
public class DictConverter implements Converter<Object> {

    private final IDictService dictService;

    @Override
    public Class<Object> supportJavaTypeKey() {
        return Object.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return null;
    }

    @Override
    public Object convertToJavaData(CellData cellData, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        Field field = contentProperty.getField();
        Dict dict = field.getAnnotation(Dict.class);
        String key = dict.value();
        DictDetail dictData = dictService.selectDictByKeyAndLabel(key, (String) cellData.getData());
        return dictData.getValue();
    }

    @Override
    public CellData<Object> convertToExcelData(Object value, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        Field field = contentProperty.getField();
        Dict dict = field.getAnnotation(Dict.class);
        String key = dict.value();
        DictDetail dictData = dictService.selectDictByKeyAndValue(key, (String) value);
        return new CellData<>(dictData.getLabel());
    }
}
