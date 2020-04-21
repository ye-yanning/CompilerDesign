package CParser.inter;

public class Break extends  Stmt{
    Stmt stmt;
    public Break(){
        if(Stmt.Enclosing == Stmt.Null)
            error(("unclosed break"));
        stmt=Stmt.Enclosing;
    }
    public void gen(int b,int a){
        quadruple[quadNum][0]="j";
        quadruple[quadNum][1]="-";
        quadruple[quadNum][2]="-";
        quadruple[quadNum][3]=Integer.toString(stmt.after);
        emit("goto L"+stmt.after);
    }
}
