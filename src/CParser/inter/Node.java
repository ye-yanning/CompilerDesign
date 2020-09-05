//表达式节点
package CParser.inter;
import CParser.lexer.*;
import CParser.inter.*;
import CParser.parser.Parser;

import java.util.*;
import java.util.Set;
import java.util.regex.Pattern;

public class Node {
    static StringBuffer threeAdress = new StringBuffer("");
     class quadruple
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
    }
    static quadruple[] quad= new quadruple[1000];
     Vector trueList;
     Vector falselist;
     Vector nextlist;
    static int quadNum;
    static boolean DoBack = false;

    int lexline = 0;
    public Node(){
        lexline = Lexer.line;
    }
    void error(String s){
        System.out.println("出错");
        Stmt.Error = true;
        Expr.Error = true;
        // lex.toTheEnd();
        Parser.isError.append("near line"+lexline+":"+s+"\n");
        Parser.ErrorSign = true;
        //throw new Error("line " + lexline+":"+s);
    }
    //newlabels, emitlabels, emit三个函数用于生成三地址代码
    static int labels = 0;
    public void recoverError(){
        Stmt.Error = false;
        Expr.Error = false;
        Parser.isError = new StringBuffer();
    }
    public int newlabels(){
        return ++labels;
    }
    public void renewLabel(){
        labels = 0;
    }
    public void emitlabels(int i){
        threeAdress.append("L"+i+":");
        System.out.print("L"+i+":");
        //System.out.print("threeAdrress:"+threeAdress);
    }
    public void renewTAString()
    {
        DoBack = false;
       threeAdress = new StringBuffer("");
        quad= new quadruple[1000];
    }
    public void emit(String s){
        threeAdress.append("\t"+s+"\n");
        System.out.println("\t"+s);
        //System.out.print("threeAdrress:"+threeAdress);
    }

    public Vector<Integer> makelist(int remain){
        //System.out.println("新建List!");
        Vector temp1 = new Vector<>(1);
        temp1.add(remain);
        return temp1;
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
            System.out.println("开始回填!  myList.size():"+myList.size());
            for(int i=0;i<myList.size();i++)
            {
                System.out.println("位于"+myList.elementAt(i)+"填入"+value);
                if(quad[myList.elementAt(i)]==null)
                    quad[myList.elementAt(i)]=new quadruple();

                quad[myList.elementAt(i)].result = Integer.toString(value);
            }
        }
        else{
           // System.out.println("回填失败");
        }
    }
    void ListPrint(Vector<Integer> ListToPrint){
        System.out.println("^^^^^");
        if(ListToPrint==null)
            System.out.println("表不存在");
        else if(ListToPrint.isEmpty())
            System.out.println("表为空");
        else{
           System.out.print("表输出:");
            for(int i=0;i<ListToPrint.size();i++)
            {
                System.out.print(ListToPrint.elementAt(i)+",");
            }
            System.out.println(" ");
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
    public void renewquad(){
        quadNum=0;
    }
    public String InfoPrint() {
        StringBuffer Display = new StringBuffer("");
        for (int i = 0; i < quadNum; i++) {
            Display.append(i + " : " + quad[i].op + "," + quad[i].arg1 + "," + quad[i].arg2  + "," + quad[i].result+"\n");
            System.out.println(i + " : " + quad[i].op + "," + quad[i].arg1 + "," + quad[i].arg2  + "," + quad[i].result);
            //System.out.println("true:"+quad[i].trueList+" false:"+quad[i].falseList);
        }
        System.out.println(quadNum+" :");
        Display.append(quadNum+" :");
        return Display.toString();
    }
    public String threeAPrint(){
        System.out.println("结果:"+threeAdress.toString());
        return threeAdress.toString();
    }

    public String DtoHex(int d){
        StringBuffer res= new StringBuffer();
        int rest = d;
        int bit = 0;
        while(rest!=0){
            bit=rest%16;
            rest/=16;
           if(bit<10&&bit>=0){
               res.insert(0,bit);
           }
           else{
               res.insert(0,(char)(bit-10+'A'));
           }
        }
        return res.toString();
    }

    //寄存器数据结构
    class Register{
        String id;      //此时寄存器中存放的标识符名称
        boolean used;   //寄存器是否被使用
    }
    String Acc = null;  //储存当前Acc中存放的标识符名称
    Register[] reg = new Register[7];   //寄存器堆
    int Ratten=0;                      //索引，用于遍历寄存器

    int AccCheck(String arg1,String arg2)
    {
        if(Acc==arg1)
            return 1;
        else if(Acc==arg2)
            return 2;
        else {
            threeAdress.append("\n");
            System.out.println();
            return 0;
        }
    }


   //寄存器选择函数
    void RegisterSelect(String id){
        int Count = 0;
        while(Count<7){
            if(reg[Ratten]==null)
                reg[Ratten]=new Register();

            if(reg[Ratten].used)
            {
                Ratten++;
                Count++;
            }
            else{
                reg[Ratten].id = id;
                reg[Ratten].used=true;
                RegisterId.put(id,"R"+Ratten);
                //System.out.println(RegisterId);
                System.out.print("R"+Ratten+",");
                return;
            }

        }
        Random random = new Random();
        Ratten=random.nextInt(7);
        if(RegisterId.containsValue("R"+Ratten))
        RegisterId.remove(reg[Ratten].id);
        reg[Ratten].id = id;
        reg[Ratten].used=true;
        RegisterId.put(id,"R"+Ratten);
        System.out.print("R"+Ratten+" ");
        return;
    }
    Map RegisterId = new HashMap();
    public void Assemble51(){
        for(int i=0;i<quadNum;i++){
            Pattern pattern = Pattern.compile("^[\\-]?[0-9]+$");

            String op = quad[i].op;
            String arg1 = quad[i].arg1;
            String arg2 = quad[i].arg2;
            String res = quad[i].result;
            boolean arg1IsNum =  pattern.matcher(arg1).matches();
            boolean arg2IsNum = pattern.matcher(arg2).matches();
            System.out.println("arg1:"+arg1+"   arg1Is?:"+arg1IsNum+"  arg2:"+arg2+"   arg2Is:"+arg2IsNum);

            switch (op){
                case ":=":
                    System.out.print("MOV ");
                    RegisterSelect(res);
                    System.out.println("#"+DtoHex(Integer.valueOf(arg1))+"H");
                    break;
                case "j<":
                    break;
                case "+":
                    AccCheck(arg1,arg2);
                    System.out.print("ADD A,");
                    System.out.println("R");
                    break;
                case "-":
                    break;
                case "*":
                    System.out.println("MUL AB");
                    break;
                case "/":
                    System.out.println("DIV AB");
                    break;
                case "j":
                    break;
            }
        }
    }
}
