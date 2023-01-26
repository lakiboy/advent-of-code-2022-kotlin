package io.dmitrijs.aoc2022

import io.dmitrijs.aoc2022.Day21.Operation.DIV
import io.dmitrijs.aoc2022.Day21.Operation.MINUS
import io.dmitrijs.aoc2022.Day21.Operation.PLUS
import io.dmitrijs.aoc2022.Day21.Operation.TIMES

class Day21(input: List<String>) {
    private val env = buildEnv(input)

    fun puzzle1(): Long {
        val (vars, _) = resolveEnvUntil(env) { resolvedVars ->
            ROOT !in resolvedVars
        }

        return vars.getValue(ROOT)
    }

    fun puzzle2(): Long {
        val modifiedEnv = env.removeVar(HUMAN)
        val (vars, expr) = resolveEnvUntil(modifiedEnv) { resolvedVars ->
            resolvedVars.isNotEmpty()
        }

        val rootExpr = expr.getValue(ROOT)

        var (lookup, result) = if (rootExpr.operand1 in vars) {
            rootExpr.operand2 to vars.getValue(rootExpr.operand1)
        } else {
            rootExpr.operand1 to vars.getValue(rootExpr.operand2)
        }

        // Solve all expressions backwards until HUMAN is found.
        do {
            expr.getValue(lookup).solve(vars, result).apply {
                lookup = first
                result = second
            }
        } while (lookup != HUMAN)

        return result
    }

    private fun resolveEnvUntil(env: Environment, predicate: (Map<String, Long>) -> Boolean): Environment {
        val vars = env.vars.toMutableMap()
        val expr = env.expr.toMutableMap()

        do {
            val resolvedVars = expr
                .filterValues { it.operand1 in vars && it.operand2 in vars }
                .mapValues { (_, op) -> op.eval(vars) }
                .also { vars.putAll(it) }

            // Cleanup expressions.
            resolvedVars.keys.forEach { name -> expr.remove(name) }
        } while (predicate(resolvedVars))

        return Environment(vars, expr)
    }

    private fun buildEnv(commands: List<String>): Environment {
        val vars = hashMapOf<String, Long>()
        val expr = hashMapOf<String, Expr>()

        commands.forEach { command ->
            val (name, op) = command.split(": ")
            op.toLongOrNull()
                ?.let { vars[name] = it }
                ?: run { expr[name] = op.toExpr() }
        }

        return Environment(vars, expr)
    }

    private data class Environment(val vars: Map<String, Long>, val expr: Map<String, Expr>) {
        fun removeVar(name: String) = copy(
            vars = vars.toMutableMap().apply { remove(name) },
            expr = expr,
        )
    }

    private fun String.toExpr(): Expr {
        val (operand1, op, operand2) = split(" ")

        return Expr(operand1, operand2, Operation.of(op))
    }

    private data class Expr(
        val operand1: String,
        val operand2: String,
        private val op: Operation,
    ) {
        fun eval(vars: Map<String, Long>) = when (op) {
            PLUS -> vars.getValue(operand1) + vars.getValue(operand2)
            MINUS -> vars.getValue(operand1) - vars.getValue(operand2)
            TIMES -> vars.getValue(operand1) * vars.getValue(operand2)
            DIV -> vars.getValue(operand1) / vars.getValue(operand2)
        }

        fun solve(vars: Map<String, Long>, result: Long): Pair<String, Long> {
            val known = when {
                operand1 in vars -> vars.getValue(operand1)
                operand2 in vars -> vars.getValue(operand2)
                else -> error("Can not solve expression: $this.")
            }

            return if (operand1 in vars) {
                operand2 to when (op) {
                    PLUS -> result - known
                    MINUS -> result * -1 + known
                    TIMES -> result / known
                    DIV -> known / result
                }
            } else {
                operand1 to when (op) {
                    PLUS -> result - known
                    MINUS -> result + known
                    TIMES -> result / known
                    DIV -> result * known
                }
            }
        }
    }

    private enum class Operation {
        PLUS, MINUS, TIMES, DIV;

        companion object {
            fun of(op: String) = when (op) {
                "+" -> PLUS
                "-" -> MINUS
                "*" -> TIMES
                "/" -> DIV
                else -> error("Unsupported operation: $op.")
            }
        }
    }

    companion object {
        private const val ROOT = "root"
        private const val HUMAN = "humn"
    }
}
