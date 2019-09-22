package cc.mrbird.febs.test;

import cc.mrbird.febs.common.utils.MD5Util;
import org.apache.commons.lang3.ArrayUtils;

/**
 * @author https://github.com/mgzu
 * @date 2019-09-01
 */
public class PasswordEncryptTest {

    public static void main(String[] args) {
        String username = "mrbird";
        String password = "123456";
        System.out.println(MD5Util.encrypt(username, password));
    }
}
