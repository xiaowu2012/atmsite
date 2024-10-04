package com.xiaowudr.atmsite;

import javax.swing.*;

public class TestHello {
    public static void main(String[] args) {
        JOptionPane optionPane = new JOptionPane("这是一个警告对话框", JOptionPane.WARNING_MESSAGE);
        javax.swing.JDialog dialog = optionPane.createDialog("警告");
        dialog.setModal(false); // 将对话框设置为非模态，允许程序继续执行
        dialog.setVisible(true); // 显示对话框
        System.out.println("1234");
    }
}
