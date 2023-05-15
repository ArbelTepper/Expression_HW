package Binary;

import Miscellaneous.Expression;
import Miscellaneous.Num;

import java.util.List;
import java.util.Map;

public class Plus extends BinaryExpression implements Expression {
    public Plus (Expression expression1, Expression expression2) {
        super(expression1, expression2);
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
        return new Plus(new Num(this.getExpression1().evaluate(assignment)) ,
                new Num(this.getExpression2().evaluate(assignment))).evaluate();
    }

    /**
     * A convenience method that evaluates the expression with an empty assignment.
     *
     * @return the result of evaluating the expression
     * @throws Exception if the expression contains a variable not in the assignment
     */
    public double evaluate() throws Exception {
        return getExpression1().evaluate() + getExpression2().evaluate();
    }

    /**
     * Returns a String representation of the Plus object.
     * The returned String will be in the format of "(expression1
     * + expression2)".
     *
     * @return a String representing the Plus object.
     */
    @Override
    public String toString() {
        return ("(" + this.getExpression1().toString() + " + "
                + this.getExpression2().toString() + ")");
    }

    /**
     * Returns a new expression in which all occurrences of the variable
     * var are replaced with the provided expression (Does not modify the
     * current expression).
     *
     * @param var the name of the variable to be replaced
     * @param expression the expression to replace the variable with
     * @return a new expression with the variable replaced
     */
    @Override
    public Expression assign(String var, Expression expression) {
        return new Plus(this.getExpression1().assign(var, expression),
                this.getExpression2().assign(var, expression));
    }
}
