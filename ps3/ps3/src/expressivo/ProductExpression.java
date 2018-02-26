package expressivo;

import java.util.Objects;

/** Expression of type product **/
public class ProductExpression implements Expression {
    private Expression a;
    private Expression b;
    // Represents a product expression a * b
    
    public ProductExpression(Expression a, Expression b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b);
    }

    @Override
    public boolean equals(Object that) {
        if (!(that instanceof ProductExpression)) {
            return false;
        }
        return ((ProductExpression) that).a.equals(a) &&
                ((ProductExpression) that).b.equals(b);
    }

    @Override
    public String toString() {
        return "(" + a.toString() + ")*(" + b.toString() + ")";
    }

    @Override
    public Expression differentiate(String variable) {
        return new SumExpression(
                new ProductExpression(a.differentiate(variable), b),
                new ProductExpression(a, b.differentiate(variable))
                );
    }
    
    
}
