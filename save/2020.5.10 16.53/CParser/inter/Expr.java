package CParser.inter;
import CParser.lexer.*;
import CParser.symbols.*;
public class Expr extends Node{
    public Token op;
    public Type type;
    Expr(Token tok,Type p){
        op = tok;
        type = p;
    }
    public Expr gen(){
        return this;
    }
    public Expr reduce(){
        return this;
    }
    public void jumping(int t,int f){
        emitjumps(transforToString(),t,f);
    }
    public void emitjumps(String test, int t, int f){
        //int curSave = quadNum;
        //int curNext = quadNum+1;

        if(quad[quadNum+1]==null)
            quad[quadNum+1]=new quadruple("j","-","-",null);
        if(quad[quadNum+2]==null)
            quad[quadNum+2]=new quadruple(null,null,null,null);
        quad[quadNum].falseList=quad[quadNum+1];
        quad[quadNum].trueList=quad[quadNum+2];
        if(t!=0 && f!=0){
            emit("ifA "+test+" goto L"+t);
            emit("goto L"+f);
            quadNum+=2;


        }
        else if(t!=0) {

            emit("ifB " + test + " goto L" + t);
            quadNum+=2;

        }
        else if(f!=0) {
            //System.out.println("op.transforToString():"+op.transforToString());

            emit("iffalse "+test+" goto L"+f);
            quadNum+=2;

        }
    }
    public String transforToString()
    {
        return op.transforToString();
    }
}
