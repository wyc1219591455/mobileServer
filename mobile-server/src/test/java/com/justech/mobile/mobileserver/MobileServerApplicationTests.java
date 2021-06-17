package com.justech.mobile.mobileserver;

import com.justech.mobile.mobileserver.util.AesUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MobileServerApplicationTests {

    @Autowired
    AesUtil aesUtil;

    @Test
    public void contextLoads() throws Exception {

        // String test = "a79ffO2qBH7eJxJGb87du+DnbLv4EPJLsOyx3nO+b+/wGqyzS20ykDHBoOQBw0dVtvUUq0eWCT8xHC/50ir0W44b4oxeO5FigP/jbaIvVemJWxjjw1VSLJaBLj4hhWQgr9N7BeJK1EZY/r7HvKASjrN1532sNHGMkt/u9QHPc767WA";
        // String str = aesUtil.decrypt("Y4Fg3BiSgu/6GKBm3G2pHQ%3D%3D&".replace("\r\n", ""));
        /*System.out.println(test.length());
        String str = aesUtil.decrypt(test.replace("\r\n", ""));
        System.out.println(str);*/
        String $ip = "";
        String $host = "bbs.justech.com";
        Integer $port = 2888;
        String $path = "/uc_server/index.php?__times__=1";
        String $post = "m=user&a=synlogin&inajax=2&release=20170101&input=a1abKH8z0tzM%2BWWmoQEAj7292WxoIfcaPDgIOwzUniCOCuR06vTUM%2BoiE6MV054GYn6vK8ZVQ25UH2Vovv%2Fh7ApyIrZF43Lfrweuo2giqVYdElh%2BRXyeA03fwrU&appid=2";


        StringBuffer $out = new StringBuffer();
        $out.append("POST ").append($path).append(" HTTP/1.0\r\n");
        $out.append("Accept: */*\r\n");
        $out.append("Accept-Language: zh-cn\r\n");
        $out.append("Content-Type: application/x-www-form-urlencoded\r\n");
        $out.append("User-Agent: \r\n");
        $out.append("Host: ").append($host).append("\r\n");
        $out.append("Content-Length: ").append($post.length()).append("\r\n");
        $out.append("Connection: Close\r\n");
        $out.append("Cache-Control: no-cache\r\n");
        $out.append("Cookie: \r\n\r\n");
        $out.append($post);


        Socket $fp = new Socket($ip != null && $ip.length() > 10 ? $ip : $host, $port);

        if (!$fp.isConnected()) {
            //return "";//note $errstr : $errno \r\n
        } else {

            OutputStream os = $fp.getOutputStream();
            os.write($out.toString().getBytes());

            InputStream ins = $fp.getInputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(ins));
            while (true) {
                String $header = reader.readLine();
                if ($header == null || $header.equals("") || $header == "\r\n" || $header == "\n") {
                    break;
                }
            }

            String $return = "";

            while (true) {
                String $data = reader.readLine();
                if ($data == null || "".equals($data)) {
                    break;
                } else {
                    $return += $data;
                }
            }
            System.out.println($return);

            $fp.close();
        }

       // return "ok";
    }
}
