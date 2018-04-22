package com.dc.dcrud;

import org.apache.shiro.crypto.hash.Sha1Hash;

/**
 * <p>Descriptions...
 *
 * @author DC
 * @date 2018/4/21.
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(
//        classes=Application.class)                   //加载应用程序上下文
public class EncryptionTest {
    public static void main(String[] args) {
        System.out.println(new Sha1Hash("101010", "DC").toHex());
        System.out.println(new Sha1Hash("DC101010", "").toHex());
        System.out.println(new Sha1Hash("101010", "DC").toHex());
    }
}
