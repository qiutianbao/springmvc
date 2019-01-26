package com.bigdata.web;

import com.bigdata.exception.BDRuntimeException;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Random;

/**
 * 登录页面
 * 
 */
@Controller
@RequestMapping("/random")
public class RandomController {

    @RequestMapping(value = "/getRandomCode")
    public void getRandomCode(Model model,HttpServletRequest request,HttpServletResponse response,HttpSession session) throws BDRuntimeException {
        try {
            int width = 60, height = 20;
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

            // 获取图形上下文

            Graphics g = image.getGraphics();

            // 设定背景色

            g.setColor(Color.white);
            g.fillRect(0, 0, width, height);

            //画边框

            g.setColor(Color.black);
            g.drawRect(0, 0, width - 1, height - 1);

            // 取随机产生的认证码(4位数字)
            String rand = request.getParameter("rand");
            rand = rand.substring(0, rand.indexOf("."));
            switch (rand.length()) {
                case 1:
                    rand = "000" + rand;
                    break;
                case 2:
                    rand = "00" + rand;
                    break;
                case 3:
                    rand = "0" + rand;
                    break;
                default:
                    rand = rand.substring(0, 4);
                    break;
            }

            // 将认证码存入SESSION
            request.getSession().setAttribute("rand", rand);

            // 将认证码显示到图象中
            g.setColor(Color.black);
            g.setFont(new Font("Times New Roman", Font.PLAIN, 18));
            g.drawString(rand, 10, 15);

            // 随机产生88个干扰点，使图象中的认证码不易被其它程序探测到

            Random random = new Random();
            for (int i = 0; i < 88; i++) {
                int x = random.nextInt(width);
                int y = random.nextInt(height);
                g.drawLine(x, y, x, y);
            }

            // 图象生效
            g.dispose();

            response.setHeader("Cache-Control", "no-store");
            response.setDateHeader("Expires", 0);
            response.setContentType("image/jpeg");

            ServletOutputStream sos = response.getOutputStream();
            BufferedOutputStream gif = new BufferedOutputStream(sos);
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(gif);
            encoder.encode(image);
            sos.flush();
            gif.close();
            sos.close();
        }
        catch (Exception e) {

        }
    }

}
