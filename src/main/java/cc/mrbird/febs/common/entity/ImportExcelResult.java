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

package cc.mrbird.febs.common.entity;

import cc.mrbird.febs.common.poi.ExcelFailureMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author mgzu
 * @since 2021-06-26
 */
@Setter
@Getter
public class ImportExcelResult {
    private boolean validFailure;
    private boolean rollback;
    private List<ExcelFailureMessage> errorMessages;

    public ImportExcelResult(boolean validFailure, boolean rollback, List<ExcelFailureMessage> errorMessages) {
        this.validFailure = validFailure;
        this.rollback = rollback;
        this.errorMessages = errorMessages;
    }
}
