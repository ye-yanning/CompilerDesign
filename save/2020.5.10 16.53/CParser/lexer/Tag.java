package CParser.lexer;
// Tag类，定义编译过程中可能会用到的长标签(关键词，类型等)，将长标签定义为数字以便后续处理
public class Tag {
    public static final int
    FOR = 801, WHILE = 802, BREAK = 803, TRUE = 831, FALSE = 832, DO = 805, IF = 806, ELSE = 807,
    EQUAL = 811, LE = 812, MINUS = 813, NE = 814, TEMP = 815, OR = 816, AND = 817, GE = 818, REAL = 819,
            INDEX = 821, ID = 822, NUM = 823,BASIC = 825;
}
