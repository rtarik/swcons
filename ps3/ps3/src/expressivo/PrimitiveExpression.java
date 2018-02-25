package expressivo;

/** Represents expressions of primitive type such as variables and numbers **/
public class PrimitiveExpression implements Expression {
    private String x;
    // The String x can be a number or a the name of a variable
    
    public PrimitiveExpression(String value) {
        x = value;
    }
}
