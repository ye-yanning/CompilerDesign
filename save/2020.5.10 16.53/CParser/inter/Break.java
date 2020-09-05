package CParser.inter;

public class Break extends  Stmt{
    Stmt stmt;
    public Break(){
        if(Stmt.Enclosing == Stmt.Null)
            error(("unclosed break"));
        stmt=Stmt.Enclosing;
    }
    public void gen(int b,int a){
        quad[quadNum].op="j";
        quad[quadNum].arg1="-";
        quad[quadNum].arg2="-";
        quad[quadNum].result=Integer.toString(stmt.after);
        emit("goto L"+stmt.after);
    }
}
