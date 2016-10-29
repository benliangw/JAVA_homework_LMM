//文本框宽度不会调
//文本框位置不会调
//不会循环跳转，比如点击返回回来修改点
//在输入新的一条线段的时候，不会让他在一个窗口里实现
//ansx,ansy不能传值
//final version

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class MyLine
{
    public static int tag = 0;
    static class Point
    {
        double x, y;
        Point(double a, double b)
        {
            x = a;
            y = b;
        }
    }
    Point e1;
    Point e2;
    MyLine(Point a, Point b)
    {
        e1 = a;
        e2 = b;
    }
    MyLine()
    {
        ;
    }
    double x1, x2, x3, x4, y1, y2, y3, y4;
    public static double ansx, ansy;
    JFrame jf = new JFrame("第一次Java作业");
    private ButtonPanel choose;
    private JTextField namex1 = new JTextField(10);
    private JTextField namey1 = new JTextField(10);
    private JTextField namex2 = new JTextField(10);
    private JTextField namey2 = new JTextField(10);
    private JTextField seg = new JTextField();
    private JTextField seg2 = new JTextField();
    private JTextField ans2 = new JTextField();
    private JTextField seg3 = new JTextField();
    private JLabel labx1 = new JLabel("input x1");
    private JLabel labx2 = new JLabel("input x2");
    private JLabel laby1 = new JLabel("input y1");
    private JLabel laby2 = new JLabel("input y2");
    private JButton ok = new JButton("确认");
    private JButton ok2 = new JButton("确认");
    private JFrame frame = new JFrame();
    private JPanel top;
    private JPanel middle;
    private JPanel bottom;
    private JPanel newseg;
    private JPanel newpoint;
    private JPanel ansseg;
    public void init()
    {
        top = new JPanel();
        middle = new JPanel();
        bottom = new JPanel();
        newseg = new JPanel();
        newpoint = new JPanel();
        ansseg = new JPanel();
        middle.setVisible(false);
        newseg.setVisible(false);
        newpoint.setVisible(false);
        ansseg.setVisible(false);
        middle.setBorder(new TitledBorder(new EtchedBorder(), "您输入的线段为" , TitledBorder.CENTER , TitledBorder.TOP, new Font("StSong", Font.BOLD, 18), Color.BLUE));
        middle.setLayout(new GridLayout(4,4));
        newseg.setBorder(new TitledBorder(new EtchedBorder(), "您输入的新的线段为" , TitledBorder.CENTER , TitledBorder.TOP, new Font("StSong", Font.BOLD, 18), Color.BLUE));
        newseg.setLayout(new GridLayout(4,4));
        newpoint.setBorder(new TitledBorder(new EtchedBorder(), "您输入的新的点为" , TitledBorder.CENTER , TitledBorder.TOP, new Font("StSong", Font.BOLD, 18), Color.BLUE));
        newpoint.setLayout(new GridLayout(4,4));
        top.setBorder(new TitledBorder(new EtchedBorder(), "请输入两点坐标" , TitledBorder.CENTER , TitledBorder.TOP, new Font("StSong", Font.BOLD, 18), Color.BLUE));
        top.setLayout(new GridLayout(4,4));
        ansseg.setBorder(new TitledBorder(new EtchedBorder(), "结论" , TitledBorder.CENTER , TitledBorder.TOP, new Font("StSong", Font.BOLD, 18), Color.BLUE));
        ansseg.setLayout(new GridLayout(4,4));
        JPanel allx1 = new JPanel(new BorderLayout());
        allx1.add(labx1, BorderLayout.WEST);
        allx1.add(namex1, BorderLayout.CENTER);
        JPanel allx2 = new JPanel(new BorderLayout());
        allx2.add(labx2, BorderLayout.WEST);
        allx2.add(namex2, BorderLayout.CENTER);
        JPanel ally1 = new JPanel(new BorderLayout());
        ally1.add(laby1, BorderLayout.WEST);
        ally1.add(namey1, BorderLayout.CENTER);
        JPanel ally2 = new JPanel(new BorderLayout());
        ally2.add(laby2, BorderLayout.WEST);
        ally2.add(namey2, BorderLayout.CENTER);
        ok.addActionListener(new ShowAction());
        top.add(allx1);
        top.add(ally1);
        top.add(allx2);
        top.add(ally2);
        top.add(ok);
        middle.add(seg);
        newseg.add(seg2);
        ansseg.add(ans2);
        newpoint.add(seg3);
        seg.setEditable(false);
        seg2.setEditable(false);
        ans2.setEditable(false);
        seg3.setEditable(false);
        choose = new ButtonPanel(null,new String[]{"检查线段是否位于第一象限", "求线段的长度", "判断两条线段是否相交", "求一点到该线段（延长线）的距离" });
        ok2.addActionListener(new ShowAction());
        bottom.add(choose);
        bottom.add(ok2);
        bottom.setBorder(new TitledBorder(new EtchedBorder(), "请选择要进行的操作" , TitledBorder.CENTER ,TitledBorder.TOP, new Font("StSong", Font.BOLD, 18), Color.BLUE));
        bottom.setLayout(new GridLayout(1, 4));
        Box box = new Box(BoxLayout.Y_AXIS);
        bottom.setVisible(false);
        box.add(top);
        box.add(middle);
        box.add(bottom);
        box.add(newseg);
        box.add(newpoint);
        box.add(ansseg);
        jf.add(box);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.pack();
        jf.setVisible(true);
    }
    private class ShowAction implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            if(e.getSource()==ok)
            {
                x1 = Double.parseDouble(namex1.getText());
                x2 = Double.parseDouble(namex2.getText());
                y1 = Double.parseDouble(namey1.getText());
                y2 = Double.parseDouble(namey2.getText());
                if (x1 == x2 && y1 == y2)
                {
                    JOptionPane.showMessageDialog(frame, "输入错误，请重新输入");
                    jf.pack();
                }
                else
                {
                    middle.setVisible(true);
                    bottom.setVisible(true);
                    //让窗口自动调整大小
                    jf.pack();
                    seg.setText("(" + x1 + "," + y1 + ")" + "         " + "(" + x2 + "," + y2 + ")");
                    seg.setHorizontalAlignment(SwingConstants.CENTER);
                    JOptionPane.showMessageDialog(frame, "设置成功");
                }
            }
            else if(e.getSource() == ok2)
            {
                Point point1 = new Point(x1, y1);
                Point point2 = new Point(x2, y2);
                Point point3;
                Point point4;
                MyLine line2;
                MyLine line1 = new MyLine(point1, point2);
                if (choose.getSelection() == "检查线段是否位于第一象限")
                {
                    newseg.setVisible(false);
                    newpoint.setVisible(false);
                    ansseg.setVisible(false);
                    jf.pack();
                    if(line1.check())
                    {
                        JOptionPane.showMessageDialog(frame, "线段位于第一象限");
                        ans2.setText("该线段位于第一象限");
                        ans2.setHorizontalAlignment(SwingConstants.CENTER);
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(frame, "线段不位于第一象限");
                        ans2.setText("该线段不位于第一象限");
                        ans2.setHorizontalAlignment(SwingConstants.CENTER);
                    }
                    ansseg.setVisible(true);
                    jf.pack();
                }
                if (choose.getSelection() == "求线段的长度")
                {
                    newseg.setVisible(false);
                    newpoint.setVisible(false);
                    ansseg.setVisible(false);
                    jf.pack();
                    double tmp = line1.length();
                    JOptionPane.showMessageDialog(frame, tmp);
                    ans2.setText("线段长度为: "+tmp);
                    ans2.setHorizontalAlignment(SwingConstants.CENTER);
                    ansseg.setVisible(true);
                    jf.pack();
                }
                if (choose.getSelection() == "判断两条线段是否相交")
                {
                    tag = 0;
                    newpoint.setVisible(false);
                    ansseg.setVisible(false);
                    jf.pack();
                    x3 = Double.parseDouble(JOptionPane.showInputDialog("请输入第三个点的横坐标"));
                    y3 = Double.parseDouble(JOptionPane.showInputDialog("请输入第三个点的纵坐标"));
                    x4 = Double.parseDouble(JOptionPane.showInputDialog("请输入第四个点的横坐标"));
                    y4 = Double.parseDouble(JOptionPane.showInputDialog("请输入第四个点的纵坐标"));
                    if (x3 == x4 && y3 == y4)
                    {
                        JOptionPane.showMessageDialog(frame, "输入错误，请重新输入");
                        jf.pack();
                    }
                    else
                    {
                        point3 = new Point(x3, y3);
                        point4 = new Point(x4, y4);
                        line2 = new MyLine(point3, point4);
                        newseg.setVisible(true);
                        jf.pack();
                        seg2.setText("(" + x3 + "," + y3 + ")" + "         " + "(" + x4 + "," + y4 + ")");
                        seg2.setHorizontalAlignment(SwingConstants.CENTER);
                        if (line1.cross(line2)) {
                            JOptionPane.showMessageDialog(frame, "两条线段相交，交点为(" + ansx + "," + ansy + ")");//，交点为("+ansx+","+ansy+")
                            ans2.setText("交点坐标为：(" + ansx + "," + ansy + ")");
                            ans2.setHorizontalAlignment(SwingConstants.CENTER);
                        } else if (tag == 1) {
                            JOptionPane.showMessageDialog(frame, "两条线段部分重合");
                            ans2.setText("两条线段部分重合");
                            ans2.setHorizontalAlignment(SwingConstants.CENTER);
                        } else if (tag == 2) {
                            JOptionPane.showMessageDialog(frame, "两条线段重合");
                            ans2.setText("两条线段重合");
                            ans2.setHorizontalAlignment(SwingConstants.CENTER);
                        } else {
                            JOptionPane.showMessageDialog(frame, "两条线段不相交");
                            ans2.setText("两条线段不相交");
                            ans2.setHorizontalAlignment(SwingConstants.CENTER);
                        }
                        ansseg.setVisible(true);
                        jf.pack();
                    }
                }
                if (choose.getSelection() == "求一点到该线段（延长线）的距离")
                {
                    newseg.setVisible(false);
                    ansseg.setVisible(false);
                    jf.pack();
                    x3 = Double.parseDouble(JOptionPane.showInputDialog("请输入第三个点的横坐标"));
                    y3 = Double.parseDouble(JOptionPane.showInputDialog("请输入第三个点的纵坐标"));
                    newpoint.setVisible(true);
                    jf.pack();
                    seg3.setText("("+x3+","+y3+")");
                    seg3.setHorizontalAlignment(SwingConstants.CENTER);
                    double ans = line1.distance(x3, y3);
                    JOptionPane.showMessageDialog(frame, "该点到该线段（延长线）的距离为"+ans);
                    ans2.setText("该点到该线段（延长线）的距离为"+ans);
                    ans2.setHorizontalAlignment(SwingConstants.CENTER);
                    ansseg.setVisible(true);
                    jf.pack();
                }
            }
            else
            {
                JOptionPane.showMessageDialog(frame, "something wrong happened...");
            }
        }
    }
    public boolean check ()
    {
        if (e1.x > 0 && e1.y > 0 && e2.x > 0 && e2.y > 0)
            return true;
        else if ((e1.x > 0 && e1.y == 0 && e2.x > 0 && e2.y > 0) || (e1.x > 0 && e1.y > 0 && e2.x > 0 && e2.y == 0))
            return true;
        else if ((e1.x == 0 && e1.y > 0 && e2.x > 0 && e2.y > 0) || (e1.x > 0 && e1.y > 0 && e2.x == 0 && e2.y > 0))
            return true;
        else if ((e1.x == 0 && e1.y > 0 && e2.x > 0 && e2.y == 0) || (e1.x > 0 && e1.y == 0 && e2.x == 0 && e2.y > 0))
            return true;
        else
            return false;
    }
    public double length()
    {
        double ans;
        ans = Math.sqrt((e1.x - e2.x)*(e1.x - e2.x) + (e1.y - e2.y) * (e1.y - e2.y));
        return ans;
    }
    public double determinant(double v1, double v2, double v3, double v4)  // 行列式
    {
        return (v1*v3-v2*v4);
    }
    public boolean cross(MyLine line)
    {
        if (this.e1.x == line.e1.x && this.e1.y == line.e1.y && this.e2.x == line.e2.x && this.e2.y == line.e2.y)
        {
            tag = 2;
            return false;
        }
        double delta = determinant(this.e2.x-this.e1.x, line.e1.x-line.e2.x, line.e1.y - line.e2.y, this.e2.y-this.e1.y);
        if (delta <= (1e-6) && delta >= -(1e-6))
        {
            double tmp = determinant(line.e1.y - this.e1.y, this.e2.y - line.e1.y, this.e2.x - line.e1.x, line.e1.x - this.e1.x);
            if ((tmp <= (1e-6) && tmp >= -(1e-6)))
            {
                if ((line.e1.x > this.e1.x && line.e1.x < this.e2.x) || (line.e2.x > this.e1.x && line.e2.x < this.e2.x))
                    tag = 1;
            }
            return false;
        }
        double lamda = determinant(line.e1.x - this.e1.x, line.e1.x - line.e2.x, line.e1.y - line.e2.y, line.e1.y - this.e1.y) / delta;
        if (lamda>1 || lamda<0)
        {
            return false;
        }
        double miu = determinant(this.e2.x - this.e1.x, line.e1.x - this.e1.x, line.e1.y - this.e1.y, this.e2.y - this.e1.y) / delta;
        if (miu>1 || miu<0)
        {
            return false;
        }
        ansx = lamda*(this.e2.x - this.e1.x) + this.e1.x;
        ansy = lamda*(this.e2.y - this.e1.y) + this.e1.y;
        return true;
    }
    public double distance(double x, double y)
    {
        double d=(Math.abs((this.e2.y-this.e1.y)*x+(this.e1.x-this.e2.x)*y+((this.e2.x*this.e1.y)-(this.e1.x*this.e2.y))))/(Math.sqrt(Math.pow(this.e2.y-this.e1.y,2)+Math.pow(this.e1.x-this.e2.x,2)));
        return d;
    }
    public static void main(String[] args)
    {
        new MyLine().init();
    }
}

// 定义一个JPanel类扩展类，该类的对象包含多个纵向排列的JRadioButton控件,且Panel扩展类可以指定一个字符串作为TitledBorder
class ButtonPanel extends JPanel
{
    private ButtonGroup group;
    public ButtonPanel(String title, String[] options)
    {
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), title));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        group = new ButtonGroup();
        for (int i = 0; options!= null && i < options.length; i++)
        {
            JRadioButton b = new JRadioButton(options[i]);
            b.setActionCommand(options[i]);
            add(b);
            group.add(b);
            b.setSelected(i == 0);
        }
    }
    // 定义一个方法，用于返回用户选择的选项
    public String getSelection()
    {
        return group.getSelection().getActionCommand();
    }
}