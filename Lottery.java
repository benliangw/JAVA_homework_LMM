//final version
import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
public class Lottery
{
    Lottery()
    {
        ;
    }
    void play()
    {
        ;
    }
    static int count = 0;
    static int count2 = 0;
    static int[] a = new int[22];
    static int[] ans_a = new int[5]; //存放用户输入的数字
    static int[] b = new int[10];
    static int[] ans_b = new int[6];
    static int special_a;
    JFrame jf = new JFrame();
    JFrame frame = new JFrame();
    JTextField seg = new JTextField();
    Lottery test;
    public enum Operator
    {
        n0, n1, n2, n3, n4, n5, n6, n7, n8, n9, n10, n11, n12, n13, n14, n15, n16, n17, n18, n19, n20, n21;
        public static final String[] labs = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21"};
        public static final String[] labs2 = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    }
    private void init()
    {
        jf.setTitle("第二次Java作业");//标题
        jf.setResizable(false);//不可改变大小
        jf.setLocationRelativeTo(null);//居中
        jf.setSize(500, 400);//大小
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//默认关闭方式
        JPanel choose1 = new JPanel();
        choose1.setBorder(new TitledBorder(new EtchedBorder(), "请选择5个数字", TitledBorder.CENTER, TitledBorder.TOP, new Font("StSong", Font.BOLD, 18), Color.BLUE));
        choose1.setLayout(new GridLayout(3, 9));
        JPanel choose2 = new JPanel();
        choose2.setBorder(new TitledBorder(new EtchedBorder(), "请选择6个数字并输入一位数字", TitledBorder.CENTER, TitledBorder.TOP, new Font("StSong", Font.BOLD, 18), Color.BLUE));
        choose2.setLayout(new GridLayout(3, 9));
        for (int i = 0; i < 21; i++)
        {
            JToggleButton[] toggle = new JToggleButton[21];
            toggle[i] = new JToggleButton(Operator.labs[i]);
            toggle[i].addActionListener(new ShowAction());
            choose1.add(toggle[i]);
        }
        for (int i = 0; i < 10; i++)
        {
            JToggleButton[] toggle2 = new JToggleButton[10];
            toggle2[i] = new JToggleButton(Operator.labs2[i]);
            toggle2[i].addActionListener(new ShowAction2());
            choose2.add(toggle2[i]);
        }
        JButton ok = new JButton("试试吧");
        ok.addActionListener(new ShowAction());//对于同一种试试吧，采用两个监听渠道
        JButton ok2 = new JButton("试试吧");
        ok2.addActionListener(new ShowAction2());
        Box box = new Box(BoxLayout.Y_AXIS);
        box.add(choose1);
        box.add(ok);
        box.add(choose2);
        box.add(seg);
        box.add(ok2, BorderLayout.SOUTH);
        jf.add(box);
        jf.setVisible(true);
    }

