package CParser.inter;

import CParser.symbols.Array;
import CParser.symbols.Type;

public class SetElem extends Stmt{
    public Id array;
    public Expr index;
    public Expr expr;
    public SetElem(Access x,Expr y){
        if(Error)
            return;
        //System.out.println("x:"+x.transforToString());
        //System.out.println("y:"+y.transforToString());
        array = x.array;
        index = x.index;
       // System.out.println(x.array.transforToString()+" "+x.index.transforToString());
        expr = y;
        if(check(x.type,expr.type)==null)
            error("type error");
    }
    public Type check(Type p1,Type p2){
        if(Error)
            return null;
        if(p1 instanceof Array || p2 instanceof  Array)
            return null;
        else if(p1==p2)
            return p2;
        else if(Type.numeric(p1)&&Type.numeric(p2))
            return p2;
        else return null;
    }
    public void gen(int b,int a){
        if(Error)
            return;
        String s1 = index.reduce().transforToString();

        quad[quadNum++]=new quadruple(":=",expr.transforToString(),"-",array.transforToString()+"["+s1+"]");
        String s2 =expr.reduce().transforToString();

        emit(array.transforToString()+"["+s1+"] ="+s2);
    }
}
