//表达式节点
package CParser.inter;
import CParser.lexer.*;
import CParser.inter.*;
public class Node {
    static String[][] quadruple = new String[1000][4];
    static int quadNum;

    int lexline = 0;
    public Node(){
        lexline = Lexer.line;
    }
    void error(String s){
        throw new Error("line " + lexline+":"+s);
    }
    //newlabels, emitlabels, emit三个函数用于生成三地址代码
    static int labels = 0;
    public int newlabels(){
        return ++labels;
    }
    public void emitlabels(int i){
        System.out.print("L"+i+":");
    }
    public void emit(String s){
        System.out.println("\t"+s);
    }
    public void InfoPrint() {
        for (int i = 0; i < quadNum; i++) {
            System.out.println(i + " : " + quadruple[i][0] + "," + quadruple[i][1] + "," + quadruple[i][2] + "," + quadruple[i][3]);
        }
    }
}
