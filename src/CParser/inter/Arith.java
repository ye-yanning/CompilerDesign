package CParser.inter;
import CParser.lexer.*;
import CParser.symbols.*;
//该类用于实现双目运算符
public class Arith extends Op{
    public Expr expr1,expr2;
    public Arith(Token tok,Expr x1,Expr x2){
        super(tok,null);
        expr1=x1;
        expr2=x2;
        //计算出结果的类型,调用Type中的max函数
        if(Error)
            return;
        type = Type.max(expr1.type,expr2.type);
        if(type==null)error("type error");
    }
    public Expr gen(){
        return new Arith(op,expr1.reduce(),expr2.reduce());
    }
    public String transforToString(){
        //System.out.println("套娃");
        if(quad[quadNum]==null)
        quad[quadNum]=new quadruple();
        quad[quadNum].op=op.transforToString();
        quad[quadNum].arg1=expr1.transforToString();
        quad[quadNum].arg2=expr2.transforToString();
        if(quad[quadNum].result!=null)     //因为临时变量的话，是临时变量先添加到result中再完善op,arg1,arg2,因此当此时reuslt不为空时，说明这是一个临时变量式，已经完成建立
            quadNum++;
    //System.out.println("quadNum:"+quadNum+" expr1.transforToString():"+expr1.transforToString()+" op.transforToString():"+op.transforToString()+" expr2.transforToString():"+expr2.transforToString());

        return expr1.transforToString()+" "+op.transforToString()+" "+expr2.transforToString();


    }
}
