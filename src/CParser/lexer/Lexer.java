package CParser.lexer;
import CParser.symbols.*;
import java.io.IOException;
import java.util.Hashtable;
import java.util.regex.Pattern;

public class Lexer {
    String regN = "[0-9]";
    String regL = "[a-zA-Z]";
    String regLN="[a-zA-Z0-9]";

    String sourceText=" ";
    int curIndex = 0;

    public static int line = 1;
    char peek = ' ';
    Hashtable words = new Hashtable();
    Hashtable values = new Hashtable();
    void reserve(Word w)
    {
        words.put(w.lexeme,w);
    }
    public Lexer(String SF, int cIndex){
        sourceText = SF;
        curIndex = cIndex;
        reserve(new Word("if",Tag.IF));
        reserve(new Word("else",Tag.ELSE));
        reserve(new Word("while",Tag.WHILE));
        reserve(new Word("do",Tag.DO));
        reserve(new Word("break",Tag.BREAK));
        reserve(new Word("for",Tag.FOR));
       reserve(Word.True);
        reserve(Word.False);
        reserve(Type.Int);
        reserve(Type.Char);
        reserve(Type.Bool);
        reserve(Type.Float);
    }
    void getChar()
    {
        peek = sourceText.charAt(curIndex);
        curIndex++;
    }
    boolean readch(char c){
        getChar();
        if(peek!=c)
            return false;
        peek=' ';
        return true;
    }
    public void TokenPrint(){
        System.out.println("词法输r出");
        System.out.println(words);
        System.out.println(values);

    }
   public void renewLex(){
        line=1;
       words = new Hashtable();
       values = new Hashtable();
   }
    public Token scan() {
        for(;;getChar()){
            if(peek==' '||peek=='\t')continue;   //遇到空格或者制表符就跳过
            else if(peek=='\n')line++;           //遇到换行符则将当前行数+1
            else break;
        }
        //switch 确认当前读入的是否包含 >=,<=,!=,||,&&,==等长度为2的运算符
        switch (peek)
        {

            case '&':
                if(readch('&')) return Word.and;
                else return new Token('&');
            case '|':
                if(readch('|')) return  Word.or;
                else return new Token('|');
            case '=':
                if(readch('=')) return Word.equal;
                else return new Token('=');
            case '>':
                if(readch('=')) return Word.ge;
                else return new Token('>');
            case '<':
                if(readch('=')) return Word.le;
                else return new Token('<');
            case '!':
                if(readch('=')) return Word.ne;
                else return new Token('!');
        }

        //运用正则表达式匹配当前字符
        /*
        regL 为26个字母的大小写
        regN 为0~9的数字
        regNL 为所有字母和数字之和
         */

        //匹配到数字开头的，进入数字识别与构造
        if(Pattern.matches(regN,String.valueOf(peek)))
        {
            int v = 0;
            //在读取到下一个非数字符号之前不断扫描，相当于完成整数部分的构造
            while (Pattern.matches(regN,String.valueOf(peek)))
            {
                v = v*10+(peek-'0');
                getChar();
            }
            //如果没有读取到小数点，返回整数，否则进入浮点数构造模块
            if(peek!='.')
                return new Num(v);
            float x =v;
            float d =10;
            while(true)
            {
                getChar();
                if(!Pattern.matches(regN,String.valueOf(peek)))
                    break;
                x+=(peek-'0')/d;
                d*=10;
            }

            return new Real(x);
        }
        //如果读取到字母开头的，进入标识符与关键字识别模块
        if(Pattern.matches(regL,String.valueOf(peek)))
        {
            StringBuffer strb1 = new StringBuffer();
            //无论后面是字母还是数字，均可以进行读取
            while(Pattern.matches(regLN,String.valueOf(peek)))
            {
                strb1.append(peek);
                getChar();
            }
            String str1 =strb1.toString();

            Word w = (Word)words.get(str1);
            if(w!=null) return w;
            w=new Word(str1,Tag.ID);
            words.put(str1,w);
            values.put(str1,w);
            System.out.println("词法:"+w.lexeme);
            return w;
        }
        Token tok = new Token(peek);
        peek=' ';
        return tok;
    }
}
