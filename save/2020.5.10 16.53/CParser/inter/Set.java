package CParser.inter;

import CParser.symbols.Type;

import java.util.regex.Pattern;

public class Set extends Stmt {
    public Id id;
    public  Expr expr;
    public Set(Id i,Expr x){
        id=i;
        expr=x;
    if(check(id.type,expr.type)==null)
        error("type error");
    }
    public Type check(Type p1,Type p2){
        if(Type.numeric(p1)&&Type.numeric(p2))
            return p2;
        else if(p1 ==Type.Bool&&p2 == Type.Bool)
            return p2;
        else return null;
    }

    public void gen(int b,int a){

        String target = expr.gen().transforToString();
        emit(id.transforToString()+"="+target);
        if(quad[quadNum]==null)
        quad[quadNum]=new quadruple();
        quad[quadNum].result=id.transforToString();
                if(whetherNum(target))
                {
                    quad[quadNum].arg1 = target;
                    quad[quadNum].op= ":=";
                    quad[quadNum].arg2="-";
                }
        //System.out.println("test:"+quad[quadNum].op+" "+quad[quadNum].arg1+" "+quad[quadNum].arg2);


        //System.out.println("quadNum:"+quadNum+" id.transforToString():"+id.transforToString());
        quadNum++;
    }
    boolean whetherNum(String target){
        String regN = "[0-9|/.]";
        for(int i =0;i<target.length();i++)
            if((target.charAt(i)<'0'||target.charAt(i)>'9')&&(target.charAt(i)!='.'))
                return false;
            return true;
    }
}
