package Unary;

import Binary.Mult;
import Miscellaneous.Expression;
import Miscellaneous.Num;
import Miscellaneous.Var;

import java.util.List;
import java.util.Map;

public class Sin extends UnaryExpression implements Expression {
    public Sin(Expression expression) {
        super(expression);
    }

    /**
     * Evaluate the expression using the variable values provided
     * in the assignment, and return the result. If the expression
     * contains a variable which is not in the assignment, an exception
     * is thrown.
     *
     * @param assignment a map of variable names to their corresponding values
     * @return the result of evaluating the expression
     * @throws Exception if the expression contains a variable not in the assignment
     */
    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        // evaluate returns a double, which is made into a Num object, which
        // is made into a Sin object, whose value can be evaluated.
        return new Sin (new Num(this.getExpression().evaluate(assignment))).evaluate();
    }

    /**
     * A convenience method that evaluates the expression with an empty assignment.
     *
     * @return the result of evaluating the expression
     * @throws Exception if the expression contains a variable not in the assignment
     */
    @Override
    public double evaluate() throws Exception {
        return Math.sin(Math.toRadians((this.getExpression().evaluate())));
    }

    /**
     * Returns a string representation of the expression.
     *
     * @return A string representation of the expression.
     */
    @Override
    public String toString() {
        return ("sin(" + this.getExpression().toString() + ")");
    }

    public Expression assign(String var, Expression expression) {
        return new Sin (this.getExpression().assign(var, expression));
    }

    @Override
    public Expression differentiate(String var) {
        return new Mult(new Cos(this.getExpression()),
                this.getExpression().differentiate(var));
    }

    // Returned a simplified version of the current expression.
    public Expression simplify() throws Exception { // shouldn't throw exception
        if (this.getVariables().isEmpty()) {
            return new Num(this.evaluate());
        } else {
            return new Sin(this.getExpression().simplify());
        }
    }
}
