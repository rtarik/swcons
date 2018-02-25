package expressivo;

import java.util.Stack;

import expressivo.parser.ExpressionBaseListener;
import expressivo.parser.ExpressionParser.ExprContext;
import expressivo.parser.ExpressionParser.FactorContext;
import expressivo.parser.ExpressionParser.TermContext;

/** Make a Expression value from a parse tree. */
public class MakeExpression extends ExpressionBaseListener {
    private Stack<Expression> stack = new Stack<>();
    // Invariant: stack contains the Expression value of each parse
    // subtree that has been fully-walked so far, but whose parent has not yet
    // been exited by the walk. The stack is ordered by recency of visit, so that
    // the top of the stack is the Expression for the most recently walked
    // subtree.
    
    /**
     * Returns the expression constructed by this listener object.
     * Requires that this listener has completely walked over an IntegerExpression
     * parse tree using ParseTreeWalker.
     * @return IntegerExpression for the parse tree that was walked
     */
    public Expression getExpression() {
        return stack.get(0);
    }

    @Override
    public void exitExpr(ExprContext ctx) {
        //System.out.println("exiting expr: " + ctx.getChildCount());
        if (ctx.getChildCount() == 3) {
            // matched expr + term
            assert stack.size() >= 2;
            Expression b = stack.pop();
            Expression a = stack.pop();
            stack.push(new SumExpression(a, b));
        }
    }

    @Override
    public void exitTerm(TermContext ctx) {
        //System.out.println("exiting term: " + ctx.getChildCount());
        if (ctx.getChildCount() == 3) {
            // matched term * factor
            assert stack.size() >= 2;
            Expression b = stack.pop();
            Expression a = stack.pop();
            stack.push(new ProductExpression(a, b));
        } else {
            // matched factor
            // do nothing
        }
    }

    @Override
    public void exitFactor(FactorContext ctx) {
        //System.out.println("exiting factor: " + ctx.getChildCount());
        if (ctx.NUMBER() != null) {
            String value = ctx.NUMBER().getText();
            stack.push(new PrimitiveExpression(value));
        } else if (ctx.VARIABLE() != null) {
            String value = ctx.VARIABLE().getText();
            stack.push(new PrimitiveExpression(value));
        } else {
            // matched the (expr)
            // do nothing
        }
    }
    
    
}
