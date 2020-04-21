package CParser.inter;
import CParser.lexer.*;

public class And extends Logical{
    public And(Token tok,Expr x1,Expr x2){
        super(tok,x1,x2);
    }
    public void jumping(int t,int f){
        int label = f!=0 ? f:newlabels();
        expr1.jumping(0,label);
        expr2.jumping(t,f);
        if(f==0)
            emitlabels(label);
    }

}