    private class ShowAction implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            if (e.getActionCommand().equals( "试试吧"))
            {
                if (count != 5)
                    JOptionPane.showMessageDialog(frame, "选择的数据个数不正确");
                else//开始玩
                {
                    int j = 0;
                    for (int i = 0; i <= 21; ++i)
                    {
                        if (a[i] == 1)
                        {
                            ans_a[j] = i;
                            j++;
                        }
                    }
                    test = new twenty_one_choose_five();
                    test.play();
                }
            }
            else
            {
                if (((JToggleButton) e.getSource()).getModel().isSelected())
                {
                    count++;//计算点了几个
                    a[Integer.parseInt(e.getActionCommand())] = 1;
                }
                else
                {
                    count--;
                    a[Integer.parseInt(e.getActionCommand())] = 0;
                }
                int tem = Integer.parseInt(e.getActionCommand());
            }
        }
    }

    private class ShowAction2 implements ActionListener//第二种监听
    {
        public void actionPerformed(ActionEvent e)
        {
            if (e.getActionCommand().equals("试试吧"))
            {
                special_a = Integer.parseInt(seg.getText());
                if (count2 != 6)
                    JOptionPane.showMessageDialog(frame, "选择的数据个数不正确");
                else if (special_a > 9 || special_a < 0)
                    JOptionPane.showMessageDialog(frame, "特殊数字输入越界");
                else//开始玩
                {
                    int j = 0;
                    for (int i = 0; i <= 9; ++i)
                    {
                        if (b[i] == 1)
                        {
                            ans_b[j] = i;
                            j++;
                        }
                    }
                    test = new six_and_special();
                    test.play();
                }
            }
            else
            {
                if (((JToggleButton) e.getSource()).getModel().isSelected())
                {
                    count2++;
                    b[Integer.parseInt(e.getActionCommand())] = 1;
                }
                else
                {
                    b[Integer.parseInt(e.getActionCommand())] = 0;
                    --count2;
                }
                int tem = Integer.parseInt(e.getActionCommand());
            }
        }
    }

    public static void main(String argc[])
    {
        new Lottery().init();
    }
}
//二十一选五
class twenty_one_choose_five extends Lottery
{
    void play()
    {
        int[] rand_a = new int[5];
        Random rand = new Random();
        rand_a[0] = rand.nextInt(21) + 1;
        for (int i = 1; i < 5; i++)
        {
            int t = rand.nextInt(21) + 1;
            for (int j = 0; j < i; j++)
            {
                if (rand_a[j] == t)
                {
                    j = -1;
                    t = rand.nextInt(21) + 1;
                    continue;
                }
                rand_a[i] = t;
            }
        }
        Arrays.sort(rand_a);
        //判断是否中奖
        int same = 0;
        for (int i = 0; i < 5; ++i)
        {
            for (int j = 0; j < 5; ++j)
            {
                if (ans_a[i] == rand_a[j])
                {
                    ++same;
                    break;
                }
            }
        }
        if (same == 5)
        {
            JOptionPane.showMessageDialog(frame, "一等奖");
            JOptionPane.showMessageDialog(frame, "中奖号码为："+rand_a[0]+" "+rand_a[1]+" "+rand_a[2]+" "+rand_a[3]+" "+rand_a[4]);
        }
        else if (same == 4)
        {
            JOptionPane.showMessageDialog(frame, "二等奖");
            JOptionPane.showMessageDialog(frame, "中奖号码为："+rand_a[0]+" "+rand_a[1]+" "+rand_a[2]+" "+rand_a[3]+" "+rand_a[4]);
        }
        else if (same == 3)
        {
            JOptionPane.showMessageDialog(frame, "三等奖");
            JOptionPane.showMessageDialog(frame, "中奖号码为："+rand_a[0]+" "+rand_a[1]+" "+rand_a[2]+" "+rand_a[3]+" "+rand_a[4]);
        }
        else
        {
            JOptionPane.showMessageDialog(frame, "未中奖");
            JOptionPane.showMessageDialog(frame, "中奖号码为："+rand_a[0]+" "+rand_a[1]+" "+rand_a[2]+" "+rand_a[3]+" "+rand_a[4]);
        }
    }
}
//6+1
class six_and_special extends Lottery
{
    void play()
    {
        int[] rand_b = new int[6];
        int special_b;
        Random rand = new Random();
        special_b = rand.nextInt(10);//特殊数字可以重复
        rand_b[0] = rand.nextInt(10);
        for (int i = 1; i < 6; i++)
        {
            int t = rand.nextInt(10);
            for (int j = 0; j < i; j++)
            {
                if (rand_b[j] == t)
                {
                    j = -1;
                    t = rand.nextInt(10);
                    continue;
                }
                rand_b[i] = t;
            }
        }
        Arrays.sort(rand_b);
        //判断是否中奖
        int same = 0;
        for (int i = 0; i < 6; ++i)
        {
            for (int j = 0; j < 6; ++j)
            {
                if (ans_b[i] == rand_b[j])
                {
                    ++same;
                    break;
                }
            }
        }
        if (same == 6)
        {
            if (special_a == special_b)
            {
                JOptionPane.showMessageDialog(frame, "特等奖");
                JOptionPane.showMessageDialog(frame, "中奖号码为："+rand_b[0]+" "+rand_b[1]+" "+rand_b[2]+" "+rand_b[3]+" "+rand_b[4]+" "+rand_b[5]+" + "+special_b);
            }
            else
            {
                JOptionPane.showMessageDialog(frame, "一等奖");
                JOptionPane.showMessageDialog(frame, "中奖号码为："+rand_b[0]+" "+rand_b[1]+" "+rand_b[2]+" "+rand_b[3]+" "+rand_b[4]+" "+rand_b[5]+" + "+special_b);
            }
        }
        if (same == 5)
        {
            JOptionPane.showMessageDialog(frame, "二等奖");;
            JOptionPane.showMessageDialog(frame, "中奖号码为："+rand_b[0]+" "+rand_b[1]+" "+rand_b[2]+" "+rand_b[3]+" "+rand_b[4]+" "+rand_b[5]+" + "+special_b);
        }
        else if (same == 4)
        {
            JOptionPane.showMessageDialog(frame, "三等奖");
            JOptionPane.showMessageDialog(frame, "中奖号码为："+rand_b[0]+" "+rand_b[1]+" "+rand_b[2]+" "+rand_b[3]+" "+rand_b[4]+" "+rand_b[5]+" + "+special_b);
        }
        else if (same == 3)
        {
            JOptionPane.showMessageDialog(frame, "四等奖");
            JOptionPane.showMessageDialog(frame, "中奖号码为："+rand_b[0]+" "+rand_b[1]+" "+rand_b[2]+" "+rand_b[3]+" "+rand_b[4]+" "+rand_b[5]+" + "+special_b);
        }
        else
        {
            JOptionPane.showMessageDialog(frame, "未中奖");
            JOptionPane.showMessageDialog(frame, "中奖号码为："+rand_b[0]+" "+rand_b[1]+" "+rand_b[2]+" "+rand_b[3]+" "+rand_b[4]+" "+rand_b[5]+" + "+special_b);
        }
    }
}
