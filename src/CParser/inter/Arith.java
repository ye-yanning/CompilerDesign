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
        type = Type.max(expr1.type,expr2.type);
        if(type==null)error("type error");
    }
    public Expr gen(){
        return new Arith(op,expr1.reduce(),expr2.reduce());
    }
    public String transforToString(){
        quadruple[quadNum][0]=op.transforToString();
        quadruple[quadNum][1]=expr1.transforToString();
        quadruple[quadNum][2]=expr2.transforToString();
        return expr1.transforToString()+" "+op.transforToString()+" "+expr2.transforToString();


    }
}
