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
        if(t!=0 && f!=0){

            emit("if "+test+" goto L"+t);
            emit("goto L"+f);
        }
        else if(t!=0) emit("if "+test+" goto L"+t);
        else if(f!=0) emit("iffalse "+test+" goto L"+f);
    }
    public String transforToString()
    {
        return op.transforToString();
    }
}