package br.unb.cic

trait Analysis {
  def interprocedural(): Boolean

  def intraprocedural(): Boolean = ! interprocedural()
}

trait Interprocedural extends Analysis {
  override def interprocedural(): Boolean = true
}

trait Intraprocedural extends Analysis {
  override def intraprocedural(): Boolean = false
}
