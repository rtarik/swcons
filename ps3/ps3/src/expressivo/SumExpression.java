package expressivo;

import java.util.Objects;

public class SumExpression implements Expression {
    private Expression a;
    private Expression b;
    // Represents a sum expression a + b
    
    public SumExpression(Expression a, Expression b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b);
    }

    @Override
    public boolean equals(Object that) {
        if (!(that instanceof SumExpression)) {
            return false;
        }
        return ((SumExpression) that).a.equals(a) &&
                ((SumExpression) that).b.equals(b);
    }

    @Override
    public String toString() {
        return "(" + a.toString() + ")+(" + b.toString() + ")";
    }

    @Override
    public Expression differentiate(String variable) {
        return new SumExpression(a.differentiate(variable), b.differentiate(variable));
    }
    
    
}
