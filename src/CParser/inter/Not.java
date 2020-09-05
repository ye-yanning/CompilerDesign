package CParser.inter;
import CParser.lexer.*;

public class Not extends Logical{
    public Not(Token tok,Expr x2){
        super(tok,x2,x2);

    }
    public void jumping(int t,int f){
        expr2.jumping(f,t);
    }
    public String transforToString(){
        return op.transforToString()+" "+expr2.transforToString();
    }
}
