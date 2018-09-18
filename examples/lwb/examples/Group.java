package lwb.examples;

import java.util.Arrays;
import java.util.List;
import kodkod.ast.*;
import kodkod.ast.operator.*;
import kodkod.instance.*;
import kodkod.engine.*;
import kodkod.engine.satlab.SATFactory;
import kodkod.engine.config.Options;

public final class Group {

public static void main(String[] args) throws Exception {

Relation x3 = Relation.unary("this/unit");
Relation x4 = Relation.unary("this/Element remainder");
Relation x5 = Relation.nary("this/Element.inv", 2);
Relation x6 = Relation.nary("this/Element.op", 3);

List<String> atomlist = Arrays.asList(
 "x0", "x1", "x2", "x3", "x4"
);

Universe universe = new Universe(atomlist);
TupleFactory factory = universe.factory();
Bounds bounds = new Bounds(universe);

TupleSet x3_upper = factory.setOf("x0");
bounds.boundExactly(x3, x3_upper);

TupleSet x4_upper = factory.setOf("x1", "x2");
bounds.boundExactly(x4, x4_upper);

// bounds for inv
TupleSet x5_upperX = factory.allOf(2);
bounds.bound(x5, x5_upperX);

// bounds for op
TupleSet x6_upperX = factory.allOf(3);
bounds.bound(x6, x6_upperX);

// all x: univ ??
Variable x10=Variable.unary("showGroup_this");
//Expression x11=x3.union(x4);
Expression x11=Expression.UNIV;
Decls x9=x10.oneOf(x11);
Expression x14=x10.join(x5);
Formula x13=x14.one();
Formula x15=x14.in(x11);
Formula x12=x13.and(x15);
Formula x8=x12.forAll(x9);

//inv.univ in univ (ist doch sowieso klar, oder?)
Expression x17=x5.join(Expression.UNIV);
Formula x16=x17.in(x11);

Variable x21=Variable.unary("showGroup_this");
Decls x20=x21.oneOf(x11);
Expression x25=x21.join(x6);
Expression x26=x11.product(x11);
Formula x24=x25.in(x26);
Variable x29=Variable.unary("v76");
Decls x28=x29.oneOf(x11);
Expression x32=x29.join(x25);
Formula x31=x32.one();
Formula x33=x32.in(x11);
Formula x30=x31.and(x33);
Formula x27=x30.forAll(x28);
Formula x23=x24.and(x27);
Variable x36=Variable.unary("v77");
Decls x35=x36.oneOf(x11);
Expression x38=x25.join(x36);
Formula x37=x38.in(x11);
Formula x34=x37.forAll(x35);
Formula x22=x23.and(x34);
Formula x19=x22.forAll(x20);

// (op.univ).univ in univ (ist doch sowieso klar?)
Expression x41=x6.join(Expression.UNIV);
Expression x40=x41.join(Expression.UNIV);
Formula x39=x40.in(x11);

// associativity
Variable x45=Variable.unary("showGroup_g1");
Decls x44=x45.oneOf(x11);
Variable x47=Variable.unary("showGroup_g2");
Decls x46=x47.oneOf(x11);
Variable x49=Variable.unary("showGroup_g3");
Decls x48=x49.oneOf(x11);
Decls x43=x44.and(x46).and(x48);
Expression x54=x45.join(x6);
Expression x53=x47.join(x54);
Expression x52=x53.join(x6);
Expression x51=x49.join(x52);
Expression x57=x47.join(x6);
Expression x56=x49.join(x57);
Expression x58=x45.join(x6);
Expression x55=x56.join(x58);
Formula x50=x51.eq(x55);
Formula x42=x50.forAll(x43);

// unit
Variable x61=Variable.unary("showGroup_g");
Decls x60=x61.oneOf(x11);
Expression x65=x61.join(x6);
Expression x64=x3.join(x65);
Formula x63=x61.eq(x64);
Expression x68=x3.join(x6);
Expression x67=x61.join(x68);
Formula x66=x61.eq(x67);
Formula x62=x63.and(x66);
Formula x59=x62.forAll(x60);

// inverse
Variable x71=Variable.unary("showGroup_g");
Decls x70=x71.oneOf(x11);
Expression x76=x71.join(x5);
Expression x75=x76.join(x6);
Expression x74=x71.join(x75);
Formula x73=x3.eq(x74);
Expression x79=x71.join(x5);
Expression x80=x71.join(x6);
Expression x78=x79.join(x80);
Formula x77=x3.eq(x78);
Formula x72=x73.and(x77);
Formula x69=x72.forAll(x70);

//IntExpression x82=x11.count();
//IntExpression x83=IntConstant.constant(5);
//Formula x81=x82.eq(x83);

//Formula x84=x0.eq(x0);
//Formula x85=x1.eq(x1);
//Formula x86=x2.eq(x2);
//Formula x87=x3.eq(x3);
//Formula x88=x4.eq(x4);
//Formula x89=x5.eq(x5);
//Formula x90=x6.eq(x6);

//Formula x7=Formula.compose(FormulaOperator.AND, x8, x16, x19, x39, x42, x59, x69, x81, x84, x85, x86, x87, x88, x89, x90);
Formula x7=Formula.compose(FormulaOperator.AND, x8 /*, x16 */, x19 /*, x39*/, x42, x59, x69/*, x81*//*, x87, x88, x89, x90*/);

Solver solver = new Solver();
solver.options().setSolver(SATFactory.DefaultSAT4J);
//solver.options().setBitwidth(1);
//solver.options().setFlatten(false);
solver.options().setIntEncoding(Options.IntEncoding.TWOSCOMPLEMENT);
solver.options().setSymmetryBreaking(20);
solver.options().setSkolemDepth(0);
System.out.println("Solving...");
System.out.flush();
Solution sol = solver.solve(x7,bounds);
System.out.println(sol.toString());
}}

