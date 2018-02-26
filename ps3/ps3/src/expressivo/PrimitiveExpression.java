package expressivo;

/** Represents expressions of primitive type such as variables and numbers **/
public class PrimitiveExpression implements Expression {
    private String x;
    // The String x can be a number or a the name of a variable
    
    public PrimitiveExpression(String value) {
        x = value;
    }

    @Override
    public int hashCode() {
        return x.hashCode();
    }

    @Override
    public boolean equals(Object that) {
        if (!(that instanceof PrimitiveExpression)) {
            return false;
        }
        return ((PrimitiveExpression) that).x.equals(x);
    }

    @Override
    public String toString() {
        return x;
    }

    @Override
    public Expression differentiate(String variable) {
        if (x.equals(variable)) {
            return new PrimitiveExpression("1");
        } else {
            return new PrimitiveExpression("0");
        }
    }
    
    
}
