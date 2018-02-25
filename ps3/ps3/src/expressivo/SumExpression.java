package expressivo;


public class SumExpression implements Expression {
    private Expression a;
    private Expression b;
    // Represents a sum expression a + b
    
    public SumExpression(Expression a, Expression b) {
        this.a = a;
        this.b = b;
    }
}
