//表达式节点
package CParser.inter;
import CParser.lexer.*;
import CParser.inter.*;

import java.util.Vector;

public class Node {
     class  quadruple
    {
        quadruple(String o,String a1,String a2,String r)
        {
            this.op=o;
            this.arg1=a1;
            this.arg2=a2;
            this.result=r;
        }
        quadruple(){

        }
        String op;
        String arg1;
        String arg2;
        String result;
        quadruple trueList = null;
        quadruple falseList = null;
        boolean temp = false;

    }
    static quadruple[] quad= new quadruple[1000];
     Vector trueList;
     Vector falselist;
     Vector nextlist;
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

    public Vector<Integer> makelist(int remain){
        System.out.println("新建List!");
        return new Vector<>(1,remain);
    }
    public Vector<Integer> merge(Vector<Integer> first,Vector<Integer> second){
        Vector<Integer> target = new Vector<>();
        if(first!=null)
        target.addAll(first);
        if(second!=null)
        target.addAll(second);
        return target;
    }
    public void backpatch(Vector<Integer> myList,int value){
        if(myList!=null)
        {
            System.out.println("开始回填!");
            for(int i=0;i<myList.size();i++)
                quad[myList.elementAt(i)].result = Integer.toString(value);
        }
        else{
            System.out.println("回填失败");
        }
    }
    public Vector<Integer> equal(Vector<Integer> duplicate){
        Vector<Integer> temp = null;
        if(duplicate!=null)
        {
            temp = (Vector<Integer>)duplicate.clone();
        }
        else{
            System.out.println("克隆失败!");
        }
        return temp;
    }

    public void InfoPrint() {
        for (int i = 0; i < quadNum; i++) {
            System.out.println(i + " : " + quad[i].op + "," + quad[i].arg1 + "," + quad[i].arg2  + "," + quad[i].result);
            //System.out.println("true:"+quad[i].trueList+" false:"+quad[i].falseList);
        }
        System.out.println(quadNum+" :");
    }
}
