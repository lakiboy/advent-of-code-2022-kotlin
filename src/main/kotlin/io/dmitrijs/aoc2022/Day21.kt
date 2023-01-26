package io.dmitrijs.aoc2022

import io.dmitrijs.aoc2022.Day21.Operation.DIV
import io.dmitrijs.aoc2022.Day21.Operation.MINUS
import io.dmitrijs.aoc2022.Day21.Operation.PLUS
import io.dmitrijs.aoc2022.Day21.Operation.TIMES

class Day21(input: List<String>) {
    private val env = buildEnv(input)

    private fun resolveVarsUntil(vars: Map<String, Long>, expr: Map<String, Expr>, predicate: (Map<String, Long>) -> Boolean) {

    }

    fun puzzle1(): Long {
        val vars = env.first.toMutableMap()
        val expr = env.second.toMutableMap()

        resolveVarsUntil(vars, expr) {
            ROOT !in vars
        }

        while (ROOT !in vars) {

            val resolvedVars = expr
                .filterValues { it.operand1 in vars && it.operand2 in vars }
                .mapValues { (_, op) -> op.eval(vars) }
                .also { vars.putAll(it) }

            // Cleanup expressions.
            resolvedVars.keys.forEach { name -> expr.remove(name) }
        }

        return vars.getValue(ROOT)
    }

    fun puzzle2(): Long {
        val vars = env.first.toMutableMap().apply { remove(HUMAN) }
        val expr = env.second.toMutableMap()

        do {
            val resolvedVars = expr
                .filterValues { it.operand1 in vars && it.operand2 in vars }
                .mapValues { (_, op) -> op.eval(vars) }
                .also { vars.putAll(it) }

            // Cleanup expressions.
            resolvedVars.keys.forEach { name -> expr.remove(name) }
        } while (resolvedVars.isNotEmpty())

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

    private fun Expr.eval(env: Map<String, Long>) = when (op) {
        PLUS -> env.getValue(operand1) + env.getValue(operand2)
        MINUS -> env.getValue(operand1) - env.getValue(operand2)
        TIMES -> env.getValue(operand1) * env.getValue(operand2)
        DIV -> env.getValue(operand1) / env.getValue(operand2)
    }

    private fun buildEnv(commands: List<String>): Pair<Map<String, Long>, Map<String, Expr>> {
        val vars = hashMapOf<String, Long>()
        val expr = hashMapOf<String, Expr>()

        commands.forEach { command ->
            val (name, op) = command.split(": ")
            op.toLongOrNull()
                ?.let { vars[name] = it }
                ?: run { expr[name] = op.toExpr() }
        }

        return vars to expr
    }

    private fun String.toExpr(): Expr {
        val (operand1, op, operand2) = split(" ")

        return Expr(operand1, operand2, Operation.of(op))
    }

    private data class Expr(val operand1: String, val operand2: String, val op: Operation) {
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
