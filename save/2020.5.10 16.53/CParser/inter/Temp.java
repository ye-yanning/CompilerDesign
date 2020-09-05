package CParser.inter;
import CParser.lexer.*;
import CParser.symbols.*;
public class Temp extends Expr{
    static int count = 0;
    int number=0;
    public Temp(Type p){
        super(Word.temp,p);
        number = ++count;
    }
    public String transforToString()
    {
        String OpResult = "t"+number;
        //System.out.println("quadNum:"+quadNum+" OpResult:"+OpResult);
        if(quad[quadNum]==null)
            quad[quadNum]=new quadruple();
        if(quad[quadNum].op==null)      //假如运算符为空，则将当前临时符号作为四元式的结果
        quad[quadNum].result=OpResult;
        return  OpResult;
    }
}
