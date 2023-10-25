package br.unb.cic.syntax;

import sootup.core.jimple.common.stmt.Stmt;
import sootup.core.model.Body;
import br.unb.cic.syntax.StmtSVFA.isSourceStmt

def getSourceStatements(body: Body): Set[Stmt] = {
    var sourceStmt: Set[Stmt] = Set()
    body.getStmts().forEach(stmt => {
        isSourceStmt(stmt) match
            case true => sourceStmt += stmt
            case _ =>
    })
    sourceStmt
}
