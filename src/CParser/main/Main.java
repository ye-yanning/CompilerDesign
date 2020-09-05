package CParser.main;
import java.io.IOException;
import CParser.inter.Node;
import CParser.lexer.Lexer;
import CParser.parser.Parser;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import CParser.main.Main;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;

import java.io.*;
public class Main {
    JFrame frame = new JFrame("C简单编译器");
    JPanel panel = new JPanel();
    JPanel TextAreaPanel = new JPanel();

    JLabel titleLabel = new JLabel("C简单编译器");
    JTextArea input = new JTextArea();
    JTextArea output = new JTextArea();
    JTextArea infoput = new JTextArea();
    JTable ValueTable = new JTable();
    JButton clearButton = new JButton();
    JButton compileButton = new JButton();
    JButton displayButton = new JButton();
    JComboBox selectBox = new JComboBox();


    int width=1500;

    private void prepareGUI() throws IOException{

        frame.setSize(width,800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.setLayout(null);
        TextAreaPanel.setLayout(null);
        titleLabel.setBounds(width/2-(width/50),20,80,25);
        JScrollPane sc1 = new JScrollPane();
        JScrollPane sc2 = new JScrollPane();
        sc1.setBounds(380,100,550,400);
        sc2.setBounds(950,100,350,600);
        input.setBounds(380,100,550,400);
        output.setBounds(950,100,350,600);
        infoput.setBounds(380,530,550,170);
        sc1.setViewportView(input);
        sc2.setViewportView(output);
        panel.add(titleLabel);
        panel.add(sc1);
        panel.add(sc2);
        panel.add(infoput);

        selectBox.addItem("--请选择生成类型--");
        selectBox.addItem("三地址代码");
        selectBox.addItem("四元式");

        selectBox.setBounds(950,65,150,30);

        clearButton.setText("清除");
        compileButton.setText("编译");
        displayButton.setText("显示");
        clearButton.setBounds(380,65,70,30);
        compileButton.setBounds(460,65,70,30);
        displayButton.setBounds(1150,65,70,30);
        panel.add(clearButton);
        panel.add(compileButton);
        panel.add(displayButton);
        panel.add( selectBox);
        frame.add(panel);
        frame.setVisible(true);


        clearButton.setActionCommand("clear");
        compileButton.setActionCommand("compile");
        displayButton.setActionCommand("display");

        clearButton.addActionListener(new SelfButtonActionListener());
        compileButton.addActionListener(new SelfButtonActionListener());
        displayButton.addActionListener(new SelfButtonActionListener());
    }

    Lexer lex;
    Parser parser;
    Node node;
    String inputText = "";
    String DisplaySelect = "";
    String FourSentences;
    String threeAdress ="";
    String ConsoleMessage="";
    void CompileFunction(String inputText) {
      lex = new Lexer(inputText,0);
      parser = new Parser(lex);
      parser.recover();
      node = new Node();
      parser.program();
      lex.TokenPrint();
      node.InfoPrint();
      ConsoleMessage = parser.ConsolePrint();
    }
    class SelfButtonActionListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            //点击开始按钮，进入对测试句的文法分析
            if (command.equals("clear")) {

                input.setText("");
                output.setText("");
                node.renewLabel();
                node.renewquad();
                node.renewTAString();
                lex.renewLex();
                node.recoverError();
                infoput.setText("重置成功");
            }
            else if(command.equals("compile")) {
                inputText = input.getText();
                CompileFunction(inputText);
                infoput.setText(ConsoleMessage);
            }
            else if(command.equals("display")){
                DisplaySelect = (String)selectBox.getSelectedItem();
                System.out.println(DisplaySelect);
                if(DisplaySelect.equals("四元式")){
                    if(node!=null) {

                        FourSentences = node.InfoPrint();
                        if (FourSentences.equals(""))
                            output.setText("空");
                        else output.setText(FourSentences);
                    }
                    else JOptionPane.showMessageDialog(frame.getContentPane(),"请先进行编译", "系统信息", JOptionPane.INFORMATION_MESSAGE);

                }
                else if(DisplaySelect.equals("--请选择生成类型--"))
                {
                    JOptionPane.showMessageDialog(frame.getContentPane(),"请选择输出内容的类型", "系统信息", JOptionPane.INFORMATION_MESSAGE);
                }
                else if(DisplaySelect.equals("三地址代码")){
                    if(node!=null) {
                        threeAdress = node.threeAPrint();
                        if (threeAdress.equals(""))
                            output.setText("空");
                        else output.setText(threeAdress);

                    }
                    else JOptionPane.showMessageDialog(frame.getContentPane(),"请先进行编译", "系统信息", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }
    void MainFunction() throws IOException{prepareGUI();}
    void axic(){
        System.out.println("xaac");
    }
    public static void main(String[] args) throws IOException{
        Main total = new Main();
        total.MainFunction();

        System.out.write('\n');
        System.out.println("the End?");
        Node n=new Node();
        n.InfoPrint();
        //node.Assemble51();
    }
}
