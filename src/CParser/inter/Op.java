package CParser.inter;
import CParser.lexer.*;
import CParser.symbols.*;

import java.util.regex.Pattern;

public class Op extends Expr {
    public Op(Token tok, Type p){
        super(tok,p);
    }
    boolean whehterCaculate(String tar)
    {
        for(int i=0;i<tar.length();i++)
        {
            if(tar.charAt(i)=='*'||tar.charAt(i)=='/'||tar.charAt(i)=='+'||tar.charAt(i)=='-')
                return true;
        }
        return false;
    }
    public Expr reduce(){
        Expr x = gen();
        Temp t = new Temp(type);
        System.out.println("quad:"+quadNum);
        String xString = x.transforToString();
        String tString = t.transforToString();
        //String regN = "\\-|\\+|\\*|\\[|j";
        System.out.println("xString："+xString);
        if(whehterCaculate(xString))
        {
            System.out.println("包含!!");
        }
        else{
            System.out.println("不包含");
        }
        //System.out.println(t.transforToString()+" = "+x.transforToString());
            if(whehterCaculate(xString))
                quad[quadNum] = new quadruple(null,null,null,tString);
            else quad[quadNum++] = new quadruple(":=",xString,"-",tString);

        emit(t.transforToString()+" = "+x.transforToString());
        return t;
    }
}
