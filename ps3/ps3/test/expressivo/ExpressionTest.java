/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package expressivo;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests for the Expression abstract data type.
 */
public class ExpressionTest {

    //  Testing strategy
    //    parse():
    //      valid inputs
    //    toString():
    //      One variable expression, operations ordered with parentheses
    //    equals():
    //      One variable expression, variables order, grouping
    //    hashCode():
    //      same as equals()
    //    differentiate(String variable):
    //      Product, Sum, Primitive
    //      variable in the expression, variable not in the expression
    
    @Test
    public void testParseValidInput() {
        Expression.parse("3 + 2");
        Expression.parse("3 * x + 2");
        Expression.parse("((3 + 4) * x * x)");
        Expression.parse("foo + bar+baz");
    }
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    
    @Test
    public void testToStringOneVariable() {
        Expression expression = Expression.parse(" x ");
        assertEquals("Expected expression to be x", "x", expression.toString());
    }
    
    @Test
    public void testToStringOperationsOrder() {
        Expression expression = Expression.parse("x*(y+z)");
        assertEquals("Expected (x)*((y)+(z))", "(x)*((y)+(z))", expression.toString());
    }
    
    @Test
    public void testEqualsOneVariable() {
        Expression xExp1 = Expression.parse("x");
        Expression xExp2 = Expression.parse(" x");
        Expression yExp = Expression.parse("y");
        assertTrue("Expected expressions to be equal", xExp1.equals(xExp2));
        assertFalse("Expected expressions to be different", xExp1.equals(yExp));
    }
    
    @Test
    public void testEqualsVariablesOrder() {
        Expression XPlusY1 = Expression.parse("(X + Y)");
        Expression XPlusY2 = Expression.parse("X+Y");
        Expression YPlusX = Expression.parse("Y+X");
        assertTrue("Expected expressions to be equal", XPlusY1.equals(XPlusY2));
        assertFalse("Expected expressions to be different", XPlusY1.equals(YPlusX));
    }
    
    @Test
    public void testEqualsGrouping() {
        Expression XPlusOne1 = Expression.parse("(X + 1)");
        Expression XPlusOne2 = Expression.parse("(X) + (1)");
        Expression XYZ1 = Expression.parse("X*(Y+Z)");
        Expression XYZ2 = Expression.parse("X*Y+Z");
        Expression XYZ3 = Expression.parse("X+Y+Z");
        Expression XYZ4 = Expression.parse("(X+Y)+Z");
        Expression XYZ5 = Expression.parse("X+(Y+Z)");
        assertTrue("Expected expressions to be equal", XPlusOne1.equals(XPlusOne2));
        assertFalse("Expected expressions to be different", XYZ1.equals(XYZ2));
        assertTrue("Expected expressions to be equal", XYZ3.equals(XYZ4));
        assertFalse("Expected expressions to be different", XYZ4.equals(XYZ5));
    }
    
    @Test
    public void testEqualsParseToString() {
        Expression expr = Expression.parse("x+(y+z)");
        assertTrue("Expected parsed toString to reproduce the expression",
                expr.equals(Expression.parse(expr.toString())));
    }
    
    @Test
    public void testHashCodeOneVariable() {
        Expression xExp1 = Expression.parse("x");
        Expression xExp2 = Expression.parse(" x");
        Expression yExp = Expression.parse("y");
        assertTrue("Expected hashCodes to be equal", xExp1.hashCode() == xExp2.hashCode());
        assertFalse("Expected hashCodes to be different", xExp1.hashCode() == yExp.hashCode());
    }
    
    @Test
    public void testHashCodeVariablesOrder() {
        Expression XPlusY1 = Expression.parse("(X + Y)");
        Expression XPlusY2 = Expression.parse("X+Y");
        Expression YPlusX = Expression.parse("Y+X");
        assertTrue("Expected hashCodes to be equal", XPlusY1.hashCode() == XPlusY2.hashCode());
        assertFalse("Expected hashCodes to be different", XPlusY1.hashCode() == YPlusX.hashCode());
    }
    
    @Test
    public void testHashCodeGrouping() {
        Expression XPlusOne1 = Expression.parse("(X + 1)");
        Expression XPlusOne2 = Expression.parse("(X) + (1)");
        Expression XYZ1 = Expression.parse("X*(Y+Z)");
        Expression XYZ2 = Expression.parse("X*Y+Z");
        assertTrue("Expected hashCodes to be equal", XPlusOne1.hashCode() == XPlusOne2.hashCode());
        assertFalse("Expected hashCodes to be different", XYZ1.hashCode() == XYZ2.hashCode());
    }
    
    @Test
    public void testDifferentiateProductVarIn() {
        Expression xy = Expression.parse("x*y");
        assertTrue("expected differentiated expression", xy.differentiate("x").equals(
                Expression.parse("1*y + x*0")
                ));
    }
    
    @Test
    public void testDifferentiateSumVarNotIn() {
        Expression xy = Expression.parse("x+y");
        assertTrue("expected differentiated expression", xy.differentiate("z").equals(
                Expression.parse("0+0")
                ));
    }
    
    @Test
    public void testDifferentiatePrimitive() {
        Expression x = Expression.parse("x");
        assertTrue("expected differentiated expression", x.differentiate("x").equals(
                Expression.parse("1")
                ));
        assertTrue("expected differentiated expression", x.differentiate("y").equals(
                Expression.parse("0")
                ));
    }
    
}
