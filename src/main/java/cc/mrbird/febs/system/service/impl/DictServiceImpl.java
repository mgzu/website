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

package cc.mrbird.febs.system.service.impl;

import cc.mrbird.febs.system.entity.DictDetail;
import cc.mrbird.febs.system.mapper.DictBaseMapper;
import cc.mrbird.febs.system.mapper.DictDetailMapper;
import cc.mrbird.febs.system.service.IDictService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author mgzu
 * @since 2021-06-25
 */
@RequiredArgsConstructor
@Service
public class DictServiceImpl implements IDictService {

    @Autowired
    private DictBaseMapper dictBaseMapper;
    @Autowired
    private DictDetailMapper dictDetailMapper;

    @Override
    public DictDetail selectDictByKeyAndLabel(String key, String label) {
        return dictDetailMapper.selectDictByKeyAndLabel(key, label);
    }

    @Override
    public DictDetail selectDictByKeyAndValue(String key, String value) {
        return dictDetailMapper.selectDictByKeyAndValue(key, value);
    }
}
