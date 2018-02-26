package expressivo;

import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

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

    @Override
    public Expression simplify(Map<String, Double> environment) {
        Expression simplifiedFirst = a.simplify(environment);
        Expression simplifiedSecond = b.simplify(environment);
        if (simplifiedFirst.variables().isEmpty() && simplifiedSecond.variables().isEmpty()) {
            return new PrimitiveExpression(
                    String.valueOf(simplifiedFirst.reduce() + simplifiedSecond.reduce())
                    );
        } else {
            return new SumExpression(simplifiedFirst, simplifiedSecond);
        }
    }

    @Override
    public Set<String> variables() {
        Set<String> union = new HashSet<>(a.variables());
        union.addAll(b.variables());
        return union;
    }

    @Override
    public int reduce() {
        return a.reduce() + b.reduce();
    }
    
}
