package com.msk.bs.utils;

import com.hoperun.core.utils.StringUtil;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

/**
 * Created by ni_shaotang on 2016/10/24.
 */
public class CodeImage {
    private int height = 35;
    private int width = 70;
    private int num = 4;
    private int fontSize = 15;
    private StringBuilder sb = new StringBuilder();
    private String[] fontName = {"宋体", "华文楷体", "黑体", "微软雅黑"};
    Random rd = new Random();

    /**
     * @return 获取当前二维码图片的长度
     */
    public int getWidth() {
        return width;
    }

    /**
     * 设置二维码图片的长度
     *
     * @param width
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * @return 获取当前二维码图片的高度
     */
    public int getHeight() {
        return height;
    }

    /**
     * 设置二维码图片的高度
     *
     * @param height
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * @return 返回二维码字符个数
     */
    public int getNum() {
        return num;
    }

    /**
     * @param num 设置二维码的字符个数.
     */
    public void setNum(int num) {
        this.num = num;
    }

    /**
     * @return 当前二维码字体的大小
     */
    public int getFontSize() {
        return fontSize;
    }

    /**
     * 获取二维码字体大小
     *
     * @param fontSize
     */
    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    /**
     * @return 返回当前生成的二维码的内容
     */
    public String getText() {
        return sb.toString();
    }

    /**
     * @return 返回一个图片缓冲区对象 BufferedImage 包含了绘制完成的图片.
     */
    public BufferedImage getBufferedImage() {
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = (Graphics2D) bi.getGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);
        sb.delete(0, num);
        for (int i = 0; i < num; i++) {
            g.setColor(getColor());
            g.setFont(getFont());
            randomLine(g);
            String s = getStr();
            sb.append(s);
            g.drawString(s, i * (width / num), height / 2 + rd.nextInt(height / 2));
        }
        return bi;
    }

    /**
     * @return 返回一个图片缓冲区对象 BufferedImage 包含了绘制完成的图片.
     */
    public BufferedImage getBufferedImage(String code) {
        if (StringUtil.isNullOrEmpty(code) || code.length() != num) {
            return null;
        }
        String[] ss = code.split("");
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = (Graphics2D) bi.getGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);
        sb.delete(0, num);
        for (int i = 0; i < num; i++) {
            g.setColor(getColor());
            g.setFont(getFont());
            randomLine(g);
            String s = ss[i+1];
            sb.append(s);
            g.drawString(s, i * (width / num), height / 2 + rd.nextInt(height / 2));
        }
        return bi;
    }

    /**
     * @return 一个随机颜色的Color
     */
    public Color getColor() {
        return new Color(rd.nextInt(255), rd.nextInt(255), rd.nextInt(255));
    }

    /**
     * @return 一个随记的数字或者字母
     */
    public String getStr() {
        String s = "23456789abcdefghigkmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String ch = String.valueOf(s.charAt(rd.nextInt(s.length())));
        return ch;
    }

    /**
     * 绘制一条任意的线 画笔对象
     *
     * @param
     */
    public void randomLine(Graphics2D g) {
        int ax = rd.nextInt(width), ay = rd.nextInt(height);
        int bx = rd.nextInt(width), by = rd.nextInt(height);
        g.drawLine(ax, ay, bx, by);
    }

    /**
     * @return 返回一个字体大小和字体随记.
     */
    public Font getFont() {
        return new Font(fontName[rd.nextInt(fontName.length)], rd.nextInt(4), rd.nextInt(10) + fontSize);
    }
}

class OutImage {
    public static void main(String[] args) {
        CodeImage c = new CodeImage();

        BufferedImage bi = c.getBufferedImage();

        System.out.println(c.getText());
        try {

            ImageIO.write(bi, "JPG", new FileOutputStream("E:/abc.jpg"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
