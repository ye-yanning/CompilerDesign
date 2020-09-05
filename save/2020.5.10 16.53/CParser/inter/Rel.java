package CParser.inter;
 import CParser.lexer.*;
 import CParser.symbols.*;
public class Rel extends Logical{
    public Rel(Token tok,Expr x1,Expr x2){
        super(tok,x1,x2);
    }
    public Type check(Type p1,Type p2){
        if(p1 instanceof Array || p2 instanceof Array)
            return null;
        else if(p1 == p2) return Type.Bool;
        else return null;
    }
    public void jumping(int t,int f){
        Expr a = expr1.reduce();
        Expr b =expr2.reduce();

        trueList = makelist(quadNum+1);    //B.truelist = makelist(nextinstr)
        falselist = makelist(quadNum+2);   //B.falselist = makelist(nextinstr+1)


        if(quad[quadNum]==null)
            quad[quadNum]=new quadruple();
        quad[quadNum].arg1=a.transforToString();
        quad[quadNum].op="j"+op.transforToString();
        quad[quadNum].arg2=b.transforToString();
        String test = a.transforToString() + " "+op.transforToString()+" "+b.transforToString();
        emitjumps(test,t,f);
    }
}
