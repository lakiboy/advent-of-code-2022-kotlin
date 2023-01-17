package io.dmitrijs.aoc2022

class Day21(input: List<String>) {
    private val env = buildEnv(input)

    fun puzzle1(): Long {
        val vars = env.first.toMutableMap()
        val expr = env.second.toMutableMap()

        while ("root" !in vars) {
            val resolvedVars = expr
                .filterValues { it.operand1 in vars && it.operand2 in vars }
                .mapValues { (_, op) -> op.eval(vars) }
                .also { vars.putAll(it) }

            // Cleanup expressions.
            resolvedVars.keys.forEach { name -> expr.remove(name) }
        }

        return vars.getValue("root")
    }

    private fun Expr.eval(env: Map<String, Long>) = when (op) {
        "+" -> env.getValue(operand1) + env.getValue(operand2)
        "-" -> env.getValue(operand1) - env.getValue(operand2)
        "*" -> env.getValue(operand1) * env.getValue(operand2)
        "/" -> env.getValue(operand1) / env.getValue(operand2)
        else -> error("Unsupported operation: $op.")
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

        return Expr(operand1, operand2, op)
    }

    private data class Expr(val operand1: String, val operand2: String, val op: String)
}
