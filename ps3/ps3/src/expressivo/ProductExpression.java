package expressivo;

/** Expression of type product **/
public class ProductExpression implements Expression {
    private Expression a;
    private Expression b;
    // Represents a product expression a * b
    
    public ProductExpression(Expression a, Expression b) {
        this.a = a;
        this.b = b;
    }
}
