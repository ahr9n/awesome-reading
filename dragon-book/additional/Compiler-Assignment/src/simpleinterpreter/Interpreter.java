package simpleinterpreter;

import java.io.IOException;
import java.util.List;

class Interpreter implements Expr.Visitor<Object> {

    void interpret(List<Expr> expressions) throws IOException {
        try {
            for (Expr expression : expressions) {
                System.out.println(evaluate(expression));
            }
        } catch (RuntimeError error) {
            SimpleInterpreter.runtimeError(error);
        }
    }

    private Object evaluate(Expr expr) {
        return expr.accept(this);
    }

    @Override
    public Object visitBinaryExpr(Expr.Binary expr) {
        Object left = evaluate(expr.left);
        Object right = evaluate(expr.right);

        checkNumberOperands(expr.operator, left, right);
        switch (expr.operator.type){
/*
           ###############################################################################
                TODO: - Handle the evaluation of these operators (MINUS, SLASH, STAR).
                      - Don't write more than three lines of code.
           ###############################################################################
*/
            case PLUS: return (double)left + (double)right; // Take this line as a guide.
            /* TODO: Add your code below this comment: */


        }

        return null;
    }

    @Override
    public Object visitUnaryExpr(Expr.Unary expr) {
/*
           ###############################################################################
                TODO: - Handle the evaluation of negation unary operator.
                      - Use 'evaluate()' function to evaluate (expr.right) then return the negated expression instead of null.
           ###############################################################################
*/

        return null;
    }

    @Override
    public Object visitNumberExpr(Expr.Number expr) {
        return expr.value;
    }

    private void checkNumberOperands(Token operator,
                                     Object left, Object right) {
        if (left instanceof Double && right instanceof Double) return;

        throw new RuntimeError(operator, "Operands must be numbers.");
    }
}