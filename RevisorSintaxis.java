/*
 * Gerardo Andres Moguel Rovelo
 */
package ProyectoCalculadora;

import Pila.PilaA;

public class RevisorSintaxis {
	private String operacion;

	public RevisorSintaxis(String operacion) {
		this.operacion = operacion;
	}

	// metodo para saber si es correcta la sintaxis de la operacion.
	public boolean isSyntaxOk() {
		boolean resp = true;
		boolean flag = false;
		PilaA<Character> x = new PilaA();
		int i = 0;
		while (i < operacion.length()) {
			switch (operacion.charAt(i)) {
			case '(':
				if (i > 0) {
					if (!(operacion.charAt(i - 1) == '+' || operacion.charAt(i - 1) == '-'
							|| operacion.charAt(i - 1) == '*' || operacion.charAt(i - 1) == '/'
							|| operacion.charAt(i - 1) == '^' || operacion.charAt(i - 1) == '(')) {
						resp = false;
						i = operacion.length();
					}
				}
				x.push('(');
				break;
			case ')':
				if (i < operacion.length() - 1) {
					if (!(operacion.charAt(i + 1) == '+' || operacion.charAt(i + 1) == '-'
							|| operacion.charAt(i + 1) == '*' || operacion.charAt(i + 1) == '/'
							|| operacion.charAt(i + 1) == '^' || operacion.charAt(i + 1) == ')')) {
						resp = false;
						i = operacion.length();
					}
				} else if (x.isEmpty()) {
					i = operacion.length();
					resp = false;
				} else if (operacion.charAt(i - 1) == '(') {
					resp = false;
					i = operacion.length();
				}
				if (x.pop() != '(') {
					i = operacion.length();
				}
				break;
			case '+', '-', '/', '*', '^':
				if (i > 0 && i < operacion.length() - 1) {
					if (!(((Character.isDigit(operacion.charAt(i - 1))) && (Character.isDigit(operacion.charAt(i + 1))))
							|| ((Character.isDigit(operacion.charAt(i - 1))) && (operacion.charAt(i + 1) == '('))
							|| ((Character.isDigit(operacion.charAt(i + 1))) && (operacion.charAt(i - 1) == ')'))
							|| ((operacion.charAt(i - 1) == ')') && (operacion.charAt(i + 1) == '(')))) {
						resp = false;
						i = operacion.length();
					}
				} else
					resp = false;
				break;
			case '.':
				int j = i + 1;
				if (i < operacion.length() && i > 0) {
					while (!(operacion.charAt(j) == '+' || operacion.charAt(j) == '-' || operacion.charAt(j) == '*'
							|| operacion.charAt(j) == '/' || operacion.charAt(j) == '^' || operacion.charAt(j) == ')'
							|| operacion.charAt(j) == '(')) {
						if (operacion.charAt(j) == '.') {
							flag = true;
							i = operacion.length();
						}
						j++;
					}
					i++;
				}

				break;
			default:
				if (!Character.isDigit(operacion.charAt(i)) && operacion.charAt(i) != '.') {
					resp = false;
					i = operacion.length();
				}
			}
			i++;
		}
		return x.isEmpty() && resp && !flag;
	}

	public static void main(String[] args) {
		RevisorSintaxis a = new RevisorSintaxis("8.35.4*9");// doble punto
		RevisorSintaxis b = new RevisorSintaxis("(3+1)(3)");// falta operador entre los parentesis
		RevisorSintaxis c = new RevisorSintaxis("((2+4)^7)/34"); // doble parentesis izq (correcto)
		RevisorSintaxis d = new RevisorSintaxis("x^2+3"); // no se aceptan letras
		RevisorSintaxis e = new RevisorSintaxis("(4+6)*(8-(34-6))");// doble parentesis a la derecha (correcto)
		RevisorSintaxis f = new RevisorSintaxis("(2+3)3+9");// no hay operador entre ultimo parentesis y numero
		RevisorSintaxis g = new RevisorSintaxis("hola");// String
		RevisorSintaxis h = new RevisorSintaxis("9+3=12");// simbolos que no sean +,-,*,/,^
		RevisorSintaxis i = new RevisorSintaxis("");// cadena vacia
		RevisorSintaxis j = new RevisorSintaxis("(12)-(23)");
		RevisorSintaxis k = new RevisorSintaxis("()()");// parentesis vacios

		System.out.println("Operacion a: " + a.isSyntaxOk());
		System.out.println("Operacion b: " + b.isSyntaxOk());
		System.out.println("Operacion c: " + c.isSyntaxOk());
		System.out.println("Operacion d: " + d.isSyntaxOk());
		System.out.println("Operacion e: " + e.isSyntaxOk());
		System.out.println("Operacion f: " + f.isSyntaxOk());
		System.out.println("Operacion g: " + g.isSyntaxOk());
		System.out.println("Operacion h: " + h.isSyntaxOk());
		System.out.println("Operacion i: " + i.isSyntaxOk());
		System.out.println("Operacion j: " + j.isSyntaxOk());
		System.out.println("Operacion k: " + k.isSyntaxOk());

	}

}
