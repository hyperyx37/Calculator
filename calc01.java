import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class calc01 extends Frame implements ActionListener {
    TextField calcTextField;
    Button[] buttons = new Button[18];
    char[] buttonText={'9','8','7','X','4','5',
            '6','/','1','2','3','-','0','C','=','+','I','.',};
    double ans=0, nk=0;
    char opr=' ', key=' ';
    int state = 0;
    int i=0;
    double[] n = new double[20];
    public calc01() {
        setLayout(new FlowLayout());
        calcTextField = new TextField(5);
        add(calcTextField);
        for (int i=0; i<18; i++) {
            buttons[i] = new Button (""+buttonText[i]);
            add(buttons[i]);
            buttons[i].addActionListener(this);
        }
    }
    public double apply (double ans, char opr, double nk)  {
        switch (opr) {
            case '+': return ans += nk;
            case '-': return ans -= nk;
            case 'X': return ans *= nk;
            case '/': return ans /= nk;
            case '.': return ans += nk/10;
            case '=': return ans = ans;
        } return -1;
    }
    public void actionPerformed(ActionEvent event)  {
        key = event.getActionCommand().charAt(0);
        if (state == 0) {
            if ((key >= '0') && (key <= '9')) {
                nk = (key - '0');
                calcTextField.setText("" + nk);
                if (opr == '.') {
                    calcTextField.setText(Double.toString(apply(ans, opr, nk)));
                }
            } else if (key == 'C') {
                ans = 0;
                nk = 0;
                opr = ' ';
                key = ' ';
                calcTextField.setText("" + ans);
            } else if (key == 'I') {
                state = 1;
                if (opr == ' ') n[0] = nk;
                else n[0] = ans;
                i =1;
            }else {
                if (opr == ' ') {
                    ans = nk;
                } else {
                    ans = apply(ans, opr, nk);
                }
                opr = key;
                calcTextField.setText("" + ans);

            }
        } else {
            if((key>='0')&&(key<='9')) {
                n[i]=(key-'0');
                calcTextField.setText(""+n[i]);
                i++;
            } else if (key=='I') {
                state = 0;
                nk = ans;
                opr = ' ';
                key = ' ';
            } else if (key == 'C') {
                ans=0;
                nk=0;
                opr=' ';
                key=' ';
                i=0;
                n = null;
                calcTextField.setText(""+ans);
            }else {
                opr = key;
                n[i-2] = apply(n[i-2],opr,n[i-1]);
                calcTextField.setText(""+n[i-2]);
                ans = n[i-2];
                i--;
            }

        }
        System.out.println(state+"");

    }
    public static void main(String[] s){ new calc01().setVisible(true); }
}