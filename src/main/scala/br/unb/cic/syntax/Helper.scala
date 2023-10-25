package br.unb.cic.syntax;

import sootup.core.jimple.common.stmt.Stmt
import sootup.core.model.Body
import br.unb.cic.syntax.StmtSVFA.isSourceStmt

import scala.collection.mutable

def getSourceAndSinkStatements(body: Body): mutable.HashMap[String, Set[Stmt]] = {

    val sourceStmt: mutable.HashMap[String, Set[Stmt]] = mutable.HashMap()

    sourceStmt("source") = Set()
    sourceStmt("sink") = Set()

    body.getStmts().forEach(stmt => {
        isSourceStmt(stmt) match
            case true => sourceStmt("source") += stmt
            case _ =>
    })
    sourceStmt
}

def getSourceStatements(body: Body): Set[Stmt] = {
    getSourceAndSinkStatements(body)("source")
}
